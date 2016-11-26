package ferhan.cse.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class TicTacToe extends JFrame {

    /**
     * Instance variables constants
     */

    private static final int ROW = 3;  // ROW by COLUMN cells
    private static final int COLUMN = 3;
    public static int Ex_WonCount = 0;
    public static int Naught_wonCount = 0;
    public static int DrawnCount = 0;
    /**
     * Variable using different dimensions and shall be used for GUI
     */

    private static final int CELL_SIZE = 125; // cell width and height
    private static final int CANVAS_WIDTH = CELL_SIZE * COLUMN;  // the drawing canvas
    private static final int CANVAS_HEIGHT = CELL_SIZE * ROW;
    private static final int GRID_WIDTH = 9;                   // Grid-line's width
    private static final int GRID_HALF_WIDTH = GRID_WIDTH / 2; // Grid-line's half-width

    /**
     * Xs and Ys are displayed inside of the cell with lined border
     */
    private static final int CELL_LINING = CELL_SIZE / 7;
    private static final int SPEN_SIZE = CELL_SIZE - CELL_LINING * 2; // width/height
    private static final int SPEN_WIDTH = 9; // spen stroke width


    /**
     * Instance variables for gameplay
     */
    public GameState gameState;  // the current game state
    public Player gamePlayer;  // the current player
    public Player[][] playBoard; // Game playBoard of ROW-by-COLUMN cells
    public Random random;


    /**
     * Instance variables for drawing
     */
    public JLabel statusBar;  // the status bar where all the messages will be shown
    public PaintCanvas canvas;  //painting canvas with jpanel for the game board

    /**
     * Constructor
     */
    public TicTacToe() {

        /* Graphics components */
        canvas = new PaintCanvas();  // Construct a drawing canvas (a JPanel)
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));


        /**
         * The canvas (JPanel) creates a MouseEvent when mouse button is clicked
         */
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {  // mouse click handler

                // X and y coordinates of the clicked pixel
                int mouseX = mouseEvent.getX();
                int mouseY = mouseEvent.getY();

                // Convert x and y coordinates to a new row and column index.
                int rowSelected = mouseY / CELL_SIZE;
                int colSelected = mouseX / CELL_SIZE;

                // Make the selected move and update the state of the game.
                MoveOrRestart(rowSelected, colSelected);
                /**
                 *  Refreshes painting canvas by posting the repaint event
                 */
                 repaint();
            }
        });


        /* Setup the status bar to display status message */
        statusBar = new JLabel("  ");
        statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 14));
        statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));

        /* Container created for holding graphics component */
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(canvas, BorderLayout.CENTER);
        contentPane.add(statusBar, BorderLayout.PAGE_END);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();  // pack all the components in this JFrame
        final String VERSION = "0.1FR";
        setTitle("Tic Tac Toe " + VERSION);
        setVisible(true);  // show this JFrame
        setResizable(false);


        //game components are being defined

        Random random = new Random();
        playBoard = new Player[ROW][COLUMN]; // array allocated
        resetGame(); // initialize the game playBoard contents and game variables
    }


    // Initialize the playboard contents and the status
    public void resetGame() {
        for (int row = 0; row < ROW; ++row) {
            for (int col = 0; col < COLUMN; ++col) {
                playBoard[row][col] = Player.EMPTY; // all cells empty
            }
        }
        gameState = GameState.PLAYING; // ready to play
        gamePlayer = Player.EX;       // ex plays first
    }

    /**
     *If game is still playing nobody has won or drawn then changeGameState
     */
    public void MoveOrRestart(int rowSelected, int colSelected) {
        if (gameState == GameState.PLAYING) {
            if (validMove(rowSelected, colSelected)) {
                changeGameState(gamePlayer, rowSelected, colSelected);
                switchPlayer();
            }
        } else {       // game over
            resetGame(); // restart the game
        }
    }

    /**
     *  checks whether the move is valid or not
     */

    public boolean validMove(int row, int col) {
        return row >= 0 && row < ROW && col >= 0 && col < COLUMN && playBoard[row][col] == Player.EMPTY;
    }

    /**
     * makes the move and changes gameState
     */
    public void changeGameState(Player thePlayer, int rowSelected, int colSelected) {
        playBoard[rowSelected][colSelected] = gamePlayer; // move
        if (waysToWin(thePlayer, rowSelected, colSelected)) {
            gameState = (thePlayer == Player.EX) ? GameState.EX_WON : GameState.NAUGHT_WON;

        } else if (hasDrawn()) {
            gameState = GameState.DRAWN; // if there is no result game continues to GameState.Playing
        }
    }

    /* Switches player */
    public void switchPlayer() {
        gamePlayer = (gamePlayer == Player.EX) ? Player.NAUGHT : Player.EX;
    }

    /**
     * True when drawn. When there are no more empty cells, it's a draw
     */
    public boolean hasDrawn() {
        for (int row = 0; row < ROW; ++row) {
            for (int col = 0; col < COLUMN; ++col) {
                if (playBoard[row][col] == Player.EMPTY) {
                    return false; // an empty cell found, not draw, exit
                }
            }
        }
        return true; /** no more empty cells, so game drawn */
    }

    /**
     * Returns true EX/X has won against NAUGHT/O, or vice versa after placing at rowClicked and colClicked
     */
    public boolean waysToWin(Player thePlayer, int rowClicked, int colClicked) {
        return ((playBoard[rowClicked][0] == thePlayer      // three in the row
                && playBoard[rowClicked][1] == thePlayer
                && playBoard[rowClicked][2] == thePlayer) ||
                (playBoard[0][colClicked] == thePlayer      // three in the column
                        && playBoard[1][colClicked] == thePlayer
                        && playBoard[2][colClicked] == thePlayer) ||
                (playBoard[0][0] == thePlayer                // three in the diagonal
                        && playBoard[1][1] == thePlayer
                        && playBoard[2][2] == thePlayer) ||
                (playBoard[0][2] == thePlayer                // three in the reverse diagonal
                        && playBoard[1][1] == thePlayer
                        && playBoard[2][0] == thePlayer));
    }



    /*============================  Inner class for GUI actions =========================== */

    /**
     * This is paint mark for all the cells when they are not empty
     */
    class PaintCanvas extends JPanel {
        @Override
        public void paintComponent(Graphics g) {  // invoke via repaint()
            super.paintComponent(g);    // fill background
            setBackground(Color.white); // set its background color

            // hasDrawn the grid-lines
            g.setColor(Color.BLACK);
            for (int row = 1; row < ROW; ++row) {
                g.fillRoundRect(0, CELL_SIZE * row - GRID_HALF_WIDTH,
                        CANVAS_WIDTH - 1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
            }
            for (int col = 1; col < COLUMN; ++col) {
                g.fillRoundRect(CELL_SIZE * col - GRID_HALF_WIDTH, 0,
                        GRID_WIDTH, CANVAS_HEIGHT - 1, GRID_WIDTH, GRID_WIDTH);

            }

            /**
             * Paints the marks of all the cells that are not empty. Graphics2d is used to set SPEN's stroke
             */

            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(SPEN_WIDTH, BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND));  // Graphics2D only
            for (int row = 0; row < ROW; ++row) {
                for (int col = 0; col < COLUMN; ++col) {
                    int x1 = col * CELL_SIZE + CELL_LINING;
                    int y1 = row * CELL_SIZE + CELL_LINING;
                    if (playBoard[row][col] == Player.EX) {
                        g2d.setColor(Color.ORANGE);
                        int x2 = (col + 1) * CELL_SIZE - CELL_LINING;
                        int y2 = (row + 1) * CELL_SIZE - CELL_LINING;
                        g2d.drawLine(x1, y1, x2, y2);
                        g2d.drawLine(x2, y1, x1, y2);
                    } else if (playBoard[row][col] == Player.NAUGHT) {
                        g2d.setColor(Color.GREEN);
                        g2d.drawOval(x1, y1, SPEN_SIZE, SPEN_SIZE);
                    }
                }
            }


            /**
             * This prints status bar message
             */
            if (gameState == GameState.PLAYING) {
                statusBar.setForeground(Color.BLACK);
                if (gamePlayer == Player.EX) {
                    statusBar.setText("X's Turn");
                } else {
                    statusBar.setText("O's Turn");
                }
            } else if (gameState == GameState.DRAWN) {
                statusBar.setForeground(Color.RED);
                DrawnCount++;
                statusBar.setText("It's a Draw! " + " Draws: " +DrawnCount+ " Click to play again.");
            } else if (gameState == GameState.EX_WON) {
                statusBar.setForeground(Color.RED);
                Ex_WonCount++;
                statusBar.setText("'X' Won!  Score is X: " + Ex_WonCount + " &  O: " + Naught_wonCount);
            } else if (gameState == GameState.NAUGHT_WON) {
                statusBar.setForeground(Color.RED);
                Naught_wonCount++;
                statusBar.setText("'O' Won!  Score is O: " + Naught_wonCount + " &  X: " + Ex_WonCount);
            }
            }

        }
    }



