/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package collegecrm;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class StudentManagement extends JFrame {
    JTable table;
    DefaultTableModel model;
    JTextField t_name, t_roll, t_course, t_mobile, t_branch;

    public StudentManagement() {
        setTitle("College CRM - Students | Manglesh Tiwari");
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
        sidebar.add(menuActive("🎓 Students"));
        sidebar.add(menu("📊 Dashboard"));
        sidebar.add(menu("💰 Fees"));

        JPanel main = new JPanel(new BorderLayout());
        String[] cols = {"student_id", "student_name", "roll_no", "course", "mobile", "branch"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);
        table.setRowHeight(30);

        JPanel input = new JPanel(new GridLayout(1,6,10,10));
        input.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        t_name = new JTextField(); t_name.setBorder(BorderFactory.createTitledBorder("Student Name"));
        t_roll = new JTextField(); t_roll.setBorder(BorderFactory.createTitledBorder("Roll No"));
        t_course = new JTextField(); t_course.setBorder(BorderFactory.createTitledBorder("Course"));
        t_mobile = new JTextField(); t_mobile.setBorder(BorderFactory.createTitledBorder("Mobile"));
        t_branch = new JTextField(); t_branch.setBorder(BorderFactory.createTitledBorder("Branch"));
        
        JButton add = new JButton("Add Admission");
        add.setBackground(new Color(0,102,255));
        add.setForeground(Color.WHITE);
        add.addActionListener(e -> addStudent());
        input.add(t_name); input.add(t_roll); input.add(t_course); input.add(t_mobile); input.add(t_branch); input.add(add);

        main.add(new JScrollPane(table), BorderLayout.CENTER);
        main.add(input, BorderLayout.SOUTH);
        add(sidebar, BorderLayout.WEST);
        add(main, BorderLayout.CENTER);
        loadStudents();
    }

    private JLabel menu(String text){ JLabel l = new JLabel(text); l.setOpaque(true); l.setBackground(new Color(10,20,70)); l.setForeground(new Color(170,190,220)); l.setBorder(BorderFactory.createEmptyBorder(12,15,12,15)); l.setCursor(new Cursor(Cursor.HAND_CURSOR)); l.addMouseListener(new java.awt.event.MouseAdapter(){ public void mouseClicked(java.awt.event.MouseEvent e){ new Dashboard().setVisible(true); dispose(); }}); return l; }
    private JLabel menuActive(String text){ JLabel l = new JLabel(text); l.setOpaque(true); l.setBackground(new Color(20,35,100)); l.setForeground(Color.WHITE); l.setBorder(BorderFactory.createEmptyBorder(12,15,12,15)); return l; }

    private void loadStudents(){
        try{
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM students");
            model.setRowCount(0);
            while(rs.next()){
                model.addRow(new Object[]{ rs.getInt("student_id"), rs.getString("student_name"), rs.getString("roll_no"), rs.getString("course"), rs.getString("mobile"), rs.getString("branch") });
            }
            con.close();
        }catch(Exception e){ JOptionPane.showMessageDialog(this, "Error: "+e.getMessage()); }
    }

    private void addStudent(){
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO students(student_name, roll_no, course, mobile, branch) VALUES(?,?,?,?,?)");
            ps.setString(1, t_name.getText());
            ps.setString(2, t_roll.getText());
            ps.setString(3, t_course.getText());
            ps.setString(4, t_mobile.getText());
            ps.setString(5, t_branch.getText());
            ps.executeUpdate();
            con.close();
            JOptionPane.showMessageDialog(this, "Admission Added!");
            loadStudents();
        }catch(Exception e){ JOptionPane.showMessageDialog(this, "Add Error: "+e.getMessage()); }
    }
    public static void main(String[] args){ SwingUtilities.invokeLater(() -> new StudentManagement().setVisible(true)); }
}