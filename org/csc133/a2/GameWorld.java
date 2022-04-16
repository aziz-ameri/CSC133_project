package org.csc133.a2;

import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.geom.Dimension;
import org.csc133.a2.gameobjects.*;
import java.util.ArrayList;
import java.util.Random;

public class GameWorld {

    public static final int FUEL = 25000;
    private Dimension worldSize;
    private Helipad helipad;
    private River river;
    private Helicopter helicopter;
    private int ticks;
    private int DamageToAllBuildings;
    private int lossOFAllBuildings;
    private ArrayList<GameObject> gameObjects;

    private static GameWorld theGameWorld;

    //this method will check if there is an instance of
    // gameWorld object has been initialized or not, Singleton pattern
    //
    public static synchronized GameWorld getGameWorld() {
        if (theGameWorld == null)
            theGameWorld = new GameWorld();

        return theGameWorld;
    }


    private GameWorld() {
        worldSize = new Dimension();
    }


    public void tick() {
        ticks++;

        helicopter.move();

        if (ticks %5 == 0) {
            for (GameObject go : gameObjects) {
                if (go instanceof Fire)
                    ((Fire) go).move();
            }
        }

        if(helicopter.helicopterIsOnHelipad(helipad) &&
                helicopter.getFuel() > 0 && helicopter.getSpeed() < 1
                && Integer.parseInt(getNumberOfFires()) < 1 ) {

            finish(1);
        }
        if ( helicopter.getFuel() < 1)
            finish(2);


        if (DamageToAllBuildings > 99)
            finish(3);



        for (GameObject go: gameObjects) {
            if (go instanceof Building) {
                ((Building) go).checkTheDamage(gameObjects);
            }
        }

    }

    public ArrayList<GameObject> getGameObjectCollection() {
        return gameObjects;
    }

    public void setDimension(Dimension worldSize) {
        this.worldSize = worldSize;
    }

    public void init() {

        int initialFire = 1000;
        DamageToAllBuildings =0;
        lossOFAllBuildings =0;
        helipad = new Helipad(worldSize);
        river = new River(worldSize);
        helicopter = new Helicopter(worldSize,helipad);
        gameObjects = new ArrayList<>();
        gameObjects.add(helipad);
        gameObjects.add(river);


        //I added a parameter to the Building Object to
        // draw each object in different location
        // I assign each object location in initialization
        //
        gameObjects.add(new Building(worldSize,1));
        gameObjects.add(new Building(worldSize,2));
        gameObjects.add(new Building(worldSize,3));

        //Array for holing the UnStarted created fires
        //
        ArrayList<GameObject> fires = new ArrayList<>();

        //creating fires without adding them into the screen
        // with size and width but not Growth while the initial Fire
        // is more than 100 we will create fires.
        //
        while (initialFire > 100 ) {
            Random r = new Random();
            int temp;
            int size = 11 + r.nextInt(5);
            fires.add(new UnStarted(size));
            //fires.add(new UnStarted(worldSize,size,numberOfFire));
            size = size/2;
            temp = (int)(size * size * 3.12);
            initialFire -= temp;
        }

        int FireBuket= fires.size();
        int buildingLooping=0;
        int keepTrack=2;

        for (GameObject go: gameObjects) {

            if (go instanceof Building) {
                while (buildingLooping < keepTrack ) {
                        //System.out.println(buildingLooping);
                        ((Building) go).setFireInBuilding((Fire)
                                fires.get(buildingLooping));
                        buildingLooping++;

                }
                //The Firs Time while Loop adds Two fires in first building
                // Next loop will add three fires in the second building
                //
                if (keepTrack == 2)
                    keepTrack =5;
                else keepTrack = FireBuket;
            }
        }
        ArrayList<GameObject> readyFires = new ArrayList<>();
        for (GameObject go:gameObjects) {
            if (go instanceof Building) {
                readyFires.addAll(((Building) go).getMyFires());

            }
        }


        //Adding the fires to screen
        //
        gameObjects.addAll(readyFires);


        for (GameObject go: gameObjects) {
            if (go instanceof Building) {
                ((Building) go).checkTheDamage(gameObjects);
            }
        }

        //This for loop invoke the start method in fire
        // class throw startTheFires method in the
        // Building class
        //
        for (GameObject go: gameObjects) {
            if (go instanceof Building)
                ((Building) go).startTheFires(gameObjects);
        }


        gameObjects.add(helicopter);

    }

    public String getHelicopterFuel() {
        return String.valueOf(helicopter.getFuel() );
    }

    public String getHelicopterHeading() {
        return String.valueOf(helicopter.getHeading() );
    }

    public String getHelicopterSpeed() {
        return String.valueOf(helicopter.getSpeed() );
    }

    public String getNumberOfFires() {
        int numberOfFires=0;
        for (GameObject go: gameObjects) {
            if (go instanceof Burning){
                numberOfFires++;
            }
        }
        return String.valueOf(numberOfFires);
    }

    public String getTotalFiresSize() {
        int fireSize=0;
        for (GameObject go: gameObjects) {
            if (go instanceof Burning){
                fireSize += ((Burning) go).getTotalSize();
            }
        }
        return String.valueOf(fireSize);
    }

    public String getTotalDamage() {
        int totalDamage=0;
        for (GameObject go: gameObjects) {
            if (go instanceof Building)
                totalDamage += ((Building) go).getTheDamage();
        }
        totalDamage = totalDamage /3;
        if (DamageToAllBuildings < totalDamage)
            DamageToAllBuildings = totalDamage;

        return String.valueOf(DamageToAllBuildings);
    }

    public String getLossOFAllBuildings() {
        int totalLoss=0;
        int temp=0;
        for (GameObject go: gameObjects) {
            if (go instanceof Building)
               totalLoss += ((Building) go).getBuildingDamage();
        }

        if (lossOFAllBuildings < totalLoss)
            lossOFAllBuildings = totalLoss;

        return String.valueOf(lossOFAllBuildings);
    }

    public void quit() {
        Display.getInstance().exitApplication();
    }

    public void drinkWater() {
        helicopter.canDrinkFrom(river);
    }

    public void upArrow() {
        helicopter.speedUp();
    }

    public void downArrow() {
        helicopter.speedDown();
    }

    public void leftArrow() {
        helicopter.steerLeft();
    }

    public void rightArrow() {
        helicopter.steerRight();
    }

    public void dumpingWater() {
        helicopter.fight(getGameObjectCollection());
    }

    private void finish(int result) {

        if(result == 1){

            if(Dialog.show("Do you want to restart","You won, your Score is :"
                    + (100 - DamageToAllBuildings) ,"Yes", "No") ) {
                // user clicked yes
                new Game();
            } else {
                // user clicked no
                quit();
            }
        }

        if(result ==2) {

            if(Dialog.show("Do you want to restart",
                    "You lost,ran of fuel",
                    "Yes", "No")) {
                // user clicked yes
                new Game();
            } else {
                // user clicked no
                quit();
            }
        }

        if (result == 3) {

            if(Dialog.show("Do you want to restart",
                    "You lost, Buildings are Burned",
                    "Yes", "No")) {
                // user clicked yes
                new Game();
            } else {
                // user clicked no
                quit();
            }

        }
    }
}