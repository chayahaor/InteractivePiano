package piano.SaveAudio;

import piano.recorder.KeyPressedInfo;

import javax.sound.midi.*;
import java.io.File;
import java.util.ArrayList;

public class AudioToMidi {

    ArrayList<KeyPressedInfo> recordedKeysInfo;
    File location;
    int instrumentNum;

    public AudioToMidi(ArrayList<KeyPressedInfo> getRecordedKeysInfo, File locationToSave) {
        location = locationToSave;
        recordedKeysInfo = getRecordedKeysInfo;
        writeMidiFile();
    }

    public void writeMidiFile() {
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
                int note = recordedKeysInfo.get(i).getLabelPressed().getKey();
                // Add Note On event - Command 144 is to play a note.
                currTrack.add(makeASong(144, instrumentNum, note, 120, i));
            }

            sequencer.setSequence(sequence);
            sequencer.setTempoInBPM(120);
            int[] allowedTypes = MidiSystem.getMidiFileTypes(sequence);

            //TODO: Allow user to determine file name in addition to path
            MidiSystem.write(sequence, allowedTypes[0],
                    new File(location.getAbsolutePath() + "\\Music.mid"));

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
