import java.awt.BorderLayout;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.DecimalFormat;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class reserveth extends javax.swing.JFrame {

  private JPanel contentPane;
  private ImageIcon icon;
  private user my;
  private int movieId;
  private Icon poster;
  private Map<String, Integer> ScreeningIds = new HashMap<>();

  JList<String> regionList;
  JList<String> cinemaList;

  public reserveth() {
  }

  public reserveth(user my) {
    this.my = my;
    this.movieId = my.getmovieId();
    this.poster = my.getIcon();
    DecimalFormat formats = new DecimalFormat("###,###");
    ImageIcon reserve1 = new ImageIcon("./image/next4.jpg");
    ImageIcon reserve2 = new ImageIcon("./image/next5.jpg");
    ImageIcon backBtn = new ImageIcon("./image/btn/back_btn2.png");
    icon = new ImageIcon("./image/bg_mainFrame.jpg");
    try {
      UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch (Exception e) {
      e.printStackTrace();
    }
    initComponents();

    contentPane = new JPanel() {
      public void paintComponent(Graphics g) {
        g.drawImage(icon.getImage(), 0, 0, null);
        poster.paintIcon(this, g, 50, 150);
        setOpaque(false);
        super.paintComponent(g);
      }
    };
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    setLocationRelativeTo(null);

    JButton checkButton = new JButton(reserve1);
    checkButton.setRolloverIcon(reserve2);
    checkButton.setBounds(790, 620, 327, 68);
    contentPane.add(checkButton);

    checkButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (regionList.getSelectedValue() == null) {
          JOptionPane.showMessageDialog(null, "지역을 선택해주세요.");
          return;
        }

        if (cinemaList.getSelectedValue() == null) {
          JOptionPane.showMessageDialog(null, "지점을 선택해주세요.");
          return;
        }
        my.setmoviedId(movieId);
        reserveFrame p = new reserveFrame(my);
        p.setVisible(true);
        dispose();
      }
    });

    JPanel regionPanel = new JPanel();
    regionPanel.setBounds(600, 175, 300, 400);
    contentPane.add(regionPanel);
    regionPanel.setLayout(new BorderLayout());

    JLabel regionLabel = new JLabel("지역 선택");
    regionLabel.setHorizontalAlignment(SwingConstants.CENTER);

    Font font = new Font("맑은 고딕", Font.BOLD, 17);
    regionLabel.setFont(font);

    regionPanel.add(regionLabel, BorderLayout.NORTH);

    regionList = new JList<>(new String[] { "서울", "경기", "인천" });
    regionPanel.add(new JScrollPane(regionList), BorderLayout.CENTER);

    JPanel cinemaPanel = new JPanel();
    cinemaPanel.setBounds(1010, 175, 300, 400);
    contentPane.add(cinemaPanel);
    cinemaPanel.setLayout(new BorderLayout());

    JLabel cinemaLabel = new JLabel("지점 선택");
    cinemaLabel.setFont(font);
    cinemaLabel.setHorizontalAlignment(SwingConstants.CENTER);
    cinemaPanel.add(cinemaLabel, BorderLayout.NORTH);

    cinemaList = new JList<>(new String[] { "가양", "노원", "신도림" });
    cinemaPanel.add(new JScrollPane(cinemaList), BorderLayout.CENTER);

    regionList.addListSelectionListener(e -> updateCinemaLocations());
  }

  private void updateCinemaLocations() {
    Map<String, String[]> cinemaMap = new HashMap<>();
    cinemaMap.put("서울", new String[] { "가양", "노원", "신도림" });
    cinemaMap.put("경기", new String[] { "부천", "성남중앙", "용인기흥" });
    cinemaMap.put("인천", new String[] { "부평", "영종하늘도시", "인천터미널" });

    String selectedRegion = regionList.getSelectedValue();
    String[] cinemaLocations = cinemaMap.get(selectedRegion);

    cinemaList.setListData(cinemaLocations);

  }

  // @SuppressWarnings("unchecked")
  private void initComponents() {
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setMaximumSize(new java.awt.Dimension(1366, 768));
    setMinimumSize(new java.awt.Dimension(1366, 768));
    setResizable(false);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0,
        1366,
        Short.MAX_VALUE));
    layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0,
        768,
        Short.MAX_VALUE));

    pack();
  }

}
