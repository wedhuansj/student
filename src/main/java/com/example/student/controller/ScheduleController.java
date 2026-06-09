package com.example.student.controller;
import com.example.student.model.Schedule;
import com.example.student.service.ScheduleService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/schedules")
@Validated
public class ScheduleController {
    private final ScheduleService srv;
    public ScheduleController(ScheduleService srv) { this.srv = srv; }
    @GetMapping("/class/{cId}")
    public ResponseEntity<List<Schedule>> getByClass(@PathVariable @NotBlank String cId) {
        return ResponseEntity.ok(srv.getClassSchedule(cId));
    }
    @PostMapping
    public ResponseEntity<String> add(@RequestBody Schedule s) {
        try {
            if (s == null)
                return ResponseEntity.badRequest().body("failed");
            if (s.getClassId() == null || s.getClassId().trim().isEmpty())
                return ResponseEntity.badRequest().body("failed");
            if (s.getSubId() == null || s.getSubId().trim().isEmpty())
                return ResponseEntity.badRequest().body("failed");
            if (s.getTeacherId() == null || s.getTeacherId().trim().isEmpty())
                return ResponseEntity.badRequest().body("failed");
            if (s.getRoom() == null || s.getRoom().trim().isEmpty())
                return ResponseEntity.badRequest().body("failed");
            if (s.getDay() < 2 || s.getDay() > 8)
                return ResponseEntity.badRequest().body("failed");
            if (s.getSlot() < 1 || s.getSlot() > 10)
                return ResponseEntity.badRequest().body("failed");
            return srv.addSchedule(s) ? ResponseEntity.ok("Success") : ResponseEntity.badRequest().body("failed");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("failed");
        }
    }
    @DeleteMapping("/class/{cId}")
    public ResponseEntity<String> delete(@PathVariable @NotBlank String cId) {
        return srv.deleteSchedule(cId) ? ResponseEntity.ok("Success") : ResponseEntity.notFound().build();
    }
}
