package com.stafico.remonttracker.bluetooth;

import java.io.Serializable;

public class ListItem implements Serializable {
    private String name;
    private String mac;

    public ListItem(String name, String mac) {
        this.name = name;
        this.mac = mac;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
