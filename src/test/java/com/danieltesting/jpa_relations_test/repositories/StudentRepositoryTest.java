package com.danieltesting.jpa_relations_test.repositories;

import com.danieltesting.jpa_relations_test.entity.Guardian;
import com.danieltesting.jpa_relations_test.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
//@DataJpaTest //This one is to test the different repository layers, then after the test is completed we can flush the
             //data, but here we want to affect the database, that's why it is marked as comment
class StudentRepositoryTest {

//    Here I will be testing only the repository, for the service I do it directly from POSTMAN

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student dan = Student.builder()
                .emailId("dan1@gmail.com")
                .firstName("Daniel")
                .lastName("Gon")
//                .guardianName("Who?")
//                .guardianEmail("who@gmail.com")
//                .guardianMobile("123456789")
                .build();

        studentRepository.save(dan);
    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian who = Guardian.builder()
                .name("who")
                .email("who@gmail.com")
                .mobile("123456")
                .build();

        Student dan = Student.builder()
                .emailId("dan2@gmail.com")
                .firstName("Dan")
                .lastName("Gon")
                .guardian(who)
                .build();

        studentRepository.save(dan);
    }

    @Test
    public void printAllStudents(){

        List<Student> studentList = studentRepository.findAll();

        studentList.forEach(System.out::println);
    }

    @Test
    public void printStudentByFirstName(){

        List<Student> studentList = studentRepository.findStudentByFirstName("Dan");

        studentList.forEach(System.out::println);
//        System.out.println("students with name "+"Dan"+": "+studentList);

    }

    @Test
    public void printStudentsNyFirstNameContaining(){

        List<Student> studentList = studentRepository.findStudentByFirstNameContaining("Jo");

        studentList.forEach(System.out::println);
//        System.out.println("Students with "+"Jo"+": "+studentList);
    }

    @Test
    public void printStudentsByGuardianName(){

        List<Student> studentList = studentRepository.findByGuardianName("Who");

        studentList.forEach(System.out::println);
//        System.out.println("Students whose guardian is "+"Who"+": "+studentList.forEach(););
    }

    @Test
    public void printStudentByEmailId(){
        Student student = studentRepository.getStudentByEmailId("dan1@gmail.com");

        System.out.println("student = " + student.toString());
    }

    @Test
    public void printStudentFirstNameByEmailId(){
        String firstName = studentRepository.getStudentFirstNameByEmailId("dan1@gmail.com");

        System.out.println("firstName = " + firstName);
    }

    @Test
    public void printStudentByEmailNative(){
        Student student = studentRepository.getStudentByEmailIdNative("dan1@gmail.com");

        System.out.println("student.toString() = " + student.toString());
    }

    @Test
    public void printStudentByEmailNativeNamedParam(){
        Student student = studentRepository.getStudentByEmailIdNativeNamedParam("dan1@gmail.com");

        System.out.println("student.toString() = " + student.toString());
    }

    @Test
    public void updateStudentNameByEmail(){

        String email = "dan3@gmail.com";

        System.out.println("before = "+studentRepository.getStudentFirstNameByEmailId(email));

        studentRepository.updateStudentFirstNameByEmail("Karl", email);

        System.out.println("after = "+studentRepository.getStudentFirstNameByEmailId(email));

    }



}