import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.Collections;
import java.util.Random;

import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class TicTacToe here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TicTacToe
{
    private JFrame frame;
    private JTextField lowerBox;
    private JLabel upperBox;
    private JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, 
    btn9,toBegin,startOver;
    private boolean controls;
    private ArrayList<JButton> selectors;
    private Random rand = new Random();
    private JPanel title_panel = new JPanel();
    private boolean launchGame = false;
    private boolean beginBtn = false;

    public TicTacToe(){
        selectors = new ArrayList<>();

        btn1 = new JButton();
        btn2 = new JButton(); 
        btn3 = new JButton();
        btn4 = new JButton();
        btn5 = new JButton();
        btn6 = new JButton();
        btn7 = new JButton();
        btn8 = new JButton();
        btn9 = new JButton();

        selectors.add(btn1);
        selectors.add(btn2);
        selectors.add(btn3);
        selectors.add(btn4);
        selectors.add(btn5);
        selectors.add(btn6);
        selectors.add(btn7);
        selectors.add(btn8);
        selectors.add(btn9);

        makeFrame();
    }

    private void reset () 
    {
        for(JButton x:selectors) {
            x.setText("");
            x.setBackground(new Color(255,255,255));
            //x.setOpaque(false);
        }
        controls = false;
        launchGame = true;
        upperBox.setText("Welcome to Tic-TacToe!, What is your name ?");
        toBegin.setBackground(new Color(51,204,255));
        lowerBox.setEnabled(true);

    }

    private void startOver(){
        if(beginBtn == true) {
            for(JButton x:selectors) {
                x.setText("");
                x.setBackground(new Color(255,255,255));
                frame.setVisible(true);
            }
            controls = false;
            launchGame = true;
            retrieve(lowerBox.getText());

        }
    }

    private void begin (JButton begin){
        if(launchGame == false && beginBtn == true){
            launchGame = true; 
            begin.setBackground(new Color(204,204,204));
        }

    }

    private void quit()
    {
        System.exit(0);
    }

    private void retrieve (String input) {
        if(input.equals("")){};
        upperBox.setText(input+" please place a nought the AI will place a cross after");
        lowerBox.setEnabled(false);
        beginBtn = true;
    }

    private void functions (JButton x) {
        x.setFont(new Font("SansSerif",Font.BOLD,60));
        if(launchGame == true){
            if(x.getText().equals("")) {
                if(controls){
                    x.setBackground(new Color(204,204,204));
                    x.setForeground(new Color(0,204 ,255));
                    x.setText("X");
                    controls = false;
                    showWinner();
                }else {
                    x.setForeground(new Color(0,0 ,0));
                    x.setText("O");
                    controls = true;
                    AI();
                    showWinner();
                    controls = false;
                } 
            }
        }
    }

    private boolean checkRows() {
        int i = 0;
        for(int c = 0;c<3;c++) {
            if(!selectors.get(i).equals("") && selectors.get(i).equals(selectors.get(i+1))
            && selectors.get(i).equals(selectors.get(i+2))) {
                return true;
            }
            i = i + 3;
        }
        return false;
    }

    private boolean checkColumns() {
        int i = 0;
        for(int c = 0;c<3;c++) {
            if(!selectors.get(i).equals("") && selectors.get(i).equals(selectors.get(i+3))
            && selectors.get(i).equals(selectors.get(i+6))) {
                return true;
            }
            i++;
        }

        return false;
    }

    private boolean checkDiags() {
        int i = 0;
        if(selectors.get(0).equals(selectors.get(4)) && selectors.get(0).equals(selectors.get(8))) {
            return true;
        }
        if(selectors.get(2).equals(selectors.get(4)) && selectors.get(2).equals(selectors.get(6))) {
            return true; 
        }
        return false;
    }

    private boolean checkForChampion() {
        if(checkRows() == true || checkColumns() == true || checkDiags() == true) {return true;}
        else {return false;}

    }

    private void showWinner () {
        if(checkForChampion() == true) {
            if(controls == true) {
                controls = false;
                upperBox.setText(lowerBox.getText() + "AI won");
            }
            else {
                controls = false;
                upperBox.setText(lowerBox.getText() + " won");
            }
        } 
    }

    private void AI () {
        ArrayList<JButton> position  = new ArrayList<>();

        for(JButton x: selectors ) {
            if(x.getText().equals("")){
                position.add(x);
            }

        }

        Collections.shuffle(position);

        if(launchGame == true) { 
            functions(position.get(0));

        }
    }

    private void makeMenuBar(JFrame frame){
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        JMenu MainMenu = new JMenu("Menu");
        menubar.add(MainMenu);

        JMenuItem ResetItem = new JMenuItem("Reset");
        ResetItem.addActionListener(ex -> reset());
        MainMenu.add(ResetItem);

        JMenuItem QuitItem = new JMenuItem("Quit");
        QuitItem.addActionListener(ex -> quit());
        MainMenu.add(QuitItem);

    }

    private void makeFrame(){
        frame = new JFrame ("Naomi's Tic-TacToe Game");
        Container contentPane = frame.getContentPane();

        makeMenuBar(frame);

        contentPane.setLayout(new BorderLayout(1,1));
        contentPane.setBackground(new Color(0,0,0));

        upperBox = new JLabel();
        contentPane.add(upperBox, BorderLayout.NORTH);
        upperBox.setHorizontalAlignment(JLabel.CENTER);
        upperBox.setBackground(new Color(51,204 ,255));
        upperBox.setFont(new Font("SansSerif",Font.BOLD,40));

        upperBox.setText("Welcome to Tic-TacToe!, What is your name ?");
        upperBox.setOpaque(true);

        lowerBox = new JTextField();
        lowerBox.addActionListener(ex -> retrieve(lowerBox.getText()));
        contentPane.add(lowerBox, BorderLayout.SOUTH);
        lowerBox.setHorizontalAlignment(JLabel.LEFT);
        lowerBox.setBackground(new Color(51,204,255));
        lowerBox.setFont(new Font("SansSerif",Font.BOLD,30));

        JPanel sideBar = new JPanel();
        sideBar.setLayout(new GridLayout(0, 1));

        toBegin = new JButton("BEGIN");
        toBegin.addActionListener(ex -> begin(toBegin));
        sideBar.add(toBegin);
        toBegin.setBackground(new Color(51,204 ,255));
        toBegin.setFont(new Font("SansSerif",Font.BOLD,20));

        startOver = new JButton("START OVER");
        startOver.addActionListener(ex -> startOver());
        sideBar.add(startOver);
        startOver.setBackground(new Color(51,204 ,255));
        startOver.setFont(new Font("SansSerif",Font.BOLD,20));

        JPanel collect = new JPanel();
        collect.add(sideBar);
        contentPane.add(collect, BorderLayout.EAST);

        JPanel game = new JPanel();
        game.setLayout(new GridLayout(3,3));
        contentPane.add(game, BorderLayout.CENTER);

        for(JButton x:selectors) {
            x.addActionListener(ex -> functions(x));
            x.setBackground(new Color(255,255,255));
            game.add(x);
        }

        frame.pack();
        frame.setSize(550, 550);
        frame.setVisible(true);
    }
}

