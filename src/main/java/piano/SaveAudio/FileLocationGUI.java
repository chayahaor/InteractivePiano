package piano.SaveAudio;

import piano.main.PianoGUI;
import piano.recorder.KeyPressedInfo;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class FileLocationGUI extends JFrame {
    JFileChooser fileChooser;
    PianoGUI pianoGUI;
    File fileLocation;
    AudioToMidi audioToMidi;

    public FileLocationGUI(ArrayList<KeyPressedInfo> recordedKeys) {
        setTitle("Save Audio");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fileChooser.showOpenDialog(pianoGUI);

        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            fileLocation = fileChooser.getSelectedFile();
        }
        audioToMidi = new AudioToMidi(recordedKeys, fileLocation);

    }


}
