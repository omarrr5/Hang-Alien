package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

import static javax.swing.JOptionPane.showMessageDialog;


public class Management extends JFrame {
    // linkedlist that holds the words
    static LinkedList<String> Words = new LinkedList<String>();
    static String[] paths = {"wordEasy.txt", "wordMedium.txt", "wordHard.txt"};
    static String pathSelected="";
    static String levelSelected = "";

    Management() throws IOException {

// management frame
        JFrame manage = new JFrame("Hang Alien");
        manage.setVisible(true);
        manage.setSize(1440, 810);
        manage.setDefaultCloseOperation(EXIT_ON_CLOSE);
        manage.setResizable(false);
        manage.setLayout(null);


        JLabel choose = new JLabel();
        choose.setIcon(new ImageIcon("src/button_choose.png"));
        choose.setVisible(true);
        choose.setLocation(450, 200);
        choose.setSize(519, 66);
        manage.add(choose);


        JLabel backgroundPlay;
        JButton medium = new JButton("medium");
        medium.setIcon(new ImageIcon("src/button_medium.png"));
        medium.setSize(130, 45);
        medium.setLocation(650, 600);
        manage.add(medium);
        medium.setVisible(true);


        JButton easy = new JButton("easy");
        easy.setIcon(new ImageIcon("src/button_easy.png"));
        easy.setSize(130, 45);
        easy.setLocation(150, 600);
        manage.add(easy);
        easy.setVisible(true);


        JButton hard = new JButton("hard");
        hard.setIcon(new ImageIcon("src/button_hard.png"));
        hard.setSize(130, 45);
        hard.setLocation(1100, 600);
        manage.add(hard);
        hard.setVisible(true);
        ImageIcon playIMg = new ImageIcon("src/playFrame.png");
        backgroundPlay = new JLabel(playIMg, JLabel.CENTER);
        backgroundPlay.setBounds(0, 0, 1440, 810);
        manage.add(backgroundPlay);


        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GridLayout grid = new GridLayout();
                grid.setColumns(1);
                grid.setRows(3);
                JFrame wordFrame = new JFrame("HANG ALIEN");
                wordFrame.setLayout(grid);
                JLabel enterWord = new JLabel("       ENTER 4 letter word   ");
                JTextField wordEntry = new JTextField();
                JButton ok = new JButton("OK");
                wordFrame.setVisible(true);
                wordFrame.setSize(300, 300);
                wordFrame.setLocation(600, 200);
                wordFrame.add(enterWord);
                wordFrame.add(wordEntry);
                wordFrame.add(ok);
                try {
                    fileReader(easy.getText());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        wordFrame.dispose();
                        try {
                            saveFile(wordEntry.getText());
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }

                    }
                });

            }
        });

        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GridLayout grid = new GridLayout();
                grid.setColumns(1);
                grid.setRows(3);
                JFrame wordFrame = new JFrame("HANG ALIEN");
                wordFrame.setLayout(grid);
                JLabel enterWord = new JLabel("       ENTER 8 letter word   ");
                JTextField wordEntry1 = new JTextField();

                JButton ok = new JButton("OK");
                wordFrame.setVisible(true);
                wordFrame.setSize(300, 300);
                wordFrame.setLocation(600, 200);
                wordFrame.add(enterWord);
                wordFrame.add(wordEntry1);
                wordFrame.add(ok);

                try {
                    fileReader(medium.getText());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        wordFrame.dispose();
                        try {
                            saveFile(wordEntry1.getText());
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }

                    }
                });
            }
        });

        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GridLayout grid = new GridLayout();
                grid.setColumns(1);
                grid.setRows(3);
                JFrame wordFrame = new JFrame("HANG ALIEN");
                wordFrame.setLayout(grid);
                JLabel enterWord = new JLabel("       ENTER 12 letter word   ");
                JTextField wordEntry2 = new JTextField();
                JButton ok = new JButton("OK");
                wordFrame.setVisible(true);
                wordFrame.setSize(300, 300);
                wordFrame.setLocation(600, 200);
                wordFrame.add(enterWord);
                wordFrame.add(wordEntry2);
                wordFrame.add(ok);
                try {
                    fileReader(hard.getText());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        wordFrame.dispose();
                        try {
                            saveFile(wordEntry2.getText());
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }

                    }
                });
            }
        });


    }

// file reader functions which fetches the data from txt file and save it to linkedlist
    public static void fileReader(String level) throws IOException {
        String text = "";
        levelSelected = level;

        if (level.equals("easy")) {
            pathSelected = paths[0];
        } else if (level.equals("medium")) {
            pathSelected = paths[1];
        } else {
            pathSelected = paths[2];
        }

        File file = new File(pathSelected);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            text = text.concat(scanner.nextLine());
        }
        String[] arrayText = text.split(" ");
        Collections.addAll(Words, arrayText);

    }

    // function which returns back the data from linedlist to txt file
    private void saveFile(String userInput) throws IOException {

        if (checkValidInput(userInput)){
            Words = Words.stream().distinct().collect(Collectors.toCollection(LinkedList::new));
            Words.add(userInput.toLowerCase());
            String newString = "";

            for (String word : Words) {
                newString += word + " ";
            }
            FileWriter fileWriter = new FileWriter(pathSelected);
            try {
                fileWriter.write(newString);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            try {
                fileWriter.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            Words.clear();
        }
    }


    // component shows warning message when user enters wrong input
    static Component wrongLetter(){
        showMessageDialog(null, "your inout is not matched with the letter length");

        return null;
    }

    static boolean checkValidInput(String userInput) {
        if (levelSelected.equals("easy") && userInput.length() !=4){
            wrongLetter();
            return false;
        }
        else if (levelSelected.equals("medium") && userInput.length()!=8){
            wrongLetter();
            return false;
        }

        else if (levelSelected.equals("hard") && userInput.length()!=12){
            wrongLetter();
            return false;
        }

        return true;
    }
}














