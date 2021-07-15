package com.company;

import javax.sound.sampled.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;



class Main extends JFrame {
    public Main() {
        // main frame
        JFrame mainFrame = new JFrame("Hang Alien");
        mainFrame.setVisible(true);
        mainFrame.setSize(1440, 810);
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);


        JLabel background;
        ImageIcon img = new ImageIcon("src/HangAlien.png");
        background = new JLabel(img, JLabel.CENTER);
        mainFrame.add(background);
        background.setBounds(0, 0, 1440, 810);
        background.setVisible(true);



        JButton start = new JButton("");
        start.setIcon(new ImageIcon("src/startButton.png"));
        start.setSize(150, 55);
        start.setLocation(650, 420);
        mainFrame.add(start);
        start.setBorderPainted(false);
        start.setVisible(true);


        JButton exit = new JButton("");
        exit.setIcon(new ImageIcon("src/button_exit.png"));
        exit.setSize(140, 50);
        exit.setLocation(120, 600);
        mainFrame.add(exit);
        exit.setBorderPainted(false);
        exit.setVisible(true);


        JButton management = new JButton("");
        management.setIcon(new ImageIcon("src/button_management.png"));
        management.setSize(140, 48);
        management.setLocation(1200, 600);
        mainFrame.add(management);
        management.setBorderPainted(false);
        management.setVisible(true);


        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonFunction();
                mainFrame.dispose();

            }
        });


        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        management.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Management();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                mainFrame.dispose();

            }
        });



    }

// function that holds all jbuttons that control the game
    void buttonFunction() {
        JFrame playFrame = new JFrame("Hang Alien");
        playFrame.setVisible(true);
        playFrame.setSize(1440, 810);
        playFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        playFrame.setResizable(false);
        playFrame.setLayout(null);
        JLabel backgroundPlay;


        JButton medium = new JButton("");
        medium.setIcon(new ImageIcon("src/button_medium.png"));
        medium.setSize(130, 45);
        medium.setLocation(650, 600);
        playFrame.add(medium);
        medium.setVisible(true);

        JButton easy = new JButton("");
        easy.setIcon(new ImageIcon("src/button_easy.png"));
        easy.setSize(130, 45);
        easy.setLocation(150, 600);
        playFrame.add(easy);
        easy.setVisible(true);


        JButton hard = new JButton("");
        hard.setIcon(new ImageIcon("src/button_hard.png"));
        hard.setSize(130, 45);
        hard.setLocation(1100, 600);
        playFrame.add(hard);
        hard.setVisible(true);


        JButton returnBack = new JButton("");
        returnBack.setIcon(new ImageIcon("src/button_back.png"));
        returnBack.setSize(150, 55);
        returnBack.setLocation(650, 400);
        playFrame.add(returnBack);
        returnBack.setVisible(true);


        ImageIcon playIMg = new ImageIcon("src/HangAlien.png");
        backgroundPlay = new JLabel(playIMg, JLabel.CENTER);
        backgroundPlay.setBounds(0, 0, 1440, 810);
        playFrame.add(backgroundPlay);


        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Game.gameEasy();
                playFrame.dispose();


            }
        });

        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.gameMedium();
                playFrame.dispose();
            }
        });

        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.gameHard();
                playFrame.dispose();
            }
        });

        returnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main();
                playFrame.dispose();
            }
        });
    }

// main function which runs the game and run the wav sound
    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        new Main();
        File file = new File("src/sound.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();

    }


}
