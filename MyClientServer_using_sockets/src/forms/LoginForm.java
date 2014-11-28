package forms;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JFrame{
	JPanel panel;
	JTextField loginField;
	JPasswordField passwordField;
	JButton okay;
	JLabel message;
	
	
	public JLabel getMessage() {
		return message;
	}

	public LoginForm(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int sizeWidth = 300;
		int sizeHeight = 140;
		int locationX = (screenSize.width - sizeWidth) / 2;
		int locationY = (screenSize.height - sizeHeight) / 2;
		this.setSize(sizeWidth,sizeHeight);
		this.setLocation(locationX,locationY);
		this.setTitle("Authorization");
		this.panel = new JPanel();
		this.loginField = new JTextField(20);
		this.message = new JLabel("");
//		this.loginField.addFocusListener(new FocusListener() {
//		    public void focusGained(FocusEvent e) {
//		    	loginField.setText("q");
//		    }
//
//		    public void focusLost(FocusEvent e) {
//		        // nothing
//		    	loginField.setText("w");
//		    }
//		});
		//this.loginField.("Enter login:");
		
		this.passwordField = new JPasswordField(20);
		this.okay = new JButton("Sign in");
		this.panel.add(loginField);
		this.panel.add(passwordField);
		this.panel.add(okay);
		this.panel.add(message);
		this.add(panel);
	}
	
	public JButton getButton(){
		return this.okay;
	}
	public JPasswordField getPasswordField(){
		return this.passwordField;
	}
	public JTextField getLoginField(){
		return this.loginField;
	}
}
