package com.danieltesting.jpa_relations_test.repositories;

import com.danieltesting.jpa_relations_test.entity.Course;
import com.danieltesting.jpa_relations_test.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){

//        This is an example, but it is better if we have a different method to get the courses
        Course course1 =
                Course.builder()
                        .title("Physics")
                        .credits(7)
                        .build();

             Course course2 =
                Course.builder()
                        .title("Geometry")
                        .credits(3)
                        .build();

             Course course3 =
                Course.builder()
                        .title("History")
                        .credits(2)
                        .build();

             Course course4 =
                Course.builder()
                        .title("Spanish")
                        .credits(2)
                        .build();

        Teacher teacher =
                Teacher.builder()
                        .firstName("Teacher1")
                        .lastName("PHD")
//                        .courseList(List.of(course1, course2, course3, course4))
                        .build();

        teacherRepository.save(teacher);
    }

}