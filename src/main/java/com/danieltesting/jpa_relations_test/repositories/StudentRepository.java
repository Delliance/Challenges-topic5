package com.danieltesting.jpa_relations_test.repositories;

import com.danieltesting.jpa_relations_test.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
//    THIS ONE IS DIFFERENT FROM THE METHOD getStudentByEmailId
    Optional<Student> findStudentByEmailId(String emailId);
    List<Student> findStudentByFirstName(String firstName);
    List<Student> findStudentByFirstNameContaining(String firstName);
//    Notice that here I call first the parameter "Guardian" that was defined in the Student Class, then I call the
//    parameter from the Guardian class, the Name
    List<Student> findByGuardianName(String guardianName);
//    Result with singular student, not list
    Student findByFirstNameAndLastName(String firstName, String lastName);

//    Here an example using JPQL, JPQL are based on the attributes you create not how they are named in the table
    @Query("SELECT s FROM Student s WHERE s.emailId = ?1")
    Student getStudentByEmailId(String emailId);
}
