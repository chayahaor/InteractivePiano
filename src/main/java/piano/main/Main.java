package piano.main;

import piano.intro.Intro;
import piano.keyboard.keyboardaudio.Channel;
import piano.recorder.Recorder;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiUnavailableException;

public class Main {
    public static void main(String[] args) {
        try {
            MidiChannel midiChannel = new Channel().getChannel();
            PianoGUI frame = new PianoGUI(midiChannel);
            frame.setVisible(true);
            new Intro().playIntro(midiChannel);
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }
}
