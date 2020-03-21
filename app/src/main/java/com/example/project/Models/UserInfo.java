package com.example.project.Models;


import org.json.JSONArray;

public class UserInfo {

    private String username;

    private String Password;

    private String message;

    private int auth;

    private String status;

    private String exp_date;

    private String  is_trial;

    private String active_cons;

    private String created_at;

    private String max_connections;

    private JSONArray allowed_output_formats;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return Password;
    }

    public String getMessage() {
        return message;
    }

    public int getAuth() {
        return auth;
    }

    public String getStatus() {
        return status;
    }

    public String getExp_date() {
        return exp_date;
    }

    public String getIs_trial() {
        return is_trial;
    }

    public String getActive_cons() {
        return active_cons;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getMax_connections() {
        return max_connections;
    }

    public JSONArray getAllowed_output_formats() {
        return allowed_output_formats;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setExp_date(String exp_date) {
        this.exp_date = exp_date;
    }

    public void setIs_trial(String is_trial) {
        this.is_trial = is_trial;
    }

    public void setActive_cons(String active_cons) {
        this.active_cons = active_cons;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setMax_connections(String max_connections) {
        this.max_connections = max_connections;
    }

    public void setAllowed_output_formats(JSONArray allowed_output_formats) {
        this.allowed_output_formats = allowed_output_formats;
    }
}
