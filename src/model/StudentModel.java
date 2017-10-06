/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ueda
 */
public class StudentModel {
    
    
    public ArrayList<Student> getListStudent(){
        ArrayList<Student> listStudent=new ArrayList<>();
        try {
            Statement stt=DAO.getConnection().createStatement();
            ResultSet rs=stt.executeQuery("select * from student");
            while(rs.next()){
                Student student=new Student();
                student.setId((rs.getInt("id")));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setClassName(rs.getString("class_name"));
                student.setRollNumber(rs.getString("roll_number"));
                listStudent.add(student);
            }
            System.out.println("Get list student success!");
        } catch (SQLException e) {
            System.err.println("Error in getting list of students "+e.getMessage());
        }
        
        return listStudent;
    }
    
    public Student getById(int id){
        try {
             PreparedStatement preStt=DAO.getConnection().prepareStatement("select * from student where id=?");
             preStt.setInt(1, id);
             ResultSet rs=preStt.executeQuery();
             if(rs.next()){
                 Student student=new Student();
                 student.setId(rs.getInt("id"));
                 student.setName(rs.getString("name"));
                 student.setEmail(rs.getString("email"));
                 student.setPhone(rs.getString("phone"));
                 student.setClassName(rs.getString("class_name"));
                 student.setRollNumber(rs.getString("roll_number"));
                 return student;
             }
             
        } catch (SQLException e) {
            System.err.println("Error when getById "+e.getMessage());
        }
       return null;
    }
    
    public void insert(Student student){
        try {
            
            Connection cnn=DAO.getConnection();
            PreparedStatement preStt=cnn.prepareStatement("insert into student (name, email, phone, class_name, roll_number) values(?, ?, ?, ?, ?)");
            

            preStt.setString(1, student.getName());
            preStt.setString(2, student.getEmail());
            preStt.setString(3, student.getPhone());
            preStt.setString(4, student.getClassName());
            preStt.setString(5, student.getRollNumber());
            System.out.println("----------SQL Query----------");
            System.out.println(preStt.toString());
            System.out.println("----------End SQL Query-----------");
            preStt.execute();
            System.out.println("Success!");
        } catch (SQLException e) {
            System.err.println("Error in inserting data "+e.getMessage());
        }
    }
    
    public void update(Student student){
        try {
            Connection cnn=DAO.getConnection();
            String sqlQuery="update student set name=?, email=?, phone=?, class_name=?, roll_number=? where id=?";
            PreparedStatement preStt=cnn.prepareStatement(sqlQuery);
            preStt.setString(1, student.getName());
            preStt.setString(2, student.getEmail());
            preStt.setString(3, student.getPhone());
            preStt.setString(4, student.getClassName());
            preStt.setString(5, student.getRollNumber());
            preStt.setInt(6, student.getId());
            
            int rowsUpdated=preStt.executeUpdate();
            if(rowsUpdated>0){
                System.out.println("Update success!");
            }
        } catch (SQLException e) {
            System.err.println("Error in updating data "+e.getMessage());
        }
    }
    
    public void delete(int id){
        try {
            String sqlQuery="delete from student where id=?";
            PreparedStatement preStt=DAO.getConnection().prepareStatement(sqlQuery);
            preStt.setInt(1, id);
            int rowsDeleted=preStt.executeUpdate();
            if(rowsDeleted>0){
                System.out.println("Deleted success a student!");
            }
        } catch (SQLException e) {
            System.err.println("Error in deleting data "+e.getMessage());
        }
    }
    
    
    
    
}
