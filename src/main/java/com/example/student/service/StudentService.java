package com.example.student.service;

import com.example.student.model.Student;
import com.example.student.repository.GenericRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService {
    private final GenericRepositoryImpl<Student, String> repo;
    public StudentService(GenericRepositoryImpl<Student, String> repo) {
        this.repo = repo;
    }
    public List<Student> getAllStudents() {
        return repo.findAll(Student.class);
    }
    public boolean addStudent(Student s) {
        for (Student x : repo.findAll(Student.class)) {
            if (x.getId().equals(s.getId()))
                return false;
        }
        repo.add(s);
        return true;
    }
    public boolean inputScore(String id, double m, double l, double e) {
        if (m < 0 || m > 10 || l < 0 || l > 10 ||  e < 0 || e > 10)
            return false;
        Student s = repo.findById(id, Student.class);
        if (s == null)
            return false;
        repo.updateStudentScores(id, m, l, e);
        return true;
    }
    public Student getStudentById(String id) {
        return repo.findById(id, Student.class);
    }
    public boolean deleteStudent(String id) {
        Student s = repo.findById(id, Student.class);
        if (s == null)
            return false;
        repo.delete(id, Student.class);
        return true;
    }
    public boolean updateStudent(Student s) {
        Student x = repo.findById(s.getId(), Student.class);
        if (x == null)
            return false;
        repo.update(s);
        return true;
    }

}