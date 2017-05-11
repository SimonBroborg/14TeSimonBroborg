import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class MainApp extends JPanel implements ActionListener, KeyListener {
	// Sets the statement and connection objects to global
	private Connection con = null;
	private Statement stt = null;
	JLabel addressBook = null;
	JTextField newLnameText = null;
	JTextField newFnameText = null;
	JTextField newAddressText = null;
	JTextField searchAddressText = null;
	boolean inSearchField = false; 
	JPanel addresses = null; 
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Creates a object
				MainApp app = new MainApp();

				// Connects to the database
				app.connectDatabase("jdbc:mysql://localhost:3306/", "root", "");

				// Creates the window
				app.init();			
				
			}
		});

	}

	// Connects to the database with url, user name and password
	public void connectDatabase(String url, String userName, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, userName, password);

			stt = con.createStatement();
			// Create and select db

			stt.execute("CREATE DATABASE IF NOT EXISTS addressBook");
			stt.execute("USE addressBook");

			/*
			 * //Create out table stt.execute("DROP TABLE IF EXISTS people");
			 * stt.execute("CREATE TABLE people("+
			 * "id BIGINT NOT NULL AUTO_INCREMENT, " + "fname VARCHAR(25)," +
			 * "lname VARCHAR(25)," + "address VARCHAR (50)," +
			 * "PRIMARY KEY(id)" + ")");
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Creates the window frame, buttons and other things for the GUI
	public void init() {
		// Creates the JFrame which is the GUI window
		JFrame frame = new JFrame();

		frame.setSize(640, 480);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("MySQL databas sak");
		frame.setDefaultCloseOperation(3);

		// JTextField textField = new JTextField(20);

		// textField.setVisible(true);

		// Creates a button, when pressed it prints out the address book table
		JButton searchAddress = new JButton("Search");
		searchAddress.setVisible(true);
		searchAddress.setEnabled(true);
		searchAddress.addActionListener(this);
		searchAddress.setActionCommand("Search address");
		

		JButton addAddress = new JButton("Add address");
		addAddress.setVisible(true);
		addAddress.setEnabled(true);
		addAddress.addActionListener(this);
		addAddress.setActionCommand("Add address");
		

		newFnameText = new JTextField(10);
		newFnameText.setVisible(true);

		newLnameText = new JTextField(10);
		newLnameText.setVisible(true);

		newAddressText = new JTextField(10);
		newAddressText.setVisible(true);
		

		newFnameText.setBorder(BorderFactory.createTitledBorder("First name"));
		newLnameText.setBorder(BorderFactory.createTitledBorder("Surname"));
		newAddressText.setBorder(BorderFactory.createTitledBorder("Address"));

		addressBook = new JLabel("<html>");
		addressBook.setVisible(true);
		
		searchAddressText = new JTextField(10);
		searchAddressText.setVisible(true);
		searchAddressText.setActionCommand("searching");
		searchAddressText.addKeyListener(this); 
		searchAddressText.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				inSearchField = true; 
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				inSearchField = false; 
			}
        });
		

		
		JPanel searchPanel = new JPanel();
		searchPanel.add(searchAddressText);
		searchPanel.setBackground(Color.white);
		searchPanel.add(searchAddress);

		
		JPanel addAddressPanel = new JPanel();
		addAddressPanel.setBackground(Color.white);
		addAddressPanel.setBorder(BorderFactory.createTitledBorder("Add new address"));

		
		addAddressPanel.add(newFnameText);
		addAddressPanel.add(newLnameText);
		addAddressPanel.add(newAddressText);
		addAddressPanel.add(addAddress);

		addresses = new JPanel();
		addresses.setLayout(new BoxLayout(addresses, BoxLayout.Y_AXIS));
		addresses.add(searchPanel);
		addresses.add(addressBook);
		
		addresses.setBackground(Color.white);
		addresses.setBorder(BorderFactory.createTitledBorder("Address book"));

		frame.add(addresses, BorderLayout.NORTH);
		frame.add(addAddressPanel, BorderLayout.CENTER);	
		
		// Print out the address book
		printAddressBook("");
			
				
		
	}
	// Fetches the whole address book and
	public void printAddressBook(String searchText) {
		try {
			// Get all the rows
			ResultSet res = stt.executeQuery("SELECT * FROM people WHERE address LIKE '%" + searchText + "%' OR fname LIKE '%" + searchText + "%' OR lname LIKE '%" + searchText + "%'");
			addressBook.setText("<html>");
			
			while (res.next()) {
				addressBook.setText(addressBook.getText() + "<br>" + res.getString("fname") + " "
						+ res.getString("lname") + " " + res.getString("address"));
				
				
			}
			addressBook.setText(addressBook.getText() + "</html>");
			
			// Same as the last query but prep'd instead
			// PreparedStatement prep = con.prepareStatement("SELECT * FROM
			// people WHERE lname = ?");
			// prep.setString(1, "");

			// ResultSet res = prep.executeQuery()

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void freeResources() throws SQLException {
		// Free resources
		stt.close();
		con.close();
	}
	
	public void addNewAddress() {
		try {
			if (!(newFnameText.getText().equals("") || newLnameText.getText().equals("")
					|| newAddressText.getText().equals("")))
				stt.executeUpdate("INSERT INTO people VALUES (" + "NULL,'" + newFnameText.getText() + "','"
						+ newLnameText.getText() + "','" + newAddressText.getText() + "')");
			newFnameText.setText("");
			newLnameText.setText("");
			newAddressText.setText("");

			printAddressBook("");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void removeAddress(int id) {
		
		try {
			stt.executeUpdate("DELETE FROM people WHERE id = '" +  id + "'");

			printAddressBook("");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


	// Action listener for the buttons
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Search address")) {
			printAddressBook(searchAddressText.getText());
		}
		if (e.getActionCommand().equals("searching")) {
			printAddressBook(searchAddressText.getText());
		}
		if (e.getActionCommand().equals("Add address")) {
			addNewAddress();
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(inSearchField){
			printAddressBook(searchAddressText.getText());	
		}
	}

	
	
}
