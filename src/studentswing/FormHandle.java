/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentswing;

import entity.Student;
import java.util.HashMap;
import validate.Validator;

/**
 *
 * @author ueda
 */
public class FormHandle {

    public HashMap<String, String> validateLogin(Student stu) {
        HashMap<String, String> errors = new HashMap<>();
        if (stu.getId()==0) {
            errors.put("txtId", "Please type id");
        }
        if (stu.getName().isEmpty()) {
            errors.put("txtName", "Please type name");
        }
        if (stu.getEmail().isEmpty()) {
            errors.put("txtEmail", "Please type email");
        }else if(Validator.isEmailValid(stu.getEmail())==false){
            errors.put("txtEmail", "Email wrong format");
        }

        if (stu.getPhone().isEmpty()) {
            errors.put("txtPhone", "please type phone");
        }else if(Validator.isPhoneValid(stu.getPhone())==false){
            errors.put("txtPhone", "Phone wrong format");
        }

        if (stu.getClassName().isEmpty()) {
            errors.put("txtClassName", "Please type class name");
        }
        if (stu.getRollNumber().isEmpty()) {
            errors.put("txtRollNumber", "Please type roll number");
        }

        return errors;
    }

}
