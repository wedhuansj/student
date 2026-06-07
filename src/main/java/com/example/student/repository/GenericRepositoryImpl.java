package com.example.student.repository;

import com.example.student.model.Schedule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class GenericRepositoryImpl<T,ID> implements BaseRepository<T,ID> {
    @PersistenceContext
    private EntityManager em;
    @Override
    public void add(T e) {
        em.persist(e);
    }
    @Override
    public void update(T e) {
        em.merge(e);
    }
    @Override
    public void delete(ID id,Class<T> cls) {
        T e= em.find(cls,id);
        if(e != null) em.remove(e);
    }
    @Override
    public T findById(ID id,Class<T> cls) {
        return em.find(cls,id);
    }
    @Override
    public List<T> findAll(Class<T> cls) {
        return em.createQuery("SELECT x FROM "+cls.getSimpleName()+" x",cls).getResultList();
    }
    public void updateStudentScores(String id,double math,double lit,double eng) {
        em.createQuery("UPDATE Student s SET s.mathScore=:math,s.literatureScore=:lit,s.englishScore=:eng WHERE s.id=:id")
                .setParameter("math",math).setParameter("lit",lit).setParameter("eng",eng).setParameter("id",id).executeUpdate();
    }
    public List<String> findSubById(String teacherId) {
        TypedQuery<String> q=em.createQuery("SELECT s.name FROM Subject s WHERE Teacher.id=:id_",String.class);
        return q.setParameter("id_",teacherId).getResultList();
    }
    public List<Schedule> findSchedules(String cId) {
        return em.createQuery("SELECT s FROM Schedule s WHERE s.classId = :id", Schedule.class).setParameter("id", cId).getResultList();
    }
}