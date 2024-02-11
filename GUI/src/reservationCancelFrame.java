
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;

import java.util.Collections;

import java.util.HashMap;


import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;



import javax.swing.JButton;

public class reservationCancelFrame {
	private final Image BG_IMAGE = new ImageIcon("./image/bg_checkFrame(cancel).jpg").getImage();
	private JFrame frame;
	private JLabel title;
	private JLabel detail;
	private JLabel count;
	private JLabel seats;
	private JLabel poster;
	private String groupId;
	private Integer MovieId;
	private long canceled = 0;
	private user my;
	private HashMap<String, Reservation> reservations;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

	}

	public reservationCancelFrame(user my, String groupId) {
		this.my = my;
		this.groupId = groupId;
		this.reservations = new HashMap<>(); // HashMap 초기화
	// 예시로 몇 가지 예약 정보를 미리 추가합니다.
	// 실제로는 사용자의 입력 등을 통해 예약 정보를 추가해야 합니다.
		this.reservations.put("group1", new Reservation(1, 1, 1, "group1", "2023-12-01", 1, false, "서울의 봄", "상영 날짜", 2, "좌석 정보", "상영 시간"));

		initialize();
		this.getResverationInfo(this.groupId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();

		ImagePanel bgPanel = new ImagePanel(this.BG_IMAGE);
		frame.setSize(bgPanel.getWidth(), bgPanel.getHeight());
		frame.getContentPane().add(bgPanel);
		bgPanel.setLayout(null);

		this.drawInfoButton(bgPanel);
		this.drawCheckButton(bgPanel);
		this.drawCancelButton(bgPanel);
		this.drawBackButton(bgPanel);
		this.drawReservInfo(bgPanel);

		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void getResverationInfo(String groupId) {
		Reservation reservation = reservations.get(groupId);
		if (reservation != null) {
			this.title.setText(reservation.getTitle());
			this.detail.setText(reservation.getScreenDate() + " | " + reservation.getTime());
			this.count.setText(String.valueOf(reservation.getCount()));
			this.seats.setText(reservation.getSeats());
	
			this.canceled = reservation.isCanceled() ? 1 : 0;
			this.MovieId = reservation.getMovieId();
			ImageIcon posterImg = resizeIcon(new ImageIcon("./image/poster/poster_" + this.MovieId.toString() + ".jpg"), 120, 168);
	
			poster.setIcon(posterImg);
		}
	}

	private static ImageIcon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
		Image img = icon.getImage();
		Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}

	public void drawInfoButton(ImagePanel bgPanel) {
		JButton button = this.makeImageButton("info1.jpg", "info2.jpg");
		button.setBounds(100, 360, 225, 54);
		button.setBorderPainted(false);
		button.setFocusPainted(false);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				mypageFrame mypageframe = new mypageFrame(my);
				mypageframe.setVisible(true);
			}
		});

		bgPanel.add(button);
	}

	public void drawCheckButton(ImagePanel bgPanel) {
		JButton button = this.makeImageButton("check1.jpg", "check2.jpg");
		button.setBounds(100, 450, 225, 54);
		button.setBorderPainted(false);
		button.setFocusPainted(false);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				checkFrame checkframe = new checkFrame(my);
				checkframe.setVisible(true);
			}
		});

		bgPanel.add(button);
	}

	public void drawCancelButton(ImagePanel bgPanel) {
		String btnOff = "cancel1.jpg";
		String btnOn = "cancel2.jpg";
		JButton button = this.makeImageButton(btnOff, btnOn);
		button.setBounds(765, 555, 235, 54);
		button.setBorderPainted(false);
		button.setFocusPainted(false);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (canceled == 1) {
					JOptionPane.showMessageDialog(bgPanel, "이미 예매취소되었습니다.");
				} else {
					int res = JOptionPane.showConfirmDialog(bgPanel, "정말 취소하시겠습니까?","취소",JOptionPane.YES_NO_OPTION);
					if (res == JOptionPane.OK_OPTION) {
						// 예매취소
						cancel(groupId);
					}
				}
			}
		});
		bgPanel.add(button);
	}

	public void drawBackButton(ImagePanel bgPanel) {
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("./image/back_btn.png"));
		button.setBounds(1245, 10, 85, 85);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setOpaque(false);  // Set background color to transparent
		button.setContentAreaFilled(false);  // Set content area filled to false

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame m = new mainFrame(my);
				m.setVisible(true);
				frame.dispose();
			}
		});

		bgPanel.add(button);
	}

	public void drawReservInfo(ImagePanel bgPanel) {
		title = new JLabel("영화제목");
		detail = new JLabel("세부정보");
		count = new JLabel("2");
		seats = new JLabel("J4 J5");

		// ImageIcon icon = new
		poster = new JLabel();

		title.setBounds(780, 230, 500, 50);
		detail.setBounds(780, 280, 400, 50);
		count.setBounds(654, 416, 50, 50);
		seats.setBounds(774, 430, 500, 50);
		poster.setBounds(640, 195, 120, 160);

		this.setFontWeight(title, TextAttribute.WEIGHT_SEMIBOLD);
		this.setFontWeight(detail, TextAttribute.WEIGHT_SEMIBOLD);
		this.setFontWeight(seats, TextAttribute.WEIGHT_BOLD);
		this.setFontWeight(count, TextAttribute.WEIGHT_EXTRABOLD);
		this.setFontSize(title, 30);
		this.setFontSize(detail, 24);
		this.setFontSize(seats, 25);
		this.setFontSize(count, 40);

		bgPanel.add(title);
		bgPanel.add(detail);
		bgPanel.add(count);
		bgPanel.add(seats);
		bgPanel.add(poster);
	}

	public void setFontWeight(JLabel label, Float weightBold) {
		Font font = label.getFont();
		font = font.deriveFont(Collections.singletonMap(TextAttribute.WEIGHT, weightBold));
		label.setFont(font);
	}

	public void setFontSize(JLabel label, int size) {
		Font font = label.getFont();
		font = font.deriveFont(Collections.singletonMap(TextAttribute.SIZE, size));
		label.setFont(font);
	}

	/**
	 * 이미지 버튼 생성
	 *
	 * @param img      기본 이미지명
	 * @param hoverImg hover 이미지명
	 * @return
	 */
	public JButton makeImageButton(String img, String hoverImg) {
		Icon IMG = new ImageIcon("./image/" + img);
		Icon IMG_HOVER = new ImageIcon("./image/" + hoverImg);
		JButton btn = new JButton();

		btn.setIcon(IMG);
		btn.setRolloverIcon(IMG_HOVER);

		return btn;
	}

	public void cancel(String groupId) {
		Reservation res = reservations.get(groupId);
		if (res != null && !res.isCanceled()) {
			res.cancel();
			JOptionPane.showMessageDialog(null, "취소되었습니다.");
		} else {
			JOptionPane.showMessageDialog(null, "취소 실패");
		}
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void dispose() {
		frame.dispose();
	}
}
