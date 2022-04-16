package org.csc133.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a2.GameWorld;

public class Brake extends Command {
    GameWorld gw;

    public Brake(GameWorld gw) {
        super("Break");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.downArrow();
    }
}
