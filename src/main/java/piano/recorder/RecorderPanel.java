package piano.recorder;

import piano.SaveAudio.SaveOption;
import piano.main.MainFrameInterface;
import javax.swing.*;
import java.awt.*;

public class RecorderPanel extends JPanel
{
    private JButton btnStartRecording;
    private JButton btnStopRecording;
    private JButton btnPlayBack;
    private Recorder recorder;

    private final Color START_BTN_NOT_PRESSED_COLOR = new Color(46, 177, 8);
    private final Color START_BTN_PRESSED_COLOR = new Color(8, 74, 14, 141);
    private final Color STOP_BTN_ACTIVE_COLOR = new Color(254, 45, 77, 253);
    private final Color PLAYBACK_BTN_ACTIVE_COLOR = new Color(108, 63, 177, 253);
    private final Color INACTIVE_COLOR = new Color(88, 88, 88, 253);

    public RecorderPanel(Recorder recorder)
    {
        this.recorder = recorder;
        setBackground(new Color(4, 4, 4, 253));
        setSize(MainFrameInterface.KEYBOARD_WIDTH, MainFrameInterface.RECORDER_PANEL_HEIGHT);
        btnStartRecording = createStartRecordingBtn();
        btnStopRecording = createStopRecordingBtn();
        btnPlayBack = createPlaybackBtn();
        add(btnStartRecording);
        add(btnStopRecording);
        add(btnPlayBack);
    }

    private JButton createStartRecordingBtn()
    {
        JButton start = new JButton("Record");
        start.setBackground(START_BTN_NOT_PRESSED_COLOR);
        start.addActionListener(e -> {
            if (!recorder.getIsRecording())
            {
                recorder.setIsRecording(true);
                start.setBackground(START_BTN_PRESSED_COLOR);
                start.setText("Recording...");
                btnStopRecording.setBackground(STOP_BTN_ACTIVE_COLOR);
                recorder.clearRecordedNotes();
            }
        });
        return start;
    }

    private JButton createStopRecordingBtn()
    {
        JButton stop = new JButton("Stop");
        stop.setBackground(INACTIVE_COLOR);
        stop.addActionListener(e -> {
            if (recorder.getIsRecording())
            {
                recorder.setIsRecording(false);
                stop.setBackground(INACTIVE_COLOR);
                btnStartRecording.setText("Record");
                btnStartRecording.setBackground(START_BTN_NOT_PRESSED_COLOR);
                btnPlayBack.setBackground(PLAYBACK_BTN_ACTIVE_COLOR);
            }
        });
        return stop;
    }

    private JButton createPlaybackBtn()
    {
        JButton playBack = new JButton("Play Back");
        playBack.setBackground(INACTIVE_COLOR);
        playBack.addActionListener(e -> {
            if (!recorder.getRecordedKeysInfo().isEmpty())
            {
                new Thread(() -> recorder.playBack()).start();
            }
        });
        return playBack;
    }
}
