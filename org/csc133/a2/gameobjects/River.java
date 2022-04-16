package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

public class River extends Fixed{


    private int getLargeDim() {
        return Math.max(dimension.getWidth(),dimension.getHeight());
    }
    private int getSmallDim() {
        return Math.min(dimension.getWidth(),dimension.getHeight());
    }

    private final int height;
    private final int width;

    public River(Dimension worldSize){
        this.worldSize = worldSize;
        this.color = ColorUtil.BLUE;
        this.location = new Point2D(-10, worldSize.getHeight()/4 );
        dimension = new Dimension(worldSize.getWidth(), worldSize.getHeight());
        height =  getSmallDim() / 5;
        width = getLargeDim();
    }

    @Override
    public void draw(Graphics g, Point containerOrigin) {
        int x = containerOrigin.getX() + (int)location.getX();
        int y = containerOrigin.getY() + (int)location.getY();
        g.setColor(color);
        g.drawRect(x,y,width,height);
    }

    public int getHeight() {return height;}
    public int getWidth() {return width;}

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
