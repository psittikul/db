import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.border.*;

public class TestUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	NumberFormat amountFormat = NumberFormat.getNumberInstance();
	private int personId;
	private JTextField personIdField = new JFormattedTextField(amountFormat);
	private JTextField fNameField = new JTextField(30);
	private JTextField lNameField = new JTextField(30);
	private JButton createButton = new JButton ("New");
	private JButton updateButton = new JButton ("Update");
	private JButton deleteButton = new JButton ("Delete");
	private JButton searchButton = new JButton ("Search Client");
	private JButton totalButton = new JButton ("Current Totals");
	
	private EmployeeBean bean = new EmployeeBean();
	
	   public TestUI() {
		      setBorder(new TitledBorder
		      (new EtchedBorder(),"Employee Details"));
		      setLayout(new BorderLayout(5, 5));
		      add(initFields(), BorderLayout.NORTH);
		      add(initButtons(), BorderLayout.CENTER);
		   }

		   private JPanel initButtons() {
		      JPanel panel = new JPanel();
		      panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		      panel.add(createButton);
		      panel.add(updateButton);
		      panel.add(deleteButton);
		      panel.add(searchButton);
		      panel.add(totalButton);
		      return panel;
		   }

		   private JPanel initFields() {
		      JPanel panel = new JPanel();
		      panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		      panel.add(new JLabel("ID"), "align label");
		      panel.add(personIdField, "wrap");
		      personIdField.setEnabled(false);
		      panel.add(new JLabel("First Name"), "align label");
		      panel.add(fNameField);
		      panel.add(new JLabel("Employee Last Name"), "align label");
		      panel.add(lNameField);
		      return panel;
		   }
		}
