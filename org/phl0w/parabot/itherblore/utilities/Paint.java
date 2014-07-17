package org.phl0w.parabot.itherblore.utilities;

import org.parabot.environment.input.Mouse;
import org.phl0w.parabot.itherblore.iTHerblore;
import org.rev317.min.api.methods.Skill;

import java.awt.*;

public class Paint {
    private static final Font title = new Font("Monotype Corsiva", Font.PLAIN, 25);
    private static final Font author = new Font("Monotype Corsiva", Font.PLAIN, 16);
    private static final Font info = new Font("Book Antiqua", Font.PLAIN, 15);

    private static final Shape bg = new Rectangle(10, 23, 170, 160);
    private static final Shape border = new Rectangle(8, 21, 170, 164);

    private static final Composite bgComposite = makeComposite(0.5F);
    private static final Composite borderComposite = makeComposite(1.0F);

    private static AlphaComposite makeComposite(final float alpha) {
        final int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    public static void onRepaint(Graphics g) {
        final int levelsGained = Utilities.getLevel(Skill.HERBLORE) - iTHerblore.startLevel;
        final int xpGained = Skill.HERBLORE.getExperience() - iTHerblore.startXp;
        final Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.CYAN);
        g2d.setComposite(bgComposite);
        g2d.fill(bg);
        g2d.setColor(Color.BLACK);
        g2d.fill(border);
        g2d.setComposite(borderComposite);
        g2d.setColor(Color.WHITE);
        g2d.fill(new Rectangle((int) Mouse.getInstance().getPoint().getX() + 1, (int) Mouse.getInstance().getPoint().getY() - 4, 2, 15));
        g2d.fill(new Rectangle((int) Mouse.getInstance().getPoint().getX() - 6, (int) Mouse.getInstance().getPoint().getY() + 2, 16, 2));
        g2d.setFont(title);
        g2d.drawString("iTHerblore", 12, 43);
        g2d.setFont(author);
        g2d.drawString("By: _phl0w", 12, 58);
        g2d.setFont(info);
        g2d.drawString("Runtime: " + Utilities.formatRuntime(iTHerblore.startTime), 12, 78);
        if (iTHerblore.fetchedStats) {
            g2d.drawString("Herblore: " + Utilities.getLevel(Skill.HERBLORE) + " (+" + levelsGained + ")", 12, 93);
            g2d.drawString("XP gained: " + xpGained, 12, 108);
            g2d.drawString("XP/h: " + Utilities.getPerHour(xpGained), 12, 123);
        }
        g2d.drawString("Potions made: " + iTHerblore.potionsMade, 12, 138);
        g2d.drawString("Potions/h: " + Utilities.getPerHour(iTHerblore.potionsMade), 12, 153);
        g2d.drawString("Status: " + iTHerblore.status, 12, 168);
    }
}
