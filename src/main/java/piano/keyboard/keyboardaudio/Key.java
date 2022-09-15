package piano.keyboard.keyboardaudio;

import javax.sound.midi.MidiChannel;

public class Key
{
	private int pitch;
	private MidiChannel midiChannel;

	public Key(int position, MidiChannel midiChannel) {
		pitch = position + Notes.startingC;
		this.midiChannel = midiChannel;
	}

	public void play() {
		SoundThread s = new SoundThread(pitch, midiChannel); // pass in pitch to play
		s.start();
	}

	public int getPitch() {
		return pitch;
	}
}
