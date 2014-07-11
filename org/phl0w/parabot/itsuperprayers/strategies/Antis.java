package org.phl0w.parabot.itsuperprayers.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itsuperprayers.iTSuperPrayers;
import org.phl0w.parabot.itsuperprayers.utilities.Area;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;

import java.awt.*;


public class Antis implements Strategy {
    final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
    private Area bobsIsland = new Area(new Tile(2511, 4765),
            new Tile(2511, 4790),
            new Tile(2542, 4790),
            new Tile(2542, 4765));
    private Npc antiRandom;
    private int[] randoms = {410, 1091, 3117, 3022, 3351, 409};
    private int rCount;

    public boolean activate() {
        for (Npc n : Npcs.getNearest(randoms)) {
            if (n.getLocation().distanceTo() < 3) {
                antiRandom = n;

                return true;
            }
        }
        return false;
    }

    public void execute() {
        iTSuperPrayers.status = "Antirandom";
        // Makes the noise
        if (runnable != null)
            runnable.run();

        Time.sleep(750);

        System.out.println("There is a random nearby!");

        Time.sleep(750);

        if (antiRandom.getDef().getId() == 1091) {
            SceneObject[] portal = SceneObjects.getNearest(8987);

            for (SceneObject aPortal : portal) {
                if (bobsIsland.contains(Players.getMyPlayer().getLocation())) {
                    final SceneObject portal2 = aPortal;

                    aPortal.interact(0);

                    Time.sleep(new SleepCondition() {
                        @Override
                        public boolean isValid() {
                            return portal2.getLocation().distanceTo() < 2;
                        }

                    }, 7500);

                    Time.sleep(1000);
                }
            }

            System.out.println("Bob's Island has been completed");
        } else if (antiRandom.getDef().getId() == 3022 || antiRandom.getDef().getId() == 3351 || antiRandom.getDef().getId() == 409) {
            System.exit(0);

            System.out.println("A mod called a Genie random onto you.\n" +
                    "The client was closed to protect your account.");
        } else {
            antiRandom.interact(0);
            Time.sleep(1500);

            System.out.println("Sandwich lady/Old man random has been completed");
        }

        rCount++;
    }
}
