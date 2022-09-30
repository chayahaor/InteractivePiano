package piano.SaveAudio;

import piano.main.PianoGUI;
import piano.recorder.KeyPressedInfo;

import javax.swing.*;
import java.util.ArrayList;

public class SaveOption {

    private int response;

    public SaveOption(PianoGUI parent, ArrayList<KeyPressedInfo> recordedKeys) {
        response = JOptionPane.showConfirmDialog(parent, "Would you like to save your stunning music?");
        if (response == JOptionPane.YES_OPTION)
        {
            new FileLocationGUI(recordedKeys);
        } else if (response == JOptionPane.NO_OPTION)
        {
            JOptionPane.getRootFrame().dispose();
        }
    }
}
