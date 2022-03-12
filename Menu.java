//import javax.sound.sampled.LineUnavailableException;
//import javax.sound.sampled.UnsupportedAudioFileException;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//public class Menu extends JFrame {
//    private JMenuBar menuBar;
//    private JMenu menu;
//    private JMenuItem save,load;
//    private JPanel p1;
//    private Board board;
//    public Menu() {
//        menuBar = new JMenuBar();
//        menu = new JMenu("Menu");
//        menuBar.add(menu);
//        setLayout(new BorderLayout());
//        this.setBounds(200,200,1000,900);
//        add(menuBar);
//        setJMenuBar(menuBar);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setLayout(new BorderLayout());
//        p1 = new JPanel();
//        setJMenuBar(menuBar);
//        save = new JMenuItem("Save");
//        save.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent ev) {
//                final JFileChooser SaveAs = new JFileChooser();
//                SaveAs.setApproveButtonText("Save");
//                int actionDialog = SaveAs.showOpenDialog(null);
//                File fileName = new File(SaveAs.getSelectedFile() + ".txt");
//                try {
//                    BufferedWriter outFile = new BufferedWriter(new FileWriter(fileName));
//                    List<Card> cards = board.getCards();
//                    int removedCards = 0;
//                    for(Card i : cards) {
//                        if(i.isRemoved())
//                            removedCards++;
//                    }
//                    System.out.println(removedCards);
//                    int removedPairs;
//                    if(removedCards > 0)
//                        removedPairs = removedCards / 2;
//                    else
//                        removedPairs = 0;
//                    System.out.println(removedPairs);
//                    outFile.write(removedPairs + "\n");
//
//                    String buf;
//                    String hex;
//                    Card temp;
//                    for(int i = 0; i < 36; i++) {
//                        temp = cards.get(i);
//                        buf = Integer.toHexString(temp.getColor().getRGB());
//                        hex = "0x"+buf.substring(buf.length()-6).toUpperCase();
//
//                        outFile.write(hex + " " + temp.isAdded() + " " + temp.isActive() + " " + temp.isRemoved()+"\n");
//                    }
//                    outFile.close();
//                } catch (IOException ignored) {
//                }
//            }
//        });
//        menu.add(save);
//
//        load = new JMenuItem("Load");
//        load.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent ev) {
//                final JFileChooser Load = new JFileChooser();
//                Load.setApproveButtonText("Load");
//                int actionDialog = Load.showOpenDialog(null);
//                File fileName = new File(Load.getSelectedFile() + ".txt");
//                try {
//                    String textLine;
//                    FileReader fr = new FileReader(Load.getSelectedFile());
//                    BufferedReader reader = new BufferedReader(fr);
//                    Card temp;
//                    int removedPairs = 0;
//                    List<Card> cards = new ArrayList<>();
//                    textLine = reader.readLine();
//                    removedPairs = Integer.parseInt(textLine);
//                    Color color = null;
//                    boolean added, active;
//                    boolean removed;
//                    while((textLine = reader.readLine()) != null){
//                        System.out.println(textLine);
//                        String[] output = textLine.split(" ");
//                        color = new Color(Integer.decode(output[0]));
//                        active = Boolean.parseBoolean(output[2]);
//                        removed = Boolean.parseBoolean(output[3]);
//                        temp = new Card(color, false, active, removed);
//                        cards.add(temp);
//                    }
//                    board = new Board(cards, removedPairs, this);
//                    p1.add(board);
//                    p1.setVisible(true);
//                    add(p1,BorderLayout.CENTER);
//                    setVisible(true);
//                }
//                catch (IOException ignored) {
//                } catch (UnsupportedAudioFileException e) {
//                    System.out.println("Wrong audio format!");
//                } catch (LineUnavailableException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        menu.add(load);
//        setVisible(true);
//    }
//}
