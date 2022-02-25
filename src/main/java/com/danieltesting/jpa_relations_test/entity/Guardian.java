package com.danieltesting.jpa_relations_test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

//I don't need a table of guardians, but to embed them into the list of students
@Embeddable
//This @Data adds getters, setters, toString, equalsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //just using it for testing
//We need the @AttributeOverride, because it was in the Student table, and we are now giving the values from here
@AttributeOverrides({
        @AttributeOverride(
                name = "name",
                column = @Column(name = "guardian_name") //here we override the column guardian_name, that is how it is called in the database
        ),
        @AttributeOverride(
                name = "email",
                column = @Column(name = "guardian_email") //here we override the column guardian_email, that is how it is called in the database
        ),
        @AttributeOverride(
                name = "mobile",
                column = @Column(name = "guardian_mobile") //here we override the column guardian_email, that is how it is called in the database
        )
})
public class Guardian {
//    Since they are in their own class, is not necessary this naming, I just leave them here to remember their names in the Student table
//    private String guardianName;
//    private String guardianEmail;
//    private String guardianMobile;
    private String name;
    private String email;
    private String mobile;

}
