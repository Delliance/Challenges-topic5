package com.danieltesting.jpa_relations_test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
        cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "course_id", //here any name we want to give, I chose the same as the reference Column
            referencedColumnName = "course_id" //Here we want that the relation is based on the ID of the course, sine it is unique
    )
    private Course course;


}
