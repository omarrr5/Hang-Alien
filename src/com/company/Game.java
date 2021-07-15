package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;


class Game extends JFrame{


    File fw = new File("name.txt");
    static keyboard kb ;

    // easy mode function which lets player only enter 4 letters
    static void gameEasy() {

        JFrame playFrame_easy = new JFrame("Hang Alien");
        playFrame_easy.setVisible(true);
        playFrame_easy.setSize(1440, 810);
        playFrame_easy.setDefaultCloseOperation(EXIT_ON_CLOSE);
        playFrame_easy.setResizable(false);
        playFrame_easy.setLayout(null);

        JLabel backgroundPlay;
        ImageIcon playIMg = new ImageIcon("src/playFrame.png");
        backgroundPlay = new JLabel(playIMg, JLabel.CENTER);
        backgroundPlay.setBounds(0, 0, 1440, 810);

        playFrame_easy.add(backgroundPlay);
        kb = new keyboard("easy");
        backgroundPlay.add(kb.getGUI(1));
        backgroundPlay.add(kb.getGUI(2));
        backgroundPlay.add(kb.getImage(0));
        backgroundPlay.add(kb.getImage(1));
        createImageLabel(playFrame_easy);
        nameDialog();




    }



    // easy mode function which lets player only enter 8 letters
    static void gameMedium() {
        JFrame playFrame_medium = new JFrame("Hang Alien");
        playFrame_medium.setVisible(true);
        playFrame_medium.setSize(1440, 810);
        playFrame_medium.setDefaultCloseOperation(EXIT_ON_CLOSE);
        playFrame_medium.setResizable(false);
        playFrame_medium.setLayout(null);
        JLabel backgroundPlay;



        ImageIcon playIMg = new ImageIcon("src/playFrame.png");
        backgroundPlay = new JLabel(playIMg, JLabel.CENTER);
        backgroundPlay.setBounds(0, 0, 1440, 810);
        playFrame_medium.add(backgroundPlay);
        nameDialog();

        kb = new keyboard("medium");
        backgroundPlay.add(kb.getGUI(1));
        backgroundPlay.add(kb.getGUI(2));

    }

    // easy mode function which lets player only enter 12 letters
    static void gameHard() {
        JFrame playFrame_hard = new JFrame("Hang Alien");
        playFrame_hard.setVisible(true);
        playFrame_hard.setSize(1440, 810);
        playFrame_hard.setDefaultCloseOperation(EXIT_ON_CLOSE);
        playFrame_hard.setResizable(false);
        playFrame_hard.setLayout(null);



        JLabel backgroundPlay;
        ImageIcon playIMg = new ImageIcon("src/playFrame.png");
        backgroundPlay = new JLabel(playIMg, JLabel.CENTER);
        backgroundPlay.setBounds(0, 0, 1440, 810);
        playFrame_hard.add(backgroundPlay);
        nameDialog();

        kb = new keyboard("hard");
        backgroundPlay.add(kb.getGUI(1));
        backgroundPlay.add(kb.getGUI(2));
    }

// name dialog function which holds the name and save it to txt file
    static void nameDialog()  {

        GridLayout grid = new GridLayout();
        grid.setColumns(1);
        grid.setRows(3);
        JFrame nameFrame = new JFrame("HANG ALIEN");
        nameFrame.setLayout(grid);
        JLabel playerName = new JLabel("       ENTER YOUR NAME");
        JTextField name = new JTextField();

        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameFrame.dispose();

                try(FileWriter fw = new FileWriter("name.txt", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw))
                {
                    out.println(name.getText());


                } catch (IOException ee) {
                    System.out.println("error");

                }


            }
        });

        nameFrame.setVisible(true);
        nameFrame.setSize(300,300);
        nameFrame.setLocation(600,200);
        nameFrame.add(playerName);
        nameFrame.add(name);
        nameFrame.add(ok);


    }

    // create image label function which takes the photo and send it to the label
    public static void createImageLabel(JFrame level){
        JLabel alienImage = new JLabel();
        JLabel shipImage = new JLabel();
        alienImage.setSize(150,150);
        shipImage.setSize(150,150);
        alienImage.setBackground(Color.red);
        shipImage.setBackground(Color.red);
        alienImage.setLayout(null);
        shipImage.setLayout(null);
        alienImage.setLocation(1200,200);
        shipImage.setLocation(1200,200);
        level.add(alienImage);
        level.add(shipImage);
        alienImage.setVisible(true);
        shipImage.setVisible(true);




    }



}