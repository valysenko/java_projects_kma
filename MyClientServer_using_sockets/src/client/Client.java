package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
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

public class Client {
	private static int id_user = 0;
	private static ArrayList<Book> books;
	private static ArrayList<Book> orders;
	private static ArrayList<Order> allOrders;
	private static PrintWriter pw; 
	private static ObjectInputStream ois; 
	private static Socket socket;
	private static LoginForm loginForm;
	private static ClientForm clForm;
	private static AdminForm admForm;
	

	public static void main(String[] args) {

		try {
			socket = new Socket(args[0], Integer.parseInt(args[1]));
			pw= new PrintWriter(socket.getOutputStream(),true);
			ois = new ObjectInputStream(socket.getInputStream());
		
			//Login form
			loginForm = new LoginForm();
			loginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			loginForm.setVisible(true);
			loginForm.getButton().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String login = loginForm.getLoginField().getText();
					String password = loginForm.getPasswordField().getText();
					pw.println("getUser "+login+" "+password);
					try {
						User u = (User) ois.readObject();
						id_user = u.getId();
						
						 // user frame
						if (u.getRole().equals("user")) {
							loginForm.setVisible(false);
							clForm = new ClientForm();
							clForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							clForm.setVisible(true);
							// taking all books
							pw.println("get");
							try {
								Client.books = (ArrayList<Book>) ois.readObject();
								clForm.getTbable().setModel(new UserTableModel(Client.books));
							} catch (ClassNotFoundException ee) {
								ee.printStackTrace();
							}
							
							// taking all orders of the user
							pw.println("getUsOrders" + id_user);

							try {
								Client.orders = (ArrayList<Book>) ois.readObject();
								clForm.getUserTbable().setModel(new UserTableModel(Client.orders));

							} catch (ClassNotFoundException ee) {
								ee.printStackTrace();
							}

							
							
							//if order button is pressed
							clForm.getMakeOrderButton().addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									int[] rows = clForm.getTbable().getSelectedRows();
									
									
									if (rows.length > 0) {
										String str = "insert" + id_user;
										for (int i = 0; i < rows.length; i++) {
											str += " "+ Client.books.get(rows[i]).getId();
										}
										// selected rows to server in dored to add to books of current user
										pw.println(str);
									}
								
									
									// update orders of the user
									try {
										Client.orders =  (ArrayList<Book>) ois.readObject();
										clForm.getUserTbable().setModel(new UserTableModel(Client.orders));
									} catch (ClassNotFoundException e1) {
										e1.printStackTrace();
									} catch (IOException e1) {
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
											Book s = Client.books.get(ind);
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
										Book s = Client.orders.get(ind);
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
							pw.println("getOrders");
							allOrders = (ArrayList<Order>) ois.readObject();
							admForm.getTable().setModel(new AdminTableModel(allOrders));

							//if delete button is pressed
							admForm.getButton().addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										int[] rows = admForm.getTable().getSelectedRows();
										
										if (rows.length > 0) {
											String str = "delete";
											for (int i = 0; i < rows.length; i++) {
												
												str +=  allOrders.get(rows[i]).getOrderId()+" ";
											}
											// selected rows to server in order to delete these orders 
											pw.println(str);
											try {
												allOrders = (ArrayList<Order>) ois.readObject();
												admForm.getTable().setModel(new AdminTableModel(allOrders));
											} catch (ClassNotFoundException e1) {
												e1.printStackTrace();
											} catch (IOException e1) {
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
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
			});

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			/*try {
				socket.close();
				pw.close(); 
				ois.close(); 
				ous.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		
	}
}
