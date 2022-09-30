package piano.main;

import piano.instruments.InstrumentsPanel;
import piano.keyboard.keyboardaudio.KeyboardListener;
import piano.keyboard.keyboardui.Keyboard;
import piano.recorder.Recorder;
import piano.recorder.RecorderPanel;
import java.awt.*;
import javax.sound.midi.MidiChannel;
import javax.swing.*;

public class PianoGUI extends JFrame {
    private Keyboard keyboard;

    public PianoGUI(MidiChannel midiChannel) {
        keyboard = new Keyboard(midiChannel, recorder);

        setTitle("MY PIANO");
        setSize(MainFrameInterface.KEYBOARD_WIDTH, MainFrameInterface.FRAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Color.BLACK);
        Recorder recorder = new Recorder(this);

        root.add(new RecorderPanel(recorder), BorderLayout.NORTH);
        root.add(keyboard, BorderLayout.CENTER);
        root.add(new InstrumentsPanel(midiChannel), BorderLayout.SOUTH);
        setContentPane(root);

        root.addKeyListener(new KeyboardListener(recorder, this));
        root.setFocusable(true);
    }
    public Keyboard getKeyboard() {
        return keyboard;
    }
}
