import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class newClientPanel extends TabbedUI {

	NumberFormat amountFormat = NumberFormat.getNumberInstance();
	private int personId;
	private String firstName;
	private String lastName;
	private String email;
	private String company;
	private JComponent newPanel = new JPanel();

	private JTextField personIdField = new JFormattedTextField(amountFormat);
	private JTextField fNameField = new JTextField(30);
	private JTextField lNameField = new JTextField(30);
	private JTextField emailField = new JTextField(30);
	private JTextField companyField = new JTextField(30);
	private JButton saveButton = new JButton("Save");
	private JButton exitButton = new JButton("Client Homepage");

	public newClientPanel() {
		/*
		 * Set basic layout of this panel
		 */
		newPanel.setLayout(new GridLayout(0, 2));
		newPanel.setPreferredSize(new Dimension(1000, 1000));

		
		/*
		 * Add data fields and corresponding labels to this panel
		 */
		newPanel.add(new JLabel("Client ID"), "align-label");
		newPanel.add(personIdField, "wrap");
		personIdField.setEnabled(false);
		newPanel.add(new JLabel("First Name"), "align-label");
		newPanel.add(fNameField, "wrap");
		newPanel.add(new JLabel("Employee Last Name"), "align-label");
		newPanel.add(lNameField, "wrap");
		newPanel.add(new JLabel("Employee Email"), "align-label");
		newPanel.add(emailField, "wrap");
		newPanel.add(new JLabel("Employee Company"), "align-label");
		newPanel.add(companyField, "wrap");
		
		/*
		 * Add buttons to this panel
		 */
		newPanel.add(saveButton);
		newPanel.add(exitButton);
		saveButton.addActionListener(new ButtonHandler());
		exitButton.addActionListener(new ButtonHandler());

	}

}
