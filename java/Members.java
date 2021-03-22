package com.example.travel;

public class Members {

    private String from;
    private String to;
    private String date;
    private String time;
    private String seats;
    private String name;
    private String phone;


    public Members(){

    }


    public Members(String name,String phone, String from, String to, String date, String time, String seats) {
        this.name=name;
        this.phone=phone;
        this.from = from;
        this.to = to;

        this.date = date;
        this.time = time;
        this.seats = seats;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
}
