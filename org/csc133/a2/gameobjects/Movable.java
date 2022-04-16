package org.csc133.a2.gameobjects;

abstract public class Movable extends GameObject{

    private int speed;
    private int heading;
    @Override
    public void setColor(int color) {

    }
    public abstract int getSpeed();
    public abstract int getHeading();
    public abstract void move();

}
