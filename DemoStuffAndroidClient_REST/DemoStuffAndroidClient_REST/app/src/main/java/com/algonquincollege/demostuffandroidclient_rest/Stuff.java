package com.algonquincollege.demostuffandroidclient_rest;

public class Stuff {
    public final String id;
    public final String delta;
    public final String theta;
    public final String omega;
    public final String lambda;
    public final String recordNumber;
    public Stuff(String id, String delta, String theta, String omega, String lambda, String recordNumber){
        this.id = id;
        this.delta = delta;
        this.theta = theta;
        this.omega = omega;
        this.lambda = lambda;
        this.recordNumber = recordNumber;

    }
}
