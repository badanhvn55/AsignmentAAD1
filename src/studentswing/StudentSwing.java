/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentswing;

import entity.Student;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import model.StudentModel;

/**
 *
 * @author ueda
 */
public class StudentSwing extends JFrame {

    private JLabel id, name, email, phone, className, rollNumber, lblIdMessage, lblNameMessage, lblEmailMessage, lblPhoneMessage, lblClassNameMessage, lblRollNumberMessage, lblIdNew, lblNameNew, lblEmailNew, lblPhoneNew, lblClassNameNew, lblRollNumberNew;
    private JTextField txtId, txtName, txtClassName, txtRollNumber, txtEmail, txtPhone, txtIdNew, txtNameNew, txtEmailNew, txtPhoneNew, txtClassNameNew, txtRollNumberNew;
    private JPanel panel;
    private JButton accept, reset, update, delete, OK;
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private ArrayList<Student> listStudent;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;

    public StudentSwing() {
        super("Student Information");
        this.setSize(1000, 800);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuItem = new JMenuItem("Show List Student");
        menuItem.addActionListener(new table());
        menu.add(menuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        panel = new JPanel();
        panel.setBounds(100, 0, 800, 800);
        panel.setBackground(Color.lightGray);
        panel.setLayout(null);

        id = new JLabel("ID");
        name = new JLabel("Name");
        email = new JLabel("Email");
        phone = new JLabel("Phone");
        className = new JLabel("Class name");
        rollNumber = new JLabel("Roll number");

        txtId = new JTextField();
        txtName = new JTextField();
        txtClassName = new JTextField();
        txtRollNumber = new JTextField();
        txtEmail = new JTextField();
        txtPhone = new JTextField();

        accept = new JButton("Accept");
        reset = new JButton("Reset");

        id.setBounds(150, 50, 100, 40);
        txtId.setBounds(255, 50, 300, 40);

        lblIdMessage = new JLabel();
        lblIdMessage.setBounds(560, 50, 200, 40);

        name.setBounds(150, 100, 100, 40);
        txtName.setBounds(255, 100, 300, 40);

        lblNameMessage = new JLabel();
        lblNameMessage.setBounds(560, 100, 200, 40);

        email.setBounds(150, 150, 100, 40);
        txtEmail.setBounds(255, 150, 300, 40);

        lblEmailMessage = new JLabel();
        lblEmailMessage.setBounds(560, 150, 200, 40);

        phone.setBounds(150, 200, 100, 40);
        txtPhone.setBounds(255, 200, 300, 40);

        lblPhoneMessage = new JLabel();
        lblPhoneMessage.setBounds(560, 200, 200, 40);

        className.setBounds(150, 250, 100, 40);
        txtClassName.setBounds(255, 250, 300, 40);

        lblClassNameMessage = new JLabel();
        lblClassNameMessage.setBounds(560, 250, 200, 40);

        rollNumber.setBounds(150, 300, 100, 40);
        txtRollNumber.setBounds(255, 300, 300, 40);

        lblRollNumberMessage = new JLabel();
        lblRollNumberMessage.setBounds(560, 300, 200, 40);

        accept.setBounds(255, 350, 140, 40);
        reset.setBounds(415, 350, 140, 40);

        reset.addActionListener(new ResetHandle());
        accept.addActionListener(new ButtonHandle());

        
        panel.add(id);
        panel.add(txtId);
        panel.add(name);
        panel.add(txtName);
        panel.add(email);
        panel.add(txtEmail);
        panel.add(phone);
        panel.add(txtPhone);
        panel.add(className);
        panel.add(txtClassName);
        panel.add(rollNumber);
        panel.add(txtRollNumber);
        panel.add(accept);
        panel.add(reset);
        panel.add(lblIdMessage);
        panel.add(lblNameMessage);
        panel.add(lblEmailMessage);
        panel.add(lblPhoneMessage);
        panel.add(lblClassNameMessage);
        panel.add(lblRollNumberMessage);
        this.add(panel);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        StudentSwing stu = new StudentSwing();
    }

    class table implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame j1 = new JFrame();
            j1.setSize(800, 500);
            j1.setLayout(null);

            //list table...
            tableModel = new DefaultTableModel();
            tableModel.addColumn("id");
            tableModel.addColumn("name");
            tableModel.addColumn("email");
            tableModel.addColumn("phone");
            tableModel.addColumn("class name");
            tableModel.addColumn("roll number");

            StudentModel studentModel = new StudentModel();
            listStudent = studentModel.getListStudent();
            listStudent.forEach(student -> {
                Object[] arr = new Object[]{
                    student.getId(), student.getName(), student.getEmail(), student.getPhone(), student.getClassName(), student.getRollNumber()
                };
                tableModel.addRow(arr);
            });
            table = new JTable(tableModel);
            scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setBounds(50, 50, 500, 250);
            table.setAutoResizeMode(MAXIMIZED_BOTH);
            update = new JButton("Update");
            delete = new JButton("Delete");
            update.setBounds(600, 50, 90, 30);
            delete.setBounds(600, 100, 90, 30);
            update.addActionListener(new UpdateDB());
            delete.addActionListener(new deleteRow());
            
            j1.add(scrollPane);
            j1.add(update);
            j1.add(delete);
            j1.setVisible(true);
            j1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

    }

