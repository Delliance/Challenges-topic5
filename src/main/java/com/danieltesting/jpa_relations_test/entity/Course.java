package com.danieltesting.jpa_relations_test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    @Column(
            name = "course_id",
            updatable = false
    )
    private Long courseId;
    private String title;
    private int credits;

//    This is to allow a two-way relationship, without this it'll be just a relation in one direction
//    This will also also allow that when you fetch this Class, you also fetch the CourseMaterial
    @OneToOne(
            mappedBy = "course" //it is mapped from course in the CourseMaterial Class
    )
    private CourseMaterial courseMaterial;

//    If you make this @ManyToOne here, it is the same as if we do it @OneToMany in the professor class, but this one
//    is easier to read
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacher_id"
    )
    private Teacher teacher;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
//    In a manyToMany relation, we need an intermediary table, not just a column
    @JoinTable(
            name = "student_course_map",
            joinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "course_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "id" //this is the name of the id column from students
            )
    )
    private List<Student> studentList;

    public void addStudents(Student student){
        if (studentList == null) studentList = new ArrayList<>();
        studentList.add(student);
    }

}
