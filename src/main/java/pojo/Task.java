package com.sample;


public class Task {

  private long idTask;
  private long idTechnologicalEngineer;
  private long idBeerKind;
  private java.sql.Date date;
  private String status;
  private long amount;


  public long getIdTask() {
    return idTask;
  }

  public void setIdTask(long idTask) {
    this.idTask = idTask;
  }


  public long getIdTechnologicalEngineer() {
    return idTechnologicalEngineer;
  }

  public void setIdTechnologicalEngineer(long idTechnologicalEngineer) {
    this.idTechnologicalEngineer = idTechnologicalEngineer;
  }


  public long getIdBeerKind() {
    return idBeerKind;
  }

  public void setIdBeerKind(long idBeerKind) {
    this.idBeerKind = idBeerKind;
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


  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }

}
