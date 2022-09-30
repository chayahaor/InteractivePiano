package piano.keyboard.keyboardaudio;

import piano.keyboard.keyboardui.PianoLabel;
import piano.main.PianoGUI;
import piano.recorder.Recorder;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
    private final Recorder recorder;
    private final PianoGUI pianoGUI;
    private PianoLabel pianoLabel;

    public KeyboardListener(Recorder recorder, PianoGUI pianoGUI) {
        this.recorder = recorder;
        this.pianoGUI = pianoGUI;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        int labelIndex = getPosition(key);

        if (labelIndex != -1) {
            pianoLabel = pianoGUI.getKeyboard().getPianoLabelList().get(labelIndex);
            pianoLabel.play();

            if (recorder.getIsRecording()) {
                recorder.append(pianoLabel, System.currentTimeMillis());
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (pianoLabel != null) {
            pianoLabel.setColorToDefaultColor();
        }
    }
    private int getPosition(char key) {
        int labelIndex = switch (key) {
            case '1' -> 0;
            case '!' -> 21;
            case '2' -> 1;
            case '@' -> 22;
            case '3' -> 2;
            case '4' -> 3;
            case '$' -> 23;
            case '5' -> 4;
            case '%' -> 24;
            case '6' -> 5;
            case '^' -> 25;
            case '7' -> 6;
            case '8' -> 7;
            case '*' -> 26;
            case '9' -> 8;
            case '(' -> 27;
            case '0' -> 9;
            case 'q' -> 10;
            case 'Q' -> 28;
            case 'w' -> 11;
            case 'W' -> 29;
            case 'e' -> 12;
            case 'E' -> 30;
            case 'r' -> 13;
            case 't' -> 14;
            case 'T' -> 31;
            case 'y' -> 15;
            case 'Y' -> 32;
            case 'u' -> 16;
            case 'i' -> 17;
            case 'I' -> 33;
            case 'o' -> 18;
            case 'O' -> 34;
            case 'p' -> 19;
            case 'P' -> 35;
            case 'a' -> 20;
            default -> -1;
        };
        return labelIndex;
    }
}
