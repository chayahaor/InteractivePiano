package piano.intro;

import piano.keyboard.keyboardaudio.Notes;
import piano.keyboard.keyboardaudio.SoundSettings;

import javax.sound.midi.MidiChannel;

public class Intro {
    public void playIntro(MidiChannel channel)
    {
        try
        {
            int[] notes = { Notes.C, Notes.D, Notes.E, Notes.F, Notes.G, Notes.A, Notes.B };
            for (int i = 0; i < notes.length; ++i)
            {
                channel.noteOn(notes[i], SoundSettings.VOLUME);
                Thread.sleep(100);
                channel.noteOff(notes[i]);
            }
            // Play a C major chord.
            channel.noteOn(Notes.C, SoundSettings.VOLUME);
            channel.noteOn(Notes.E, SoundSettings.VOLUME);
            channel.noteOn(Notes.G, SoundSettings.VOLUME);
            channel.noteOn(Notes.C + 12, SoundSettings.VOLUME);
            Thread.sleep(3000);
            channel.allNotesOff();
            Thread.sleep(500);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
