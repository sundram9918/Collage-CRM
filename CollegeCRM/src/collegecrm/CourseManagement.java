/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package collegecrm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class CourseManagement extends JFrame {
    
    JTable table;
    DefaultTableModel model;
    JTextField t_name, t_code, t_dept, t_dur, t_fees;

    public CourseManagement() {
        setTitle("College CRM - Courses | Manglesh Tiwari");
        setSize(1250, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== LEFT SIDEBAR - Fees Jaisa =====
        JPanel left = new JPanel();
        left.setBackground(new Color(19, 27, 84));
        left.setPreferredSize(new Dimension(220, 700));
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(" COLLEGE CRM");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(20, 15, 30, 15));
        left.add(title);

        String[] menus = {"Students", "Dashboard", "Faculty", "Fees", "Courses", "Attendance"};
        for (String menu : menus) {
            JLabel lbl = new JLabel("  □ " + menu);
            lbl.setOpaque(true);
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            lbl.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
            lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            if (menu.equals("Courses")) {
                lbl.setBackground(new Color(36, 48, 127)); // Selected
            } else {
                lbl.setBackground(new Color(19, 27, 84));
            }

            lbl.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    dispose();
                    if(menu.equals("Students")) new StudentManagement().setVisible(true);
                    if(menu.equals("Dashboard")) new Dashboard().setVisible(true);
                    if(menu.equals("Faculty")) new FacultyManagement().setVisible(true);
                    if(menu.equals("Fees")) new FeesManagement().setVisible(true);
                    if(menu.equals("Attendance")) new AttendanceManagement().setVisible(true);
                    if(menu.equals("Courses")) new CourseManagement().setVisible(true);
                }
                public void mouseEntered(java.awt.event.MouseEvent e){
                    lbl.setBackground(new Color(45, 60, 150));
                }
                public void mouseExited(java.awt.event.MouseEvent e){
                    if(menu.equals("Courses")) lbl.setBackground(new Color(36, 48, 127));
                    else lbl.setBackground(new Color(19, 27, 84));
                }
            });
            left.add(lbl);
        }

        // ===== CENTER TABLE =====
        String[] cols = {"course_id", "course_name", "course_code", "department", "duration", "total_fees"};
        model = new DefaultTableModel(cols, 0){
            public boolean isCellEditable(int r, int c){ return false; }
        };
        table = new JTable(model);
        table.setRowHeight(32);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(Color.WHITE);
        JScrollPane sp = new JScrollPane(table);

        // ===== BOTTOM INPUT - Fees Jaisa Green Button =====
        JPanel bottom = new JPanel(new GridLayout(1, 6, 10, 10));
        bottom.setBackground(Color.WHITE);
        bottom.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));

        t_name = new JTextField(); t_name.setBorder(BorderFactory.createTitledBorder("course_name"));
        t_code = new JTextField(); t_code.setBorder(BorderFactory.createTitledBorder("course_code"));
        t_dept = new JTextField(); t_dept.setBorder(BorderFactory.createTitledBorder("department"));
        t_dur = new JTextField(); t_dur.setBorder(BorderFactory.createTitledBorder("duration"));
        t_fees = new JTextField(); t_fees.setBorder(BorderFactory.createTitledBorder("total_fees"));

        JButton addBtn = new JButton("Add Course");
        addBtn.setBackground(new Color(39, 174, 96));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        addBtn.setFocusPainted(false);

        bottom.add(t_name);
        bottom.add(t_code);
        bottom.add(t_dept);
        bottom.add(t_dur);
        bottom.add(t_fees);
        bottom.add(addBtn);

        add(left, BorderLayout.WEST);
        add(sp, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> addCourse());
        loadData();
    }

    void loadData() {
        try {
            model.setRowCount(0);
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM courses ORDER BY course_id");
            while(rs.next()){
                model.addRow(new Object[]{
                    rs.getInt("course_id"),
                    rs.getString("course_name"),
                    rs.getString("course_code"),
                    rs.getString("department"),
                    rs.getString("duration"),
                    rs.getInt("total_fees")
                });
            }
        } catch(Exception e){ e.printStackTrace(); }
    }

    void addCourse() {
        try {
            String sql = "INSERT INTO courses(course_name, course_code, department, duration, total_fees) VALUES(?,?,?,?,?)";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, t_name.getText());
            ps.setString(2, t_code.getText());
            ps.setString(3, t_dept.getText());
            ps.setString(4, t_dur.getText());
            ps.setInt(5, Integer.parseInt(t_fees.getText()));
            ps.executeUpdate();
            loadData();
            t_name.setText(""); t_code.setText(""); t_dept.setText(""); t_dur.setText(""); t_fees.setText("");
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new CourseManagement().setVisible(true));
    }
}