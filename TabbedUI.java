import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

public class TabbedUI extends JPanel{
	
	public TabbedUI() {
		super(new GridLayout(1,1));
		
		JTabbedPane tabbedPane = new JTabbedPane();
		ImageIcon icon = createImageIcon("images/logo.jpg");
	}

	private ImageIcon createImageIcon(String path) {
		/** Returns an ImageIcon, or null if the path was invalid. */
		    java.net.URL imgURL = getClass().getResource(path);
		    if (imgURL != null) {
		        return new ImageIcon(imgURL);
		    } else {
		        System.err.println("Couldn't find file: " + path);
		        return null;
		    }	
	}
}
