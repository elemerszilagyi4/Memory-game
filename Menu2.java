import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Menu2 extends JFrame {
    private Board board;
    private final JPanel playPanel;
    private final JPanel menu;
    private JMenuBar menuBar;
    private JMenuItem saveGame, exitMainMenu;
    public Menu2() {
        setTitle("Train your memory! (Menu)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton newGame = new JButton("New Game");
        newGame.setFont(new Font("Arial", Font.BOLD, 40));
        JButton load = new JButton("Load game");
        load.setFont(new Font("Arial", Font.BOLD, 40));
        this.setBounds(200,200,1000,800);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        playPanel = new JPanel(new BorderLayout());
        menu = new JPanel(new GridLayout(1, 2));
        menu.add(newGame);
        menu.add(load);
        menu.setVisible(true);
        add(menu, BorderLayout.CENTER);
        newGame.addActionListener(ev -> {
            setTitle("Train your memory! (Gameplay)");
            menuBar = new JMenuBar();
            saveGame = new JMenuItem("Save");
            menuBar.add(saveGame);
            setLayout(new BorderLayout());
            board = new Board();
            playPanel.add(board);
            playPanel.setVisible(true);
            remove(menu);
            menuBar = new JMenuBar();

            saveGame = new JMenuItem("Save");
            saveGame.addActionListener(ev1 -> saveState());

            exitMainMenu = new JMenuItem("To Main Menu");
            exitMainMenu.addActionListener(ev12 -> exitToMainMenu());

            menuBar.add(exitMainMenu);
            menuBar.add(saveGame);
            add(menuBar);
            setJMenuBar(menuBar);
            add(playPanel,BorderLayout.CENTER);
            setVisible(true);
        });
        load.addActionListener(ev -> {
            final JFileChooser Load = new JFileChooser();
            Load.setApproveButtonText("Load");
            Load.showOpenDialog(null);
            try {
                setTitle("Train your memory! (Gameplay)");
                String textLine;
                FileReader fr = new FileReader(Load.getSelectedFile());
                BufferedReader reader = new BufferedReader(fr);
                Card temp;
                int removedPairs;
                List<Card> cards = new ArrayList<>();
                textLine = reader.readLine();
                removedPairs = Integer.parseInt(textLine);
                Color color;
                boolean active;
                boolean removed;
                while((textLine = reader.readLine()) != null){
                    String[] output = textLine.split(" ");
                    color = new Color(Integer.decode(output[0]));
                    active = Boolean.parseBoolean(output[1]);
                    removed = Boolean.parseBoolean(output[2]);
                    temp = new Card(color, false, active, removed);
                    cards.add(temp);
                }
                board = new Board(cards, removedPairs);
                playPanel.add(board);
                playPanel.setVisible(true);
                setLayout(new BorderLayout());
                remove(menu);
                menuBar = new JMenuBar();

                saveGame = new JMenuItem("Save");
                saveGame.addActionListener(ev13 -> saveState());

                exitMainMenu = new JMenuItem("To Main Menu");
                exitMainMenu.addActionListener(ev14 -> exitToMainMenu());

                menuBar.add(exitMainMenu);
                menuBar.add(saveGame);
                add(menuBar);
                setJMenuBar(menuBar);
                add(playPanel,BorderLayout.CENTER);
                setVisible(true);
            }
            catch (IOException ignored) {
            } catch (UnsupportedAudioFileException e) {
                System.out.println("Wrong audio format");
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        });
        this.setVisible(true);
    }

    private void exitToMainMenu() {
        board.stopThread();
        setTitle("Train your memory! (Menu)");
        board = null;
        remove(playPanel);
        setJMenuBar(null);
        add(menu);
        setVisible(true);
    }

    private void saveState() {
        final JFileChooser SaveAs = new JFileChooser();
        SaveAs.setApproveButtonText("Save");
        SaveAs.showOpenDialog(null);
        File fileName = new File(SaveAs.getSelectedFile() + ".txt");
        try {
            BufferedWriter outFile = new BufferedWriter(new FileWriter(fileName));
            List<Card> cards = board.getCards();
            int removedCards = 0;
            for(Card i : cards) {
                if(i.isRemoved())
                    removedCards++;
            }
            int removedPairs;
            if(removedCards > 0)
                removedPairs = removedCards / 2;
            else
                removedPairs = 0;
            outFile.write(removedPairs + "\n");

            String buf;
            String hex;
            Card temp;
            for(int i = 0; i < 36; i++) {
                temp = cards.get(i);
                buf = Integer.toHexString(temp.getColor().getRGB());
                hex = "0x"+buf.substring(buf.length()-6).toUpperCase();

                outFile.write(hex + " " + temp.isActive() + " " + temp.isRemoved()+"\n");
            }
            outFile.close();
        } catch (IOException ignored) {
        }
    }
}
