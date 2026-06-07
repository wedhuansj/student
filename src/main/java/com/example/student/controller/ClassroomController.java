package com.example.student.controller;
import com.example.student.model.Classroom;
import com.example.student.service.ClassroomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {
    private final ClassroomService srv;
    public ClassroomController(ClassroomService srv) { this.srv = srv; }
    @GetMapping
    public ResponseEntity<List<Classroom>> getAll() {
        return ResponseEntity.ok(srv.getAll());
    }
    @GetMapping("/{id}/students")
    public ResponseEntity<Classroom> getStudents(@PathVariable String id) {
        Classroom c = srv.getStudents(id);
        return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<String> add (@RequestParam String id, @RequestParam String name, @RequestParam String tId) {
        return srv.addNewClass(id, name, tId) ? ResponseEntity.ok("success") : ResponseEntity.badRequest().body("failed");
    }
}
