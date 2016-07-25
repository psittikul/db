import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;

public class TabbedUI extends JPanel {
	private EmployeeBean bean = new EmployeeBean();
	private int tabNumber = 5;
	private JTabbedPane tabbedPane;
	/*
	 * Buttons, fields, etc. for Dashboard
	 */

	private JLabel welcomeLabel = new JLabel("Welcome!");
	private JLabel clientUpdate = new JLabel("Clients approaching end of services: ");

	/*
	 * Buttons, fields, etc. for Client
	 */
	private JComponent newPanel = new JPanel();
	private JComponent panel2;
	private JButton newButton = new JButton("New Client");
	private JButton editButton = new JButton("Edit Existing Client");
	private JButton deleteButton = new JButton("Delete Existing Client(s)");
	private JButton queryButton = new JButton("Run Client Query");
	private static ImageIcon icon = new ImageIcon("images/logo.png");

	NumberFormat amountFormat = NumberFormat.getNumberInstance();
	private int personId;
	private String first;
	private String last;
	private String email;
	private String company;
	private JTextField personIdField = new JFormattedTextField(amountFormat);
	private JTextField fNameField = new JTextField(30);
	private JTextField lNameField = new JTextField(30);
	private JTextField emailField = new JTextField(30);
	private JTextField companyField = new JTextField(30);
	GridLayout paneLayout= new GridLayout(1, 1);
	
