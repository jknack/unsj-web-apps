package org.unsj.todo.view;

import static org.apache.commons.lang3.Validate.notNull;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.unsj.todo.api.TodoManager;
import org.unsj.todo.domain.Todo;

import com.google.common.collect.Maps;

@Controller
@RequestMapping("/")
public class VistaPendientes {

  private TodoManager todoManager;

  @Inject
  public VistaPendientes(final TodoManager todoManager) {
    this.todoManager = notNull(todoManager, "The todoManager can't be null.");
  }

  @RequestMapping
  public String todoList(final Model model) {
    model.addAttribute("pendientes", todoManager.list());
    return "todoList";
  }

  @RequestMapping("/todo/{id}")
  public ModelAndView todoForm(@PathVariable final int id) {
    return todoForm(todoManager.get(id));
  }

  @RequestMapping("/todo")
  public ModelAndView todoForm(final Todo todo) {
    Map<String, Object> model = Maps.newHashMap();
    model.put("pendiente", todo);
    boolean isNew = todo.getId() == null;
    model.put("isNew", isNew);
    model.put("action", isNew ? "./todo" : "../todo");
    return new ModelAndView("todoForm", model);
  }

  @RequestMapping(value = "/todo", method = POST)
  public String mergeTodo(final Todo todo) {
    todoManager.merge(todo);
    return "redirect:/";
  }
}
