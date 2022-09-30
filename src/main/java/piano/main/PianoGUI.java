package piano.main;

import piano.instruments.InstrumentsPanel;
import piano.keyboard.keyboardui.Keyboard;
import piano.recorder.Recorder;
import piano.recorder.RecorderPanel;
import java.awt.*;
import javax.sound.midi.MidiChannel;
import javax.swing.*;

public class PianoGUI extends JFrame {

    public PianoGUI(MidiChannel midiChannel) {
        setTitle("MY PIANO");
        setSize(MainFrameInterface.KEYBOARD_WIDTH, MainFrameInterface.FRAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Color.BLACK);
        Recorder recorder = new Recorder(this);

        root.add(new RecorderPanel(recorder), BorderLayout.NORTH);
        root.add(new Keyboard(midiChannel, recorder), BorderLayout.CENTER);
        root.add(new InstrumentsPanel(midiChannel), BorderLayout.SOUTH);
        setContentPane(root);

	}
}
