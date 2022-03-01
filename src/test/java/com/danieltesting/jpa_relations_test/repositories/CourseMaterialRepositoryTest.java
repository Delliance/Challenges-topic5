package com.danieltesting.jpa_relations_test.repositories;

import com.danieltesting.jpa_relations_test.entity.Course;
import com.danieltesting.jpa_relations_test.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    public void SaveCourseMaterial(){

        Course course = Course.builder()
                .title("Math")
                .credits(6)
                .build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.course1.com")
                .course(course)
                .build();

//        This by itself won't work, it'll throw an exception, because the course MUST be already in the database
//        to solve it we need to do cascading, this is done in the @OneToOne annotation in the CourseMaterial class
        courseMaterialRepository.save(courseMaterial);
    }

}