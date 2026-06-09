package com.example.student.controller;

import com.example.student.model.Student;
import com.example.student.service.StudentService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/students")
@Validated
public class StudentController {
    private final StudentService srv;
    public StudentController(StudentService srv) { this.srv = srv; }
    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(srv.getAllStudents());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable @NotBlank String id) {
        Student s = srv.getStudentById(id);
        return s != null ? ResponseEntity.ok(s) : ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<String> add (@RequestBody Student s) {
        try {
            if (s == null)
                return ResponseEntity.badRequest().body("failed");
            if (s.getId() == null || s.getId().trim().isEmpty())
                return ResponseEntity.badRequest().body("failed");
            if (s.getName() == null || s.getName().trim().isEmpty())
                return ResponseEntity.badRequest().body("failed");
            if (s.getAge() < 1 || s.getAge() > 100)
                return ResponseEntity.badRequest().body("failed");
            if (s.getGender() == null)
                return ResponseEntity.badRequest().body("failed");
            if (s.getAddress() == null || s.getAddress().trim().isEmpty())
                return ResponseEntity.badRequest().body("failed");
            if (s.getClassId() == null || s.getClassId().trim().isEmpty())
                return ResponseEntity.badRequest().body("failed");
            if (s.getMathScore() < 0 || s.getMathScore() > 10)
                return ResponseEntity.badRequest().body("failed");
            if (s.getLiteratureScore() < 0 || s.getLiteratureScore() > 10)
                return ResponseEntity.badRequest().body("failed");
            if (s.getEnglishScore() < 0 || s.getEnglishScore() > 10)
                return ResponseEntity.badRequest().body("failed");
            return srv.addStudent(s) ? ResponseEntity.ok("success") : ResponseEntity.badRequest().body("failed");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("failed");
        }
    }
    @PutMapping("/{id}/score")
    public ResponseEntity<String> updateScore(@PathVariable @NotBlank String id, @RequestParam @Min(0) @Max(10) double m, @RequestParam @Min(0) @Max(10) double l, @RequestParam @Min(0) @Max(10) double e) {
        return srv.inputScore(id, m, l, e) ? ResponseEntity.ok("updated") : ResponseEntity.badRequest().body("failed");
    }
    @PutMapping
    public ResponseEntity<String> update (@RequestBody Student s) {
        return srv.updateStudent(s) ? ResponseEntity.ok("updated") : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotBlank String id) {
        return srv.deleteStudent(id) ? ResponseEntity.ok("deleted") : ResponseEntity.notFound().build();
    }
}