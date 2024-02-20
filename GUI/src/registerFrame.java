import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;

public class registerFrame {

	private JFrame frame;
	private JTextField id;
	private JPasswordField password;
	private JTextField name;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerFrame window = new registerFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public registerFrame() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame();

		ImagePanel bgPanel = new ImagePanel(new ImageIcon("./image/bg_registerFrame.jpg").getImage());
		frame.setSize(bgPanel.getWidth(), bgPanel.getHeight());
		frame.getContentPane().add(bgPanel);
		bgPanel.setLayout(null);

		this.drawIdFeild(bgPanel);
		this.drawPwFeild(bgPanel);
		this.drawNameFeild(bgPanel);
		this.drawRegisterBtn(bgPanel);

		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void drawIdFeild(ImagePanel bgPanel) {
		id = new JTextField();
		id.setBounds(519, 387, 313, 45);
		id.setColumns(10);
		bgPanel.add(id);
	}

	public void drawPwFeild(ImagePanel bgPanel) {
		password = new JPasswordField();
		password.setBounds(519, 495, 313, 45);
		bgPanel.add(password);
	}

	public void drawNameFeild(ImagePanel bgPanel) {
		name = new JTextField();
		name.setBounds(519, 279, 313, 45);
		name.setColumns(10);
		bgPanel.add(name);
	}

	public void drawRegisterBtn(ImagePanel bgPanel) {
		JButton btn_register = new JButton("New button");
		btn_register.setBackground(Color.LIGHT_GRAY);
		btn_register.setForeground(Color.WHITE);
		btn_register.setIcon(new ImageIcon("./image/register1.jpg"));
		btn_register.setRolloverIcon(new ImageIcon("./image/register2.jpg"));
		btn_register.setBounds(519, 586, 313, 57);
		btn_register.addActionListener(new RegisterAction());
		bgPanel.add(btn_register);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void dispose() {
		frame.dispose();
	}

	public class RegisterAction implements ActionListener {
    private Map<String, String> accounts = new HashMap<>();

    public boolean checkDuplicatedId(String ID) {
        return accounts.containsKey(ID);
    }

    public void actionPerformed(ActionEvent e) {
        String ID = id.getText();
        char[] PW = password.getPassword();
        String NICK = name.getText();

        if (ID.length() == 0) {
            JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
        } else if (PW.length == 0) {
            JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
        } else if (NICK.length() == 0) {
            JOptionPane.showMessageDialog(null, "이름을 입력해주세요.");
        } else if (this.checkDuplicatedId(ID)) {
            JOptionPane.showMessageDialog(null, "중복된 아이디입니다.");
        } else {
            accounts.put(ID, new String(PW));
            JOptionPane.showMessageDialog(null, "회원가입 되었습니다.");
            loginFrame frame = new loginFrame(false);
            frame.setVisible(true);
            dispose();
        }
    }
}
}
