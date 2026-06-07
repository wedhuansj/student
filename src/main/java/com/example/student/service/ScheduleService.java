package com.example.student.service;

import com.example.student.model.Schedule;
import com.example.student.repository.GenericRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    private final GenericRepositoryImpl<Schedule , String> repo;
    public ScheduleService(GenericRepositoryImpl<Schedule, String> repo) {
        this.repo = repo;
    }
    private boolean checkConflict(Schedule sched) {
        List<Schedule> list = repo.findAll(Schedule.class);
        for (Schedule s : list) {
            if (s.getClassId().equals(sched.getClassId()) || s.getTeacherId().equals(sched.getTeacherId()) || s.getRoom().equals(sched.getRoom()))
                return false;
        }
        return true;
    }
    private boolean checkSub(String tId, String sId) {
        List<String> list = repo.findSubById(tId);
        for (String x : list) {
            if (x.equals(sId))
                return true;
        }
        return false;
    }
    public boolean addSchedule(Schedule s) {
        if (!checkSub(s.getTeacherId(), s.getSubId())) return false;
        repo.add(s);
        return true;
    }
    public List<Schedule> getClassSchedule(String cId) {
        return repo.findSchedules(cId);
    }
    public boolean deleteSchedule(String cId) {
        List<Schedule>  s = repo.findSchedules(cId);
        if (s == null)
            return false;
        repo.delete(cId, Schedule.class);
        return true;
    }
}