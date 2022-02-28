package com.danieltesting.jpa_relations_test.controllers;

import com.danieltesting.jpa_relations_test.entity.Student;
import com.danieltesting.jpa_relations_test.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "studentApp/v1/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @GetMapping(path = "/search/{firstName}")
    public List<Student> getStudentsByName(@PathVariable("firstName") String firstName) {
        return studentService.getStudentsByName(firstName);
    }

    @GetMapping(path = "/search/contain/{firstName}")
    public List<Student> getStudentsByNameContain(@PathVariable("firstName") String firstName) {
        return studentService.getStudentsByNameContain(firstName);
    }

    @GetMapping(path = "/search/guardianName/{name}")
    public List<Student> getStudentsByGuarianName(@PathVariable("name") String name) {
        return studentService.getStudentsByGuardianName(name);
    }

    @GetMapping(path = "/search/singleStudent/{firstName}&{lastName}")
    public Student getStudentByFirstAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        return studentService.getStudentByFirstAndLastName(firstName, lastName );
    }

    @GetMapping(path = "/search/email/{email}")
    public Student getStudentByEmailId(@PathVariable("email") String email){
        return studentService.getStudentByEmailId(email);
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent (@PathVariable("studentId") long studentId){ //Here I capture the id from the url to delete
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent (@PathVariable("studentId") long studentId, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName, @RequestParam(required = false) String email){
        studentService.updateStudent(studentId, firstName, lastName, email);
    }

}
