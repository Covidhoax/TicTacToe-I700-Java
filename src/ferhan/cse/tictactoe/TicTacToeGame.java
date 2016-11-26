package ferhan.cse.tictactoe;

import javax.swing.*;

/**
 * Created by Farhan on 11/26/2016.
 */
public class TicTacToeGame {
    public static void main(String[] args) throws InterruptedException {
        try {
            {
                int reply = JOptionPane.showConfirmDialog(null,
                        "This game was made by Farhan Nayeem Islam C11 IT College. Do you want to play?", "Loading .......",
                        JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }

                BgMusic midi = new BgMusic();
                TicTacToe ticTacToeFr = new TicTacToe();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
