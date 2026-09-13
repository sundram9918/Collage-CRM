/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



package collegecrm;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class FeesManagement extends JFrame {
    JTable table;
    DefaultTableModel model;
    JTextField t_roll, t_name, t_course, t_total, t_paid;

    public FeesManagement() {
        setTitle("College CRM - Fees | Manglesh Tiwari");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(10,20,70));
        sidebar.setPreferredSize(new Dimension(220, 700));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20,15,20,15));
        JLabel logo = new JLabel("COLLEGE CRM");
        logo.setForeground(new Color(90,220,255));
        logo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(30));
        sidebar.add(menu("🎓 Students"));
        sidebar.add(menu("📊 Dashboard"));
        sidebar.add(menu("👨‍🏫 Faculty"));
        sidebar.add(menuActive("💰 Fees"));

        JPanel main = new JPanel(new BorderLayout());
        // Teri table ke hisab se fee_id hai
        String[] cols = {"fee_id", "roll_no", "student_name", "course", "total_fees", "paid_amount", "due_amount"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);
        table.setRowHeight(32);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        JPanel input = new JPanel(new GridLayout(1,6,10,10));
        input.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        input.setBackground(Color.WHITE);

        t_roll = new JTextField(); t_roll.setBorder(BorderFactory.createTitledBorder("Roll No"));
        t_name = new JTextField(); t_name.setBorder(BorderFactory.createTitledBorder("Student Name"));
        t_course = new JTextField(); t_course.setBorder(BorderFactory.createTitledBorder("Course"));
        t_total = new JTextField(); t_total.setBorder(BorderFactory.createTitledBorder("Total Fees"));
        t_paid = new JTextField(); t_paid.setBorder(BorderFactory.createTitledBorder("Paid Amount"));

        JButton add = new JButton("Add Fees");
        add.setBackground(new Color(0, 180, 90));
        add.setForeground(Color.WHITE);
        add.setFont(new Font("Segoe UI", Font.BOLD, 13));
        add.addActionListener(e -> addFees());

        input.add(t_roll); input.add(t_name); input.add(t_course); input.add(t_total); input.add(t_paid); input.add(add);

        main.add(new JScrollPane(table), BorderLayout.CENTER);
        main.add(input, BorderLayout.SOUTH);
        add(sidebar, BorderLayout.WEST);
        add(main, BorderLayout.CENTER);
        loadFees();
    }

    private JLabel menu(String text){
        JLabel l = new JLabel(text); l.setOpaque(true); l.setBackground(new Color(10,20,70)); l.setForeground(new Color(170,190,220)); l.setFont(new Font("Segoe UI", Font.BOLD, 13)); l.setBorder(BorderFactory.createEmptyBorder(12,15,12,15)); l.setCursor(new Cursor(Cursor.HAND_CURSOR));
        l.addMouseListener(new java.awt.event.MouseAdapter(){ public void mouseClicked(java.awt.event.MouseEvent e){ if(text.contains("Students")){ new StudentManagement().setVisible(true); dispose(); } else if(text.contains("Faculty")){ new FacultyManagement().setVisible(true); dispose(); } else if(text.contains("Dashboard")){ new Dashboard().setVisible(true); dispose(); } }});
        return l;
    }
    private JLabel menuActive(String text){ JLabel l = new JLabel(text); l.setOpaque(true); l.setBackground(new Color(20,35,100)); l.setForeground(Color.WHITE); l.setFont(new Font("Segoe UI", Font.BOLD, 13)); l.setBorder(BorderFactory.createEmptyBorder(12,15,12,15)); return l; }

    private void loadFees(){
        try{
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM fees");
            model.setRowCount(0);
            while(rs.next()){
                // Ab fee_id sahi hai - tere screenshot wala
                model.addRow(new Object[]{ rs.getInt("fee_id"), rs.getString("roll_no"), rs.getString("student_name"), rs.getString("course"), rs.getInt("total_fees"), rs.getInt("paid_amount"), rs.getInt("due_amount") });
            }
            con.close();
        }catch(Exception e){ JOptionPane.showMessageDialog(this, "Error: "+e.getMessage()); }
    }

    private void addFees(){
        try{
            int total = Integer.parseInt(t_total.getText());
            int paid = Integer.parseInt(t_paid.getText());
            int due = total - paid;

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO fees(roll_no, student_name, course, total_fees, paid_amount, due_amount) VALUES(?,?,?,?,?,?)");
            ps.setString(1, t_roll.getText());
            ps.setString(2, t_name.getText());
            ps.setString(3, t_course.getText());
            ps.setInt(4, total);
            ps.setInt(5, paid);
            ps.setInt(6, due);
            ps.executeUpdate();
            con.close();
            JOptionPane.showMessageDialog(this, "Fees Added! Due: "+due);
            loadFees();
            t_roll.setText(""); t_name.setText(""); t_course.setText(""); t_total.setText(""); t_paid.setText("");
        }catch(Exception e){ JOptionPane.showMessageDialog(this, "Add Error: "+e.getMessage()); }
    }

    public static void main(String[] args){ SwingUtilities.invokeLater(() -> new FeesManagement().setVisible(true)); }
}