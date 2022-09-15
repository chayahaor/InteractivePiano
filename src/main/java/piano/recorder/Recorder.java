package piano.recorder;

import piano.keyboard.keyboardui.PianoLabel;

import javax.sound.midi.*;
import java.io.File;
import java.util.ArrayList;

public class Recorder {
    private ArrayList<KeyPressedInfo> recordedKeysInfo = new ArrayList<>();
    private boolean isRecording;
    private Sequence sequence;


    public void append(PianoLabel labelPressed, long timePressed) {
        recordedKeysInfo.add(new KeyPressedInfo(labelPressed, timePressed));
    }

    void playBack() {
        long waitTime;
        for (int ix = 0; ix < recordedKeysInfo.size(); ix++)
        {
            PianoLabel labelPressed = recordedKeysInfo.get(ix).getLabelPressed();
            if (ix > 0)
            {
                waitTime = recordedKeysInfo.get(ix).getTime() - recordedKeysInfo.get(ix - 1).getTime();
                try
                {
                    Thread.sleep(waitTime);
                } catch (Exception exc)
                {
                    System.out.println(exc.getMessage());
                }
            }
            labelPressed.play();
            try
            {
                Thread.sleep(150);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            labelPressed.setColorToDefaultColor();
        }
        writeMidiFile();
    }

    void setIsRecording(boolean isRecording) {
        this.isRecording = isRecording;
    }

    public boolean getIsRecording() {
        return isRecording;
    }

    void clearRecordedNotes() {
        recordedKeysInfo.clear();
    }

    ArrayList<KeyPressedInfo> getRecordedKeysInfo() {
        return recordedKeysInfo;
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
            for (int i = 5; i < (4 * 20) + 5; i += 4)
            {

                // Add Note On event
                currTrack.add(makeASong(144, 1, i, 100, i));

                // Add Note Off event
                currTrack.add(makeASong(128, 1, i, 100, i + 2));
            }


            sequencer.setSequence(sequence);
            sequencer.setTempoInBPM(120);
            int[] allowedTypes = MidiSystem.getMidiFileTypes(sequence);
            MidiSystem.write(sequence, allowedTypes[0], new File("C:\\temp\\o.mid"));
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
