package com.danieltesting.jpa_relations_test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher {

    @Id
    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence"
    )
    @Column(
            name = "teacher_id",
            updatable = false
    )
    private Long teacherId;
    private String firstName;
    private String lastName;


//    THIS IS A RELATION ONE TO MANY, BUT IT IS SUGGESTED TO USE @ManyToOne SINCE IT IS EASIER TO READ, SO THIS IS
//    REPLACED BY THE @ManyToOne IN THE COURSE
//    @OneToMany(
//            cascade = CascadeType.ALL
//    )
//    @JoinColumn(
//            name = "teacher_id",
//            referencedColumnName = "teacher_id"
//    )
//    private List <Course> courseList;

}
