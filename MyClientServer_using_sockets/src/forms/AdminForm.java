package forms;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AdminForm extends JFrame{
	private JPanel mainPanel;
	private JTable allOrdersTable;
	private JScrollPane allOrdersPane;
	private JButton makeOrder;
	private JLabel label;
	private JButton button;
	private void initPanel(){
		//table
		this.allOrdersTable = new JTable();
		this.allOrdersTable.setPreferredScrollableViewportSize(new Dimension(750,100));
		this.allOrdersTable.setSize(800, 500);
		
		//label
		this.label = new JLabel("Всі замовлення");
		
		//button
		this.button = new JButton("Видалити");
		//scroll pane
		this.allOrdersPane = new JScrollPane(allOrdersTable);
		this.allOrdersPane.setPreferredSize(new Dimension(750,400));
		this.allOrdersPane.setSize(800, 500);
		
		//panel: scroll pane(table)
		this.mainPanel = new JPanel();
		this.mainPanel.add(label);
		this.mainPanel.add(allOrdersPane);
		this.mainPanel.add(button);
	}
	
	public AdminForm() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int sizeWidth = 800;
		int sizeHeight = 500;
		int locationX = (screenSize.width - sizeWidth) / 2;
		int locationY = (screenSize.height - sizeHeight) / 2;
		this.setSize(sizeWidth,sizeHeight);
		this.setLocation(locationX,locationY);
		this.setTitle("Admin panel");
		initPanel();
		this.add(mainPanel);
	}
	
	public JTable getTable(){
		return this.allOrdersTable;
	}
	public JButton getButton(){
		return this.button;
	}
	
}
