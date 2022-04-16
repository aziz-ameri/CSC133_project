package org.csc133.a2.views;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import org.csc133.a2.GameWorld;
import org.csc133.a2.commands.*;

public class ControlCluster extends Container {
    private GameWorld gw;

    private final Button left = new Button("Left");
    private final Button right = new Button("Right");
    private final Button fight = new Button("Fight");
    private final Button exit = new Button("Exit");
    private final Button drink = new Button("Drink");
    private final Button bBreak = new Button("Break");
    private final Button accel= new Button("Accel");

    public ControlCluster(GameWorld gw) {
        this.gw = gw;

        setLayout(new GridLayout(1,7));


        add(left); add(right); add(fight);
        add(exit); add(drink); add(bBreak);
        add(accel);
        setStyle(left); setStyle(right);
        setStyle(fight); setStyle(exit);
        setStyle(drink); setStyle(bBreak);
        setStyle(accel);


        left.setCommand(new TurnLeft(gw));
        right.setCommand(new TurnRight(gw));
        fight.setCommand(new Fight(gw));
        exit.setCommand(new Exit(gw));
        drink.setCommand(new Drink(gw));
        bBreak.setCommand(new Brake(gw));
        accel.setCommand(new Accel(gw));
    }


    private void setStyle(Button b) {
        Style setting = b.getStyle();
        setting.setFgColor(ColorUtil.GREEN);
        setting.setBgColor(ColorUtil.WHITE);
        setting.setBorder(Border.createOutsetBorder(5,ColorUtil.WHITE),false);

    }

}
