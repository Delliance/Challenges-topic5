package com.danieltesting.jpa_relations_test.services;

import com.danieltesting.jpa_relations_test.entity.Student;
import com.danieltesting.jpa_relations_test.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public List<Student> getStudentsByName(String name){
        return studentRepository.findStudentByFirstName(name);
    }

    public List<Student> getStudentsByNameContain(String name){
        return studentRepository.findStudentByFirstNameContaining(name);
    }

    public List<Student> getStudentsByGuardianName(String name){
        return studentRepository.findByGuardianName(name);
    }

    public Student getStudentByFirstAndLastName(String firstName, String lastName){
        return studentRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public Student getStudentByEmailId(String email){
        return studentRepository.getStudentByEmailId(email);
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmailId(student.getEmailId());

        if (studentOptional.isPresent()){
            throw new IllegalStateException("Email taken");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(long studentId){
        boolean idExists = studentRepository.existsById(studentId);

        if (!idExists){
            throw new IllegalStateException("Student with the ID "+studentId+" does not exist");
        }

        studentRepository.deleteById(studentId);
    }

    @Transactional //Here I'm just going to update the students data not the guardian
    public void updateStudent(long studentId, String firstName, String lastName, String email){
        Student student = studentRepository.findById(studentId).orElseThrow( () ->
                new IllegalStateException("Student with the ID \"+studentId+\" does not exist"));

        if (firstName != null && firstName.length() > 0 && !Objects.equals(student.getFirstName(), firstName)){
            student.setFirstName(firstName);
        }

        if (lastName != null && lastName.length() > 0 && !Objects.equals(student.getLastName(), lastName)){
            student.setLastName(lastName);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmailId(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmailId(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email taken");
            }
            student.setEmailId(email);
        }

    }

}
