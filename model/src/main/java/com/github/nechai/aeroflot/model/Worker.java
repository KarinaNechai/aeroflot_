package com.github.nechai.aeroflot.model;

import java.util.Objects;

public class Worker {
    private String workerSurname;
    private String workeFirstname;
    private String workerPatronomic;
    private Profession profession;
    private boolean isActual;
    private int workerid;

    public Worker (){
    }

    public Worker(int workerid) {
        this.workerid = workerid;
    }

    public String getFullName () {
        return workerSurname+" "+this.workeFirstname+" "+this.workerPatronomic;
    }

    public Worker(String workerSurname, String workeFirstname, String workerPatronomic, Profession profession) {
        this.workerSurname = workerSurname;
        this.workeFirstname = workeFirstname;
        this.workerPatronomic = workerPatronomic;
        this.profession = profession;
        this.isActual = true;
    }

    public Worker(int workerid,String workerSurname, String workeFirstname, String workerPatronomic, Profession profession, boolean isActual ) {
        this.workerSurname = workerSurname;
        this.workeFirstname = workeFirstname;
        this.workerPatronomic = workerPatronomic;
        this.profession = profession;
        this.isActual = isActual;
        this.workerid = workerid;
    }

    public String getWorkerSurname() {
        return workerSurname;
    }

    public void setWorkerSurname(String workerSurname) {
        this.workerSurname = workerSurname;
    }

    public String getWorkeFirstname() {
        return workeFirstname;
    }

    public void setWorkeFirstname(String workeFirstname) {
        this.workeFirstname = workeFirstname;
    }

    public String getWorkerPatronomic() {
        return workerPatronomic;
    }

    public void setWorkerPatronomic(String workerPatronomic) {
        this.workerPatronomic = workerPatronomic;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public boolean isActual() {
        return isActual;
    }

    public void setActual(boolean actual) {
        isActual = actual;
    }

    public int getWorkerid() {
        return workerid;
    }

    public void setWorkerid(int workerid) {
        this.workerid = workerid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Worker)) return false;
        Worker worker = (Worker) o;
        boolean a=getWorkerSurname().equals(worker.getWorkerSurname()) &&
                getWorkeFirstname().equals(worker.getWorkeFirstname()) &&
                getWorkerPatronomic().equals(worker.getWorkerPatronomic()) &&
                getProfession() == worker.getProfession();
        return  getWorkerSurname().equals(worker.getWorkerSurname()) &&
                getWorkeFirstname().equals(worker.getWorkeFirstname()) &&
                getWorkerPatronomic().equals(worker.getWorkerPatronomic()) &&
                getProfession() == worker.getProfession();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWorkerSurname(), getWorkeFirstname(), getWorkerPatronomic(), getProfession());
    }
}
