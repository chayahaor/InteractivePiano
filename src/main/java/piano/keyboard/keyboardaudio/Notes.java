package piano.keyboard.keyboardaudio;

import piano.keyboard.keyboardui.KeyStats;

public interface Notes
{
	int C =57;
	int startingC = C - (KeyStats.OCTAVES/2 * 12);
	int D = 59;
	int E = 61;
	int F = 62;
	int G = 64;
	int A = 66;
	int B = 68;
}
