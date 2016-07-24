import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.border.*;

import net.miginfocom.swing.MigLayout;

public class EmployeeUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	NumberFormat amountFormat = NumberFormat.getNumberInstance();
	private int personId;
	private JTextField personIdField = new JFormattedTextField(amountFormat);
	private JTextField fNameField = new JTextField(30);
	private JTextField lNameField = new JTextField(30);
	private JTextField emailField = new JTextField(30);
	private JTextField companyField = new JTextField(30);
	private JButton createButton = new JButton ("Save");
	private JButton updateButton = new JButton ("Update");
	private JButton deleteButton = new JButton ("Delete");
	private JButton newButton = new JButton ("New");
	private JButton totalButton = new JButton ("Current Totals");
	
	private EmployeeBean bean = new EmployeeBean();
	
	   public EmployeeUI() {
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
		      createButton.addActionListener(new ButtonHandler());
		      panel.add(updateButton);
		      updateButton.addActionListener(new ButtonHandler());
		      panel.add(deleteButton);
		      deleteButton.addActionListener(new ButtonHandler());
		      panel.add(newButton);
		      newButton.addActionListener(new ButtonHandler());
		      panel.add(totalButton);
		      totalButton.addActionListener(new ButtonHandler());
		      return panel;
		   }

		   private JPanel initFields() {
		      JPanel panel = new JPanel();
		      panel.setLayout(new MigLayout());
		      panel.add(new JLabel("ID"), "align label");
		      panel.add(personIdField, "wrap");
		      personIdField.setEnabled(false);
		      panel.add(new JLabel("First Name"), "align label");
		      panel.add(fNameField, "wrap");
		      panel.add(new JLabel("Employee Last Name"), "align label");
		      panel.add(lNameField, "wrap");
		      panel.add(new JLabel("Employee Email"), "align label");
		      panel.add(emailField, "wrap");
		      panel.add(new JLabel("Company"), "align label");
		      panel.add(companyField, "wrap");
		      return panel;
		   }

		   private Employee getFieldData() {
		      Employee emp = new Employee();
		      emp.setPersonId(personIdField.getText());
		      emp.setFirstName(fNameField.getText());
		      emp.setLastName(lNameField.getText());
		      emp.setEmail(emailField.getText());
		      emp.setCompany(companyField.getText());
		      return emp;
		   }

		   private void setFieldData(Employee emp) {
		      personIdField.setText(String.valueOf(personId));
		      fNameField.setText(emp.getFirstName());
		      lNameField.setText(emp.getLastName());
		      emailField.setText(emp.getEmail());
		      companyField.setText(emp.getCompany());
		   }

		   private boolean isEmptyFieldData() {
		      return (fNameField.getText().trim().isEmpty()
		         && lNameField.getText().trim().isEmpty()
		         && emailField.getText().trim().isEmpty()
		         && companyField.getText().trim().isEmpty());
		   }

		   private class ButtonHandler implements ActionListener {
		      @Override
		      public void actionPerformed(ActionEvent e) {
		         Employee emp = getFieldData();
		         switch (e.getActionCommand()) {
		         case "Save":
		            if (isEmptyFieldData()) {
		               JOptionPane.showMessageDialog(null,
		               "Cannot create an empty record");
		               return;
		            }
		            if (bean.create(emp) != null)
		               JOptionPane.showMessageDialog(null,
		               "New person created successfully.");
		               createButton.setText("New");
		               break;
		         case "New":
		            emp.setFirstName("");
		            emp.setLastName("");
		            emp.setEmail("");
		            emp.setCompany("");
		            setFieldData(emp);
		            createButton.setText("Save");
		            break;
		         case "Update":
		            if (isEmptyFieldData()) {
		               JOptionPane.showMessageDialog(null,
		               "Cannot update an empty record");
		               return;
		            }
		            if (bean.update(emp) != null)
		               JOptionPane.showMessageDialog(
		               null,"Person with ID:" + String.valueOf(emp.getPersonId()
		               + " is updated successfully"));
		               break;
		         case "Delete":
		            if (isEmptyFieldData()) {
		               JOptionPane.showMessageDialog(null,
		               "Cannot delete an empty record");
		               return;
		            }
		            //emp = bean.getCurrent();
		            bean.delete();
		            JOptionPane.showMessageDialog(
		               null,"Person with ID:"
		               + String.valueOf(emp.getPersonId()
		               + " is deleted successfully"));
		               break;
		         default:
		            JOptionPane.showMessageDialog(null,
		            "invalid command");
		         }
		      }
		   }
		}
