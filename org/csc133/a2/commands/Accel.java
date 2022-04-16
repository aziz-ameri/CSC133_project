package org.csc133.a2.commands;


import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a2.GameWorld;

public class Accel extends Command {
    GameWorld gw;

    public Accel(GameWorld gw) {
        super("Accel");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.upArrow();
    }
}
