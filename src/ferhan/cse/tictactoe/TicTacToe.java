package ferhan.cse.tictactoe;

import javax.swing.*;

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
    private static final int SPEN_SIZE = CELL_SIZE - CELL_LINING * 2; // paintbrush size
    private static final int SPEN_WIDTH = 9 ; // paintbrush stroke width

}
