package org.csc133.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a2.GameWorld;

public class TurnLeft extends Command {
    GameWorld gw;
    public TurnLeft(GameWorld gw) {
        super("Left");
        this.gw = gw;

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.leftArrow();
    }
}
