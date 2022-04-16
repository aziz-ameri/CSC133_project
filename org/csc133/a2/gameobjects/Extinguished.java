package org.csc133.a2.gameobjects;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Extinguished extends Fire{


    public Extinguished(GameObject fires) {
        fires.dimension.setHeight(0);
        fires.dimension.setWidth(0);
    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public int getHeading() {
        return 0;
    }

    @Override
    int getTotalSize() {
        return 0;
    }

    @Override
    public void fireGrowthSize() {

    }

    @Override
    public void move() {
        
    }

    @Override
    public void draw(Graphics g, Point containerOrigin) {

    }

    @Override
    void start() {

    }
}
