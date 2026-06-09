package com.example.student.controller;
import com.example.student.model.Teacher;
import com.example.student.service.TeacherService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/teachers")
@Validated
public class TeacherController {
    private final TeacherService srv;
    public TeacherController(TeacherService srv) { this.srv = srv; }
    @GetMapping
    public ResponseEntity<List<Teacher>> getAll() {
        return ResponseEntity.ok(srv.getAllTeachers());
    }
    @PostMapping
    public ResponseEntity<String> add (@RequestBody Teacher t) {
        try {
            if (t.getId() == null && t.getId().trim().isEmpty())
                return ResponseEntity.badRequest().body("failed");
            if (t.getName() == null && t.getName().trim().isEmpty())
                return ResponseEntity.badRequest().body("failed");
            if (t.getGender() == null)
                return ResponseEntity.badRequest().body("failed");
            if (t.getAddress() == null && t.getAddress().trim().isEmpty())
                return ResponseEntity.badRequest().body("failed");
            if (t.getSalary() <= 0)
                return ResponseEntity.badRequest().body("failed");
            if (t.getExp() < 0 && t.getExp() > t.getAge() - 22)
                return ResponseEntity.badRequest().body("failed");
            return srv.addTeacher(t) ? ResponseEntity.ok("success") : ResponseEntity.badRequest().body("failed");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("failed");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotBlank String id) {
        return srv.deleteTeacher(id) ? ResponseEntity.ok("deleted") : ResponseEntity.notFound().build();
    }
}
