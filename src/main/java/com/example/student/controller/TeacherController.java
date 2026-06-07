package com.example.student.controller;
import com.example.student.model.Teacher;
import com.example.student.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService srv;
    public TeacherController(TeacherService srv) { this.srv = srv; }
    @GetMapping
    public ResponseEntity<List<Teacher>> getAll() {
        return ResponseEntity.ok(srv.getAllTeachers());
    }
    @PostMapping
    public ResponseEntity<String> add (@RequestBody Teacher t) {
        return srv.addTeacher(t) ? ResponseEntity.ok("success") : ResponseEntity.badRequest().body("failed");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return srv.deleteTeacher(id) ? ResponseEntity.ok("deleted") : ResponseEntity.notFound().build();
    }
}
