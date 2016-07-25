import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ButtonTabComponent extends JPanel {
	private final JTabbedPane pane;
	
	public ButtonTabComponent(final JTabbedPane pane) {
		if (pane == null) {
			throw new NullPointerException("TabbedPane is empty");
		}
		
		this.pane = pane;
		setOpaque(false);
		
		
		
	}
}
