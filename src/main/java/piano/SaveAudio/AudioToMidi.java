package piano.SaveAudio;

import piano.recorder.KeyPressedInfo;

import javax.sound.midi.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.*;
import java.util.ArrayList;

public class AudioToMidi {

    private Sequence sequence;
    ArrayList<KeyPressedInfo> recordedKeysInfo;
    File location;
    Path completePath;
    public AudioToMidi(ArrayList<KeyPressedInfo> getRecordedKeysInfo, File locationToSave) {
        location = locationToSave;
        recordedKeysInfo = getRecordedKeysInfo;
        writeMidiFile();
    }

    public void writeMidiFile() {
        try
        {
            sequence = new Sequence(Sequence.PPQ, 4);
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            Track currTrack = sequence.createTrack();
            int numNotes = recordedKeysInfo.size();

            for (int i = 0; i < numNotes; i++)
            {
                // Add Note On event
                int note = recordedKeysInfo.get(i).getLabelPressed().getKey();
                currTrack.add(makeASong(144, 1, note, 100, i));

                // Add Note Off event - if want the keys to not fade out
                //currTrack.add(makeASong(128, 1, i, 100, i + 2));
            }

            sequencer.setSequence(sequence);
            sequencer.setTempoInBPM(120);
            int[] allowedTypes = MidiSystem.getMidiFileTypes(sequence);


            MidiSystem.write(sequence, allowedTypes[0], new File(location.getAbsolutePath() + "\\Music.mid"));

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public MidiEvent makeASong(int command, int channel,
                               int note, int velocity, int tick) {
        MidiEvent event = null;

        try
        {
            // ShortMessage stores a note as command type, channel,
            // instrument it has to be played on and its speed.
            ShortMessage a = new ShortMessage();
            a.setMessage(command, channel, note, velocity);

            // A midi event is composed of a short message(representing
            // a note) and the tick at which that note has to be played
            event = new MidiEvent(a, tick);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return event;
    }

}
