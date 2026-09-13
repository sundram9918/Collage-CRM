/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package collegecrm;
import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {
    public LoginPage() {
        setTitle("College CRM - Login | Manglesh Tiwari");
        setSize(1080, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel main = new JPanel(new GridLayout(1,2));

        // LEFT BLUE
        JPanel left = new JPanel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D)g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0,0,new Color(10,20,70),0,getHeight(),new Color(22,55,125));
                g2.setPaint(gp);
                g2.fillRect(0,0,getWidth(),getHeight());
            }
        };
        left.setLayout(new BorderLayout());

        // TOP
        JLabel top = new JLabel("Manglesh Tiwari College CRM", SwingConstants.CENTER);
        top.setForeground(new Color(85,210,255));
        top.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        top.setBorder(BorderFactory.createEmptyBorder(15,0,0,0));

        // CENTER TEXT
        JPanel titleBox = new JPanel();
        titleBox.setOpaque(false);
        titleBox.setLayout(new BoxLayout(titleBox, BoxLayout.Y_AXIS));
        JLabel t1 = new JLabel("COLLEGE CRM", SwingConstants.CENTER);
        t1.setAlignmentX(Component.CENTER_ALIGNMENT);
        t1.setForeground(new Color(95,225,255));
        t1.setFont(new Font("Segoe UI", Font.BOLD, 34));
        JLabel t2 = new JLabel("Manage Students • Courses • Admissions • Analytics", SwingConstants.CENTER);
        t2.setAlignmentX(Component.CENTER_ALIGNMENT);
        t2.setForeground(Color.WHITE);
        t2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        titleBox.add(t1);
        titleBox.add(Box.createVerticalStrut(8));
        titleBox.add(t2);
        titleBox.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));

        // BUILDING - PURA BADA WALA - KABHI CUT NAHI HOGA
        JPanel buildingPanel = new JPanel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                setOpaque(false);
                Graphics2D g2 = (Graphics2D)g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(140,235,255));
                g2.setStroke(new BasicStroke(1.8f));
                
                int W = getWidth();
                int cx = W/2;
                int y = 5; // top se start

                // 1. KALASH
                g2.setColor(Color.WHITE);
                g2.fillOval(cx-3, y, 6, 9);
                g2.setColor(new Color(140,235,255));

                // 2. CHHOTA GUMBAD
                g2.drawArc(cx-32, y+10, 64, 32, 0, 180);
                g2.drawRect(cx-30, y+30, 60, 16);
                // uspe 3 khidki
                g2.drawRect(cx-22, y+34, 8, 8);
                g2.drawRect(cx-4, y+34, 8, 8);
                g2.drawRect(cx+14, y+34, 8, 8);

                // 3. BADA GUMBAD
                g2.setStroke(new BasicStroke(2f));
                g2.drawArc(cx-62, y+46, 124, 42, 0, 180);

                // 4. CHHAT
                g2.drawLine(cx-62, y+67, cx-125, y+88);
                g2.drawLine(cx+62, y+67, cx+125, y+88);
                g2.drawRect(cx-128, y+88, 256, 10); // patti

                // 5. MAIN BUILDING
                g2.drawRect(cx-118, y+98, 236, 80);

                // 6. KHIDKIYA - 2 LINE
                g2.setStroke(new BasicStroke(1.5f));
                for(int i=-2; i<=2; i++){
                    int wx = cx + i*44 - 8;
                    g2.drawRect(wx-10, y+105, 18, 16); // upper
                    g2.drawRect(wx-10, y+130, 18, 26); // lower
                }

                // 7. 4 PILLAR - MOLDING KE SAATH
                g2.setStroke(new BasicStroke(2.2f));
                int[] pillars = {-80,-28,18,70};
                for(int px : pillars){
                    g2.drawRect(cx+px, y+98, 12, 80); // pillar
                    g2.drawRect(cx+px-4, y+94, 20, 6); // upar ka design
                    g2.drawRect(cx+px-4, y+174, 20, 6); // niche ka design
                }

                // 8. 3 SEEDHI
                g2.setStroke(new BasicStroke(1.8f));
                g2.drawRect(cx-90, y+182, 180, 7);
                g2.drawRect(cx-102, y+189, 204, 7);
                g2.drawRect(cx-115, y+196, 230, 7);
            }
        };
        buildingPanel.setPreferredSize(new Dimension(500, 220));
        buildingPanel.setMaximumSize(new Dimension(500, 220));
        buildingPanel.setMinimumSize(new Dimension(500, 220));
        buildingPanel.setOpaque(false);

        JPanel centerAll = new JPanel();
        centerAll.setOpaque(false);
        centerAll.setLayout(new BorderLayout());
        centerAll.add(titleBox, BorderLayout.NORTH);
        centerAll.add(buildingPanel, BorderLayout.CENTER);

        JLabel bottom = new JLabel("200+ Colleges Trust Us | Secure & Reliable", SwingConstants.CENTER);
        bottom.setForeground(new Color(170,200,255));
        bottom.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        bottom.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));

        left.add(top, BorderLayout.NORTH);
        left.add(centerAll, BorderLayout.CENTER);
        left.add(bottom, BorderLayout.SOUTH);

        // RIGHT LOGIN
        JPanel right = new JPanel();
        right.setBackground(Color.WHITE);
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setBorder(BorderFactory.createEmptyBorder(100,55,50,55));

        JLabel w1 = new JLabel("Welcome Back!"); w1.setFont(new Font("Segoe UI", Font.BOLD, 28));
        JLabel w2 = new JLabel("Please login to continue to your dashboard"); w2.setForeground(new Color(120,120,120)); w2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        JLabel ul = new JLabel("Username"); ul.setFont(new Font("Segoe UI", Font.BOLD, 12));
        JTextField uf = new JTextField(); uf.setMaximumSize(new Dimension(360,42));
        JLabel pl = new JLabel("Password"); pl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        JPasswordField pf = new JPasswordField(); pf.setMaximumSize(new Dimension(360,42));
        JPanel row = new JPanel(new BorderLayout()); row.setBackground(Color.WHITE); row.setMaximumSize(new Dimension(360,25));
        JCheckBox rem = new JCheckBox("Remember me"); rem.setBackground(Color.WHITE); rem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        JLabel forgot = new JLabel("Forgot Password?"); forgot.setForeground(new Color(0,102,255)); forgot.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        row.add(rem, BorderLayout.WEST); row.add(forgot, BorderLayout.EAST);
        JButton btn = new JButton("LOGIN"); btn.setMaximumSize(new Dimension(360,48));
        btn.setBackground(new Color(0,90,220)); btn.setForeground(Color.WHITE); btn.setFocusPainted(false); btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.addActionListener(e -> { new Dashboard().setVisible(true); dispose(); });

        right.add(w1); right.add(Box.createVerticalStrut(8)); right.add(w2); right.add(Box.createVerticalStrut(35));
        right.add(ul); right.add(Box.createVerticalStrut(6)); right.add(uf); right.add(Box.createVerticalStrut(18));
        right.add(pl); right.add(Box.createVerticalStrut(6)); right.add(pf); right.add(Box.createVerticalStrut(18));
        right.add(row); right.add(Box.createVerticalStrut(30)); right.add(btn);

        main.add(left); main.add(right);
        setContentPane(main);
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }
}
  