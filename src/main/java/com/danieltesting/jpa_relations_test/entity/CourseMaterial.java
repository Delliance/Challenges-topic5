package com.danieltesting.jpa_relations_test.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//We are ding lazy Fetch, so if you don't exclude the course it'll give an exception
@ToString(exclude = "course")
public class CourseMaterial {

    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    @Column(
            name = "course_material_id",
            updatable = false
    )
    private Long courseMaterialId;
    private String url;

    @OneToOne( //Here we are defining that for one course there will be one course material
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, //LAZY: When you call the CourseMaterial, it does not call the info of Course, unless specified; EAGER: when you call the course material, it also calls the info of the course
            optional = false //this is so when you try to save a course, a course material is required
    )
    @JoinColumn(
            name = "course_id", //here any name we want to give, I chose the same as the reference Column
            referencedColumnName = "course_id" //Here we want that the relation is based on the ID of the course, sine it is unique
    )
    private Course course;


}
