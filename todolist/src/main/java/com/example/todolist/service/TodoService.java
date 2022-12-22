package com.example.todolist.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.example.todolist.entity.Todo;
import com.example.todolist.interfaces.IDAO;
import com.example.todolist.tools.ServiceHibernate;

@Service
public class TodoService implements IDAO<Todo> {

    private ServiceHibernate serviceHibernate;

    private Session session;

    public TodoService(ServiceHibernate serviceHibernate) {
        this.serviceHibernate = serviceHibernate;
        this.session = this.serviceHibernate.getSession();
    }

    @Override
    public boolean create(Todo o) {
        this.session.beginTransaction();
        this.session.persist(o);
        this.session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Todo o) {
        this.session.beginTransaction();
        this.session.merge(o);
        this.session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Todo o) {
        this.session.beginTransaction();
        this.session.remove(o);
        this.session.getTransaction().commit();
        return true;
    }

    @Override
    public Todo findById(int id) {
        Todo todo = session.get(Todo.class,id);
        return todo;
    }

    @Override
    public List<Todo> findAll() {
        Query<Todo> todoQuery = session.createQuery("from Todo", Todo.class);
        return todoQuery.list();
    }

    public List<Todo> findDone() {
        Query<Todo> todoQuery = session.createQuery("from Todo t where t.isDone", Todo.class);
        return todoQuery.list();
    }
    
    public List<Todo> findNotDone() {
        Query<Todo> todoQuery = session.createQuery("from Todo t where !t.isDone", Todo.class);
        return todoQuery.list();
    }
}
