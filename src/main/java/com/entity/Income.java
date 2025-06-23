package com.entity;

import jakarta.persistence.*;

/**
 * Entitas untuk mencatat pemasukan (Income).
 */
@Entity
@Table(name = "income")
public class Income
{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String time;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private double amount;

    protected Income() { }

    /**
     * Konstruktor lengkap tanpa user.
     */
    public Income(String title,
                  String date,
                  String time,
                  String description,
                  double amount) {
        this.title       = title;
        this.date        = date;
        this.time        = time;
        this.description = description;
        this.amount      = amount;
    }

    // --- getters & setters ---

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    @Override
    public String toString() {
        return "Income{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", date='" + date + '\'' +
               ", time='" + time + '\'' +
               ", description='" + description + '\'' +
               ", amount=" + amount +
               '}';
    }
}