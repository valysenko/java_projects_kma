package forms;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entities.Book;

public class UserTableModel extends AbstractTableModel{
	
	private ArrayList<Book> alb;
	private String[] colNames = {"Name","Author"};
	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return alb.size();
	}

	public UserTableModel(ArrayList<Book> alb) {
		this.alb = alb;
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		switch(colIndex) {
			case 0 :
		        return alb.get(rowIndex).getName();
		    case 1 :
		        return alb.get(rowIndex).getAuthor();	
		}
		return 0;
	}
	
	@Override
	public String getColumnName(int column) {
				return colNames[column];
	}

	
	
}
