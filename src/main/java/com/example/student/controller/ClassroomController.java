package com.example.student.controller;
import com.example.student.model.Classroom;
import com.example.student.service.ClassroomService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/classrooms")
@Validated
public class ClassroomController {
    private final ClassroomService srv;
    public ClassroomController(ClassroomService srv) { this.srv = srv; }
    @GetMapping
    public ResponseEntity<List<Classroom>> getAll() {
        return ResponseEntity.ok(srv.getAll());
    }
    @GetMapping("/{id}/students")
    public ResponseEntity<Classroom> getStudents(@PathVariable @NotBlank String id) {
        Classroom c = srv.getStudents(id);
        return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<String> add (@RequestParam @NotBlank String id, @RequestParam @NotBlank String name, @RequestParam @NotBlank String tId) {
        return srv.addNewClass(id, name, tId) ? ResponseEntity.ok("success") : ResponseEntity.badRequest().body("failed");
    }
}
