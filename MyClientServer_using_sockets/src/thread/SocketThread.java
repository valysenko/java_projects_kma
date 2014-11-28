package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import client.Client;
import entities.Book;
import entities.DAO;
import entities.Order;
import entities.User;

public class SocketThread implements Runnable {
	private static Socket socket;
	private static BufferedReader inStream;
	private static ObjectOutputStream ous;
	public SocketThread(Socket socket) {

		this.socket = socket;
	}

	public void run() {
		try {
			inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			ous = new ObjectOutputStream(socket.getOutputStream());
			while (true) {
				String input = inStream.readLine();
				if (input != null) {
					if (input.equals("get")) {
						ArrayList<Book> alb = (ArrayList<Book>) DAO.getBooks();
						ous.writeObject(alb);
						ous.flush();
					}else if (input.substring(0, 7).equals("getUser")) {
						System.out.println("in getUser");
						String[] logPass = input.split(" ");
						User u = (User) DAO.getUser(logPass[1],logPass[2]);
						ous.writeObject(u);
						
						ous.flush();
					}
					else if (input.equals("getOrders")) {
						ArrayList<Order> alu = (ArrayList<Order>) DAO
								.getOrders();
						ous.writeObject(alu);
						ous.flush();
					} 
					else if (input.substring(0, 6).equals("insert")) {
						String[] indexes = input.substring(6).split(" ");
						DAO.makeOrder(indexes);
						ArrayList<Book> alb = (ArrayList<Book>) DAO
								.getBooksOfUser(Integer.parseInt(indexes[0]));
						ous.writeObject(alb);
						ous.flush();
					} 
					else if(input.substring(0, 6).equals("delete")){
						String[] words = input.substring(6).split(" ");
						
						ArrayList<Order> alO = DAO.deleteOrders(words);
						ous.writeObject(alO);
						ous.flush();
					}
					else if (input.substring(0, 11).equals("getUsOrders")) {
						String[] indexes = input.substring(11).split(" ");
						ArrayList<Book> alb = (ArrayList<Book>) DAO
								.getBooksOfUser(Integer.parseInt(indexes[0]));
						ous.writeObject(alb);
						ous.flush();
					}
					
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				socket.close();
				inStream.close();
				ous.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
