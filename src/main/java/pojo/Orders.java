package com.sample;


public class Orders {

  private long idOrder;
  private long idClient;
  private long idManager;
  private java.sql.Date date;
  private String status;


  public long getIdOrder() {
    return idOrder;
  }

  public void setIdOrder(long idOrder) {
    this.idOrder = idOrder;
  }


  public long getIdClient() {
    return idClient;
  }

  public void setIdClient(long idClient) {
    this.idClient = idClient;
  }


  public long getIdManager() {
    return idManager;
  }

  public void setIdManager(long idManager) {
    this.idManager = idManager;
  }


  public java.sql.Date getDate() {
    return date;
  }

  public void setDate(java.sql.Date date) {
    this.date = date;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
