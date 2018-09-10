package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalLookAndFeel;

@SuppressWarnings("serial")
public class LoginPage extends JFrame {
	
	Statement statement;
	ResultSet resultSet;
	Connection connection;
	
	static Container container;
	JLayeredPane layer;
	JPanel logPanel;
	JPanel botPanel;
	JPanel signPanel;
	
	JLabel errorLogin = new JLabel();
	JLabel errorSignUp = new JLabel();
	JLabel errorPassword = new JLabel();
	
	JLabel id;
	JLabel pswd;
	JLabel login;
	JLabel newUser;
	JTextField idText;
	JPasswordField pswdText;
	JButton logBtn;
	JButton createBtn;

	JLabel signUp;
	JLabel name;
	JLabel email;
	JLabel password;
	JLabel confpswd;
	JLabel phone;
	JLabel address;
	JLabel already;

	JTextField nameText;
	JTextField emailText;
	JPasswordField passwordText;
	JPasswordField confpswdText;
	JTextField phoneText;
	JTextField addressText;
	JButton signUpBtn;
	JButton signBtn;

	Font f = new Font("Arial", Font.PLAIN, 15);
	Font f1 = new Font("Tahoma", Font.BOLD, 14);
	
	public LoginPage() {
		super("ShopKart - an offline shopping portal");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/Shopkart","root","root");
		} catch(Exception e) { 
			e.printStackTrace();
		}
		initLoginPage();
		loginPage();
	}
	
	private void initLoginPage() {
		try {
			Image img = ImageIO.read(LoginPage.class.getResourceAsStream("/image/bag0.jpg"));
			setIconImage(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
		setSize(new Dimension(1366, 735));
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container = getContentPane();
		container.setBackground(new Color(0, 160, 230));
		container.setLayout(new BorderLayout());
		ImageIcon i = new ImageIcon(LoginPage.class.getResource("/image/store.jpg"));
		botPanel = new JPanel();
		JLabel l = new JLabel(i);
		botPanel.add(l, BorderLayout.CENTER);
		botPanel.setBounds(0, 0, 1366, 735);
		botPanel.setBackground(Color.WHITE);
		layer = new JLayeredPane();
		layer.setLayout(null);
		layer.add(botPanel, Integer.valueOf(1));
	}
	
	private void loginPage() {
		logPanel = new JPanel();
		logPanel.setBackground(new Color(0, 150, 200));
		logPanel.setBorder(new LineBorder(new Color(220, 220, 220), 10));
		logPanel.setLayout(null);

		login = new JLabel("Login");
		id = new JLabel("E-mail");
		pswd = new JLabel("Password");
		newUser = new JLabel("New user?");
		idText = new JTextField();
		pswdText = new JPasswordField("");
		logBtn = new JButton("Login");
		createBtn = new JButton("Create your account");

		id.setForeground(Color.white);
		pswd.setForeground(Color.white);
		newUser.setForeground(Color.white);
		logBtn.setFocusPainted(false);
		logBtn.setForeground(Color.BLACK);
		createBtn.setFocusPainted(false);
		createBtn.setForeground(Color.BLACK);
		login.setFont(new Font("Arial", Font.BOLD, 22));
		login.setForeground(Color.white);

		id.setFont(f1);
		pswd.setFont(f1);
		newUser.setFont(f1);

		logBtn.setFont(f);
		createBtn.setFont(f);

		Cursor cur = new Cursor(Cursor.HAND_CURSOR);
		logBtn.setCursor(cur);
		createBtn.setCursor(cur);
		
		errorLogin.setForeground(Color.RED);
		errorLogin.setFont(new Font("Arial", Font.PLAIN, 14));

		login.setBounds(25, 20, 150, 30);
		id.setBounds(20, 80, 150, 20);
		idText.setBounds(20, 105, 200, 25);
		pswd.setBounds(20, 140, 200, 20);
		pswdText.setBounds(20, 165, 200, 25);
		logBtn.setBounds(70, 210, 100, 25);
		errorLogin.setBounds(30, 240, 200, 20);
		newUser.setBounds(80, 260, 100, 20);
		createBtn.setBounds(20, 290, 200, 25);
		
		logPanel.add(login);
		logPanel.add(id);
		logPanel.add(idText);
		logPanel.add(pswd);
		logPanel.add(pswdText);
		logPanel.add(logBtn);
		logPanel.add(errorLogin);
		logPanel.add(createBtn);
		
		logPanel.add(newUser);
		logPanel.add(createBtn);
		logPanel.setBounds(getWidth() / 2 - 125, 200, 250, 350);
		layer.add(logPanel, Integer.valueOf(2));
		add(layer);

		createBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layer.remove(logPanel);
				pack();
				setSize(1366, 735);
				signUpPanel();
			}
		});
		
		logBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String query="select * from customer";
					String emailVer = new String(idText.getText());
					String passVer = new String(pswdText.getPassword());
					statement=connection.createStatement();
					resultSet=statement.executeQuery(query);
					resultSet.next();
					do
					{
						String email = resultSet.getString("Email");
						String password = resultSet.getString("Password");
						if(email.equals(null) || password.equals(null)) {
							errorLogin.setText("Incorrect email or password!");
						}
						else if(emailVer.equals(email) && passVer.equals(password)) {
							UIManager.setLookAndFeel(new MetalLookAndFeel());
							String name = resultSet.getString("name");
							dispose();
							new FrontPage(name, email).setVisible(true);
							statement.close();
							connection.close();
						}
					}while(resultSet.next());
					errorLogin.setText("Incorrect email or password!");
				} catch (Exception ex) {
					errorLogin.setText("Incorrect email or password!");
				}
			}
		});
	}
	
	protected void signUpPanel() {
		signPanel = new JPanel();
		signPanel.setBackground(new Color(0, 150, 200));
		signPanel.setBorder(new LineBorder(new Color(220, 220, 220), 10));
		signPanel.setPreferredSize(new Dimension(250, 600));
		signPanel.setLayout(null);

		signUp = new JLabel("Sign up");
		name = new JLabel("Name");
		email = new JLabel("E-mail");
		password = new JLabel("Enter password");
		confpswd = new JLabel("Confirm password");
		phone = new JLabel("Phone");
		address = new JLabel("Address");
		already = new JLabel("Already a customer?");

		signUp.setForeground(Color.WHITE);
		name.setForeground(Color.WHITE);
		email.setForeground(Color.WHITE);
		password.setForeground(Color.WHITE);
		confpswd.setForeground(Color.WHITE);
		phone.setForeground(Color.WHITE);
		address.setForeground(Color.WHITE);
		already.setForeground(Color.WHITE);

		nameText = new JTextField();
		emailText = new JTextField();
		passwordText = new JPasswordField("");
		confpswdText = new JPasswordField("");
		phoneText = new JTextField();
		addressText = new JTextField();
		signUpBtn = new JButton("Sign up");
		signBtn = new JButton("Sign in");

		signUpBtn.setForeground(Color.BLACK);
		signBtn.setForeground(Color.BLACK);
		
		signBtn.setFocusPainted(false);
		signUpBtn.setFocusPainted(false);

		signUp.setFont(new Font("Arial", Font.BOLD, 22));

		name.setFont(f1);
		email.setFont(f1);
		password.setFont(f1);
		confpswd.setFont(f1);
		phone.setFont(f1);
		address.setFont(f1);
		already.setFont(f1);

		signUpBtn.setFont(f);
		signBtn.setFont(f);

		Cursor cur = new Cursor(Cursor.HAND_CURSOR);
		signUpBtn.setCursor(cur);
		signBtn.setCursor(cur);
		
		errorSignUp.setForeground(Color.RED);
		errorSignUp.setFont(new Font("Arial", Font.PLAIN, 14));

		errorPassword.setForeground(Color.RED);
		errorPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		
		signUp.setBounds(50, 20, 150, 30);
		name.setBounds(50, 80, 150, 20);
		nameText.setBounds(50, 105, 200, 25);
		email.setBounds(50, 140, 200, 20);
		emailText.setBounds(50, 165, 200, 25);
		password.setBounds(50, 200, 200, 20);
		passwordText.setBounds(50, 225, 200, 25);
		confpswd.setBounds(50, 260, 200, 20);
		confpswdText.setBounds(50, 285, 200, 25);
		phone.setBounds(50, 320, 200, 20);
		phoneText.setBounds(50, 345, 200, 25);
		address.setBounds(50, 380, 200, 20);
		addressText.setBounds(50, 405, 200, 25);
		signUpBtn.setBounds(100, 450, 100, 25);
		errorSignUp.setBounds(80, 480, 250, 20);
		errorPassword.setBounds(30, 480, 250, 20);
		already.setBounds(80, 510, 150, 20);
		signBtn.setBounds(50, 540, 200, 25);

		signPanel.add(signUp);
		signPanel.add(name);
		signPanel.add(nameText);
		signPanel.add(email);
		signPanel.add(emailText);
		signPanel.add(password);
		signPanel.add(passwordText);
		signPanel.add(confpswd);
		signPanel.add(confpswdText);
		signPanel.add(phone);
		signPanel.add(phoneText);
		signPanel.add(address);
		signPanel.add(addressText);
		signPanel.add(signUpBtn);
		signPanel.add(errorSignUp);
		signPanel.add(errorPassword);
		signPanel.add(already);
		signPanel.add(signBtn);

		signPanel.setBounds(getWidth() / 2 - 125, 50, 300, 600);
		layer.add(signPanel, Integer.valueOf(2));
		add(layer);

		signBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginPage().setVisible(true);
			}
		});
		
		signUpBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String nameSet = new String(nameText.getText());
					String emailSet = new String(emailText.getText());
					String passSet = new String(passwordText.getPassword());
					String confSet = new String(confpswdText.getPassword());
					String phoneSet = new String(phoneText.getText());
					String addressSet = new String(addressText.getText());
					String query="Insert into customer(Email, Password, Name, Address, Phone) values ('"+emailSet+"', '"+passSet+"', '"+nameSet+"', '"+addressSet+"', '"+phoneSet+"')";
					statement=connection.createStatement();
					if(!nameSet.isEmpty() && !emailSet.isEmpty() && !passSet.isEmpty() && !phoneSet.isEmpty() && !addressSet.isEmpty()) {
						if(emailSet.contains("@")) {
							if(passSet.equals(confSet)) {
								if(passSet.length() >= 8) {
									statement.executeUpdate(query);
									UIManager.setLookAndFeel(new MetalLookAndFeel());
									dispose();
									new FrontPage(nameSet, emailSet).setVisible(true);
								} else {
									errorSignUp.setText("");
									errorPassword.setText("Password should be atleast 8 characters!");
								}
							} else {
								errorPassword.setText("");
								errorSignUp.setText("Password does not match!");
							}
						} else {
							errorPassword.setText("");
							errorSignUp.setText("E-mail format not correct!");
						}
					} else {
						errorPassword.setText("");
						errorSignUp.setText("Fields cannot be empty!");
					}
					String query0 = "select count(*) from cart";
					String query1 = "select cart_id from cart";
					statement = connection.createStatement();
					resultSet = statement.executeQuery(query0);
					resultSet.next();
					int count = resultSet.getInt(1);
					
					resultSet = statement.executeQuery(query1);
					resultSet.next();
					for(int i=0; i<count-1; i++) {
						resultSet.next();
					};
					String cart_id = resultSet.getString("cart_id");
					String query2 = "update customer set cart_id = '"+cart_id+"' where email = '"+emailSet+"'";
					statement.executeUpdate(query2);
				} catch (Exception ex) { }
			}
		});
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		new LoginPage().setVisible(true);
	}
}