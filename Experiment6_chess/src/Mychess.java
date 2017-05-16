import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;

public class Mychess extends JFrame{

    private JButton[][] buttons = new JButton[10][10];
    JButton buttonStartGame, buttonUndo, buttonAuthor, buttonExit;
    private int[][] vis = new int[10][10];
    private ImageIcon iconWhite, iconBlack;
    private int turn;
    private boolean is_over;
    private ArrayList<axis> operator = new ArrayList<axis>();

    private void initial() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                vis[i][j] = 0;
                buttons[i][j].setIcon(new ImageIcon());
            }
        }
        turn = 0;
        is_over = false;
        buttonStartGame.setText("Start again");
        operator.clear();
    }

    private boolean judgeWin() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if(j <= 5) {
                    int sum = 0;
                    for(int k = j; k < j + 5; k++) {
                        sum += vis[i][k];
                    }
                    if(Math.abs(sum) == 5) return true;
                }
                if(i <= 5) {
                    int sum = 0;
                    for(int k = i; k < i + 5; k++) {
                        sum += vis[k][j];
                    }
                    if(Math.abs(sum) == 5) return true;
                }
                if(i <= 5 && j <= 5) {
                    int sum = 0;
                    for(int k = 0; k < 5; k++) {
                        sum += vis[i + k][j + k];
                    }
                    if(Math.abs(sum) == 5) return true;
                }
                if(i <= 5 && j >= 4) {
                    int sum = 0;
                    for(int k = 0; k < 5; k++) {
                        sum += vis[i + k][j - k];
                    }
                    if(Math.abs(sum) == 5) return true;
                }
            }
        }
        return false;
    }

    public Mychess() {
        setTitle("Mychess");
        setLayout(null);
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                vis[i][j] = 0;
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.white);
                buttons[i][j].setOpaque(false);
                buttons[i][j].setBorder(BorderFactory.createBevelBorder(0, Color.WHITE, Color.BLACK));
                buttons[i][j].setIcon(new ImageIcon());
            }
        }
        setBounds(0, 0, 800, 640);
        turn = 0;
        is_over = false;

        URL url = Mychess.class.getResource("board_background.jpg");
        ImageIcon iconBackGround = new ImageIcon(url);
        Image temp = iconBackGround.getImage().getScaledInstance(getWidth(), getHeight(), iconBackGround.getImage().SCALE_DEFAULT);
        iconBackGround = new ImageIcon(temp);
        JLabel label = new JLabel(iconBackGround);
        label.setBounds(0, 0, 800, 640);
        getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));//background

        JPanel panel = (JPanel)this.getContentPane();
        panel.setOpaque(false);
        int x = 3, y = 3;
        for(int i = 0; i < 10; i++) {
            y = 3;
            for(int j = 0; j < 10; j++) {
                buttons[i][j].setBounds(x, y, 60, 60);
                //System.out.println((buttons[i][j].getX() - 3) / 60 + " " + (buttons[i][j].getY() - 3) / 60);
                this.add(buttons[i][j]);
                y += 60;
            }
            x += 60;
        }

        //Icon
        URL urlwhite = Mychess.class.getResource("WhiteChess.png");
        iconWhite = new ImageIcon(urlwhite);
        temp = iconWhite.getImage().getScaledInstance(43,
                59, iconWhite.getImage().SCALE_DEFAULT);
        iconWhite = new ImageIcon(temp);

        URL urlblack = Mychess.class.getResource("BlackChess.png");
        iconBlack = new ImageIcon(urlblack);
        temp = iconBlack.getImage().getScaledInstance(41,
                55, iconBlack.getImage().SCALE_DEFAULT);
        iconBlack = new ImageIcon(temp);

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                buttons[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(!is_over){
                            int locationx, locationy;
                            locationx = (e.getComponent().getX() - 3) / 60;
                            locationy = (e.getComponent().getY() - 3) / 60;
                            System.out.println(locationx + " " + locationy);
                            if(vis[locationx][locationy] != 0) {
                                System.out.println("What?");
                                JOptionPane.showMessageDialog(new Frame(), "Chess occupy");
                            }
                            else {
                                axis temp = new axis(locationx, locationy);
                                operator.add(temp);
                                if(turn % 2 == 0) {
                                    buttons[locationx][locationy].setIcon(iconBlack);
                                    vis[locationx][locationy] = 1;
                                }
                                else {
                                    buttons[locationx][locationy].setIcon(iconWhite);
                                    vis[locationx][locationy] = -1;
                                }
                                if(is_over = judgeWin()) {
                                    if(turn % 2 == 0) {
                                        JOptionPane.showMessageDialog(new JFrame(), "Black wins");
                                        System.out.println("Black wins");
                                    }
                                    else {
                                        JOptionPane.showMessageDialog(new JFrame(), "White wins");
                                        System.out.println("White wins");
                                    }
                                }
                                turn++;
                                if(turn == 100) {
                                    JOptionPane.showMessageDialog(new Frame(), "Nobody wins");
                                    is_over = true;
                                }
                            }
                        }

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        JButton temp = (JButton)e.getComponent();
                        //System.out.println("Mouse Enter");
                        temp.setBorder(BorderFactory.createBevelBorder(0, Color.white, Color.gray, Color.black, Color.white));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        JButton temp = (JButton)e.getComponent();
                        //System.out.println("Mouse exit");
                        temp.setBorder(BorderFactory.createBevelBorder(0, Color.WHITE, Color.BLACK));
                    }
                });
            }
        }

        setResizable(false);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        buttonStartGame = new JButton("Start Game");
        buttonStartGame.setBounds(610, 50, 180, 100);
        buttonStartGame.setFont(new Font("Arial", 1, 20));
        buttonStartGame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                initial();
                System.out.println("Press the startGameButton");
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonStartGame);

        buttonUndo = new JButton("Undo");
        buttonUndo.setBounds(610, 180, 180, 100);
        buttonUndo.setFont(new Font("Arial",1, 20));
        buttonUndo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(!operator.isEmpty() && is_over == false) {
                    int len = operator.size();
                    axis temp = operator.get(len - 1);
                    buttons[temp.locationx][temp.locationy].setIcon(new ImageIcon());
                    vis[temp.locationx][temp.locationy] = 0;
                    operator.remove(len - 1);
                    System.out.println("Successful undo");
                    turn--;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonUndo);

        buttonAuthor = new JButton("About the author");
        buttonAuthor.setBounds(610, 310, 180, 100);
        buttonAuthor.setFont(new Font("Arial",1, 18));
        buttonAuthor.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(new Frame(), "Author: 杨凌云");
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonAuthor);

        buttonExit = new JButton("Exit");
        buttonExit.setBounds(610, 440, 180,100);
        buttonExit.setFont(new Font("Arial", 1, 20));
        buttonExit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                setVisible(false);
                System.exit(0);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonExit);

    }

    public static void main(String[] args) {
        Mychess mychess = new Mychess();
        mychess.setVisible(true);

    }

}

class axis {
    public int locationx, locationy;
    public axis(int _x, int _y) {
        locationx = _x;
        locationy = _y;
    }
};