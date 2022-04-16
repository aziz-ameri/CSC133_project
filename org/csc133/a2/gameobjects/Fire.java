package org.csc133.a2.gameobjects;


import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

public abstract class Fire extends Movable{


    abstract int getTotalSize();




    abstract void fireGrowthSize();

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
    public void move() {
        fireGrowthSize();
    }


    @Override
    public void draw(Graphics g, Point containerOrigin) {

    }
    abstract void start();
}
