package com.danieltesting.jpa_relations_test.repositories;

import com.danieltesting.jpa_relations_test.entity.Guardian;
import com.danieltesting.jpa_relations_test.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@DataJpaTest //This one is to test the different repository layers, then after the test is completed we can flush the
             //data, but here we want to affect the database, that's why it is marked as comment
class StudentRepositoryTest {

//    This test don't work anymore, please use the REST endpoints

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student dan = Student.builder()
                .emailId("dan@gmail.com")
                .firstName("Dan")
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
                .emailId("dan@gmail.com")
                .firstName("Dan")
                .lastName("Gon")
                .guardian(who)
                .build();

        studentRepository.save(dan);
    }



}