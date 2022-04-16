package org.csc133.a2.gameobjects;

import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.interfaces.Drawable;

abstract public class GameObject implements Drawable {

    private int color;
    private Point2D location;
    private int size;
    private Dimension dimension;
    private Dimension worldSize;

    public abstract Point2D getLocation();

    public abstract void setLocation(Point2D location);

    public abstract void setSize(int size);

    public abstract int getSize();

    public abstract void setColor(int color);

    public abstract int getColor();


}
