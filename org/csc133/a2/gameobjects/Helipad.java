package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

public class Helipad extends Fixed{

    private final int recSize;
    private final int arcSize;
    private final Point2D arcLocation;

    public Helipad(Dimension worldSize) {
        recSize = 150;
        arcSize = 120;
        this.worldSize = worldSize;
        this.color = ColorUtil.GRAY;
        this.location = new Point2D(worldSize.getWidth()/2 - recSize/2,
                worldSize.getHeight() - recSize - recSize/2);

        arcLocation = new Point2D(worldSize.getWidth() /2 - recSize/2 + 15,
                worldSize.getHeight()-recSize-recSize/2 +15);

        //this dimension is not been used yet.
        //
        this.dimension = new Dimension(worldSize.getWidth(),
                worldSize.getHeight());
    }



    @Override
    public void draw(Graphics g, Point containerOrigin) {
        g.setColor(color);

        int x= containerOrigin.getX() + (int)location.getX();
        int y = containerOrigin.getY() + (int)location.getY() ;
        g.drawRect(x,y,recSize,recSize,5);

        int arcX = containerOrigin.getX() + (int)arcLocation.getX();
        int arcY = containerOrigin.getY() + (int)arcLocation.getY();
        g.drawArc(arcX,arcY,arcSize,arcSize,0,360);
    }

    public Point2D getArcLotion() {
        return arcLocation;
    }

    public int getArcSize() {
        return arcSize;
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
}
