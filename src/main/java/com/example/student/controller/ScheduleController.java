package com.example.student.controller;
import com.example.student.model.Schedule;
import com.example.student.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService srv;
    public ScheduleController(ScheduleService srv) { this.srv = srv; }
    @GetMapping("/class/{cId}")
    public ResponseEntity<List<Schedule>> getByClass(@PathVariable String cId) {
        return ResponseEntity.ok(srv.getClassSchedule(cId));
    }
    @PostMapping
    public ResponseEntity<String> add(@RequestBody Schedule s) {
        return srv.addSchedule(s) ? ResponseEntity.ok("Success") : ResponseEntity.badRequest().body("failed");
    }
    @DeleteMapping("/class/{cId}")
    public ResponseEntity<String> delete(@PathVariable String cId) {
        return srv.deleteSchedule(cId) ? ResponseEntity.ok("Success") : ResponseEntity.notFound().build();
    }
}
