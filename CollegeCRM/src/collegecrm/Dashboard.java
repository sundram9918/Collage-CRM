package collegecrm;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Dashboard extends JFrame {

    JLabel l_students, l_faculty, l_revenue, l_due;

    public Dashboard() {
        setTitle("College CRM - Dashboard | Manglesh Tiwari");
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

        sidebar.add(menu(" Students"));
sidebar.add(menuActive(" Dashboard"));
sidebar.add(menu(" Faculty"));
sidebar.add(menu(" Fees"));
sidebar.add(menu(" Courses"));      
sidebar.add(menu(" Attendance"));    

        // MAIN CONTENT
        JPanel main = new JPanel();
        main.setLayout(new GridLayout(2,2,20,20));
        main.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        main.setBackground(new Color(240,244,248));

        l_students = card("Total Students", "0", new Color(0,102,255));
        l_faculty = card("Total Faculty", "0", new Color(255,140,0));
        l_revenue = card("Total Revenue (Paid)", "₹ 0", new Color(0,180,90));
        l_due = card("Total Due", "₹ 0", new Color(220,50,50));

        main.add(createCardPanel(l_students, "Total Students", new Color(0,102,255)));
        main.add(createCardPanel(l_faculty, "Total Faculty", new Color(255,140,0)));
        main.add(createCardPanel(l_revenue, "Total Revenue (Paid)", new Color(0,180,90)));
        main.add(createCardPanel(l_due, "Total Due", new Color(220,50,50)));

        add(sidebar, BorderLayout.WEST);
        add(main, BorderLayout.CENTER);
        
        loadCounts();
    }

    private JPanel createCardPanel(JLabel valueLabel, String title, Color color){
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220,220,220)),
            BorderFactory.createEmptyBorder(20,20,20,20)
        ));
        
        JLabel t = new JLabel(title);
        t.setFont(new Font("Segoe UI", Font.BOLD, 14));
        t.setForeground(Color.GRAY);
        
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        valueLabel.setForeground(color);

        p.add(t, BorderLayout.NORTH);
        p.add(valueLabel, BorderLayout.CENTER);
        return p;
    }

    private JLabel card(String a, String b, Color c){ return new JLabel(b); }

    private JLabel menu(String text){
    JLabel l = new JLabel(text);
    l.setForeground(Color.WHITE);
    l.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    l.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 10));
    l.setCursor(new Cursor(Cursor.HAND_CURSOR));
    
    l.addMouseListener(new java.awt.event.MouseAdapter(){
        public void mouseClicked(java.awt.event.MouseEvent evt){
            String t = text.trim();
            if(t.equals("Students")){
                new StudentManagement().setVisible(true);
            } else if(t.equals("Faculty")){
                new FacultyManagement().setVisible(true);
            } else if(t.equals("Fees")){
                new FeesManagement().setVisible(true);
            } else if(t.equals("Courses")){
                new CourseManagement().setVisible(true);
            } else if(t.equals("Attendance")){
                new AttendanceManagement().setVisible(true);
            }
            dispose();
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

    private void loadCounts(){
        try{
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            
            ResultSet rs1 = st.executeQuery("SELECT COUNT(*) FROM students");
            if(rs1.next()) l_students.setText(rs1.getString(1));

            ResultSet rs2 = st.executeQuery("SELECT COUNT(*) FROM faculty");
            if(rs2.next()) l_faculty.setText(rs2.getString(1));

            ResultSet rs3 = st.executeQuery("SELECT SUM(paid_amount) FROM fees");
            if(rs3.next() && rs3.getString(1) != null) l_revenue.setText("₹ " + rs3.getString(1));
            else l_revenue.setText("₹ 0");

            ResultSet rs4 = st.executeQuery("SELECT SUM(due_amount) FROM fees");
            if(rs4.next() && rs4.getString(1) != null) l_due.setText("₹ " + rs4.getString(1));
            else l_due.setText("₹ 0");

            con.close();
        }catch(Exception e){ JOptionPane.showMessageDialog(this, "Dashboard Error: "+e.getMessage()); }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new Dashboard().setVisible(true));
    }
}