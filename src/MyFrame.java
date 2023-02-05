import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class MyFrame extends JFrame implements ActionListener {

    public static int width = 10;
    public static int height = 20;
    public static int sizeSquare = 20;
    public static int formationOfModel = 0;
    public static int numberOfFormation = (int)(Math.random()*7);
    public static int nextNumberOfFormation = (int)(Math.random()*7);
    public static Integer[][] currentFigure = FiguresModel.get(numberOfFormation, formationOfModel);
    public static Integer[][] nextFigure = FiguresModel.get(nextNumberOfFormation, formationOfModel);
    public static boolean[][] filledSquares = new boolean[width][height];
    public static int y = 0;
    public static int x = 0;
    public static int scoreNumber = 0;
    public static int scoreLine = 0;
    public static int numberOfFigureWithoutStick = 0;
    public static JPanel[][] squares;
    public static JPanel[][] squaresToNextFigureWindow;
    public static Labels score = new Labels(scoreNumber, 170);
    public static Labels line = new Labels(scoreLine, 220);
    public static Labels figureWithoutStick = new Labels(numberOfFigureWithoutStick, 270);
    public static Timer timer;

    MyFrame() {
        ImageIcon logo = new ImageIcon("bebrisLogo.png");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(330, 440);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Bebris");
        this.getContentPane().setBackground(new Color(80, 80, 80));
        this.setIconImage(logo.getImage());
        addKeyListener(new KeyBoard(this));
        this.setFocusable(true);
        this.setVisible(true);
        menu();
    }

    public void menu(){
        this.getContentPane().removeAll();
        repaint();
        JLabel bebris = new JLabel();
        bebris.setLayout(null);
        bebris.setText("Bebris");
        bebris.setVerticalTextPosition(JLabel.CENTER);
        bebris.setHorizontalTextPosition(JLabel.CENTER);
        bebris.setForeground(new Color(255, 255, 255));
        bebris.setFont(new Font("MV Boli", Font.BOLD, 45));
        bebris.setBackground(new Color(80, 80, 80));
        bebris.setVerticalAlignment(JLabel.CENTER);
        bebris.setHorizontalAlignment(JLabel.CENTER);
        bebris.setOpaque(true);
        bebris.setBounds(0, 0, 305, 100);
        this.add(bebris);
        Buttons start = new Buttons("Start", 120);
        ActionListener startGame = e -> game();
        start.addActionListener(startGame);
        Buttons results = new Buttons("Results", 170);
        ActionListener checkResults = e -> results();
        results.addActionListener(checkResults);
        this.add(start);
        this.add(results);
    }

    public void game(){
        this.getContentPane().removeAll();
        repaint();
        scoreNumber = 0;
        numberOfFigureWithoutStick = 0;
        scoreLine = 0;
        squares = new JPanel[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                squares[i][j] = new JPanel();
                squares[i][j].setBackground(new Color(255, 255, 255));
                squares[i][j].setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 1));
                squares[i][j].setBounds(i * sizeSquare + 3, j * sizeSquare, sizeSquare, sizeSquare);
                this.add(squares[i][j]);
            }
        }
        squaresToNextFigureWindow = new JPanel[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                squaresToNextFigureWindow[i][j] = new JPanel();
                squaresToNextFigureWindow[i][j].setBackground(new Color(80, 80, 80));
                squaresToNextFigureWindow[i][j].setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 1));
                squaresToNextFigureWindow[i][j].setBounds((15 + i) * 15, j * 15 + 50, sizeSquare - 5, sizeSquare - 5);
                this.add(squaresToNextFigureWindow[i][j]);
            }
        }
        this.add(new Labels("Next figure:", 20));
        this.add(new Labels("Score:", 147));
        score.changeLabels(0, 170);
        this.add(score);
        this.add(new Labels("Line:", 197));
        line.changeLabels(0, 220);
        this.add(line);
        this.add(new Labels("No stick:", 247));
        figureWithoutStick.changeLabels(0, 270);
        this.add(figureWithoutStick);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++)
                filledSquares[i][j] = false;
        }
        timer = new Timer(400, this);
        timer.start();
    }

    public void results(){
        String[] colNames = {"№", "Name", "Score"};
        String[][] results = new String[10][3];
        File result = new File("results.txt");
        try {
            Scanner read = new Scanner(result);
            for (int i = 0; i < 10; i++) {
                if (read.hasNextLine()) {
                    String[] stringFromResults = (read.nextLine().split(" - "));
                    results[i][0] = String.valueOf(i + 1);
                    results[i][1] = stringFromResults[0];
                    results[i][2] = stringFromResults[1];
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.getContentPane().removeAll();
        repaint();
        JLabel bebris = new JLabel();
        bebris.setLayout(null);
        bebris.setText("Results");
        bebris.setVerticalTextPosition(JLabel.CENTER);
        bebris.setHorizontalTextPosition(JLabel.CENTER);
        bebris.setForeground(new Color(255, 255, 255));
        bebris.setFont(new Font("MV Boli", Font.BOLD, 45));
        bebris.setBackground(new Color(80, 80, 80));
        bebris.setVerticalAlignment(JLabel.CENTER);
        bebris.setHorizontalAlignment(JLabel.CENTER);
        bebris.setOpaque(true);
        bebris.setBounds(0, 0, 305, 70);
        this.add(bebris);
        JTable resultsTable = new JTable(results, colNames);
        resultsTable.setRowHeight(30);
        resultsTable.setForeground(new Color(224, 3, 20));
        resultsTable.setFont(new Font("MV Boli", Font.PLAIN, 15));
        resultsTable.setBackground(new Color(80, 80, 80));
        resultsTable.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        resultsTable.setBounds(45, 70, 220, 300);
        this.add(resultsTable);
        JButton back = new JButton();
        back.setLayout(null);
        back.setText("<- Back <-");
        back.setVerticalTextPosition(JLabel.CENTER);
        back.setHorizontalTextPosition(JLabel.CENTER);
        back.setForeground(new Color(255, 255, 255));
        back.setFont(new Font("MV Boli", Font.BOLD, 14));
        back.setBackground(new Color(80, 80, 80));
        back.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        back.setVerticalAlignment(JLabel.CENTER);
        back.setHorizontalAlignment(JLabel.CENTER);
        back.setOpaque(true);
        back.setBounds(10, 375, 85, 24);
        ActionListener backToMenu = e -> menu();
        back.addActionListener(backToMenu);
        this.add(back);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int q = 0; q < 4; q++) {
                    if (currentFigure[q][0] + x == i && currentFigure[q][1] + y - 1 == j && !filledSquares[i][j])
                        squares[i][j].setBackground(new Color(255, 255, 255));
                }
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int q = 0; q < 4; q++) {
                    if (currentFigure[q][0] + x == i && currentFigure[q][1] + y == j)
                        squares[i][j].setBackground(new Color(255, 0, 0));
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                squaresToNextFigureWindow[i][j].setBackground(new Color(80, 80, 80));
                squaresToNextFigureWindow[i][j].setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 1));
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int q = 0; q < 4; q++) {
                    if (nextFigure[q][0] -3 == i && nextFigure[q][1] == j) {
                        squaresToNextFigureWindow[i][j].setBackground(new Color(255, 0, 0));
                        squaresToNextFigureWindow[i][j].setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 1));
                    }
                }
            }
        }
        if (currentFigure[0][1] + y == height - 1 || currentFigure[1][1] + y == height - 1 || currentFigure[2][1] + y == height - 1 || currentFigure[3][1] + y == height - 1 || filledSquares[currentFigure[0][0] + x][currentFigure[0][1] + y + 1] || filledSquares[currentFigure[1][0] + x][currentFigure[1][1] + y + 1] || filledSquares[currentFigure[2][0] + x][currentFigure[2][1] + y + 1] || filledSquares[currentFigure[3][0] + x][currentFigure[3][1] + y + 1]) {
            for (int i = 0; i < 4; i++)
                filledSquares[currentFigure[i][0] + x][currentFigure[i][1] + y] = true;
            timer.stop();
            scoreNumber += 10;
            score.changeLabels(scoreNumber, 170);
            checkLine();
            formationOfModel = 0;
            x = 0;
            y = 0;
            numberOfFormation = nextNumberOfFormation;
            currentFigure = FiguresModel.get(numberOfFormation, formationOfModel);
            nextNumberOfFormation = (int)(Math.random()*7);
            nextFigure = FiguresModel.get(nextNumberOfFormation, formationOfModel);
            if (numberOfFormation == 0) {
                numberOfFigureWithoutStick = 0;
            }
            else {
                numberOfFigureWithoutStick++;
            }
            figureWithoutStick.changeLabels(numberOfFigureWithoutStick, 270);
            boolean gameOver = false;
            for (int i = 0; i < 4; i++) {
                if (filledSquares[nextFigure[i][0] + x][nextFigure[i][1] + y]) {
                    gameOver = true;
                    break;
                }
            }
            if (!gameOver) {
                timer = new Timer(400, this);
                timer.start();
            }
            else {
                timer.stop();
                gameOver();
            }
        } else
            y++;
    }

    void checkLine() {
        int numberOfLine = 0;
        for (int i = 0; i < height; i++) {
            int check = 0;
            for (int j = 0; j < width; j++) {
                if(filledSquares[j][i])
                    check++;
            }
            if(check == width) {
                deleteLine(i);
                numberOfLine++;
                scoreLine++;
                line.changeLabels(scoreLine, 220);
            }
        }
        switch (numberOfLine){
            case 1:
                scoreNumber += 100;
            case 2:
                scoreNumber += 300;
            case 3:
                scoreNumber += 700;
            case 4:
                scoreNumber += 1500;
        }
        score.changeLabels(scoreNumber, 170);
    }

    void deleteLine(int x) {
        for (int i = x; i > 0; i--) {
            for (int j = 0; j < width; j++) {
                filledSquares[j][i] = filledSquares[j][i-1];
                if(filledSquares[j][i])
                    squares[j][i].setBackground(new Color(255, 0, 0));
                else
                    squares[j][i].setBackground(new Color(255, 255, 255));
            }
        }
    }

    void stopMovingDown(){
        timer.setDelay(400);
    }
    void changeMoving(int a)
    {
        try {
            switch (a) {
                case 37:
                    for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {
                            for (int q = 0; q < 4; q++) {
                                if (currentFigure[q][0] + x == i && currentFigure[q][1] + y == j) {
                                    if (!filledSquares[i][j - 1])
                                        squares[i][j - 1].setBackground(new Color(255, 255, 255));
                                    squares[i][j].setBackground(new Color(255, 255, 255));
                                }
                            }
                        }
                    }
                    x--;
                    if (currentFigure[0][0] + x < 0 || currentFigure[1][0] + x < 0 || currentFigure[2][0] + x < 0 || currentFigure[3][0] + x < 0 || filledSquares[currentFigure[0][0] + x][currentFigure[0][1] + y] || filledSquares[currentFigure[1][0] + x][currentFigure[1][1] + y] || filledSquares[currentFigure[2][0] + x][currentFigure[2][1] + y] || filledSquares[currentFigure[3][0] + x][currentFigure[3][1] + y])
                        x++;
                    for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {
                            for (int q = 0; q < 4; q++) {
                                if (currentFigure[q][0] + x == i && currentFigure[q][1] + y == j) {
                                    squares[i][j].setBackground(new Color(255, 0, 0));
                                }
                            }
                        }
                    }
                    break;
                case 39:
                    for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {
                            for (int q = 0; q < 4; q++) {
                                if (currentFigure[q][0] + x == i && currentFigure[q][1] + y == j) {
                                    if (!filledSquares[i][j - 1])
                                        squares[i][j - 1].setBackground(new Color(255, 255, 255));
                                    squares[i][j].setBackground(new Color(255, 255, 255));
                                }
                            }
                        }
                    }
                    x++;
                    if (currentFigure[0][0] + x == width || currentFigure[1][0] + x == width || currentFigure[2][0] + x == width || currentFigure[3][0] + x == width || filledSquares[currentFigure[0][0] + x][currentFigure[0][1] + y] || filledSquares[currentFigure[1][0] + x][currentFigure[1][1] + y] || filledSquares[currentFigure[2][0] + x][currentFigure[2][1] + y] || filledSquares[currentFigure[3][0] + x][currentFigure[3][1] + y])
                        x--;
                    for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {
                            for (int q = 0; q < 4; q++) {
                                if (currentFigure[q][0] + x == i && currentFigure[q][1] + y == j) {
                                    squares[i][j].setBackground(new Color(255, 0, 0));
                                }
                            }
                        }
                    }
                    break;
                case 32:
                    for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {
                            for (int q = 0; q < 4; q++) {
                                if (currentFigure[q][0] + x == i && (currentFigure[q][1] + y == j || currentFigure[q][1] + y - 1 == j))
                                    squares[i][j].setBackground(new Color(255, 255, 255));
                            }
                        }
                    }
                    formationOfModel++;
                    currentFigure = FiguresModel.get(numberOfFormation, formationOfModel % 4);
                    if (currentFigure[0][1] + y == height - 1 || currentFigure[1][1] + y == height - 1 || currentFigure[2][1] + y == height - 1 || currentFigure[3][1] + y == height - 1 || currentFigure[0][0] + x == width || currentFigure[1][0] + x == width || currentFigure[2][0] + x == width || currentFigure[3][0] + x == width || currentFigure[0][0] + x < 0 || currentFigure[1][0] + x < 0 || currentFigure[2][0] + x < 0 || currentFigure[3][0] + x < 0 || filledSquares[currentFigure[0][0] + x][currentFigure[0][1] + y] || filledSquares[currentFigure[1][0] + x][currentFigure[1][1] + y] || filledSquares[currentFigure[2][0] + x][currentFigure[2][1] + y] || filledSquares[currentFigure[3][0] + x][currentFigure[3][1] + y]) {
                        formationOfModel--;
                        currentFigure = FiguresModel.get(numberOfFormation, formationOfModel % 4);

                    }
                    for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {
                            for (int q = 0; q < 4; q++) {
                                if (currentFigure[q][0] + x == i && currentFigure[q][1] + y == j) {
                                    squares[i][j].setBackground(new Color(255, 0, 0));
                                }
                            }
                        }
                    }
                    break;
                case 40:
                    timer.setDelay(10);
                    break;
                case 27:
                    timer.stop();
                    gameOver();

            }
        } catch (ArrayIndexOutOfBoundsException ignored){}
    }

    void gameOver() {
        this.getContentPane().removeAll();
        repaint();
        JLabel gameOverText = new JLabel();
        gameOverText.setLayout(null);
        gameOverText.setText("Game over");
        gameOverText.setVerticalTextPosition(JLabel.CENTER);
        gameOverText.setHorizontalTextPosition(JLabel.CENTER);
        gameOverText.setForeground(new Color(224, 3, 20));
        gameOverText.setFont(new Font("MV Boli", Font.BOLD, 40));
        gameOverText.setBackground(new Color(80, 80, 80));
        gameOverText.setVerticalAlignment(JLabel.CENTER);
        gameOverText.setHorizontalAlignment(JLabel.CENTER);
        gameOverText.setOpaque(true);
        gameOverText.setBounds(0, 50, 315, 100);
        this.add(gameOverText);
        JLabel gameOverText2 = new JLabel();
        gameOverText2.setLayout(null);
        gameOverText2.setText("your score: " + scoreNumber);
        gameOverText2.setVerticalTextPosition(JLabel.CENTER);
        gameOverText2.setHorizontalTextPosition(JLabel.CENTER);
        gameOverText2.setForeground(new Color(224, 3, 20));
        gameOverText2.setFont(new Font("MV Boli", Font.BOLD, 22));
        gameOverText2.setBackground(new Color(80, 80, 80));
        gameOverText2.setVerticalAlignment(JLabel.CENTER);
        gameOverText2.setHorizontalAlignment(JLabel.CENTER);
        gameOverText2.setOpaque(true);
        gameOverText2.setBounds(0, 160, 315, 60);
        this.add(gameOverText2);
        JLabel gameOverText3 = new JLabel();
        gameOverText3.setLayout(null);
        gameOverText3.setText("Inter your name: ");
        gameOverText3.setVerticalTextPosition(JLabel.CENTER);
        gameOverText3.setHorizontalTextPosition(JLabel.CENTER);
        gameOverText3.setForeground(new Color(255, 255, 255));
        gameOverText3.setFont(new Font("MV Boli", Font.BOLD, 18));
        gameOverText3.setBackground(new Color(80, 80, 80));
        gameOverText3.setVerticalAlignment(JLabel.CENTER);
        gameOverText3.setHorizontalAlignment(JLabel.CENTER);
        gameOverText3.setOpaque(true);
        gameOverText3.setBounds(0, 250, 170, 20);
        this.add(gameOverText3);
        JTextField gameOverTextInput = new JTextField();
        gameOverTextInput.setLayout(null);
        gameOverTextInput.setForeground(new Color(0, 0, 0));
        gameOverTextInput.setFont(new Font("MV Boli", Font.BOLD, 18));
        gameOverTextInput.setBackground(new Color(255, 255, 255));
        gameOverTextInput.setHorizontalAlignment(JLabel.CENTER);
        gameOverTextInput.setOpaque(true);
        gameOverTextInput.setBounds(171, 250, 137, 20);
        this.add(gameOverTextInput);
        Buttons gameOver = new Buttons("Save", 280);
        ActionListener gameOverFunc = e -> {
            try {
                String[][] results = new String[10][2];
                File result = new File("results.txt");
                Scanner read = new Scanner(result);
                boolean check = false;
                for (int i = 0; i < 10; i++) {
                    if (read.hasNextLine()) {
                        String[] stringFromResults = (read.nextLine().split(" - "));
                        if (Integer.parseInt(stringFromResults[1]) < scoreNumber && !check) {
                            results[i][0] = gameOverTextInput.getText();
                            results[i][1] = String.valueOf(scoreNumber);
                            i++;
                            check = true;
                        }
                        results[i] = stringFromResults;
                    }
                }
                menu();
                FileWriter writer = new FileWriter(result);
                for (int i = 0; i < 10; i++) {
                    writer.write(results[i][0] + " - " + results[i][1] + "\n");
                }
                writer.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        };
        gameOver.addActionListener(gameOverFunc);
        this.add(gameOver);
    }
}
