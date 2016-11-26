package ferhan.cse.tictactoe;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.swing.*;
import java.io.File;

/**
 * Created by Farhan on 11/26/2016.
 */
public class BgMusic extends JFrame {

    public BgMusic() {

        playMusic();
    }

    private void playMusic() {
        try {
            //http://www.vgmusic.com/music/other/miscellaneous/arcade/1944_jungle.mid//
            Sequence sequence = MidiSystem.getSequence(new File("music/ttt.mid"));

            // Sequencer for music
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.setSequence(sequence);

            // midi plays in a infinite loop
            sequencer.start();
            sequencer.setLoopCount(99999);
        } catch (Exception exception) {

        }
    }
}