package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.UITimer;
import org.csc133.a2.views.ControlCluster;
import org.csc133.a2.views.GlassCockpit;
import org.csc133.a2.views.MapView;


/* Assignment A2b :: Hornets HFD, Abdulaziz Ameri 3/31/2020

Expected Grade Comment: Exceeds Expectations
I finished all requirements, all the fields are privets and
those that should not be changed are final, all the lines
are less than 80 columns, there is no run time error,
and I normally added and commit to git with tiles of
what been done for each commit.
 * */



public class Game extends Form implements Runnable {

    private final GameWorld gw;
    private final MapView mapView;
    private final GlassCockpit glassCockpit;
    private final ControlCluster controlCluster;



    public Game() {

        setLayout(new BorderLayout());

        // the Game class calls the getGameWorld method
        // to initialize the gw using Singleton pattern
        //
        gw = GameWorld.getGameWorld();
        mapView = new MapView(gw);
        glassCockpit = new GlassCockpit(gw);
        controlCluster = new ControlCluster(gw);

        addKeyListener('w',(evt) -> gw.quit());
        addKeyListener(-91,(evt) -> gw.upArrow());
        addKeyListener(-92,(evt) -> gw.downArrow());
        addKeyListener(-93,(evt) -> gw.leftArrow());
        addKeyListener(-94,(evt) -> gw.rightArrow());
        addKeyListener(102,(evt) -> gw.dumpingWater());
        addKeyListener(100,(evt) -> gw.drinkWater());

        this.getAllStyles().setBgColor(ColorUtil.BLACK);

        //setTitle("Abdulaziz Ameri");

        this.add(BorderLayout.CENTER,mapView);
        this.add(BorderLayout.NORTH,glassCockpit);
        this.add(BorderLayout.SOUTH,controlCluster);

        UITimer timer = new UITimer(this);
        timer.schedule(100,true,this);


        this.show();


    }
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public void run() {
        gw.tick();
        glassCockpit.update();
        repaint();

    }
}
