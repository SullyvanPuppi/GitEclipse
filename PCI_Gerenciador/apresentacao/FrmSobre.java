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
public class FrmSobre extends JWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7803148430931790902L;

	/**
	 * 
	 */
	public FrmSobre() {
		super();
	}

	// A simple little method to show a title screen in the center
	// of the screen for the amount of time given in the constructor
	public void showSplash() {
		//this.setTitle("][ SINTEG Pesquero Representações ][");
		//this.setModal(true);
		JPanel jContentPane = new JPanel();
		jContentPane.setLayout(new BorderLayout());

		int width = 360;
		int height = 498;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		setBounds(x, y, width, height);

		// Build the splash screen
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon(getClass().getResource("/apresentacao/sobre.gif")));
		jContentPane.add(logo);
		setContentPane(jContentPane);

		setVisible(false);
		logo.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				dispose();
			}
		});
		
	}

	public void showSplashAndExit() {
		showSplash();
	}
}