import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;

public class TableDemo extends JPanel {
	private EmployeeBean bean = new EmployeeBean();
	private int index = 0;
	
	public TableDemo() {
		super(new GridLayout(1,0));
		JTable table = new JTable(createTable());
		table.setPreferredScrollableViewportSize(new Dimension(500,70));
		table.setFillsViewportHeight(false);
		JScrollPane scrollPane = new JScrollPane(table);
	}
	
	public MyTableModel createTable () {
		MyTableModel clientTable = new MyTableModel();
		Object[][] clientData = bean.getResults();
		if (clientData != null) {
			while (index < clientData.length) {
				for (int i = 0; i < clientTable.getColumnCount(); i++) {
					clientTable.setValueAt(clientData[index][i], index, i);
					i++;
				}
				index++;
			}
			return clientTable;
		}
		clientTable=null;
		System.out.println("Table is empty!");
		return clientTable;
	}
	
	class MyTableModel extends AbstractTableModel {
		private String [] columnNames = {"Client ID", "Client First Name", "Client Last Name", "Client Email", "Client Company"};
		private Object[][] data;

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.length;
		}

		public Object getValueAt(int arg0, int arg1) {
			return data[arg0-1][arg1-1];
		}
		
	}

	
}