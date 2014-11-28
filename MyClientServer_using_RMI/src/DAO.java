import java.rmi.Remote;
import java.rmi.RemoteException;
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


public interface DAO extends Remote{
	public void createConnection() throws RemoteException;
	
	public  ArrayList<Book> getBooks() throws RemoteException;
	public  ArrayList<Book> getBooksOfUser(int id_user) throws RemoteException;
	public  void makeOrder(String[] indexes) throws RemoteException;
	public  int getMaxQuantityId() throws RemoteException;
	public  ArrayList<Order> getOrders() throws RemoteException;
	public  ArrayList<Order> deleteOrders(String[] words) throws RemoteException;
	public  User getUser(String login, String password) throws RemoteException;
}
