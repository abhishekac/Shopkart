package login;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.Enumeration;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class FrontPage extends JFrame {

	String profileName;
	String email;
	
	static Container con;
	static Menu menu;
	static JPanel panel;
	
	JPanel sidePanel;
	JLayeredPane layer;
	
	ImageIcon menuLogo;
	ImageIcon logoImage;
	JButton logo;
	JCheckBox menuBtn;
	JLabel categories;
	JLabel profile;
	JButton home;
	ImageIcon cartLogo;
	JButton cart;
	JButton signOut;
	
	JPanel item[] = new JPanel[16];
	ImageIcon iconLogo[] = new ImageIcon[16];
	JButton icon[] = new JButton[16];

	ImageIcon productLogo[] = new ImageIcon[16];
	JLabel product[] = new JLabel[16];
	JLabel productName[] = new JLabel[16];
	
	ImageIcon cartProdLogo[] = new ImageIcon[16];
	JLabel cartProd[] = new JLabel[16];

	String itemName[] = new String[16];
	String prodName[] = new String[16];
	
	JButton name[] = new JButton[16];
	JButton addCart[] = new JButton[16];
	JButton buyNow[] = new JButton[16];
	JLabel price[] = new JLabel[16];
	JLabel quantity[] = new JLabel[16];
	JTextArea description[] = new JTextArea[16];
	
	ActionListener productPage;
	ActionListener cartPage;

	Statement st;
	ResultSet rs;
	Connection conn;

	FrontPage(String profileName, String email) {
		super("Shopkart - an offline shopping portal");
		this.profileName = profileName;
		this.email = email;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Shopkart", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		init();
		setFrontPage();
	}

	private void init() {
		try {
			Image img = ImageIO.read(new File(FrontPage.class.getResource("/image/bag0.jpg").toURI()));
			setIconImage(img);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		setSize(new Dimension(1366, 735));
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		con = getContentPane();
		con.setBackground(Color.WHITE);
		con.setLayout(new BorderLayout());

		layer = new JLayeredPane();
		layer.setLayout(null);
		panel = new JPanel();

		prodName[0] = new String("iPhone X");
		prodName[1] = new String("Beats Studio Over Ear Headphone");
		prodName[2] = new String("Canon DSLR");
		prodName[3] = new String("JBL - GO Wireless Speaker");
		prodName[4] = new String("Nike Running Shoe");
		prodName[5] = new String("Rolex");
		prodName[6] = new String("Louis Philippe Suit");
		prodName[7] = new String("HP Odyssey Laptop Bag");
		prodName[8] = new String("Bosch Washing Machine");
		prodName[9] = new String("Samsung Refrigerator");
		prodName[10] = new String("Philips Mixer");
		prodName[11] = new String("Morphy Richard Toaoster");
		prodName[12] = new String("Complete Reference Java");
		prodName[13] = new String("Complete Reference J2EE");
		prodName[14] = new String("HeadFirst Android");
		prodName[15] = new String("PHP and mySQL");

		itemName[0] = new String("<html><u>iPhone X</u></html>");
		itemName[1] = new String("<html><u>Beats Studio Over Ear Headphone</u></html>");
		itemName[2] = new String("<html><u>Canon DSLR</u></html>");
		itemName[3] = new String("<html><u>JBL - GO Wireless Speaker</u></html>");
		itemName[4] = new String("<html><u>Nike Running Shoe</u></html>");
		itemName[5] = new String("<html><u>Rolex</u></html>");
		itemName[6] = new String("<html><u>Louis Philippe Suit</u></html>");
		itemName[7] = new String("<html><u>HP Odyssey Laptop Bag</u></html>");
		itemName[8] = new String("<html><u>Bosch Washing Machine</u></html>");
		itemName[9] = new String("<html><u>Samsung Refrigerator</u></html>");
		itemName[10] = new String("<html><u>Philips Mixer</u></html>");
		itemName[11] = new String("<html><u>Morphy Richard Toaoster</u></html>");
		itemName[12] = new String("<html><u>Complete Reference Java</u></html>");
		itemName[13] = new String("<html><u>Complete Reference J2EE</u></html>");
		itemName[14] = new String("<html><u>HeadFirst Android</u></html>");
		itemName[15] = new String("<html><u>PHP and mySQL</u></html>");

		iconLogo[0] = new ImageIcon(FrontPage.class.getResource("/image/iphone.jpg"));
		iconLogo[1] = new ImageIcon(FrontPage.class.getResource("/image/head.png"));
		iconLogo[2] = new ImageIcon(FrontPage.class.getResource("/image/canon0.jpg"));
		iconLogo[3] = new ImageIcon(FrontPage.class.getResource("/image/jbl0.jpg"));
		iconLogo[4] = new ImageIcon(FrontPage.class.getResource("/image/nike0.jpg"));
		iconLogo[5] = new ImageIcon(FrontPage.class.getResource("/image/rolex.jpg"));
		iconLogo[6] = new ImageIcon(FrontPage.class.getResource("/image/louis0.jpg"));
		iconLogo[7] = new ImageIcon(FrontPage.class.getResource("/image/hpbag0.jpg"));
		iconLogo[8] = new ImageIcon(FrontPage.class.getResource("/image/bosch0.jpg"));
		iconLogo[9] = new ImageIcon(FrontPage.class.getResource("/image/samsung0.jpg"));
		iconLogo[10] = new ImageIcon(FrontPage.class.getResource("/image/mixer0.jpg"));
		iconLogo[11] = new ImageIcon(FrontPage.class.getResource("/image/morphy0.jpg"));
		iconLogo[12] = new ImageIcon(FrontPage.class.getResource("/image/java0.jpg"));
		iconLogo[13] = new ImageIcon(FrontPage.class.getResource("/image/j2ee0.jpg"));
		iconLogo[14] = new ImageIcon(FrontPage.class.getResource("/image/headfirst0.jpg"));
		iconLogo[15] = new ImageIcon(FrontPage.class.getResource("/image/php0.jpg"));

		productLogo[0] = new ImageIcon(FrontPage.class.getResource("/image/iphone2.jpg"));
		productLogo[1] = new ImageIcon(FrontPage.class.getResource("/image/head1.png"));
		productLogo[2] = new ImageIcon(FrontPage.class.getResource("/image/canon1.jpg"));
		productLogo[3] = new ImageIcon(FrontPage.class.getResource("/image/jbl1.jpg"));
		productLogo[4] = new ImageIcon(FrontPage.class.getResource("/image/nike.jpg"));
		productLogo[5] = new ImageIcon(FrontPage.class.getResource("/image/rolex1.jpg"));
		productLogo[6] = new ImageIcon(FrontPage.class.getResource("/image/louis1.jpg"));
		productLogo[7] = new ImageIcon(FrontPage.class.getResource("/image/hpbag1.jpg"));
		productLogo[8] = new ImageIcon(FrontPage.class.getResource("/image/bosch1.jpg"));
		productLogo[9] = new ImageIcon(FrontPage.class.getResource("/image/samsung1.jpg"));
		productLogo[10] = new ImageIcon(FrontPage.class.getResource("/image/mixer1.jpg"));
		productLogo[11] = new ImageIcon(FrontPage.class.getResource("/image/morphy1.jpg"));
		productLogo[12] = new ImageIcon(FrontPage.class.getResource("/image/java1.jpg"));
		productLogo[13] = new ImageIcon(FrontPage.class.getResource("/image/j2ee1.jpg"));
		productLogo[14] = new ImageIcon(FrontPage.class.getResource("/image/headfirst1.jpg"));
		productLogo[15] = new ImageIcon(FrontPage.class.getResource("/image/php1.jpg"));

		cartProdLogo[0] = new ImageIcon(FrontPage.class.getResource("/image/iphone4.jpg"));
		cartProdLogo[1] = new ImageIcon(FrontPage.class.getResource("/image/head4.png"));
		cartProdLogo[2] = new ImageIcon(FrontPage.class.getResource("/image/canon4.jpg"));
		cartProdLogo[3] = new ImageIcon(FrontPage.class.getResource("/image/jbl4.jpg"));
		cartProdLogo[4] = new ImageIcon(FrontPage.class.getResource("/image/nike4.jpg"));
		cartProdLogo[5] = new ImageIcon(FrontPage.class.getResource("/image/rolex4.jpg"));
		cartProdLogo[6] = new ImageIcon(FrontPage.class.getResource("/image/louis4.jpg"));
		cartProdLogo[7] = new ImageIcon(FrontPage.class.getResource("/image/hpbag4.jpg"));
		cartProdLogo[8] = new ImageIcon(FrontPage.class.getResource("/image/bosch4.jpg"));
		cartProdLogo[9] = new ImageIcon(FrontPage.class.getResource("/image/samsung4.jpg"));
		cartProdLogo[10] = new ImageIcon(FrontPage.class.getResource("/image/mixer4.jpg"));
		cartProdLogo[11] = new ImageIcon(FrontPage.class.getResource("/image/morphy4.jpg"));
		cartProdLogo[12] = new ImageIcon(FrontPage.class.getResource("/image/java4.jpg"));
		cartProdLogo[13] = new ImageIcon(FrontPage.class.getResource("/image/j2ee4.jpg"));
		cartProdLogo[14] = new ImageIcon(FrontPage.class.getResource("/image/headfirst4.jpg"));
		cartProdLogo[15] = new ImageIcon(FrontPage.class.getResource("/image/php4.jpg"));

		for (int i = 0; i < 16; i++) {
			product[i] = new JLabel(productLogo[i]);
			productName[i] = new JLabel(itemName[i]);
			icon[i] = new JButton(iconLogo[i]);
			name[i] = new JButton(itemName[i]);
			cartProd[i] = new JLabel(cartProdLogo[i]);
		}

		for (int i = 0; i < 16; i++) {
			addCart[i] = new JButton("Add to Cart");
			buyNow[i] = new JButton("Buy Now");
			buyNow[i].addActionListener(cartPage);
		}
		
		MouseAdapter mouse = new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
				JButton but = (JButton) e.getSource();
				but.setForeground(Color.WHITE);
			}

			public void mouseEntered(MouseEvent e) {
				JButton but = (JButton) e.getSource();
				but.setForeground(Color.YELLOW);
			}

		};

		productPage = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (menuBtn.isSelected()) {
					layer.remove(sidePanel);
				}
				int i;
				for (i = 0; i < 16;) {
					if (e.getSource() == name[i] || e.getSource() == icon[i]) {
						break;
					} else {
						i++;
					}
				}
				if (i < 16) {
					setProductPage(i);
				}
			}
		};

		cartPage = new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				if (menuBtn.isSelected()) {
					layer.remove(sidePanel);
				}
				int i;
				for (i = 0; i < 16;) {
					if (e.getSource() == buyNow[i]) {
						break;
					} else {
						i++;
					}
				}
				int quantity = 0;
				try {
					String query = "select quantity from product where prod_id = '" + i + "'";
					st = conn.createStatement();
					rs = st.executeQuery(query);
					rs.next();
					quantity = rs.getInt(1);

					if (quantity > 0) {
						buyNow[i].enable();
					} else {
						buyNow[i].disable();
					}
				} catch (Exception f) { }
				if (i < 16 && quantity > 0) {
					try {
						String query0 = "select cart_id from customer where name = '" + profileName + "'";
						st = conn.createStatement();
						rs = st.executeQuery(query0);
						rs.next();
						int cart_id = rs.getInt(1);
						String query1 = "select prod_id from product where prod_name = '" + prodName[i] + "'";
						rs = st.executeQuery(query1);
						rs.next();
						int prod_id = rs.getInt(1);
						String query = "insert into cart_products values ('" + cart_id + "', '" + prod_id + "')";
						st.executeUpdate(query);
					} catch (Exception f) { }
					setCartPage();
				}
			}
		};

		menu = new Menu();
		menu.setBorder(new LineBorder(Color.GRAY));

		menuLogo = new ImageIcon(FrontPage.class.getResource("/image/menu0.png"));
		menuBtn = new JCheckBox(menuLogo);
		menuBtn.setFocusPainted(false);
		menuBtn.setBorder(null);

		menuBtn.addItemListener(new ItemListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void itemStateChanged(ItemEvent e) {
				JCheckBox cb = (JCheckBox) e.getItem();
				if (cb.isSelected()) {
					sidePanel = new JPanel();
					sidePanel.setLayout(null);
					sidePanel.setBounds(0, 0, 300, getHeight());
					sidePanel.setBackground(new Color(0, 50, 130));
					sidePanel.requestFocus();

					JLabel shop = new JLabel("SHOP BY CATEGORY");
					JButton elec = new JButton("Electronics");
					JButton life = new JButton("Lifestyle");
					JButton appl = new JButton("Appliances");
					JButton books = new JButton("Books & Stationaries");

					shop.setForeground(Color.WHITE);
					elec.setForeground(Color.WHITE);
					life.setForeground(Color.WHITE);
					appl.setForeground(Color.WHITE);
					books.setForeground(Color.WHITE);

					elec.setFocusPainted(false);
					elec.setBorder(null);
					elec.setBackground(new Color(0, 50, 130));
					life.setFocusPainted(false);
					life.setBorder(null);
					life.setBackground(new Color(0, 50, 130));
					appl.setFocusPainted(false);
					appl.setBorder(null);
					appl.setBackground(new Color(0, 50, 130));
					books.setFocusPainted(false);
					books.setBorder(null);
					books.setBackground(new Color(0, 50, 130));

					Font f2 = new Font("Arial", Font.PLAIN, 18);

					shop.setFont(new Font("Arial", Font.BOLD, 16));
					elec.setFont(f2);
					life.setFont(f2);
					appl.setFont(f2);
					books.setFont(f2);

					shop.setBounds(30, 100, 200, 20);
					elec.setBounds(40, 150, 100, 25);
					elec.setAlignmentX(LEFT_ALIGNMENT);
					life.setBounds(40, 200, 80, 25);
					life.setAlignmentX(LEFT_ALIGNMENT);
					appl.setBounds(40, 250, 100, 25);
					appl.setAlignmentX(LEFT_ALIGNMENT);
					books.setBounds(40, 300, 180, 25);
					books.setAlignmentX(LEFT_ALIGNMENT);

					elec.addMouseListener(mouse);
					life.addMouseListener(mouse);
					appl.addMouseListener(mouse);
					books.addMouseListener(mouse);

					elec.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							panel.removeAll();
							panel.setLayout(new GridLayout(2, 2, 20, 20));
							categories.setText("  ELECTRONICS  ");

							for (int i = 0; i < 4; i++) {
								panel.add(item[i]);
							}
							layer.remove(sidePanel);
							pack();
							setSize(1366, 735);
						}
					});

					life.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							panel.removeAll();
							panel.setLayout(new GridLayout(2, 2, 20, 20));
							categories.setText("  LIFESTYLE  ");
							for (int i = 4; i < 8; i++) {
								panel.add(item[i]);
							}
							layer.remove(sidePanel);
							pack();
							setSize(1366, 735);
						}
					});

					appl.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							panel.removeAll();
							panel.setLayout(new GridLayout(2, 2, 20, 20));
							categories.setText("  APPLIANCES  ");
							for (int i = 8; i < 12; i++) {
								panel.add(item[i]);
							}
							layer.remove(sidePanel);
							pack();
							setSize(1366, 735);
						}
					});

					books.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							panel.removeAll();
							panel.setLayout(new GridLayout(2, 2, 20, 20));
							categories.setText("  BOOKS & STATIONARIES  ");
							for (int i = 12; i < 16; i++) {
								panel.add(item[i]);
							}
							layer.remove(sidePanel);
							pack();
							setSize(1366, 735);
						}
					});

					sidePanel.add(shop);
					sidePanel.add(elec);
					sidePanel.add(life);
					sidePanel.add(appl);
					sidePanel.add(books);
					layer.add(sidePanel, Integer.valueOf(2));
				} else {
					panel.enable();
					layer.remove(sidePanel);
					pack();
					setSize(1366, 735);
				}
			}
		});

		Font f1 = new Font("Arial", Font.BOLD, 16);

		logoImage = new ImageIcon(FrontPage.class.getResource("/image/bag3.png"));
		logo = new JButton(logoImage);
		logo.setFocusPainted(false);
		logo.setBorder(null);

		profile = new JLabel("  Hi, " + profileName + "  ");
		profile.setBorder(null);
		profile.setBackground(new Color(0, 150, 200));
		profile.setForeground(Color.DARK_GRAY);
		profile.setFont(f1);

		home = new JButton("    HOME    ");
		home.setFocusPainted(false);
		home.setBorder(null);
		home.setBackground(new Color(0, 150, 200));
		home.setForeground(Color.WHITE);
		home.setFont(f1);
		home.addMouseListener(mouse);

		home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new FrontPage(profileName, email).setVisible(true);
			}
		});

		categories = new JLabel("  ALL CATEGORIES  ");
		categories.setBorder(null);
		categories.setBackground(new Color(0, 150, 200));
		categories.setForeground(Color.LIGHT_GRAY);
		categories.setFont(f1);

		signOut = new JButton("  Sign Out  ");
		signOut.setFocusPainted(false);
		signOut.setBorder(null);
		signOut.setBackground(new Color(0, 150, 200));
		signOut.setForeground(Color.WHITE);
		signOut.setFont(f1);
		signOut.addMouseListener(mouse);

		signOut.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginPage().main(null);
				;
			}
		});

		cartLogo = new ImageIcon(FrontPage.class.getResource("/image/cart1.png"));
		Integer count = 0;
		int cart_id = 0;
		try {
			String query = "select cart_id from customer where email = '" + email + "'";
			st = conn.createStatement();
			rs = st.executeQuery(query);
			rs.next();
			cart_id = rs.getInt(1);
			String query1 = "select count(*) from cart_products where cart_id = '" + cart_id + "'";
			rs = st.executeQuery(query1);
			rs.next();
			count = rs.getInt(1);
		} catch (Exception e) { }
		cart = new JButton(count.toString() + "  ", cartLogo);
		cart.setFocusPainted(false);
		cart.setSize(50, 50);
		cart.setBackground(new Color(0, 150, 200));
		cart.setBorder(null);
		cart.setForeground(Color.YELLOW);
		cart.setFont(new Font("Arial", Font.BOLD, 24));

		cart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (menuBtn.isSelected()) {
					layer.remove(sidePanel);
				}
				setCartPage();
			}
		});

		menu.add(menuBtn);
		menu.add(logo);
		menu.add(home);
		menu.add(categories);
		menu.add(Box.createHorizontalGlue());
		menu.add(profile);
		menu.add(signOut);
		menu.add(cart);
		setJMenuBar(menu);
		
		MouseAdapter m = new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
				JButton but = (JButton) e.getSource();
				but.setForeground(Color.BLACK);
				but.setBackground(Color.LIGHT_GRAY);
			}

			public void mouseEntered(MouseEvent e) {
				JButton but = (JButton) e.getSource();
				but.setForeground(Color.RED);
				but.setBackground(Color.LIGHT_GRAY);
			}
		};
		
		MouseAdapter m1 = new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
				JPanel p = (JPanel) e.getSource();
				p.setBorder(null);
			}

			public void mouseEntered(MouseEvent e) {
				JPanel p = (JPanel) e.getSource();
				p.setBorder(new LineBorder(Color.LIGHT_GRAY));
			}
		};

		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		Font f = new Font("Times New Roman", Font.BOLD, 20);
		Font f0 = new Font("Tahoma", Font.PLAIN, 16);
		
		String query = "select Price, Quantity, Prod_Desc from product";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			rs.next();
			for (int i = 0; i < 16; i++) {
				int q = rs.getInt("Quantity");
				price[i] = new JLabel("Price : " + rs.getString("Price"));
				description[i] = new JTextArea(rs.getString("Prod_Desc"));
				description[i].setRows(6);
				description[i].setLineWrap(true);
				description[i].setWrapStyleWord(true);
				if (q > 0) {
					quantity[i] = new JLabel("Quantity : " + q);
					quantity[i].setForeground(Color.BLACK);
				} else {
					quantity[i] = new JLabel("OUT OF STOCK");
					quantity[i].setForeground(Color.RED);
				}
				rs.next();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		for (int i = 0; i < 16; i++) {
			item[i] = new JPanel();
			item[i].setLayout(null);
			item[i].setBackground(Color.WHITE);

			icon[i].setBounds(20, 40, 250, 250);
			icon[i].setBackground(Color.WHITE);
			icon[i].setBorder(null);
			icon[i].setFocusPainted(false);
			icon[i].setCursor(cursor);
			icon[i].addActionListener(productPage);

			name[i].setBackground(Color.LIGHT_GRAY);
			name[i].setForeground(Color.BLACK);
			name[i].setFocusPainted(false);
			name[i].setBounds(300, 60, 300, 40);
			name[i].setFont(f);
			name[i].setBorder(null);
			name[i].addMouseListener(m);
			name[i].setCursor(cursor);
			name[i].addActionListener(productPage);

			description[i].setFont(f0);
			price[i].setBounds(300, 150, 200, 20);
			price[i].setFont(f0);
			quantity[i].setFont(f0);
			quantity[i].setBounds(300, 200, 200, 20);

			item[i].add(icon[i]);
			item[i].add(name[i]);
			item[i].add(price[i]);
			item[i].add(quantity[i]);
			item[i].addMouseListener(m1);
		}
	}

	private void setFrontPage() {
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(2, 2, 20, 20));
		for (int i = 2; i < 16; i = i + 4) {
			panel.add(item[i]);
		}
		layer.setLayout(new GridLayout());
		panel.setBounds(0, 0, 1366, 735);
		JScrollPane jsp = new JScrollPane(panel);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		layer.add(jsp, Integer.valueOf(1));
		con.add(layer);
	}

	@SuppressWarnings("deprecation")
	private void setProductPage(int i) {
		panel.removeAll();
		pack();
		setSize(1366, 735);
		panel.setLayout(null);
		
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		Font f = new Font("Times New Roman", Font.BOLD, 20);

		productName[i].setFont(new Font("Tahoma", Font.BOLD, 20));
		addCart[i].setFocusPainted(false);
		addCart[i].setBackground(Color.WHITE);
		addCart[i].setForeground(Color.BLACK);
		addCart[i].setFont(f);
		addCart[i].setCursor(cursor);

		buyNow[i].setFocusPainted(false);
		buyNow[i].setBackground(Color.RED);
		buyNow[i].setForeground(Color.WHITE);
		buyNow[i].setFont(f);
		buyNow[i].setCursor(cursor);

		product[i].setBounds(30, 30, 550, 600);
		productName[i].setBounds(750, 80, 400, 30);
		description[i].setBounds(750, 140, 400, 100);
		price[i].setBounds(750, 300, 300, 30);
		quantity[i].setBounds(750, 360, 200, 30);
		addCart[i].setBounds(800, 550, 200, 40);
		buyNow[i].setBounds(1050, 550, 200, 40);
		buyNow[i].addActionListener(cartPage);

		addCart[i].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (menuBtn.isSelected()) {
					layer.remove(sidePanel);
				}
				
				int quantity = 0;
				try {
					String query = "select quantity from product where prod_id = '" + i + "'";
					st = conn.createStatement();
					rs = st.executeQuery(query);
					rs.next();
					quantity = rs.getInt(1);

					if (quantity > 0) {
						addCart[i].disable();

					} else {
						addCart[i].disable();
					}
				} catch (Exception f) { }

				if (i < 16 && quantity > 0) {
					try {
						String query0 = "select cart_id from customer where name = '" + profileName + "'";
						st = conn.createStatement();
						rs = st.executeQuery(query0);
						rs.next();
						int cart_id = rs.getInt(1);
						String query1 = "select prod_id from product where prod_name = '" + prodName[i] + "'";
						rs = st.executeQuery(query1);
						rs.next();
						int prod_id = rs.getInt(1);
						String query = "insert into cart_products values ('" + cart_id + "', '" + prod_id + "')";
						st.executeUpdate(query);
					} catch (Exception f) { }
					setProductPage(i);
				}
			}
		});

		panel.add(product[i]);
		panel.add(productName[i]);
		panel.add(description[i]);
		panel.add(price[i]);
		panel.add(quantity[i]);
		panel.add(addCart[i]);
		panel.add(buyNow[i]);

		try {
			String query = "select quantity from product where prod_id = '" + i + "'";
			st = conn.createStatement();
			rs = st.executeQuery(query);
			rs.next();
			
			int quantity = rs.getInt(1);
			if (quantity > 0) {
				buyNow[i].enable();
				buyNow[i].setBackground(Color.RED);
				buyNow[i].setText("Buy Now");
				addCart[i].enable();
				addCart[i].setText("Add to Cart");
			} else {
				buyNow[i].disable();
				buyNow[i].setBackground(Color.GRAY);
				buyNow[i].setText("Out of Stock");
				addCart[i].enable();
				addCart[i].setText("Out of Stock");
			}
		} catch (Exception e) { }
		int cart_id;
		Integer count = 0;
		try {
			String query = "select cart_id from customer where email = '" + email + "'";
			st = conn.createStatement();
			rs = st.executeQuery(query);
			rs.next();
			cart_id = rs.getInt(1);
			String query1 = "select count(*) from cart_products where cart_id = '" + cart_id + "'";
			rs = st.executeQuery(query1);
			rs.next();
			count = rs.getInt(1);
		} catch (Exception e) { }
		cart.setText(count.toString() + "  ");
	}

	private void setCartPage() {
		panel.removeAll();
		pack();
		setSize(1366, 735);
		panel.setLayout(null);
		categories.setText(" MY CART ");

		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		Font f = new Font("Times New Roman", Font.BOLD, 20);
		Font f0 = new Font("Tahoma", Font.PLAIN, 16);

		int cart_id = 0;
		int count = 0;

		JLabel cartLabel = new JLabel("Cart items");
		cartLabel.setFont(new Font("Arial", Font.BOLD, 30));
		cartLabel.setBounds(50, 0, 200, 50);

		JLabel emptyCart = new JLabel("Your Shopping Cart is empty.");
		JTextArea cartDesc = new JTextArea(
				"Your Shopping Cart lives to serve. Give it purpose -- fill it with books, electronics, and more.");
		emptyCart.setFont(new Font("Arial", Font.BOLD, 50));
		JButton shopNow = new JButton("Shop Now");
		shopNow.setFocusPainted(false);
		shopNow.setFont(f);
		shopNow.setBackground(new Color(0, 150, 200));
		shopNow.setForeground(Color.WHITE);
		shopNow.setCursor(cursor);
		
		ImageIcon cImage = new ImageIcon(FrontPage.class.getResource("/image/emptyCart.jpg"));
		JLabel cartImage = new JLabel(cImage);

		emptyCart.setBounds(50, 5, 800, 100);
		cartDesc.setBounds(65, 150, 600, 100);
		cartDesc.setRows(4);
		cartDesc.setLineWrap(true);
		cartDesc.setWrapStyleWord(true);
		cartDesc.setFont(f);
		shopNow.setBounds(100, 300, 150, 45);
		cartImage.setBounds(650, 100, 550, 550);

		JPanel productList = new JPanel();
		productList.setBounds(30, 60, 750, 350);
		productList.setLayout(new FlowLayout(FlowLayout.LEFT, 80, 30));
		productList.setBackground(Color.WHITE);
		
		ImageIcon fullCartLogo = new ImageIcon(("/image/fullcart1.jpg"));
		JLabel fullCart = new JLabel(fullCartLogo);
		fullCart.setBounds(800, 120, 400, 400);

		JButton checkout = new JButton("Checkout");
		checkout.setFocusPainted(false);
		checkout.setBackground(Color.RED);
		checkout.setForeground(Color.WHITE);
		checkout.setFont(f);
		checkout.setCursor(cursor);
		checkout.setBounds(1050, 550, 200, 40);

		shopNow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new FrontPage(profileName, email).setVisible(true);
			}
		});

		JLabel total = new JLabel("Total Amount:");
		total.setFont(f);
		total.setBounds(50, 450, 200, 30);

		JLabel amount = new JLabel();
		amount.setFont(f);
		amount.setBounds(50, 480, 100, 30);

		JLabel modePayment = new JLabel("Payment Mode:");
		modePayment.setFont(f);
		modePayment.setBounds(400, 450, 200, 30);

		JRadioButton cod = new JRadioButton("Cash on Delivery");
		JRadioButton credit = new JRadioButton("Credit Card");
		JRadioButton debit = new JRadioButton("Debit Card");
		JRadioButton net = new JRadioButton("Net Banking");
		cod.setSelected(true);

		ButtonGroup bg = new ButtonGroup();
		bg.add(cod);
		bg.add(credit);
		bg.add(debit);
		bg.add(net);

		checkout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String modePay = "Cash on Delivery";
				for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();) {
					AbstractButton button = buttons.nextElement();
					if (button.isSelected()) {
						modePay = button.getText();
					}
				}
				setTransactionPage(modePay);
			}
		});

		cod.setBounds(400, 490, 200, 20);
		credit.setBounds(400, 515, 200, 20);
		debit.setBounds(400, 540, 200, 20);
		net.setBounds(400, 565, 200, 20);

		JLabel j[] = new JLabel[16];
		JButton delete[] = new JButton[16];
		for (int i = 0; i < 16; i++) {
			j[i] = new JLabel();
			j[i].setFont(f0);
			delete[i] = new JButton("Delete");
			delete[i].setFocusPainted(false);
			delete[i].setBackground(new Color(0, 150, 200));
			delete[i].setForeground(Color.WHITE);
			delete[i].setFont(f0);
			delete[i].setCursor(cursor);
		}

		try {
			String query0 = "select cart_id from customer where name = '" + profileName + "'";
			st = conn.createStatement();
			rs = st.executeQuery(query0);
			rs.next();
			cart_id = rs.getInt(1);
			String query1 = "select count(*) from cart_products where cart_id = '" + cart_id + "'";
			rs = st.executeQuery(query1);
			rs.next();
			count = rs.getInt(1);
		} catch (Exception e) { }

		Integer count1 = count;
		cart.setText(count1.toString() + "  ");
		if (count == 0) {
			panel.removeAll();
			pack();
			setSize(1366, 735);
			panel.add(emptyCart);
			panel.add(cartDesc);
			panel.add(shopNow);
			panel.add(cartImage);
		} else {
			panel.removeAll();
			pack();
			setSize(1366, 735);
			panel.add(cartLabel);
			panel.add(productList);
			panel.add(fullCart);
			panel.add(checkout);
			panel.add(total);
			panel.add(amount);
			panel.add(modePayment);
			panel.add(cod);
			panel.add(credit);
			panel.add(debit);
			panel.add(net);

			try {
				String query1 = "select prod_id from cart_products where cart_id = '" + cart_id + "'";
				st = conn.createStatement();
				rs = st.executeQuery(query1);
				rs.next();
				do {
					int p_id = rs.getInt(1);
					productList.add(cartProd[p_id]);
					j[p_id].setText(prodName[p_id]);
					productList.add(j[p_id]);
					productList.add(price[p_id]);
					productList.add(delete[p_id]);
				} while (rs.next());
			} catch (Exception e) { }
		}
		Integer cartAmount = 0;
		try {
			String query = "select amount from cart where cart_id = '"+cart_id+"'";
			st = conn.createStatement();
			rs = st.executeQuery(query);
			rs.next();
			cartAmount = rs.getInt(1);
		} catch(Exception e) { }
		
		amount.setText(cartAmount.toString());

		for (int i = 0; i < 16; i++) {
			String q = "delete from cart_products where prod_id = '" + i + "' and cart_id = '" + cart_id + "'";
			delete[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						st = conn.createStatement();
						st.executeUpdate(q);
						setCartPage();
					} catch (Exception d) {
					}
				}
			});
		}
	}

	protected void setTransactionPage(String modePay) {
		panel.removeAll();
		pack();
		setSize(1366, 735);
		panel.setLayout(null);
		categories.setText(" TRANSACTION ");
		int cart_id;
		int tns_id = 0;
		try {
			String query0 = "select cart_id from customer where name = '" + profileName + "'";
			st = conn.createStatement();
			rs = st.executeQuery(query0);
			rs.next();
			cart_id = rs.getInt(1);
			String query = "INSERT INTO Payment(email, cart_id, Payment_Mode) VALUES('" + email + "', '" + cart_id
					+ "','" + modePay + "')";
			st.executeUpdate(query);
			String query1 = "select tns_id from payment where cart_id = '" + cart_id + "'";
			rs = st.executeQuery(query1);
			rs.next();
			tns_id = rs.getInt(1);
		} catch (Exception e) { }

		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		Font f = new Font("Times New Roman", Font.BOLD, 20);
		JLabel transCart = new JLabel("Your Transaction is Completed.");
		JTextArea transDesc = new JTextArea(
				"Your order has been placed successfully. Your Transaction ID is " + tns_id + ".");
		transCart.setFont(new Font("Arial", Font.BOLD, 50));
		JButton goHome = new JButton("Go to Home");
		goHome.setFocusPainted(false);
		goHome.setFont(f);
		goHome.setBackground(new Color(0, 150, 200));
		goHome.setForeground(Color.WHITE);
		goHome.setCursor(cursor);
		
		ImageIcon tnsLogo = new ImageIcon(FrontPage.class.getResource("/image/tns.jpg"));
		JLabel tnsImage = new JLabel(tnsLogo);

		transCart.setBounds(50, 5, 800, 100);
		transDesc.setBounds(65, 150, 400, 100);
		transDesc.setRows(4);
		transDesc.setLineWrap(true);
		transDesc.setWrapStyleWord(true);
		transDesc.setFont(f);
		goHome.setBounds(100, 300, 150, 45);
		tnsImage.setBounds(750, 150, 400, 400);

		goHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new FrontPage(profileName, email).setVisible(true);
			}
		});

		panel.add(transCart);
		panel.add(transDesc);
		panel.add(goHome);
		panel.add(tnsImage);

		try {
			String query0 = "select count(*) from cart";
			String query1 = "select cart_id from cart";
			st = conn.createStatement();
			rs = st.executeQuery(query0);
			rs.next();
			int count = rs.getInt(1);

			rs = st.executeQuery(query1);
			rs.next();
			for (int i = 0; i < count - 1; i++) {
				rs.next();
			}
			String c_id = rs.getString("cart_id");
			String query2 = "update customer set cart_id = '" + c_id + "' where email = '" + email + "'";
			st.executeUpdate(query2);
		} catch (Exception e) { }

		Integer count = 0;
		try {
			String query = "select cart_id from customer where email = '" + email + "'";
			st = conn.createStatement();
			rs = st.executeQuery(query);
			rs.next();
			cart_id = rs.getInt(1);
			String query1 = "select count(*) from cart_products where cart_id = '" + cart_id + "'";
			rs = st.executeQuery(query1);
			rs.next();
			count = rs.getInt(1);
		} catch (Exception e) { }
		cart.setText(count.toString() + "  ");
	}
}

@SuppressWarnings("serial")
class Menu extends JMenuBar {
	Color bgColor = new Color(0, 150, 200);
	public void setColor(Color color) {
		bgColor = color;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(bgColor);
		g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
	}
}