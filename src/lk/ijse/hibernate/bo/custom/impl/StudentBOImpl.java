package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.StudentBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Student;

import java.util.ArrayList;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public class StudentBOImpl implements StudentBO {

    // Property Injection
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public ArrayList<StudentDTO> getAllStudents() throws Exception {
        ArrayList<StudentDTO> allStudents = new ArrayList<>();
        for (Student entity : studentDAO.getAll()) {
            allStudents.add(new StudentDTO(
                    entity.getStudent_id(),
                    entity.getName(),
                    entity.getAddress(),
                    entity.getContact_no(),
                    entity.getDob(),
                    entity.getGender()
            ));
        }
        return allStudents;
    }

    @Override
    public StudentDTO searchStudent(String studentID) throws Exception {
        Student entity = studentDAO.search(studentID);
        if (entity != null) {
            return new StudentDTO(
                    entity.getStudent_id(),
                    entity.getName(),
                    entity.getAddress(),
                    entity.getContact_no(),
                    entity.getDob(),
                    entity.getGender()
            );
        }
        return null;
    }

    @Override
    public boolean studentIsExists(String studentID) throws Exception {
        return studentDAO.isExists(studentID);
    }

    @Override
    public boolean saveStudent(StudentDTO dto) throws Exception {
        return studentDAO.save(new Student(dto.getStudent_id(), dto.getName(), dto.getAddress(), dto.getContact_no(), dto.getDob(), dto.getGender()));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws Exception {
        return studentDAO.update(new Student(dto.getStudent_id(), dto.getName(), dto.getAddress(), dto.getContact_no(), dto.getDob(), dto.getGender()));
    }

    @Override
    public boolean deleteStudent(String studentID) throws Exception {
        return studentDAO.delete(studentID);
    }
}
