package com.example.springboot.entity;

import lombok.Data;

@Data
public class Server_Weight {
    private String ip;
    private int weight;

    public Server_Weight(String ip) {
        super();
        this.ip = ip;
    }

    public Server_Weight(String ip, int weight) {
        this.ip     = ip;
        this.weight = weight;
    }

    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Server [ip=" + ip + ", weight=" + weight + "]";
    }
}
