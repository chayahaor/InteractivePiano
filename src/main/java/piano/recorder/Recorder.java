package piano.recorder;

import piano.SaveAudio.SaveOption;
import piano.keyboard.keyboardui.PianoLabel;

import java.util.ArrayList;

public class Recorder {
    private ArrayList<KeyPressedInfo> recordedKeysInfo = new ArrayList<>();
    private boolean isRecording;


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
        new SaveOption(getRecordedKeysInfo());

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

}