    class ButtonHandle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            StudentModel stuModel = new StudentModel();
            Student stu = new Student();
            try {
                stu.setId(Integer.parseInt(txtId.getText()));
            } catch (NumberFormatException ex) {
                lblIdMessage.setText("Please enter number " + ex);
            }

            stu.setName(txtName.getText());
            stu.setEmail(txtEmail.getText());
            stu.setPhone(txtPhone.getText());
            stu.setClassName(txtClassName.getText());
            stu.setRollNumber(txtRollNumber.getText());

            HashMap<String, String> errors = new FormHandle().validateLogin(stu);
            if (errors.size() != 0) {
                showError(errors);
            } else {
                stuModel.insert(stu);
                JOptionPane.showConfirmDialog(null, "Are you sure?\nID: " + stu.getId() + "\nName: " + stu.getName()
                        + "\nEmail: " + stu.getEmail() + "\nPhone: " + stu.getPhone() + "\nClass name: " + stu.getClassName()
                        + "\nRoll number: " + stu.getRollNumber());
                JOptionPane.showMessageDialog(null, "Login success!\n- Welcome " + stu.getName() + " to my world -");
            }
        }

    }

    class ResetHandle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            txtId.setText("");
            txtName.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            txtClassName.setText("");
            txtRollNumber.setText("");
        }

    }

    class UpdateDB implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int selectedRow = table.getSelectedRow();
            Student oldStu = listStudent.get(selectedRow);
            JFrame f = new JFrame("changed");
            f.setSize(600, 500);
            f.setLayout(null);

            lblIdNew = new JLabel("New ID");
            lblNameNew = new JLabel("New Name");
            lblEmailNew = new JLabel("New Email");
            lblPhoneNew = new JLabel("New Phone");
            lblClassNameNew = new JLabel("New Class Name");
            lblRollNumberNew = new JLabel("New Roll Number");

            txtIdNew = new JTextField();
            txtNameNew = new JTextField();
            txtEmailNew = new JTextField();
            txtPhoneNew = new JTextField();
            txtClassNameNew = new JTextField();
            txtRollNumberNew = new JTextField();

            OK = new JButton("OK");

            lblIdNew.setBounds(50, 50, 100, 30);
            txtIdNew.setBounds(155, 50, 250, 30);

            lblNameNew.setBounds(50, 90, 100, 30);
            txtNameNew.setBounds(155, 90, 250, 30);

            lblEmailNew.setBounds(50, 130, 100, 30);
            txtEmailNew.setBounds(155, 130, 250, 30);

            lblPhoneNew.setBounds(50, 170, 100, 30);
            txtPhoneNew.setBounds(155, 170, 250, 30);

            lblClassNameNew.setBounds(50, 210, 100, 30);
            txtClassNameNew.setBounds(155, 210, 250, 30);

            lblRollNumberNew.setBounds(50, 250, 100, 30);
            txtRollNumberNew.setBounds(155, 250, 250, 30);

            OK.setBounds(155, 290, 250, 30);
            OK.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    StudentModel studentModel = new StudentModel();
                    Student student = new Student();
                    try {
                        student.setId(Integer.parseInt(txtIdNew.getText()));
                    } catch (NumberFormatException ex) {
                        lblIdMessage.setText("Please enter number " + ex);
                    }

                    student.setName(txtNameNew.getText());
                    student.setEmail(txtEmailNew.getText());
                    student.setPhone(txtPhoneNew.getText());
                    student.setClassName(txtClassNameNew.getText());
                    student.setRollNumber(txtRollNumberNew.getText());
                    studentModel.update(student);
                    JOptionPane.showConfirmDialog(null, "- Old information will changed -\n" + "ID: " + student.getId()
                            + "\nName: " + student.getName() + "\nEmail: " + student.getEmail() + "\nPhone: " + student.getPhone()
                            + "\nClass Name: " + student.getClassName() + "\nRoll Number: " + student.getRollNumber()
                            + "\n- You are sure? -"
                    );
                    JOptionPane.showMessageDialog(null, "Update success!");
                }
            });

            f.add(lblIdNew);
            f.add(txtIdNew);
            f.add(lblNameNew);
            f.add(txtNameNew);
            f.add(lblEmailNew);
            f.add(txtEmailNew);
            f.add(lblPhoneNew);
            f.add(txtPhoneNew);
            f.add(lblClassNameNew);
            f.add(txtClassNameNew);
            f.add(lblRollNumberNew);
            f.add(txtRollNumberNew);
            f.add(OK);
            f.setVisible(true);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        }

    }
    
    class deleteRow implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Student student=new Student();
            int selectedRow = table.getSelectedRow();
            
            StudentModel studentModel=new StudentModel();
            studentModel.delete(selectedRow);
            JOptionPane.showMessageDialog(null, "Delete success!");
        }
        
    }
    

    private void showError(HashMap<String, String> errors) {
        if (errors.containsKey("txtId")) {
            lblIdMessage.setForeground(Color.red);
            lblIdMessage.setText(errors.get("txtId"));
        } else {
            lblIdMessage.setForeground(Color.green);
            lblIdMessage.setText("OK");
        }

        if (errors.containsKey("txtName")) {
            lblNameMessage.setForeground(Color.red);
            lblNameMessage.setText(errors.get("txtName"));
        } else {
            lblNameMessage.setForeground(Color.green);
            lblNameMessage.setText("OK");
        }

        if (errors.containsKey("txtEmail")) {
            lblEmailMessage.setForeground(Color.red);
            lblEmailMessage.setText(errors.get("txtEmail"));
        } else {
            lblEmailMessage.setForeground(Color.green);
            lblEmailMessage.setText("OK");
        }

        if (errors.containsKey("txtPhone")) {
            lblPhoneMessage.setForeground(Color.red);
            lblPhoneMessage.setText(errors.get("txtPhone"));
        } else {
            lblPhoneMessage.setForeground(Color.green);
            lblPhoneMessage.setText("OK");
        }

        if (errors.containsKey("txtClassName")) {
            lblClassNameMessage.setForeground(Color.red);
            lblClassNameMessage.setText(errors.get("txtClassName"));
        } else {
            lblClassNameMessage.setForeground(Color.green);
            lblClassNameMessage.setText("OK");
        }

        if (errors.containsKey("txtRollNumber")) {
            lblRollNumberMessage.setForeground(Color.red);
            lblRollNumberMessage.setText(errors.get("txtRollNumber"));
        } else {
            lblRollNumberMessage.setForeground(Color.green);
            lblRollNumberMessage.setText("OK");
        }

    }

}
