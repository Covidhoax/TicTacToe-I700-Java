package ferhan.cse.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class TicTacToe extends JFrame {

    /*====================== Instance variables constants ======================= */
    private static final int ROW = 3;  // ROW by COLUMN cells
    private static final int COLUMN = 3;

    /*Variable using differemt dimensions and shall be used for GUI */
    private static final int CELL_SIZE = 125; // cell width and height
    private static final int CANVAS_WIDTH = CELL_SIZE * COLUMN;  // the drawing canvas
    private static final int CANVAS_HEIGHT = CELL_SIZE * ROW;
    private static final int GRID_WIDTH = 9;                   // Grid-line's width
    private static final int GRID_HALF_WIDTH = GRID_WIDTH / 2; // Grid-line's half-width

    /* Xs and Ys are displayed inside of the cell with lined border  */
    private static final int CELL_LINING = CELL_SIZE / 7;
    private static final int SPEN_SIZE = CELL_SIZE - CELL_LINING * 2; // spen (spen is special pen or paintbrush) size
    private static final int SPEN_WIDTH = 9; // spen stroke width

    /* Instance variables for gameplay */
    private Random random;
    private GameState gameState;  // the current game state
    private Player gamePlayer;  // the current player
    private Player[][] playBoard; // Game board of ROW-by-COLUMN cells


    /* Instance variables for drawing */
    private JLabel statusBar;  // the status bar where all the messages will be shown
    private PaintCanvas canvas;  //painting canvas with jpanel for the game board

    /* Constructor  */
    public TicTacToe() {

        /* Graphics components *?
        canvas = new PaintCanvas();  // Construct a drawing canvas (a JPanel)
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));


        /* Setup the status bar to display status message */
        statusBar = new JLabel("  ");
        statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
        statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));

        /* Container created for holding graphics component */
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(canvas, BorderLayout.CENTER);
        contentPane.add(statusBar, BorderLayout.PAGE_END);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();  // pack all the components in this JFrame
        final String VERSION = "0.1F";
        setTitle("Tic Tac Toe" + VERSION);
        setVisible(true);  // show this JFrame

        //game components are being defined

        random = new Random();
        playBoard = new Player[ROW][COLUMN]; // array allocated
        resetGame(); // initialize the  playboard contents and game variables
    }


    // Initialize the playboard contents and the status
    public void resetGame() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COLUMN; col++) {
                playBoard[row][col] = Player.EMPTY;  // all cells are empty
            }
        }
        gameState = GameState.PLAYING; // all ready to play
        gamePlayer = Player.O;  // O is the first player
    }

    /* checks whether the move is valid or not */

    public boolean validMove(int row, int col) {
        return row >= 0 && row < ROW && col >= 0 && col < COLUMN && playBoard[row][col] == Player.EMPTY;

    }

    /* Switches player */
    public void SwitchPlayer() { gamePlayer = (gamePlayer == Player.O) ? Player.X : Player.O;
    }

    /* True when drawn. When there are no more empty cells, it's a draw */
    public boolean hasDrawn() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COLUMN; col++) {
                if (playBoard[row][col] == Player.EMPTY) {
                    return false; // empty cell, so not drawn
                }
            }
        }
        return true;
    }




    /*============================  Inner class for GUI actions =========================== */
    /* this is paint mark for all the cells when they are not empty  */
    class PaintCanvas extends JPanel {
        @Override
        public void paintComponent(Graphics g) {  // invoke via repaint()
            super.paintComponent(g);    // fill background
            setBackground(Color.white); // set its background color

            // Draw the grid-lines
            g.setColor(Color.LIGHT_GRAY);
            for (int row = 1; row < ROW; ++row) {
                g.fillRoundRect(0, CELL_SIZE * row - GRID_HALF_WIDTH,
                        CANVAS_WIDTH - 1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
            }
            for (int col = 1; col < COLUMN; ++col) {
                g.fillRoundRect(CELL_SIZE * col - GRID_HALF_WIDTH, 0,
                        GRID_WIDTH, CANVAS_HEIGHT - 1, GRID_WIDTH, GRID_WIDTH);
            }

        }
    }




}

