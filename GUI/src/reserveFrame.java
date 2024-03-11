import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import java.time.LocalDate;

import java.util.HashMap;
import java.util.Map;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JButton;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

public class reserveFrame extends javax.swing.JFrame {
    private JPanel contentPane;
    private ImageIcon icon;
    private int count;
    private int adultCount = 0, youthCount = 0;
    private user my;
    private int movieId;
    private Icon poster;
    private Map<String, Integer> ScreeningIds = new HashMap<>();

    private String date, time;

    public reserveFrame() {
    }

    public reserveFrame(user my) {
        this.my = my;
        this.movieId = my.getmovieId();
        this.poster = my.getIcon();
        adultCount = 0;
        youthCount = 0;
        DecimalFormat formats = new DecimalFormat("###,###");
        ImageIcon reserve1 = new ImageIcon("./image/reserve1.jpg");
        ImageIcon reserve2 = new ImageIcon("./image/reserve2.jpg");
        ImageIcon backBtn = new ImageIcon("./image/btn/back_btn2.png");
        icon = new ImageIcon("./image/bg_reserveFrame.jpg");
        try {  javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();

        contentPane = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, null);
                poster.paintIcon(this, g, 45, 100);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JLabel backButton = new JLabel("");
        backButton.setIcon(backBtn);
        backButton.setOpaque(false);
        backButton.setBounds(1245, 10, 85, 85);
        backButton.setBackground(Color.BLACK);
        contentPane.add(backButton);
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame m = new mainFrame(my);
                m.setVisible(true);
                dispose();
            }
        });

        JButton[] date_list = new JButton[6];
        for (int i = 0; i < 6; i++) {
            LocalDate currentDate = LocalDate.now();

            date_list[i] = new JButton("" + (currentDate.plusDays(i).toString()));
            date_list[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JButton button = (JButton) e.getSource();
                    for (int i = 0; i < 6; i++) {
                        date_list[i].setBackground(null);
                    }
                    button.setBackground(new Color(69, 191, 204));
                    date = button.getText();
                }
            });
            date_list[i].setBounds(700 + 100 * i, 125, 100, 50);
            contentPane.add(date_list[i]);
        }

        JButton[] time_list = new JButton[4];

        for (int j = 0; j < 4; j++) {
            String buttonText = (j + 12) + ":00";
            time_list[j] = new JButton(buttonText);

            time_list[j].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JButton button = (JButton) e.getSource();
                    for (int j = 0; j < 4; j++) {
                        time_list[j].setBackground(null);
                    }
                    button.setBackground(new Color(69, 191, 204));
                    time = button.getText();

                }
            });
            time_list[j].setBounds(700 + 100 * j, 200, 100, 50);
            contentPane.add(time_list[j]);
        }

        JLabel price = new JLabel("");
        price.setBounds(700, 505, 2000, 50);
        price.setForeground(Color.BLACK);
        price.setFont(price.getFont().deriveFont(30.0F));

        contentPane.add(price);

        JButton[] adult_btn_list = new JButton[8];
        for (int i = 0; i < 8; i++) {
            adult_btn_list[i] = new JButton("" + (i));
            adult_btn_list[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JButton button = (JButton) e.getSource();
                    if (Integer.parseInt(button.getText()) + youthCount > 8) {
                        JOptionPane.showMessageDialog(null, "8인 이하만 예매 가능합니다.");
                        return;
                    }
                    for (int i = 0; i < 8; i++) {
                        adult_btn_list[i].setBackground(null);
                    }
                    adult_btn_list[Integer.parseInt(button.getText())].setBackground(new Color(69, 191, 204));
                    adultCount = Integer.parseInt(button.getText());

                    price.setText(String.format("￦%s", formats.format(adultCount * 13000 + youthCount * 10000)));
                }
            });
            adult_btn_list[i].setBounds(700 + 60 * i, 342, 50, 50);
            contentPane.add(adult_btn_list[i]);
        }

        JButton[] youth_btn_list = new JButton[8];
        for (int i = 0; i < 8; i++) {
            youth_btn_list[i] = new JButton("" + (i));
            youth_btn_list[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JButton button = (JButton) e.getSource();
                    if (Integer.parseInt(button.getText()) + adultCount > 8) {
                        JOptionPane.showMessageDialog(null, "8인 이하만 예매 가능합니다.");
                        return;
                    }
                    for (int i = 0; i < 8; i++) {

                        youth_btn_list[i].setBackground(null);
                    }

                    youth_btn_list[Integer.parseInt(button.getText())].setBackground(new Color(69, 191, 204));
                    youthCount = Integer.parseInt(button.getText());

                    price.setText(String.format("￦%s", formats.format(adultCount * 13000 + youthCount * 10000)));
                }
            });
            youth_btn_list[i].setBounds(700 + 60 * i, 395, 50, 50);
            contentPane.add(youth_btn_list[i]);
        }

        youth_btn_list[0].setBackground(new Color(69, 191, 204));
        adult_btn_list[0].setBackground(new Color(69, 191, 204));

        JButton choiceButton = new JButton(reserve1);
        choiceButton.setRolloverIcon(reserve2);
        choiceButton.setBounds(760, 620, 327, 68);
        contentPane.add(choiceButton);

        choiceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (adultCount + youthCount == 0) {
                    return;
                }

                JDialog popupDialog = new JDialog();
                popupDialog.setTitle("관람등급안내");

                JPanel popupPanel = new JPanel();
                popupPanel.setLayout(null);

                JButton smallButton = new JButton("확인");
                smallButton.setBounds(100, 600, 400, 50);

                ImageIcon imageIcon = new ImageIcon("./image/tw.png");
                JLabel imageLabel = new JLabel(imageIcon);
                imageLabel.setBounds(170, 340, 55, 55);

                JLabel label = new JLabel("<html>본 영화는 만 12세 이상<br>관람 가능한 영화입니다.</html>");
                label.setBounds(200, 430, 200, 50);
                Font font = new Font("맑은 고딕", Font.BOLD, 17);
                label.setFont(font);

                JLabel label1 = new JLabel(
                        "<html>만 12세 미만 고객은 부모님 또는 만 19세 이상 보호자 <br>동반 시 관람이 가능합니다.<br>연령 확인 불가 시 입장이 제한 될 수 있습니다.</html>");
                label1.setBounds(99, 500, 600, 70);
                Font font1 = new Font("맑은 고딕", Font.PLAIN, 15);
                label1.setFont(font1);
                label1.setForeground(Color.gray);

                JLabel label2 = new JLabel("12세 이상 관람가");
                label2.setBounds(240, 330, 200, 70);
                Font font2 = new Font("맑은 고딕", Font.PLAIN, 22);
                label2.setFont(font2);

                JLabel posterLabel = new JLabel();

                ImageIcon posterIcon = (ImageIcon) poster;
                Image originalImage = posterIcon.getImage();
                Image scaledImage = originalImage.getScaledInstance(200, 290, Image.SCALE_SMOOTH);
                ImageIcon resizedPosterIcon = new ImageIcon(scaledImage);
                posterLabel.setIcon(resizedPosterIcon);
                posterLabel.setBounds(200, 15, 200, 290);

                popupPanel.add(label);
                popupPanel.add(label1);
                popupPanel.add(label2);
                popupPanel.add(posterLabel);
                popupPanel.add(imageLabel);
                popupPanel.add(smallButton);

                smallButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        my.setReserveMovie(date, time, adultCount, youthCount,
                                movieId);
                        seatFrame p = new seatFrame(my);
                        p.setVisible(true);
                        JOptionPane.getRootFrame().dispose();
                        dispose();
                    }
                });

                popupDialog.add(popupPanel);
                popupDialog.setSize(600, 700);
                popupDialog.setLocationRelativeTo(null);
                popupDialog.setVisible(true);
            }

        });
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1366, 768));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1366, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 768, Short.MAX_VALUE));

        pack();
    }// </editor-fold>
}