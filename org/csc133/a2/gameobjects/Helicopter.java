package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.GameWorld;
import org.csc133.a2.interfaces.Steerable;

import java.util.ArrayList;

public class Helicopter extends Movable implements Steerable {

    private int fuelConsummationRate = GameWorld.FUEL;
    private int water=0;
    private int eX;
    private int eY;
    private int speed=0;
    private int heading = 0;

    //I changed the size of helicopter to 25
    //
    public Helicopter(Dimension worldSize, Helipad helipad) {
        this.worldSize = worldSize;
        color = ColorUtil.CYAN;
        size = 25;

        location = new Point2D(helipad.getArcLotion().getX() +
                helipad.getArcSize() /2 -size/2,
                helipad.getArcLotion().getY() +
                        helipad.getArcSize() /2 - size/2);

    }

    @Override
    public void draw(Graphics g, Point containerOrigin) {
        g.setColor(color);
        int x= (int) (containerOrigin.getX() + location.getX());
        int y = (int) (containerOrigin.getY() + location.getY());

        g.fillArc(x,y,size,size,0,360);

        g.drawLine(x + size /2,  y + size /2,
                x + eX + size /2, y + eY  + size /2);

        g.setColor(ColorUtil.WHITE);

        g.drawString("F: " + fuelConsummationRate ,
                x - size  ,y + size);

        g.drawString("W: " + water ,x - size ,
                y + size  + size );

    }

    public void speedUp() {
        int MAX_SPEED = 10;
        if (speed < MAX_SPEED)
            speed++;
    }

    public void speedDown() {
        int MIN_SPEED = 0;
        if(speed > MIN_SPEED)
            speed--;
    }

    @Override
    public void move() {
        double deltaX = Math.cos(
                Math.toRadians(heading - 90)) * speed * 3;

        double deltaY = Math.sin(
                Math.toRadians(heading -90)) * speed * 3;

        int newX = (int) (location.getX() + deltaX);
        int newY = (int) (location.getY() + deltaY);
        location.setX(newX);
        location.setY(newY);
        fuelConsummationRate -= (speed * speed + 5);

        //THis is the code to for the Helicopter line after
        // hours of debugging at last it's complete.
        // The size of the line is the first argument in
        // two next for eX ann eY.
        //
        double angle = Math.toRadians(heading - 90);
        eX = (int) ((50)  *  Math.cos(angle));
        eY = (int) ((50)  *  Math.sin(angle));

    }

    @Override
    public void steerLeft() {

        if(heading >= 15)
            heading -=15;

        else {
            heading = heading - 15;
            heading = heading + 360;
        }
    }

    @Override
    public void steerRight() {

        if(heading < 345)
            heading +=15;

        else if(heading == 345)
            heading = 0;

        else {

            heading = heading - 360;
            heading = heading + 15;
        }

    }

    public void canDrinkFrom(River r) {

        if (location.getX() > r.getLocation().getX()  &&
                location.getX() < r.getLocation().getX() + r.getWidth() &&
                location.getY() > r.getLocation().getY() &&
                location.getY() < r.getLocation().getY() + r.getHeight()-size)
        {

            if(water < 1000 && speed < 3)
                water +=100;
        }

    }

    public boolean helicopterIsOnHelipad(Helipad helipad) {

        boolean helicopterIsOnHelipad = location.getX() > helipad.getArcLotion().getX() &&
                location.getX() < helipad.getArcLotion().getX() +
                        helipad.getArcSize() && location.getY() >
                helipad.getArcLotion().getY() && location.getY() <
                helipad.getArcLotion().getY() + helipad.getArcSize();

        return helicopterIsOnHelipad;
    }

    public int getFuel() {
        return fuelConsummationRate;
    }



    public void fight(ArrayList<GameObject> gameObjectCollection) {

        ArrayList<GameObject> firesDown = new ArrayList<>();
        ArrayList<Extinguished> extinguishList = new ArrayList<>();

        boolean aboveFire = false;
        int indexOfFire = -1;

        for (GameObject go: gameObjectCollection) {
            if (go instanceof Burning) {
                if (location.getX() > go.getLocation().getX() &&
                    location.getX() < go.getLocation().getX() +
                            go.dimension.getWidth()
                    && location.getY() > go.getLocation().getY() &&
                    location.getY() < go.getLocation().getY() +
                            go.dimension.getHeight() )
                {

                    aboveFire = true;
                    indexOfFire = gameObjectCollection.indexOf(go);
                    break;

                }
                else
                    aboveFire = false;
            }
        }


        if (aboveFire && indexOfFire != -1 && water > 99) {

            //this will change the fire width and height if the helicopter
            // is above the fire and water is more than 99
            // the water will be change to 0 after it's been dropped
            //
            gameObjectCollection.get(indexOfFire).dimension.setHeight(
                    gameObjectCollection.get(indexOfFire).dimension.getWidth()
                            - water /3);
            gameObjectCollection.get(indexOfFire).dimension.setHeight(
                    gameObjectCollection.get(indexOfFire).dimension.getHeight()
                            - water /3);
            water =0;

            //This if statement will remove the fire if the fire width
            // or height is less than 1
            if (gameObjectCollection.get(indexOfFire).dimension.getWidth() < 1
                    || gameObjectCollection.get(
                            indexOfFire).dimension.getHeight() < 1)
            {
                //Adding the extinguished fires in firesDown Array
                // for deleting
                firesDown.add(gameObjectCollection.get(indexOfFire));

                //Adding the Extinguished fires into extinguish list.
                //
                extinguishList.add(new Extinguished(
                        gameObjectCollection.get(indexOfFire) ));


                gameObjectCollection.get(indexOfFire).dimension.setWidth(0);
                gameObjectCollection.get(indexOfFire).dimension.setHeight(0);
            }

        }
        //this else statement will change the water to 0 if the helicopter
        // drop the water anywhere outside the fire area
        //
        else
            water=0;

        //remove the fire objects that been added to firesDown
        // list from fires list.
        //
        gameObjectCollection.removeAll(firesDown);

    }

    @Override
    public Point2D getLocation() {
        return location;
    }

    @Override
    public void setLocation(Point2D location) {

    }

    @Override
    public void setSize(int size) {

    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getHeading() {
        return heading;
    }


}
