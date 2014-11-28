package forms;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entities.Book;
import entities.Order;

public class AdminTableModel extends AbstractTableModel{
	
	private ArrayList<Order> alo;
	private String[] colNames = {"Student","Book name","Book author"};
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return alo.size();
	}

	public AdminTableModel(ArrayList<Order> alb) {
		this.alo = alb;
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		switch(colIndex) {
			case 0 :
		        return alo.get(rowIndex).getUserName();
			case 1 :
		        return alo.get(rowIndex).getBookName();
		    case 2 :
		        return alo.get(rowIndex).getBookAuthor();	
		}
		return 0;
	}
	
	@Override
	public String getColumnName(int column) {
				return colNames[column];
	}

	
	
}
