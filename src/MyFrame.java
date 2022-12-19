import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    int scoreNumber = 0;
    int scoreLine = 0;
    int numberOfFigureWithoutStick = 0;
    JPanel[][] squares;
    JPanel[][] squaresToNextFigureWindow;
    public JLabel score = new Labels(scoreNumber, 170);
    public JLabel line = new Labels(scoreLine, 220);
    public JLabel figureWithoutStick = new Labels(numberOfFigureWithoutStick, 270);
    Timer timer;

    MyFrame() {

        ImageIcon logo = new ImageIcon("bebrisLogo.png");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(330, 440);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Bebris");
        this.getContentPane().setBackground(new Color(80, 80, 80));
        this.setIconImage(logo.getImage());
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
        this.add(score);
        this.add(new Labels("Line:", 197));
        this.add(line);
        this.add(new Labels("No stick:", 247));
        this.add(figureWithoutStick);
        score.changeLabels(scoreNumber, 170);
        newSoreLine();
        newNumberOfFigureWithoutStick();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++)
                filledSquares[i][j] = false;
        }
        this.setVisible(true);
        addKeyListener(new KeyBoard(this));
        timer = new Timer(400, this);
        timer.start();
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
            newScore();
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
            //changeLabels(String.valueOf(numberOfFigureWithoutStick), 270);
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
            else
                this.setLayout(null);

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
                newSoreLine();
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
        newScore();
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

    void newScore() {
        score.setLayout(null);
        score.setText(String.valueOf(scoreNumber));
        score.setVerticalTextPosition(JLabel.CENTER);
        score.setHorizontalTextPosition(JLabel.CENTER);
        score.setForeground(new Color(224, 3, 20));
        score.setFont(new Font("MV Boli", Font.PLAIN, 15));
        score.setBackground(new Color(255, 255, 255));
        score.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0),2));
        score.setVerticalAlignment(JLabel.CENTER);
        score.setHorizontalAlignment(JLabel.CENTER);
        score.setOpaque(true);
        score.setBounds(210, 170, 100, 20);
        this.add(score);
    }

    void newSoreLine(){
        line.setLayout(null);
        line.setText(String.valueOf(scoreLine));
        line.setVerticalTextPosition(JLabel.CENTER);
        line.setHorizontalTextPosition(JLabel.CENTER);
        line.setForeground(new Color(224, 3, 20));
        line.setFont(new Font("MV Boli", Font.PLAIN, 15));
        line.setBackground(new Color(255, 255, 255));
        line.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0),2));
        line.setVerticalAlignment(JLabel.CENTER);
        line.setHorizontalAlignment(JLabel.CENTER);
        line.setOpaque(true);
        line.setBounds(210, 220, 100, 20);
        this.add(line);
    }

    void newNumberOfFigureWithoutStick(){
        figureWithoutStick.setLayout(null);
        figureWithoutStick.setText(String.valueOf(numberOfFigureWithoutStick));
        figureWithoutStick.setVerticalTextPosition(JLabel.CENTER);
        figureWithoutStick.setHorizontalTextPosition(JLabel.CENTER);
        figureWithoutStick.setForeground(new Color(224, 3, 20));
        figureWithoutStick.setFont(new Font("MV Boli", Font.PLAIN, 15));
        figureWithoutStick.setBackground(new Color(255, 255, 255));
        figureWithoutStick.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0),2));
        figureWithoutStick.setVerticalAlignment(JLabel.CENTER);
        figureWithoutStick.setHorizontalAlignment(JLabel.CENTER);
        figureWithoutStick.setOpaque(true);
        figureWithoutStick.setBounds(210, 270, 100, 20);
        this.add(figureWithoutStick);
    }

    void stopMovingDown(){
        timer.setDelay(400);
    }
    void changeMoving(int a)
    {
        switch (a){
            case 37:
                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        for (int q = 0; q < 4; q++) {
                            if (currentFigure[q][0] + x == i && currentFigure[q][1] + y == j) {
                                if(!filledSquares[i][j-1])
                                    squares[i][j-1].setBackground(new Color(255, 255, 255));
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
                                if(!filledSquares[i][j-1])
                                    squares[i][j-1].setBackground(new Color(255, 255, 255));
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
                if (currentFigure[0][1] + y == height - 1 || currentFigure[1][1] + y == height - 1 || currentFigure[2][1] + y == height - 1 || currentFigure[3][1] + y == height - 1 ||  currentFigure[0][0] + x == width || currentFigure[1][0] + x == width || currentFigure[2][0] + x == width || currentFigure[3][0] + x == width || currentFigure[0][0] + x < 0 || currentFigure[1][0] + x < 0 || currentFigure[2][0] + x < 0 || currentFigure[3][0] + x < 0 || filledSquares[currentFigure[0][0] + x][currentFigure[0][1] + y] || filledSquares[currentFigure[1][0] + x][currentFigure[1][1] + y] || filledSquares[currentFigure[2][0] + x][currentFigure[2][1] + y] || filledSquares[currentFigure[3][0] + x][currentFigure[3][1] + y]) {
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
        }
    }
}
