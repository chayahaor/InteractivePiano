package piano.keyboard.keyboardui;

import piano.keyboard.keyboardaudio.Key;
import piano.main.MainFrameInterface;

import java.awt.Color;
import javax.swing.JLabel;

public class PianoLabel extends JLabel
{
	private Key key;
	private Color defaultColor; // color of key when NOT clicked
	private Color colorOnClick; // color of key when clicked

	public PianoLabel(Color defaultColor, Color colorOnClick, Key key) {
		if (defaultColor == Color.WHITE || defaultColor == Color.BLACK) {
			createPianoLabel(defaultColor, colorOnClick, key);
		}
	}

	private void createPianoLabel(Color defaultColor, Color onClickColor, Key key) {
		if (defaultColor == Color.WHITE) {
			setSize(KeyStats.WHITE_WIDTH, MainFrameInterface.KEYBOARD_HEIGHT);
		} else {
			setSize(KeyStats.BLACK_WIDTH, KeyStats.BLACK_HEIGHT);
		}
		this.defaultColor = defaultColor;
		this.colorOnClick = onClickColor;
		this.key = key;
		setOpaque(true);
		setBackground(defaultColor);
	}

	public void setColorToDefaultColor() {
		setBackground(defaultColor);
	}

	private void setColorToOnClickColor() {
		setBackground(colorOnClick);
	}

	public void play() {
		setColorToOnClickColor();
		key.play();
	}

	public int getKey() {
		return key.getPitch();
	}
}
