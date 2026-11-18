package com.kiet.hostelmanagementmaven.controller;

import com.kiet.hostelmanagementmaven.entity.HostelStudent;
import com.kiet.hostelmanagementmaven.repository.HostelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/students")
public class HostelController {

    @Autowired
    private HostelRepository repository;

    // POST - Add Student
    @PostMapping
    public HostelStudent addStudent(@RequestBody HostelStudent student) {
        return repository.save(student);
    }

    // GET - All Students
    @GetMapping
    public List<HostelStudent> getAllStudents() {
        return repository.findAll();
    }

    // GET - Student By ID
    @GetMapping("/{id}")
    public ResponseEntity<HostelStudent> getStudent(@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT - Update Student
    @PutMapping("/{id}")
    public ResponseEntity<HostelStudent> updateStudent(
            @PathVariable int id,
            @RequestBody HostelStudent newStudent) {

        return repository.findById(id).map(student -> {

            student.setStudentName(newStudent.getStudentName());
            student.setRollNumber(newStudent.getRollNumber());
            student.setHostelName(newStudent.getHostelName());
            student.setRoomNumber(newStudent.getRoomNumber());
            student.setFee(newStudent.getFee());

            return ResponseEntity.ok(repository.save(student));

        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE - Remove Student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Student deleted successfully");
        }

        return ResponseEntity.status(404).body("Student not found");
    }
}