package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.StudentDTO;

import java.util.ArrayList;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public interface StudentBO  extends SuperBO {
    ArrayList<StudentDTO> getAllStudents() throws Exception;

    StudentDTO searchStudent(String studentID) throws Exception;

    boolean studentIsExists(String studentID) throws Exception;

    boolean saveStudent(StudentDTO dto) throws Exception;

    boolean updateStudent(StudentDTO dto) throws Exception;

    boolean deleteStudent(String studentID) throws Exception;
}
