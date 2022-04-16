package org.csc133.a2.views;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import org.csc133.a2.GameWorld;
import org.csc133.a2.gameobjects.GameObject;

public class MapView extends Container {
    private GameWorld gw;

    public MapView(GameWorld gw) {
        this.gw = gw;

    }

    @Override
    public void laidOut() {
        gw.setDimension(new Dimension(this.getWidth(),this.getHeight()));
        //This line of code will call init function in GameWorld class
        // after the view has been set and has a valid size.
        gw.init();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (GameObject go: gw.getGameObjectCollection())
            go.draw(g,new Point(this.getX(),this.getY()));
    }
}
