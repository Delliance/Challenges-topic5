package com.danieltesting.jpa_relations_test.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//Builder won't work without an AllArgsConstructor, just using it for testing
@Builder
@Entity (name = "Student")
@Table (
        name = "tbl_student",
        uniqueConstraints = {
            @UniqueConstraint(
                    name = "unique_email_address", columnNames = "email_address"
            )
        }
)
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private long studentId;

    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;

    @Column (
            name = "email_address",
            nullable = false
    )
    private String emailId;

//    I'm embedding the guardian with @Embeddable in the class to the student
    @Embedded
    private Guardian guardian;
}
