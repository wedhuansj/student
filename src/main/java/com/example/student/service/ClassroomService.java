package com.example.student.service;

import com.example.student.model.Classroom;
import com.example.student.model.Teacher;
import com.example.student.repository.GenericRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClassroomService {
    private final GenericRepositoryImpl<Classroom, String> cRepo;
    private final GenericRepositoryImpl<Teacher, String> tRepo;
    public ClassroomService(GenericRepositoryImpl<Classroom,String> cRepo,GenericRepositoryImpl<Teacher,String> tRepo) {
        this.cRepo = cRepo;
        this.tRepo = tRepo;
    }
    public Classroom getStudents(String cId) {
        return cRepo.findById(cId, Classroom.class);
    }
    public boolean addNewClass(String id, String name, String tId) {
        List<Classroom> list = cRepo.findAll(Classroom.class);
        for (Classroom x : list) {
            if (x.getId().equals(id))
                return false;
        }
        Classroom c = new Classroom(id, name);
        c.setHeadTeacherId(tId);
        cRepo.add(c);
        return true;
    }
    public List<Classroom> getAll() {
        return cRepo.findAll(Classroom.class);
    }
}