import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import entities.Book;
import entities.Order;
import entities.User;
import forms.AdminForm;
import forms.AdminTableModel;
import forms.ClientForm;
import forms.LoginForm;
import forms.UserTableModel;

public class Test {
	private static int id_user = 0;
	private static ArrayList<Book> books;
	private static ArrayList<Book> orders;
	private static ArrayList<Order> allOrders;
	private static Socket socket;
	private static LoginForm loginForm;
	private static ClientForm clForm;
	private static AdminForm admForm;
	private static DAO dao;
	public static final String SERVER_NAME = "Server";

	// use : server <port> - start server
	// client <host> <port> - start client
	public static void main(String[] args) throws Exception {
		// starts server.arguments: server 8888
		if (args[0].equals("server")) {
			Server server = new Server(args[1]);
			server.runServer();
			System.out.println("Server started on port " + args[1]);
		}

		// starts client. arguments: client localhost 8888
		else {
			String lookupString = "//" + args[1] + ":" + args[2] + "/"
					+ SERVER_NAME;
			dao = (DAO) Naming.lookup(lookupString);
			dao.createConnection();
			System.out.println("RMI object found");

			// Login form
			loginForm = new LoginForm();
			loginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			loginForm.setVisible(true);
			loginForm.getButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String login = loginForm.getLoginField().getText();
					String password = loginForm.getPasswordField().getText();
					try {
						User u = dao.getUser(login,password);
						id_user = u.getId();
						
						if (u.getRole().equals("user")){
							loginForm.setVisible(false);
							clForm = new ClientForm();
							clForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							clForm.setVisible(true);
							// taking all books
							books = (ArrayList<Book>) dao.getBooks();
							clForm.getTbable().setModel(new UserTableModel(books));
							 
							
							// taking all orders of the user
							orders = (ArrayList<Book>) dao.getBooksOfUser(id_user);
							clForm.getUserTbable().setModel(new UserTableModel(orders));
	
	
							
							
							//if order button is pressed
							clForm.getMakeOrderButton().addActionListener(new ActionListener() {
	
								@Override
								public void actionPerformed(ActionEvent e) {
									int[] rows = clForm.getTbable().getSelectedRows();
									
									
									if (rows.length > 0) {
										
										 String str = ""+ id_user;
										for (int i = 0; i < rows.length; i++) {
											str += " "+ books.get(rows[i]).getId();
										}
										String[] ar = str.split(" ");
										// selected rows to server in dored to add to books of current user
										try {
											dao.makeOrder(ar);
										} catch (RemoteException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
								
									
									// update orders of the user
									try {
										orders =  (ArrayList<Book>) dao.getBooksOfUser(id_user);
										clForm.getUserTbable().setModel(new UserTableModel(orders));
									} catch (RemoteException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
									
								}
							});
							
							//if some row is pressed, show description of a book to the textArea
							clForm.getTbable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
								@Override
								public void valueChanged(ListSelectionEvent e) {
									int ind = clForm.getTbable().getSelectedRow();
									
										if(ind!=-1){
											Book s = books.get(ind);
											clForm.getTextArea().setText(s.getDescription());
										}
										
									}	
								
							});
							
							//if some row is pressed, show description of a book to the textArea (ordered books panel)
							clForm.getUserTbable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
								@Override
								public void valueChanged(ListSelectionEvent e) {
									int ind = clForm.getUserTbable().getSelectedRow();
									if(ind!=-1){
										Book s = orders.get(ind);
										clForm.getUserOrdersTextArea().setText(s.getDescription());
									}
									
								}
							});

						}
						
						//admin frame
						else if (u.getRole().equals("admin")) {
							final ArrayList<Order> alO; 
							loginForm.setVisible(false);
							admForm = new AdminForm();
							admForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							admForm.setVisible(true);
							allOrders = (ArrayList<Order>) dao.getOrders();
							admForm.getTable().setModel(new AdminTableModel(allOrders));

							//if delete button is pressed
							admForm.getButton().addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										int[] rows = admForm.getTable().getSelectedRows();
										
										if (rows.length > 0) {
											String str = "";
											for (int i = 0; i < rows.length; i++) {
												
												str +=  allOrders.get(rows[i]).getOrderId()+" ";
											}
											// selected rows to server in order to delete these orders
											String ar[] = str.split(" ");
											try {
												allOrders = (ArrayList<Order>) dao.deleteOrders(ar);
												admForm.getTable().setModel(new AdminTableModel(allOrders));
											} catch (RemoteException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											
										
										}
										
										
									}
										
									
							});
						}

						//incorrect input
						else {
							loginForm.getMessage().setText("invalid login/password!");
						}
						
						
						
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			});
		}
	}
}
