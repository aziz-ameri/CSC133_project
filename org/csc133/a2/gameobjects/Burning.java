package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CN;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

import java.util.Random;

public class Burning extends Fire{


    private final Random r;
    private boolean startFire;
    private final Point2D fireLocation;

    public Burning(GameObject fire, Point2D location) {
        r = new Random();
        color = ColorUtil.MAGENTA;
        this.dimension = fire.dimension;
        //this.location = fire.location;
        startFire = false;
        //Adding Random to fire locations
        //
        fireLocation = new Point2D(location.getX() +
                r.nextInt(250),location.getY() + r.nextInt(150));
    }


    @Override
    public void draw(Graphics g, Point containerOrigin) {
        if (startFire) {

            g.setColor(color);

            int x = containerOrigin.getX() + (int) fireLocation.getX();
            int y = containerOrigin.getY() + (int) fireLocation.getY();

            g.fillArc(x, y, dimension.getWidth(), dimension.getHeight(),
                    0, 360);

            g.setFont(Font.createSystemFont(CN.FACE_MONOSPACE,
                    CN.STYLE_PLAIN, CN.SIZE_MEDIUM));

            g.drawString("" + dimension.getWidth(), x,
                    y + dimension.getHeight());
        }
    }

    @Override
    public int getTotalSize(){
        return dimension.getWidth();
    }


    @Override
    public void fireGrowthSize(){
        dimension.setWidth(dimension.getWidth() + r.nextInt(3) );
        dimension.setHeight(dimension.getWidth() );
    }

    @Override
    public Point2D getLocation() {
        return fireLocation;
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
    public void move() {
        if (startFire) {
            fireGrowthSize();
        }
    }

    //This method start the fires in the Building and
    // Building object invoke this method
    @Override
    public void start() {
        startFire = true;
    }
}
