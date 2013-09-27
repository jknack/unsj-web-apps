package org.unsj.todo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Todo {

  @Id
  @GeneratedValue
  private Integer id;

  private String nombre;

  private boolean finalizado;

  public Todo(final int id) {
    this.id = id;
  }

  public Todo() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(final String nombre) {
    this.nombre = nombre;
  }

  public boolean isFinalizado() {
    return finalizado;
  }

  public void setFinalizado(final boolean finalizado) {
    this.finalizado = finalizado;
  }

}
