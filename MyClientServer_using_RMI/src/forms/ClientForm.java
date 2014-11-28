package forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Toolkit;
import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import entities.Book;

public class ClientForm extends JFrame{
	private JPanel allBooksPanel;
	private JPanel myBooksPanel;
	private JTable allBooksTable;
	private JTable orderedBooksTable;
	private JScrollPane allBooksPane;
	private JScrollPane userOrdersPane;
	private JButton makeOrder;
	private JTabbedPane tabbedPane;
	private JTextArea allBooksTextArea;
	private JTextArea userBooksTextArea;
	
	private void initAllBooksTab(){
		//table of books
		this.allBooksTable = new JTable();
		this.allBooksTable.setPreferredScrollableViewportSize(new Dimension(750,100));
		this.allBooksTable.setSize(800, 300);
		
		//text area
		this.allBooksTextArea = new JTextArea(12,65);
		this.allBooksTextArea.setEditable(false);
		this.allBooksTextArea.setSize(750, 300);	
		
		//"make order" button
		this.makeOrder = new JButton("Make order");
		this.makeOrder.setSize(60, 40);
		
		// scrollpane 
		this.allBooksPane = new JScrollPane(allBooksTable);
		this.allBooksPane.setPreferredSize(new Dimension(750,100));
		this.allBooksPane.setSize(800, 200);
				
		//panel: scroll pane(table)+text area+button
		this.allBooksPanel = new JPanel();
		this.allBooksPanel.setSize(800,400);
		this.allBooksPanel.add(allBooksPane);
		this.allBooksPanel.add(allBooksTextArea);
		this.allBooksPanel.add(makeOrder);
		
	}
	
	
	private void initOrderedBooksTab(){
		//table
		this.orderedBooksTable = new JTable();
		this.orderedBooksTable.setPreferredScrollableViewportSize(new Dimension(750,100));
		this.orderedBooksTable.setSize(800, 300);
				
		//text area
		this.userBooksTextArea = new JTextArea(12,65);
		this.userBooksTextArea.setEditable(false);
				
		//scroll pane
		this.userOrdersPane = new JScrollPane(orderedBooksTable);
		this.userOrdersPane.setPreferredSize(new Dimension(750,100));
		this.userOrdersPane.setSize(800, 200);
						
		//panel: scroll pane(table)+text area
		this.myBooksPanel = new JPanel();
		this.myBooksPanel.add(userOrdersPane);
		this.myBooksPanel.add(userBooksTextArea);
		
		
	}
	
	private void initTabbedPane(){
		this.tabbedPane = new JTabbedPane();
		this.tabbedPane.setSize(800,350);
		this.tabbedPane.addTab("Всі доступні",allBooksPanel);
		this.tabbedPane.addTab("Мої замовлення",myBooksPanel);
		
		tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				allBooksTable.clearSelection();
				orderedBooksTable.clearSelection();
			}
		});
	}
	
	public ClientForm() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int sizeWidth = 800;
		int sizeHeight = 400;
		int locationX = (screenSize.width - sizeWidth) / 2;
		int locationY = (screenSize.height - sizeHeight) / 2;
		this.setSize(sizeWidth,sizeHeight);
		this.setLocation(locationX,locationY);
		this.setTitle("User panel");
		
		initAllBooksTab();
		initOrderedBooksTab();
		initTabbedPane();
		
		this.add(tabbedPane);
	}
	
	public JTable getTbable(){
		return this.allBooksTable;
	}
	
	public JTable getUserTbable(){
		return this.orderedBooksTable;
	}
	
	public JButton getMakeOrderButton(){
		return this.makeOrder;
	}
	public JTextArea getTextArea(){
		return this.allBooksTextArea;
	}
	public JTextArea getUserOrdersTextArea(){
		return this.userBooksTextArea;
	}
}
