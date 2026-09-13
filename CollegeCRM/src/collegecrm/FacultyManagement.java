/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package collegecrm;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class FacultyManagement extends JFrame {
    JTable table;
    DefaultTableModel model;
    JTextField t_name, t_code, t_dept, t_qual, t_mobile;

    public FacultyManagement() {
        setTitle("College CRM - Faculty | Manglesh Tiwari");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // SIDEBAR
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
        sidebar.add(menuActive("👨‍🏫 Faculty"));
        sidebar.add(menu("💰 Fees"));

        // MAIN
        JPanel main = new JPanel(new BorderLayout());
        
        String[] cols = {"faculty_id", "faculty_name", "faculty_code", "department", "qualification", "mobile"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);
        table.setRowHeight(32);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        JPanel input = new JPanel(new GridLayout(1,6,10,10));
        input.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        input.setBackground(Color.WHITE);

        t_name = new JTextField(); t_name.setBorder(BorderFactory.createTitledBorder("Faculty Name"));
        t_code = new JTextField(); t_code.setBorder(BorderFactory.createTitledBorder("Faculty Code (FAC001)"));
        t_dept = new JTextField(); t_dept.setBorder(BorderFactory.createTitledBorder("Department (CSE)"));
        t_qual = new JTextField(); t_qual.setBorder(BorderFactory.createTitledBorder("Qualification"));
        t_mobile = new JTextField(); t_mobile.setBorder(BorderFactory.createTitledBorder("Mobile"));
        
        JButton add = new JButton("Add Faculty");
        add.setBackground(new Color(0,102,255));
        add.setForeground(Color.WHITE);
        add.setFont(new Font("Segoe UI", Font.BOLD, 13));
        add.setFocusPainted(false);
        add.addActionListener(e -> addFaculty());

        input.add(t_name); input.add(t_code); input.add(t_dept); input.add(t_qual); input.add(t_mobile); input.add(add);

        main.add(new JScrollPane(table), BorderLayout.CENTER);
        main.add(input, BorderLayout.SOUTH);

        add(sidebar, BorderLayout.WEST);
        add(main, BorderLayout.CENTER);
        loadFaculty();
    }

    private JLabel menu(String text){
        JLabel l = new JLabel(text);
        l.setOpaque(true);
        l.setBackground(new Color(10,20,70));
        l.setForeground(new Color(170,190,220));
        l.setFont(new Font("Segoe UI", Font.BOLD, 13));
        l.setBorder(BorderFactory.createEmptyBorder(12,15,12,15));
        l.setCursor(new Cursor(Cursor.HAND_CURSOR));
        l.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent e){
                if(text.contains("Students")){ new StudentManagement().setVisible(true); dispose(); }
                else if(text.contains("Dashboard")){ new Dashboard().setVisible(true); dispose(); }
                else if(text.contains("Fees")){ new FeesManagement().setVisible(true); dispose(); }
            }
        });
        return l;
    }
    private JLabel menuActive(String text){
        JLabel l = new JLabel(text);
        l.setOpaque(true);
        l.setBackground(new Color(20,35,100));
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Segoe UI", Font.BOLD, 13));
        l.setBorder(BorderFactory.createEmptyBorder(12,15,12,15));
        return l;
    }

    private void loadFaculty(){
        try{
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM faculty");
            model.setRowCount(0);
            while(rs.next()){
                model.addRow(new Object[]{
                    rs.getInt("faculty_id"),
                    rs.getString("faculty_name"),
                    rs.getString("faculty_code"),
                    rs.getString("department"),
                    rs.getString("qualification"),
                    rs.getString("mobile")
                });
            }
            con.close();
        }catch(Exception e){ JOptionPane.showMessageDialog(this, "Error: "+e.getMessage()); }
    }

    private void addFaculty(){
        if(t_name.getText().isEmpty() || t_code.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter Name and code!");
            return;
        }
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO faculty(faculty_name, faculty_code, department, qualification, mobile) VALUES(?,?,?,?,?)");
            ps.setString(1, t_name.getText());
            ps.setString(2, t_code.getText());
            ps.setString(3, t_dept.getText());
            ps.setString(4, t_qual.getText());
            ps.setString(5, t_mobile.getText());
            ps.executeUpdate();
            con.close();
            JOptionPane.showMessageDialog(this, "Faculty Added Successfully!");
            loadFaculty();
            t_name.setText(""); t_code.setText(""); t_dept.setText(""); t_qual.setText(""); t_mobile.setText("");
        }catch(Exception e){ JOptionPane.showMessageDialog(this, "Add Error: "+e.getMessage()); }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new FacultyManagement().setVisible(true));
    }
}