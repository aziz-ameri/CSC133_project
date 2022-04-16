package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

import java.util.ArrayList;

public class Building extends Fixed{

    private double checkDamage;
    private double area;
    private double percentage;
    private double tempValForPer;
    private int buildingValue;
    private double buildingDamage;
    ArrayList<GameObject> myFires = new ArrayList<>();

    public Building(Dimension worldSize,int val) {
        tempValForPer =0;
        percentage=0;
        buildingDamage=0;
        area=0;
        color = ColorUtil.rgb(255,0,0);
        checkDamage =0;

        if (val == 1){
            dimension = new Dimension(300,450);
            location = new Point2D(dimension.getWidth(),
                    worldSize.getHeight()/2);
            buildingValue = 400;
        }
        if (val ==2) {
            dimension = new Dimension(300,450);
            location = new Point2D(worldSize.getWidth() -
                    worldSize.getHeight() /2, worldSize.getHeight()/2);

            buildingValue = 300;
        }
        if (val == 3){
            dimension = new Dimension(850,200);
            location = new Point2D(worldSize.getWidth()/2 -
                    dimension.getWidth()/2, 50 );
            buildingValue = 900;
        }
    }

    // TODO: 3/23/2022 Finish refactoring by tomorrow
    //
    @Override
    public void draw(Graphics g, Point containerOrigin) {
        g.setColor(color);
        int x = containerOrigin.getX() + (int)location.getX();
        int y = containerOrigin.getY() + (int)location.getY();

        g.drawRect(x,y,dimension.getWidth(),dimension.getHeight());


        g.drawString("D :" + (int) checkPercentage() + "%",
                x + dimension.getWidth() + 10, y + dimension.getHeight()-60);

        g.drawString("V :" + buildingValue , x +
                dimension.getWidth() + 10, y + dimension.getHeight() - 30);

    }


    @Override
    public Point2D getLocation() {
        return location;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getColor() {
        return color;
    }


    public void setFireInBuilding(Fire fire) {

        myFires.add(new Burning(fire,getLocation()));

    }

    public ArrayList<GameObject> getMyFires() {
        return myFires;
    }


    public void startTheFires(ArrayList<GameObject> obj) {
        for (GameObject go: obj) {
            if (go instanceof Fire)
                ((Fire) go).start();
        }
    }


    //This method is to check the fires in each building
    //


    // TODO: 3/19/2022 finish this part by tomorrow
    public double getTheDamage() {
        return percentage;
    }

    public double checkPercentage() {
        if (tempValForPer < percentage) {
            tempValForPer = percentage;
        }
        return tempValForPer;
    }

    public double getBuildingDamage() {
        return buildingDamage;
    }

    public void checkTheDamage(ArrayList<GameObject> obj) {
        checkDamage =0;
        area =0;
        percentage =0;
        for (GameObject go: obj) {
            if (go instanceof Burning) {
                if (go.getLocation().getX() > location.getX() &&
                go.getLocation().getX()  < location.getX()
                        + dimension.getWidth() &&
                go.getLocation().getY() > location.getY()
            ) {
                    checkDamage += go.dimension.getWidth();
                }
            }
            area = dimension.getWidth() * dimension.getHeight();

        }
        checkDamage = (checkDamage * 3.14);
        area = area/30;
        //Next line will calculate the percentage for Damage
        //
        percentage = (checkDamage/area) * 100;
        //Next line will calculate the financial loss of buildings
        //
        buildingDamage = percentage * buildingValue / 100;
    }

}
