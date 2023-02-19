package br.com.rabbithole.auth.entities;

import java.net.InetAddress;

public class LoginProcessEntity {
    private boolean isRegistered;
    private boolean isLogged;
    private InetAddress address;
    private int attempts;

    public LoginProcessEntity() {
    }

    public LoginProcessEntity(boolean isRegistered, boolean isLogged, InetAddress address) {
        this.isRegistered = isRegistered;
        this.isLogged = isLogged;
        this.address = address;
    }

    public LoginProcessEntity(boolean isRegistered, boolean isLogged, int attempts) {
        this.isRegistered = isRegistered;
        this.isLogged = isLogged;
        this.attempts = attempts;
    }

    public LoginProcessEntity(boolean isRegistered, boolean isLogged, InetAddress address, int attempts) {
        this.isRegistered = isRegistered;
        this.isLogged = isLogged;
        this.address = address;
        this.attempts = attempts;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
}
