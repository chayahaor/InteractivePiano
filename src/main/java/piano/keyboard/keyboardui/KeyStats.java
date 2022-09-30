package piano.keyboard.keyboardui;

import piano.main.MainFrameInterface;

public interface KeyStats
{
	int OCTAVES = 7;
	int OCTAVE_WIDTH = MainFrameInterface.KEYBOARD_WIDTH / OCTAVES;

	int NUM_WHITE_KEYS_IN_OCTAVE = 7;
	int NUM_BLACK_KEYS_IN_OCTAVE = 5;

	int SPACE_BETWEEN_WHITE_KEYS = (int) ((double) 2/300 * OCTAVE_WIDTH); // percentage
	int WHITE_WIDTH = OCTAVE_WIDTH / NUM_WHITE_KEYS_IN_OCTAVE - SPACE_BETWEEN_WHITE_KEYS;

	int BLACK_WIDTH = (int) (13.7 * WHITE_WIDTH / 23.5); // proportion: 13.7 and 23.5 are average size of white and black keys, respectively
	int BLACK_HEIGHT = (int) (MainFrameInterface.KEYBOARD_HEIGHT / 1.75);

	int FIRST_BLACK = (WHITE_WIDTH + SPACE_BETWEEN_WHITE_KEYS) - (BLACK_WIDTH / 2);
	int SPACE_BETWEEN_BLACK_KEYS = WHITE_WIDTH + SPACE_BETWEEN_WHITE_KEYS - BLACK_WIDTH;
	int BIG_SPACE_BETWEEN_BLACK_KEYS = 2 * (WHITE_WIDTH + SPACE_BETWEEN_WHITE_KEYS) - BLACK_WIDTH;
}
