package com.danieltesting.jpa_relations_test.repositories;

import com.danieltesting.jpa_relations_test.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
