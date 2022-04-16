package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

import java.util.Random;


public class UnStarted extends Fire{


    public UnStarted(int size) {
        this.size = size;
        this.dimension = new Dimension(size,size);
    }

    @Override
    public void draw(Graphics g, Point containerOrigin) {

    }

    @Override
    void start() {

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


    @Deprecated
    public UnStarted(Dimension worldSize, int size, int val) {

        Random r = new Random();
        this.worldSize = worldSize;
        this.size = size;
        color = ColorUtil.MAGENTA;

        //this Fire Objects are in the top Box
        //
        if (val ==0) {
            dimension = new Dimension(size,size);
            location = new Point2D(worldSize.getWidth()/2 -100 - r.nextInt(10),
                    worldSize.getHeight()/3 -300 + r.nextInt(10) );
        }
        else if (val==1){
            dimension = new Dimension(size,size);
            location = new Point2D(worldSize.getWidth()/2 + 100 + r.nextInt(10),
                    worldSize.getHeight()/3 - 350 + r.nextInt(10) );
        }

        //This fire Objects are in the left Box.
        //
        else if (val ==2) {
            dimension = new Dimension(size,size);
            location = new Point2D(450 - r.nextInt(10),
                    worldSize.getHeight() / 2 + 100 + r.nextInt(10));
        }
        else if (val == 3){
            dimension = new Dimension(size,size);
            location = new Point2D(400 + r.nextInt(10),
                    worldSize.getHeight()/2 + 300 + r.nextInt(10));
        }

        //This fire Objects are in the right Box
        //
        else if (val == 4){
            dimension = new Dimension(size,size);
            location = new Point2D(worldSize.getWidth() - 250 - r.nextInt(10),
                    worldSize.getHeight() - 400 - r.nextInt(10));

        }
        else if (val == 5) {
            dimension = new Dimension(size,size);
            location = new Point2D(worldSize.getWidth() - 350 -r.nextInt(10),
                    worldSize.getHeight()/2 + 150 - r.nextInt(10));
        }

        else if (val == 6) {
            dimension = new Dimension(size,size);
            location = new Point2D(worldSize.getWidth() - 400 -r.nextInt(10),
                    worldSize.getHeight()/2 + 250 - r.nextInt(10));
        }


        //if we have more them Seven fires others will be drawn here
        //
        else {
            dimension = new Dimension(size,size);
            location = new Point2D(worldSize.getWidth()/2 -200 - r.nextInt(200),
                    worldSize.getHeight()/3 -350 + r.nextInt(70));
        }
    }


}
