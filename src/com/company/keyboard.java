package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
///import static com.company.Game.onScreen;

class keyboard extends JFrame implements ActionListener {

    static String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "start"};
    static JButton[] letters = new JButton[27];
    private JPanel onScreen_Panel;
    static String secretWord = "";
    static String hideWord = "";
    static String updateSecret_word;
    private JPanel kb;
    private JLabel onScreen;
    static LinkedList<String> levels = new LinkedList<String>();
    static String[] paths = {"wordEasy.txt", "wordMedium.txt", "wordHard.txt"};
    static String pathSelected = "";
    static String levelSelected = "";
    int correctLetters = 0;
    int wrongLetters = 0;
    static JLabel alienImage = new JLabel();
    static JLabel shipImage = new JLabel();
    static JPanel correctImages = new JPanel();
    static JPanel wrongImage = new JPanel();

    public int getCorrectLetters() {
        return correctLetters;
    }

    public void setCorrectLetters() {
        this.correctLetters++;
    }

    public int getWrongLetters() {
        return wrongLetters;
    }

    public void setWrongLetters() {
        this.wrongLetters++;
    }

    public static void setLevel(String setSecretWord) {
        secretWord = getWord(setSecretWord);

    }

    public keyboard(String level) {
        createKeyboard(level);

        System.out.println(secretWord);
    }


    // create keyboard function which sets every letter to jbutton
    public void createKeyboard(String level) {
        setLevel(level);
        onScreen_Panel = new JPanel();
        onScreen = new JLabel();
        onScreen.setSize(250, 250);
        onScreen.setBackground(Color.green);
        onScreen.setFont(new Font("Serif", Font.BOLD, 100));
        kb = new JPanel(new GridLayout(3, 0));
        kb.setSize(650, 400);
        kb.setLocation(400, 400);
        kb.setBackground(Color.darkGray);
        onScreen_Panel.setSize(500, 120);
        onScreen_Panel.setLocation(500, 200);
        onScreen_Panel.setBackground(Color.gray);
        onScreen_Panel.setLayout(new FlowLayout());
        onScreen_Panel.add(onScreen);

        correctImages.setSize(150, 150);
        correctImages.setLocation(800, 30);
        correctImages.setBackground(Color.gray);
        correctImages.setLayout(new FlowLayout());
        correctImages.add(alienImage);

        wrongImage.setSize(150, 150);
        wrongImage.setLocation(20, 30);
        wrongImage.setBackground(Color.gray);
        wrongImage.setLayout(new FlowLayout());
        wrongImage.add(shipImage);

        alienImage.setSize(150, 150);
        shipImage.setSize(150, 150);
        alienImage.setBackground(Color.red);
        shipImage.setBackground(Color.red);
        alienImage.setLayout(null);
        shipImage.setLayout(null);
        alienImage.setLocation(1200, 200);
        shipImage.setLocation(1200, 200);
        alienImage.setVisible(true);
        shipImage.setVisible(true);

        JLabel alienLabel = new JLabel();
        ImageIcon alienBody = new ImageIcon("src/alienBody1.png");
        alienLabel = new JLabel(alienBody, JLabel.CENTER);
        alienLabel.setBounds(20, 30, 307, 476);


        for (int index = 0; index < secretWord.length(); index++) {
            hideWord = hideWord + "-";
        }

        for (int index = 0; index < alphabet.length; index++) {
            letters[index] = new JButton(alphabet[index]);
            letters[index].setFocusable(false);
            letters[index].setSize(200, 200);
            letters[index].setBackground(Color.gray);
            letters[index].addActionListener(this);
            kb.add(letters[index]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (JButton letter : letters) {
            if (e.getSource() == letter) {
                String text = ((JButton) e.getSource()).getText().toLowerCase();
                ((JButton) e.getSource()).setEnabled(false);
                ((JButton) e.getSource()).setBackground(Color.darkGray);
                doAction(text);
            }
        }
    }

    // do action which swaps the plain blank with the right letter
    private void doAction(String text) {
        updateSecret_word = "";

        int temp = 0;

        for (int index = 0; index < secretWord.length(); index++) {
            if (secretWord.charAt(index) == (text.charAt(0))) {
                updateSecret_word = updateSecret_word + secretWord.charAt(index);
                setCorrectLetters();
                temp++;
            } else {
                updateSecret_word = updateSecret_word + "-";
            }
        }
        if (temp == 0) {
            wrongLetters++;
        }
        System.out.println(correctLetters);
        System.out.println(wrongLetters);
        correctTtyImage(correctLetters);
        wrongTtyImage(wrongLetters);
        if(wrongLetters>6){
            JOptionPane.showMessageDialog(null, "Try again, you lost ");
            System.exit(3);
        }

        char[] hideWordArray = hideWord.toCharArray();
        char[] updateSecret_wordArray = updateSecret_word.toCharArray();
        for (int i = 0; i < hideWordArray.length; i++) {

            if (hideWordArray[i] != updateSecret_wordArray[i] && hideWordArray[i] == '-') {
                hideWordArray[i] = updateSecret_wordArray[i];
            }
        }
        hideWord = new String(hideWordArray);
        onScreen.setText(hideWord);
    }

    public JComponent getGUI(int i) {
        if (i == 1)
            return kb;
        else
            return onScreen_Panel;
    }

    public JComponent getImage(int i) {
        if (i == 0){

            return correctImages;
        }
        else {
            return    wrongImage;
        }
    }

    private static String getWord(String levelSelectedByUser) {
        String text1 = "";
        levelSelected = levelSelectedByUser;

        if (levelSelectedByUser.equals("easy")) {
            pathSelected = paths[0];
        } else if (levelSelectedByUser.equals("medium")) {
            pathSelected = paths[1];
        } else {
            pathSelected = paths[2];
        }

        File file = new File(pathSelected);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scanner.hasNextLine()) {
            text1 = text1.concat(scanner.nextLine());
        }
        String[] arrayText = text1.split(" ");
        Collections.addAll(levels, arrayText);

        return levels.get(random());
    }

    // functions which randomize the word for the user
    private static int random() {
        // define the range
        int max = levels.get(0).length();
        int min = 0;
        int range = max - min + 1;
        int rand;
        rand = (int) (Math.random() * range) + min;
        return rand;

    }

// function shows the correct image only
    private void correctTtyImage(int trying) {
        trying++;
        String path = "src/";
        String exs = ".jpg";

        ImageIcon image = new ImageIcon(path + trying + exs);
        Image img = image.getImage();
        Image imgFit = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon fit = new ImageIcon(imgFit);
        alienImage.setIcon(fit);

    }

    // function shows the wrong image only
    private void wrongTtyImage(int trying) {
        trying++;
        String path = "src/b";
        String exs = ".jpg";

        ImageIcon image = new ImageIcon(path + trying + exs);
        Image img = image.getImage();
        Image imgFit = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon fit = new ImageIcon(imgFit);
        shipImage.setIcon(fit);

    }
}