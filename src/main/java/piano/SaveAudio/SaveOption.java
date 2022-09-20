package piano.SaveAudio;

import piano.recorder.KeyPressedInfo;

import javax.swing.*;
import java.util.ArrayList;

public class SaveOption extends JFrame {

    int response;
    FileLocationGUI fileLocationGUI;

    public SaveOption(ArrayList<KeyPressedInfo> recordedKeys) {
        response = JOptionPane.showConfirmDialog(this, "Would you like to save your stunning music?");
        if (response == JOptionPane.YES_OPTION)
        {
            fileLocationGUI = new FileLocationGUI(recordedKeys);
        } else if (response == JOptionPane.NO_OPTION)
        {
            JOptionPane.getRootFrame().dispose();
        }
    }
}
