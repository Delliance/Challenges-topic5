package com.danieltesting.jpa_relations_test.repositories;

import com.danieltesting.jpa_relations_test.entity.Course;
import com.danieltesting.jpa_relations_test.entity.Student;
import com.danieltesting.jpa_relations_test.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses(){
        List<Course> courseList = courseRepository.findAll();

        courseList.forEach(System.out::println);
    }

    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher2 =
                Teacher.builder()
                        .firstName("Teacher 2")
                        .lastName("MSC")
                        .build();

        Course course =
                Course.builder()
                        .title("Biology")
                        .credits(4)
                        .teacher(teacher2)
                        .build();

        courseRepository.save(course);

    }

    @Test //This test is the pagination, it only means to show all the objects from the database
    public  void  findAllPagination(){

//        BE CAREFUL WHAT YOU IMPORT, YOU NEED org.springframework.data.domain
        Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);

        Pageable secondPageWithTwoRecords = PageRequest.of(1, 2);

        List <Course> courseList = courseRepository.findAll(secondPageWithTwoRecords).getContent();

        Long totalElements = courseRepository.findAll(secondPageWithTwoRecords).getTotalElements();

        int totalPages = courseRepository.findAll(secondPageWithTwoRecords).getTotalPages();

        System.out.println("totalPages = " + totalPages);

        System.out.println("totalElements = " + totalElements);

        System.out.println("courseList = " + courseList);

    }

    @Test
    public void findAllSorting (){

        Pageable sortByName = PageRequest.of(0, 3, Sort.by("title")); // here the name of the column you want to sort

        Pageable sortByCreditDex = PageRequest.of(0, 2 , Sort.by("credits").descending());

        Pageable sortByTitleAndCreditAsc = PageRequest.of(0, 4, Sort.by("title").ascending().and(Sort.by("credits")));

        List<Course> courseList = courseRepository.findAll(sortByTitleAndCreditAsc).getContent();

        System.out.println("courseList = " + courseList);

    }

    @Test
    public void printFindByTitleContaining(){
        Pageable firstPageTenRecords = PageRequest.of(0, 10);

        List<Course> courseList = courseRepository.findByTitleContaining(
                "y",
                firstPageTenRecords
        ).getContent();

        System.out.println("courseList = " + courseList);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Teacher3")
                .lastName("Monte")
                .build();

        Student student = Student.builder()
                .firstName("Sofia")
                .lastName("Something")
                .emailId("sfia@gmail.com")
                .build();

        Course course = Course.builder()
                .title("Social Sciences")
                .credits(2)
                .teacher(teacher)
                .build();

        course.addStudents(student);

        courseRepository.save(course);
    }

}