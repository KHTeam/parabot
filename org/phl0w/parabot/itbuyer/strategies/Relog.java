package org.phl0w.parabot.itbuyer.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.phl0w.parabot.itbuyer.iTBuyer;
import org.phl0w.parabot.itbuyer.utilities.Utilities;

public class Relog implements Strategy {
    public boolean activate() {
        return !Utilities.isLoggedIn();
    }

    public void execute() {
        iTBuyer.status = "Relogging";
        Keyboard.getInstance().sendKeys(iTBuyer.password);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return Utilities.isLoggedIn();
            }
        }, 6000);
    }
}
