import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Book;
import entities.Order;
import entities.User;


public class DAOImpl  extends UnicastRemoteObject implements DAO{
	final static String derbyProtocol = "jdbc:derby://localhost:1527/";
	final static String dbName = "Library";
	final static String jdbcURL = derbyProtocol + dbName;
	final static String derbyDriver = "org.apache.derby.jdbc.ClientDriver";
	final static String driver = derbyDriver;

	private static ArrayList<Book> books;
	private static ArrayList<User> users;
	private static ArrayList<Order> orders;
	
	public DAOImpl() throws RemoteException {
		 // порожнє, але мусить “кидати” RemoteException
	}
	
	public void createConnection() throws RemoteException{
		System.setProperty("jdbc.drivers", driver);
	}
	
	public  ArrayList<Book> getBooks() throws RemoteException{
		books = new ArrayList<Book>();
		try (Connection conn = DriverManager.getConnection(jdbcURL, "app",
				"root")) {
			PreparedStatement statement = null;
			ResultSet rs = null;
			final String query = "SELECT * from app.books";
			try {
				statement = conn.prepareStatement(query);
				rs = statement.executeQuery();
				while (rs.next()) {
					books.add(new Book(rs.getInt("id_book"), rs
							.getString("name"), rs.getString("author"), rs
							.getString("description")));
				}
			} catch (SQLException se) {
				System.out.println("SQL Error: " + se);
			}
		} catch (SQLException se) {
			System.out.println("Connection failed: " + se);
		}

		return books;
	}
	
	public  ArrayList<Book> getBooksOfUser(int id_user) throws RemoteException{
		books = new ArrayList<Book>();
		try (Connection conn = DriverManager.getConnection(jdbcURL, "app",
				"root")) {
			PreparedStatement statement = null;
			ResultSet rs = null;
			final String query = "SELECT * from app.books,app.orders WHERE app.orders.id_book=app.books.id_book AND app.orders.id_user =?";
			try {
				statement = conn.prepareStatement(query);
				statement.setInt(1, id_user);
				rs = statement.executeQuery();
				while (rs.next()) {
					books.add(new Book(rs.getInt("id_book"), rs
							.getString("name"), rs.getString("author"), rs
							.getString("description")));
				}
			} catch (SQLException se) {
				System.out.println("SQL Error: " + se);
			}
		} catch (SQLException se) {
			System.out.println("Connection failed: " + se);
		}

		return books;
	}

	public  void makeOrder(String[] indexes) throws RemoteException{
		ArrayList<Book> alb = getBooks();
		try (Connection conn = DriverManager.getConnection(jdbcURL, "app",
				"root")) {
			PreparedStatement statement = null;
			ResultSet rs = null;
			for (int i = 1; i < indexes.length; i++) {
				try {
					String insertQuery = "insert into app.orders values(?,?,?)";
					statement = conn.prepareStatement(insertQuery);
					statement.setInt(1,(getMaxQuantityId()+1));
					statement.setString(2,indexes[i]);
					statement.setString(3,indexes[0]);
					statement.executeUpdate();
					
				} catch (SQLException se) {
					System.out.println("SQL Error: " + se);
				}
			}
			

		} catch (SQLException se) {
			System.out.println("Connection failed: " + se);
		}

	}

	
		public  int getMaxQuantityId() throws RemoteException{
			int xxx=0;
			try (Connection conn = DriverManager.getConnection(jdbcURL, "app",
					"root")) {
				PreparedStatement statement = null;
				ResultSet rs = null;
				final String query = "SELECT max(app.orders.id_order) from app.orders";
				try {
					 Statement xstmnt = conn.createStatement();

			            if (xstmnt.execute("SELECT max(app.orders.id_order) from app.orders")){  

			                ResultSet xrs = xstmnt.getResultSet();

			                if (xrs.next()){

			                    xxx = xrs.getInt(1);
			                    
			                }

			            }
				} catch (SQLException se) {
					System.out.println("SQL Error: " + se);
				}
			} catch (SQLException se) {
				System.out.println("Connection failed: " + se);
			}
		
		return xxx;
	}

	public  ArrayList<Order> getOrders() throws RemoteException{
		orders = new ArrayList<Order>();
		try (Connection conn = DriverManager.getConnection(jdbcURL, "app",
				"root")) {
			PreparedStatement statement = null;
			ResultSet rs = null;
			final String query = "select * from app.orders left join app.books on app.books.id_book=app.orders.id_book left join  app.users on app.orders.id_user=app.users.id_user";
			try {
				statement = conn.prepareStatement(query);
				rs = statement.executeQuery();
			while (rs.next()) {
					orders.add(new Order(Integer.parseInt(rs.getString(1)),rs.getString(9),rs.getString(5),rs.getString(6)));
				}
			} catch (SQLException se) {
				System.out.println("SQL Error: " + se);
			}
		} catch (SQLException se) {
			System.out.println("Connection failed: " + se);
		}

		return orders;
	}

	public  ArrayList<Order> deleteOrders(String[] words) throws RemoteException{
		ArrayList<Book> alb = getBooks();
		try (Connection conn = DriverManager.getConnection(jdbcURL, "app",
				"root")) {
			PreparedStatement statement = null;
			ResultSet rs = null;
			for (int i = 0; i < words.length; i++) {
				try {
				    String query = "DELETE FROM app.ORDERS WHERE app.orders.id_order=?";
				    statement = conn.prepareStatement(query);
				    statement.setString(1, words[i]);
					statement.executeUpdate();
					
				} catch (SQLException se) {
					System.out.println("SQL Error: " + se);
				}
			}
			

		} catch (SQLException se) {
			System.out.println("Connection failed: " + se);
		}
		return getOrders();
	}

	public  User getUser(String login, String password) throws RemoteException{
		User us = null;
		try (Connection conn = DriverManager.getConnection(jdbcURL, "app",
				"root")) {
			PreparedStatement statement = null;
			ResultSet rs = null;
			final String query = "SELECT * FROM app.USERS WHERE app.users.name=? AND app.users.password=?";
			try {
				statement = conn.prepareStatement(query);
				statement.setString(1, login);
				statement.setString(2, password);
				rs = statement.executeQuery();
				if(rs.next())
					us = new User(rs.getInt("id_user"), rs
						.getString("name"), rs.getString("password"), rs
						.getString("role"));
			} catch (SQLException se) {
				System.out.println("SQL Error: " + se);
			}
		} catch (SQLException se) {
			System.out.println("Connection failed: " + se);
		}
		if(us==null)
			us = new User(1,"","","");
		return us;
	}
	

}
