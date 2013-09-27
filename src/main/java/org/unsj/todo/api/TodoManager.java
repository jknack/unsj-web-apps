package org.unsj.todo.api;

import static org.apache.commons.lang3.Validate.notNull;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.unsj.todo.domain.QTodo.todo;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.unsj.todo.domain.Todo;

import com.mysema.query.jpa.impl.JPAQuery;

@Transactional
@Controller
@RequestMapping("/api/todos")
public class TodoManager {

  private EntityManager em;

  @Inject
  public TodoManager(final EntityManager em) {
    this.em = notNull(em, "The em can't be null.");
  }

  public TodoManager() {
  }

  @RequestMapping(value = "/{id}", method = GET)
  @ResponseBody
  public Todo get(@PathVariable final int id) {
    Todo todo = em.find(Todo.class, id);
    return todo;
  }

  @RequestMapping(method = GET)
  @ResponseBody
  public List<Todo> list() {
    return new JPAQuery(em)
        .from(todo)
        .list(todo);
  }

  @RequestMapping(method = GET, params = "nombre")
  @ResponseBody
  public List<Todo> byName(final String nombre) {
    return new JPAQuery(em)
        .from(todo)
        .where(todo.nombre.startsWithIgnoreCase(nombre))
        .list(todo);
  }

  @RequestMapping(value = "/finalizados", method = GET)
  @ResponseBody
  public List<Todo> finalizados() {
    return new JPAQuery(em)
        .from(todo)
        .where(todo.finalizado.isTrue())
        .list(todo);
  }

  @RequestMapping(value = "/pendientes", method = GET)
  @ResponseBody
  public List<Todo> pendientes() {
    return new JPAQuery(em)
        .from(todo)
        .where(todo.finalizado.isFalse())
        .list(todo);
  }

  @RequestMapping(method = {POST, PUT })
  @ResponseBody
  public Todo merge(@RequestBody final Todo todo) {
    return em.merge(todo);
  }

  @RequestMapping(value = "/{id}", method = DELETE)
  @ResponseBody
  public Todo delete(@PathVariable final int id) {
    Todo todo = em.find(Todo.class, id);
    em.remove(todo);
    return todo;
  }
}
