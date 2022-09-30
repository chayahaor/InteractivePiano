package piano.SaveAudio;

import piano.recorder.KeyPressedInfo;

import javax.sound.midi.*;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class AudioToMidi {

    private ArrayList<KeyPressedInfo> recordedKeysInfo;
    private File location;
    private int instrumentNum;
    private int bpm = 120;
    private final int playNote = 144;


    public AudioToMidi(ArrayList<KeyPressedInfo> getRecordedKeysInfo, File locationToSave) {
        location = locationToSave;
        recordedKeysInfo = getRecordedKeysInfo;
        writeMidiFile();
    }

    private void writeMidiFile() {
        try
        {
            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            Sequencer sequencer = MidiSystem.getSequencer();
            Track currTrack = sequence.createTrack();
            int numNotes = recordedKeysInfo.size();

            //TODO: fix so takes in proper channel and plays back in that instrument (instead of piano)
            instrumentNum = 1;

            for (int i = 0; i < numNotes; i++)
            {
                int note = recordedKeysInfo.get(i).getLabelPressed().getPitch();
                // Add Note On event - Command 144 is to play a note.
                currTrack.add(makeASong(playNote, instrumentNum, note, bpm, i));
            }

            sequencer.setSequence(sequence);
            sequencer.setTempoInBPM(120);
            int[] allowedTypes = MidiSystem.getMidiFileTypes(sequence);

            String fileName = JOptionPane.showInputDialog("What would you like the file to be called?");

            MidiSystem.write(sequence, allowedTypes[0],
                    new File(location.getAbsolutePath() + "\\"+ fileName + ".midi"));

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private MidiEvent makeASong(int command, int channel,
                                int note, int velocity, int tick) {
        MidiEvent event = null;
        try
        {
            ShortMessage a = new ShortMessage();
            a.setMessage(command, channel, note, velocity);
            event = new MidiEvent(a, tick);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return event;
    }

}
