package com.github.nechai.aeroflot.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "worker")
public class WorkerEntity {
    private String workerSurname;
    private String workeFirstname;
    private String workerPatronomic;
    private ProfessionEntity profession;
    private int actFl;
    private int id;

    public WorkerEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workerid")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "workerfirstname")
    public String getWorkeFirstname() {
        return workeFirstname;
    }

    public void setWorkeFirstname(String workeFirstname) {
        this.workeFirstname = workeFirstname;
    }
    @Column(name = "workersurname")
    public String getWorkerSurname() {
        return workerSurname;
    }

    public void setWorkerSurname(String workerSurname) {
        this.workerSurname = workerSurname;
    }

    @Column(name = "workerpatronomic")
    public String getWorkerPatronomic() {
        return workerPatronomic;
    }

    public void setWorkerPatronomic(String workerPatronomic) {
        this.workerPatronomic = workerPatronomic;
    }
    @OneToOne (fetch=FetchType.EAGER,
            cascade=CascadeType.ALL)
    @JoinColumn (name= "workerprofession")
  //  @Column(name = "workerprofession")
    public ProfessionEntity getProfession() {
        return profession;
    }

    public void setProfession(ProfessionEntity profession) {
        this.profession = profession;
    }
    @Column(name = "actfl")
    public int getActFl() {
        return actFl;
    }

    public void setActFl(int actual) {
        this.actFl = actual;
    }
}
