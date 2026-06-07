package com.example.student.service;

import com.example.student.model.Teacher;
import com.example.student.repository.GenericRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherService {
    private final GenericRepositoryImpl<Teacher, String> repo;
    public TeacherService(GenericRepositoryImpl<Teacher, String> repo) {
        this.repo = repo;
    }
    public List<Teacher> getAllTeachers() {
        List<Teacher> list = repo.findAll(Teacher.class);
        for (Teacher t : list)
            t.setSubjectNames(repo.findSubById(t.getId()));
        return list;
    }
    public boolean addTeacher(Teacher t) {
        if (t.getAge() <= 0 || t.getSalary() < 0 || t.getExp() < 0)
            return false;
        for (Teacher x : repo.findAll(Teacher.class)) {
            if (x.getId().equals(t.getId()))
                return false;
        }
        repo.add(t);
        return true;
    }
    public boolean deleteTeacher(String id) {
        Teacher t = repo.findById(id, Teacher.class);
        if (t == null)
            return false;
        repo.delete(id, Teacher.class);
        return true;
    }
}