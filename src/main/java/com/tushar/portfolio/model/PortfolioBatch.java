package com.tushar.portfolio.model;

import lombok.val;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.sound.midi.Instrument;

@Entity
public class PortfolioBatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String Instrument;
    private float Qty;
    private float Avg_cost;


    public PortfolioBatch(Integer id, String instrument, float qty, float avg_cost) {
        this.id = id;
        Instrument = instrument;
        Qty = qty;
        Avg_cost = avg_cost;
    }


    public PortfolioBatch() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstrument() {
        return Instrument;
    }

    public void setInstrument(String instrument) {
        Instrument = instrument;
    }

    public float getQty() {
        return Qty;
    }

    public void setQty(float qty) {
        Qty = qty;
    }

    public float getAvg_cost() {
        return Avg_cost;
    }

    public void setAvg_cost(float avg_cost) {
        Avg_cost = avg_cost;
    }
}
