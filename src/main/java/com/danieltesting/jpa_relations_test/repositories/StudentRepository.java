package com.danieltesting.jpa_relations_test.repositories;

import com.danieltesting.jpa_relations_test.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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

//    I can specify what field to get, not necessary the whole student:
    @Query("SELECT s.firstName FROM Student s WHERE s.emailId = ?1")
    String getStudentFirstNameByEmailId(String emailId);

//    Here another example using native query, here the email, is the name of the column in the table from the database
    @Query(
            value = "SELECT * FROM tbl_student s WHERE s.email_address = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailIdNative(String emailId);

//    Another example with native, but here I specify the name of the parameter to make it more readable
    @Query(
            value = "SELECT * FROM tbl_student s WHERE s.email_address = :emailId",
            nativeQuery = true
    )
    Student getStudentByEmailIdNativeNamedParam(@Param("emailId") String emailId);

//    This method updates what is in the database, that is the reason, why it is @Modifying and @Transactional
    @Modifying
    @Transactional
    @Query(
            value = "UPDATE tbl_student SET first_name = ?1 WHERE email_address = ?2",
            nativeQuery = true
    )
    int updateStudentFirstNameByEmail(String firstName, String emailId);


}
