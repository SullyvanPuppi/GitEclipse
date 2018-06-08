/**
 * 
 */
package apresentacao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
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

		int width = 500;
		int height = 500;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		setBounds(x, y, width, height);

		// Build the splash screen
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon(getClass().getResource("/apresentacao/splash.gif")));
		JLabel sena = new JLabel("][ Sistema de Ensino Nova Athenas][",JLabel.CENTER);
		sena.setFont(new Font("Sans-Serif", Font.BOLD, 12));
		content.add(logo, BorderLayout.CENTER);
		content.add(sena, BorderLayout.SOUTH);
		Color borda = new Color(00, 51, 102);
		content.setBorder(BorderFactory.createLineBorder(borda, 10));
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