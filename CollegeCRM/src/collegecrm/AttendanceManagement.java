/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package collegecrm;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class AttendanceManagement extends JFrame {
    JTable table; 
    DefaultTableModel model;
    JTextField t_roll, t_name, t_date; 
    JComboBox<String> c_status;

    public AttendanceManagement(){
        setTitle("Attendance Management");
        setSize(1200, 700); 
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Sidebar
        JPanel sidebar = new JPanel(); 
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(10,20,70)); 
        sidebar.setPreferredSize(new Dimension(220,700));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20,15,20,15));
        
        JLabel logo = new JLabel("COLLEGE CRM"); 
        logo.setForeground(new Color(90,220,255)); 
        logo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        sidebar.add(logo); 
        sidebar.add(Box.createVerticalStrut(30));
        
        sidebar.add(menuLabel("🎓 Students"));
        sidebar.add(menuLabel("📊 Dashboard"));
        sidebar.add(menuLabel("👨‍🏫 Faculty"));
        sidebar.add(menuLabel("💰 Fees"));
        sidebar.add(menuLabel("📚 Courses"));
        
        JLabel active = new JLabel("📅 Attendance"); 
        active.setOpaque(true); 
        active.setBackground(new Color(20,35,100)); 
        active.setForeground(Color.WHITE);
        active.setFont(new Font("Segoe UI", Font.BOLD, 13)); 
        active.setBorder(BorderFactory.createEmptyBorder(12,15,12,15)); 
        sidebar.add(active);

        // Table
        String[] cols = {"ID", "Roll No", "Student Name", "Date", "Status"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model); 
        table.setRowHeight(32);
        
        // Input Panel
        JPanel input = new JPanel(new GridLayout(1,5,10,10));
        input.setBackground(Color.WHITE); 
        input.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        t_roll = new JTextField(); 
        t_roll.setBorder(BorderFactory.createTitledBorder("Roll No"));
        t_name = new JTextField(); 
        t_name.setBorder(BorderFactory.createTitledBorder("Student Name"));
        t_date = new JTextField("2026-05-13"); 
        t_date.setBorder(BorderFactory.createTitledBorder("Date (YYYY-MM-DD)"));
        c_status = new JComboBox<>(new String[]{"Present", "Absent"}); 
        c_status.setBorder(BorderFactory.createTitledBorder("Status"));
        
        JButton add = new JButton("Mark Attendance"); 
        add.setBackground(new Color(0,180,90)); 
        add.setForeground(Color.WHITE);
        add.addActionListener(e -> addAtt());
        
        input.add(t_roll); input.add(t_name); input.add(t_date); 
        input.add(c_status); input.add(add);
        
        JPanel main = new JPanel(new BorderLayout()); 
        main.add(new JScrollPane(table), BorderLayout.CENTER); 
        main.add(input, BorderLayout.SOUTH);
        
        add(sidebar, BorderLayout.WEST); 
        add(main, BorderLayout.CENTER);
        loadData();
    }
    
    private JLabel menuLabel(String text){
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
                else if(text.contains("Faculty")){ new FacultyManagement().setVisible(true); dispose(); }
                else if(text.contains("Fees")){ new FeesManagement().setVisible(true); dispose(); }
                else if(text.contains("Courses")){ new CourseManagement().setVisible(true); dispose(); }
            }
        });
        return l;
    }
    
    private void loadData(){ 
        try{ 
            Connection con=DBConnection.getConnection(); 
            ResultSet rs=con.createStatement().executeQuery("SELECT * FROM attendance ORDER BY att_date DESC"); 
            model.setRowCount(0); 
            while(rs.next()){ 
                model.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5)}); 
            } 
            con.close(); 
        }catch(Exception e){ System.out.println(e); } 
    }
    
    private void addAtt(){ 
        try{ 
            Connection con=DBConnection.getConnection(); 
            PreparedStatement ps=con.prepareStatement("INSERT INTO attendance(roll_no,student_name,att_date,status) VALUES(?,?,?,?)"); 
            ps.setString(1,t_roll.getText()); 
            ps.setString(2,t_name.getText()); 
            ps.setString(3,t_date.getText()); 
            ps.setString(4,c_status.getSelectedItem().toString()); 
            ps.executeUpdate(); 
            con.close(); 
            loadData(); 
            JOptionPane.showMessageDialog(this, "Attendance Mark Ho Gayi - " + t_date.getText()); 
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(this, "Error: Date YYYY-MM-DD me daal! " + e.getMessage()); 
        } 
    }
    
    public static void main(String[] args){ 
        SwingUtilities.invokeLater(() -> new AttendanceManagement().setVisible(true)); 
    }
}