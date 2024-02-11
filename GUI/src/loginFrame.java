import java.awt.Image;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class loginFrame {
	private JFrame frame;
	private JTextField id;
	private JPasswordField password;
	private boolean showLogo = true;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginFrame window = new loginFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	class Test extends Thread {
		ImagePanel bgPanel;
		JButton btnNewButton;
		public Test(ImagePanel bgPanel, JButton btnNewButton) {
			this.bgPanel=bgPanel;
			this.btnNewButton=btnNewButton;
		}
		public void run() {
			try {
				Thread.sleep(8000);
				bgPanel.setVisible(true);
				btnNewButton.setVisible(false);
			}catch(Exception e) {

			}

		}
	}

	public loginFrame() {
		initialize();
	}

	public loginFrame(boolean showLogo) {
		this.showLogo = showLogo;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		ImagePanel bgPanel = new ImagePanel(new ImageIcon("./image/bg_loginFrame.jpg").getImage());

		JButton btnNewButton = new JButton("");

		if (this.showLogo) {
			ImageIcon gifIcon = new ImageIcon("./image/start.gif");
        	Image gifImage = gifIcon.getImage().getScaledInstance(1366, 768, Image.SCALE_DEFAULT);
        	btnNewButton.setIcon(new ImageIcon(gifImage));
			btnNewButton.setBounds(-10, -10, 1366, 768);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					bgPanel.setVisible(true);
					btnNewButton.setVisible(false);
				}
			});
			bgPanel.setVisible(false);
			bgPanel.setLayout(null);
			frame.add(btnNewButton);
		} else {
			bgPanel.setVisible(true);
		}
		frame.setSize(bgPanel.getWidth(), bgPanel.getHeight());
		frame.getContentPane().add(bgPanel);

		Thread t = new Test(bgPanel,btnNewButton);
		t.start();
		this.id = this.drawText(bgPanel, new int[]{500, 312, 313, 45}); // ID
		this.password = this.drawPassword(bgPanel, new int[]{500, 421, 313, 45}); // password

		JButton btn_login = this.makeImageButton("login3.jpg", "login4.jpg");
		btn_login.setBounds(500, 518, 313, 57);

		btn_login.addActionListener(new btnAction());
		bgPanel.add(btn_login);

		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	class btnAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String ID = id.getText();
			char[] PW = password.getPassword();

			Long isIdValid = 1L;
        	Long isAllValid = 1L;
			if (ID.length() == 0) {
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
			} else if (PW.length==0){
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
			}
			else {
				if (isIdValid == 0) {
					JOptionPane.showMessageDialog(null, "존재하지 않는 아이디입니다.");
				} else {
					if (isAllValid == 1) {

						String id = "test";
                    	int accountId = 1;
						user my = new user(id);
						user.accountId = accountId;

						JOptionPane.showMessageDialog(null, "안녕하세요, CINEBOOK 입니다.");
						dispose();
						mainFrame frame = new mainFrame(my);
						frame.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
					}
				}
			}
			

		}
	}

	public JTextField drawText(ImagePanel bgPanel, int[] bounds) {
		JTextField txt = new JTextField();
		txt.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
		bgPanel.add(txt);
		return txt;
	}

	public JPasswordField drawPassword(ImagePanel bgPanel, int[] bounds) {
		JPasswordField txt = new JPasswordField();
		txt.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
		bgPanel.add(txt);
		return txt;
	}


	public JButton makeImageButton(String img, String hoverImg) {
		Icon IMG = new ImageIcon("./image/" + img);
		Icon IMG_HOVER = new ImageIcon("./image/" + hoverImg);
		JButton btn = new JButton();

		btn.setIcon(IMG);
		btn.setRolloverIcon(IMG_HOVER);

		return btn;
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void dispose() {
		frame.dispose();
	}
}