	public TabbedUI() {
		super.setLayout(paneLayout);

		tabbedPane = new JTabbedPane();
		tabbedPane.setLayout(super.getLayout());

		/*
		 * Dashboard panel
		 */
		JComponent panel1 = makeTextPanel("Dashboard");
		tabbedPane.addTab("Dashboard", icon, panel1, "Does nothing");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		panel1.setLayout(getLayout());

		/*---------------------------------------------------------------------*/

		/*
		 * Clients panel
		 */
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2,1));
		panel2.setPreferredSize(new Dimension(1000, 1000));
		tabbedPane.addTab("Clients", icon, panel2, "Does twice as much nothing");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		initClientTab(panel2);


		/*----------------------------------------------------------------*/

		/*
		 * Customers panel
		 */
		JComponent panel3 = makeTextPanel("Customers");
		tabbedPane.addTab("Customers", icon, panel3, "Still does nothing");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		/*---------------------------------------------------------------*/

		/*
		 * Ambassadors Panel
		 */
		JComponent panel4 = makeTextPanel("Panel #4 (has a preferred size of 410 x 50).");
		panel4.setPreferredSize(new Dimension(410, 50));
		tabbedPane.addTab("Ambassadors", icon, panel4, "Does nothing at all");
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

		/*---------------------------------------------------------------*/

		/*
		 * Contacts panel
		 */
		JComponent panel5 = makeTextPanel("Contacts");
		tabbedPane.addTab("Contacts", icon, panel5);
		tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);
		// Add the tabbed pane to this panel.
		add(tabbedPane);

		// The following line enables to use scrolling tabs.
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	/**
	 * Various helper methods
	 */

	/**
	 * 
	 * @param text - title of this tabbed pane
	 * @return a new TabbedPane to add to the Frame
	 */
	protected JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}
	
	/*
	 * /** Returns an ImageIcon, or null if the path was invalid. protected
	 * static ImageIcon createImageIcon(String path) { java.net.URL imgURL =
	 * TabbedUI.getClass.getResource(path); if (imgURL != null) { return new
	 * ImageIcon(imgURL); } else { System.err.println("Couldn't find file: " +
	 * path); return null; } }
	 */

	/**
	 * Inhabit the Clients tabbed pane with the corresponding components
	 */
	private void initClientTab(JComponent panel) {
		JTable table = new JTable();
		table.setBounds(0, 0, 200, 200);
		Color color = new Color(888888);
		table.setBackground(color);
		panel.add(table);
		GridBagLayout buttonLayout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.ipadx = 40;
		constraints.ipady = 15;
		constraints.insets = new Insets(35, 35, 35, 35);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(buttonLayout);
		buttonPanel.add(newButton, constraints);
		newButton.addActionListener(new ButtonHandler());
		buttonPanel.add(editButton, constraints);
		editButton.addActionListener(new ButtonHandler());
		buttonPanel.add(deleteButton, constraints);
		deleteButton.addActionListener(new ButtonHandler());
		panel.add(buttonPanel);
		

	}
	
	public void initNewClientTab () {

		JButton saveButton = new JButton("Save");
		JButton exitButton = new JButton("Exit");
		GridLayout layout = new GridLayout(0,2);
		panel2.setLayout(layout);
		layout.setHgap(10);
		layout.setVgap(10);
		panel2.add(new JLabel("Client ID"));
		panel2.add(personIdField);
		panel2.add(new JLabel("Client First Name"));
		panel2.add(fNameField);
		panel2.add(new JLabel("Client Last Name"));
		panel2.add(lNameField);
		panel2.add(new JLabel("Client Email"));
		panel2.add(emailField);
		panel2.add(new JLabel("Client Company"));
		panel2.add(companyField);
		
		GridBagLayout buttonLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		JComponent buttonPanel = new JPanel();
		buttonPanel.setLayout(buttonLayout);
		c.ipadx = 95;
		c.ipady = 15;
		c.insets = new Insets(55,55,55,55);
		buttonPanel.add(saveButton, c);
		buttonPanel.add(exitButton, c);
		panel2.add(buttonPanel);
		saveButton.addActionListener(new ButtonHandler());
		exitButton.addActionListener(new ButtonHandler());
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("LINC Database");
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.add(new TabbedUI(), BorderLayout.CENTER);
		// Display the window.
		frame.pack();
		frame.setVisible(true);

	}

	/**
	 * Checks if the fields are empty, and is later used in ButtonHandler to
	 * return an error message if the user attempts to click a button when there
	 * is no data
	 * 
	 * @return true if fields are empty and false if fields are not empty
	 */
	private boolean isEmptyFieldData() {
		return (fNameField.getText().trim().isEmpty() && lNameField.getText().trim().isEmpty()
				&& emailField.getText().trim().isEmpty() && companyField.getText().trim().isEmpty());
	}

	/**
	 * Retrieves the text entered into the fields and sets our variable to those
	 * entries
	 * 
	 * @return an Employee object with the parameters entered in the GUI
	 */
	private Employee getFieldData() {
		Employee emp = new Employee();
		emp.setPersonId(personIdField.getText());
		emp.setFirstName(fNameField.getText());
		emp.setLastName(lNameField.getText());
		emp.setEmail(emailField.getText());
		emp.setCompany(companyField.getText());
		return emp;
	}

	/**
	 * Sets the GUI fields to display the person's information
	 * 
	 * @param the
	 *            employee whose information we are viewing
	 */
	private void setFieldData(Employee emp) {
		personIdField.setText(String.valueOf(personId));
		fNameField.setText(emp.getFirstName());
		lNameField.setText(emp.getLastName());
		emailField.setText(emp.getEmail());
		companyField.setText(emp.getCompany());
	}
	

	/**
	 * The class that implements the ActionListener in order to perform an
	 * action when one of the buttons has been clicked
	 * 
	 * @author Patricia Sittikul
	 *
	 */
	class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Employee emp = getFieldData();
			JTabbedPane tabbed = tabbedPane;
			switch (e.getActionCommand()) {
			case "Save":
				if (isEmptyFieldData()) {
					JOptionPane.showMessageDialog(null, "Cannot create an empty record");
					return;
				}
				if (bean.create(emp) != null)
					JOptionPane.showMessageDialog(null, "New person created successfully.");
				break;
			case "New Client":
				panel2.removeAll();
				initNewClientTab();
				panel2.revalidate();
				panel2.repaint();
				break;
			case "Update":
				if (isEmptyFieldData()) {
					JOptionPane.showMessageDialog(null, "Cannot update an empty record");
					return;
				}
				if (bean.update(emp) != null)
					JOptionPane.showMessageDialog(null,
							"Person with ID:" + String.valueOf(emp.getPersonId() + " is updated successfully"));
				break;
			case "Delete":
				if (isEmptyFieldData()) {
					JOptionPane.showMessageDialog(null, "Cannot delete an empty record");
					return;
				}
				// emp = bean.getCurrent();
				bean.delete();
				JOptionPane.showMessageDialog(null,
						"Person with ID:" + String.valueOf(emp.getPersonId() + " is deleted successfully"));
				break;
			default:
				JOptionPane.showMessageDialog(null, "invalid command");
			}
		}
	}

	public static void main(String[] args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}
}