package com.example.student.controller;

import com.example.student.model.Student;
import com.example.student.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService srv;
    public StudentController(StudentService srv) { this.srv = srv; }
    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(srv.getAllStudents());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable String id) {
        Student s = srv.getStudentById(id);
        return s != null ? ResponseEntity.ok(s) : ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<String> add (@RequestBody Student s) {
        return srv.addStudent(s) ? ResponseEntity.ok("success") : ResponseEntity.badRequest().body("failed");
    }
    @PutMapping("/{id}/score")
    public ResponseEntity<String> updateScore(@PathVariable String id, @RequestParam double m, @RequestParam double l, @RequestParam double e) {
        return srv.inputScore(id, m, l, e) ? ResponseEntity.ok("updated") : ResponseEntity.badRequest().body("failed");
    }
    @PutMapping
    public ResponseEntity<String> update (@RequestBody Student s) {
        return srv.updateStudent(s) ? ResponseEntity.ok("updated") : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return srv.deleteStudent(id) ? ResponseEntity.ok("deleted") : ResponseEntity.notFound().build();
    }
}