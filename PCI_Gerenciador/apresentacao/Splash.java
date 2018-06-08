/**
 * 
 */
package apresentacao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 * @author Sullyvan Puppi
 *
 */
public class Splash extends JWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int duration;

	public Splash(int d) {
		duration = d;
	}

	// A simple little method to show a title screen in the center
	// of the screen for the amount of time given in the constructor
	public void showSplash() {
		JPanel content = (JPanel) getContentPane();

		int width = 627;
		int height = 329;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		setBounds(x, y, width, height);

		// Build the splash screen
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon(getClass().getResource("/apresentacao/fundoInstal.gif")));
		content.add(logo, BorderLayout.CENTER);
		pack();
		// Display it
		setVisible(true);

		// Wait a little while, maybe while loading resources
		try {
			Thread.sleep(duration);
		} catch (Exception e) {
		}

		setVisible(false);
	}

	public void showSplashAndExit() {
		showSplash();
	}
}