import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Board extends JPanel {
    private final List<Card> cards;
    private int pairs;
    private final Play play;
    private BufferedImage img;

    public Board(List<Card> cards, int pairs) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try {
            img = ImageIO.read(new File("src\\youwin.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
        setSize(new Dimension(1000,800));
        setLayout(new GridLayout(6, 6));
        this.pairs = pairs;
        this.cards = cards;
        Random random = new Random();
        int j;
        for(int i = 0; i < 36; i++) {
            j = random.nextInt(36);
            while(cards.get(j).isAdded())
                j = random.nextInt(36);
            add(cards.get(j));
            cards.get(j).setAdded();
        }
        play = new Play(this);
        Thread thread = new Thread(play);
        thread.start();
        setVisible(true);
    }

    public Board() {
        try {
            img = ImageIO.read(new File("src\\youwin.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
        pairs = 0;
        setSize(new Dimension(500, 500));
        setLayout(new GridLayout(6, 6));
        cards = new ArrayList<>();
        for(int i = 1; i <= 36 / 2; i++) {
            Card temp = new Card();
            cards.add(temp);
            Card temp2 = new Card();
            temp2.setColor(temp.getColor());
            cards.add(temp2);
        }
        Random random = new Random();
        int j;
        for(int i = 0; i < 36; i++) {
            j = random.nextInt(36);
            while(cards.get(j).isAdded())
                j = random.nextInt(36);
            add(cards.get(j));
            cards.get(j).setAdded();
        }

        play = new Play(this);
        Thread thread = new Thread(play);
        thread.start();
        setVisible(true);
    }

    public void stopThread() {
        play.setRuning();
        setVisible(false);
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0 ,0, null);
    }

    protected void youWin() {
        for(Card i : cards)
            remove(i);

        repaint();
        setVisible(true);
    }

    public int getPairs() {
        return pairs;
    }

    public void incrementPairs() {
        pairs++;
    }
}
