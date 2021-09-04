package edu.school21.models;

import java.util.Objects;

public class User {
    private int id;
    private String login;
    private String password;
    private boolean isAuthentificated;

    public User() {
    }

    public User(int id, String login, String password, boolean isAuthentificated) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.isAuthentificated = isAuthentificated;
    }

    public User(String login, String password, boolean isAuthentificated) {
        this.login = login;
        this.password = password;
        this.isAuthentificated = isAuthentificated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthentificated() {
        return isAuthentificated;
    }

    public void setAuthentificated(boolean authentificated) {
        isAuthentificated = authentificated;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, isAuthentificated);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User tmp = (User) obj;
        return  Objects.equals(id, tmp.id) &&
                Objects.equals(login, tmp.login) &&
                Objects.equals(password, tmp.password) &&
                Objects.equals(isAuthentificated, tmp.isAuthentificated);
    }

    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", login=" + login +
                ", password=" + password +
                ", isAuthentificated=" + isAuthentificated + "}";
    }
}
