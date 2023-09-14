package com.aulaspring.course.entities;

import java.io.Serializable;

import com.aulaspring.course.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {

  // Número de série da entidade
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private OrderItemPK id = new OrderItemPK();

  private Integer quantity;
  private Double price;

  // Construtores
  public OrderItem() {
  }

  public OrderItem(Order order, Product product, Integer quantity, Double price) {
    this.id.setOrder(order);
    this.id.setProduct(product);
    this.quantity = quantity;
    this.price = price;
  }

  // Metodo de exemplo
  /**
   * Para o resultado do metodo aparecer no Json gerado pela chamada
   * devemos iniciar o nome do metodo com 'get', pois isso é um padrão do Java
   * Enterprise
   */
  public Double getSubTotal() {
    return (price * quantity);
  }

  // Getters e Setters
  @JsonIgnore
  public Order getOrder() {
    return id.getOrder();
  }

  public void setOrder(Order order) {
    id.setOrder(order);
  }

  public Product getProduct() {
    return id.getProduct();
  }

  public void setProduct(Product product) {
    id.setProduct(product);
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  // Identificador da entidade
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  /**
   * Comparador da entidade, através do atributo ID
   * Nesse caso usando a entidade auxiliar da chave composta OrderItemPK
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    OrderItem other = (OrderItem) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
}
