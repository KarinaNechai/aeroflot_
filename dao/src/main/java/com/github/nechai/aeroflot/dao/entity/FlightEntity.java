package com.github.nechai.aeroflot.dao.entity;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "flight")
public class FlightEntity {
    private int id;
    private AirportEntity airportFrom;
    private AirportEntity airportTo;
    private List<WorkerEntity> listWorker;
    private PlaneEntity plane;
    private int flightRange;
    private int amountPassengers;
    private LocalDateTime datetime;
    int actFl;

    public FlightEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flightid")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "airportfrom")
    public AirportEntity getAirportFrom() {
        return airportFrom;
    }

    public void setAirportFrom(AirportEntity airportFrom) {
        this.airportFrom = airportFrom;
    }

    @ManyToOne
    @JoinColumn(name = "airportto")
    public AirportEntity getAirportTo() {
        return airportTo;
    }

    public void setAirportTo(AirportEntity airportTo) {
        this.airportTo = airportTo;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "crew", joinColumns = {
            @JoinColumn(name = "flightid")},
            inverseJoinColumns = {@JoinColumn(name = "workerid")})
    public List<WorkerEntity> getListWorker() {
        return listWorker;
    }

    public void setListWorker(List<WorkerEntity> listworker) {
        this.listWorker = listworker;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planeid")
    public PlaneEntity getPlane() {
        return plane;
    }

    public void setPlane(PlaneEntity plane) {
        this.plane = plane;
    }

    @Column(name = "flightrange")
    public int getFlightRange() {
        return flightRange;
    }

    public void setFlightRange(int flightRange) {
        this.flightRange = flightRange;
    }

    @Column(name = "amountpassengers")
    public int getAmountPassengers() {
        return amountPassengers;
    }

    public void setAmountPassengers(int amountPassengers) {
        this.amountPassengers = amountPassengers;
    }

    @Column(name = "flightdate")
    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    @Column(name = "actfl")
    public int getActFl() {
        return actFl;
    }

    public void setActFl(int actual) {
        this.actFl = actual;
    }
}
