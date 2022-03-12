import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Card extends JButton {
    private Color color;
    private boolean added, active;
    private final ImageIcon icon;
    private final ImageIcon good;
    boolean removed;


    private final int[] colors = {
            0xFFFFFF, 0x000000, 0xFFFF00, 0xE83000,
            0x1CE6FF, 0xFF34FF, 0x0000A6, 0x006FA6,
            0xFF913F, 0x7ED379, 0x008941, 0x00FECF,
            0xFF2F80, 0x1B4400, 0xA079BF, 0x575329,
            0x404E55, 0x66E1D3, 0xA4E804, 0x00A6AA,
            0xB4A8BD, 0x6B7900, 0xCB7E9B, 0xD790FF,
            0xA77500, 0xFFF69F, 0xBF5650, 0xC0B9B2,
            0x788D66, 0x012C58, 0xBEC459, 0x922329,
            0x372101, 0xFFB500, 0x7B4F4B, 0xC2FFED,
    };

    public Card(Color color, boolean added, boolean active, boolean removed) {
        this.color = color;
        this.added = added;
        this.active = active;
        this.removed = removed;
        icon = new ImageIcon("src\\logo.png");
        good = new ImageIcon("src\\good.png");
        if(removed)
            setIcon(good);
        else
            setIcon(icon);

        addActionListener(e -> cardChoosen());
        setVisible(true);
    }

    public Card() {
        added = false;
        removed = false;
        Random random = new Random();
        int i = random.nextInt(36);
        color = getOneColor(i);
        this.setSize(new Dimension(100,200));
        icon = new ImageIcon("src\\logo.png");
        good = new ImageIcon("src\\good.png");
        setIcon(icon);
        addActionListener(e -> cardChoosen());
        setVisible(true);
    }

    public void hideCard() {
        setIcon(icon);
        setBackground(null);
        setDisabledIcon(icon);
        setEnabled(true);
        active = false;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void removeCard() {
        setIcon(good);
        removed = true;
        setDisabledIcon(good);
        setEnabled(false);
        setBackground(null);
        setVisible(true);
        active = false;
    }

    public void blockCard() {

        setEnabled(false);
        setDisabledIcon(icon);
        setVisible(true);
    }

    public void enableCard() {
        setEnabled(true);
    }

    public boolean isActive() {
        return active;
    }

    public void cardChoosen() {
        setIcon(null);
        setBackground(color);
        setEnabled(false);
        active = true;
    }

    public Color getOneColor(int i) {
        return new Color(colors[i]);
    }

    public void setAdded() {
        this.added = true;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean isAdded() {
        return added;
    }
}
