package piano.recorder;

import piano.keyboard.keyboardui.PianoLabel;

public class KeyPressedInfo
{
    private PianoLabel labelPressed;
    private long timeWhenPressed;

    public KeyPressedInfo(PianoLabel labelPressed, long timeWhenPressed)
    {
        this.labelPressed = labelPressed;
        this.timeWhenPressed = timeWhenPressed;
    }

    public PianoLabel getLabelPressed()
    {
        return labelPressed;
    }

    long getTime()
    {
        return timeWhenPressed;
    }
}
