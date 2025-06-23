package com.entity;

import jakarta.persistence.*;

/**
 * Entitas untuk mencatat pengeluaran (Expense).
 */
@Entity
@Table(name = "expense")
public class Expense {

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
    private double price;

    protected Expense() { }

    /**
     * Konstruktor lengkap.
     */
    public Expense(
            String title,
            String date,
            String time,
            String description,
            double price
    ) {
        this.title       = title;
        this.date        = date;
        this.time        = time;
        this.description = description;
        this.price       = price;
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

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Expense{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", date='" + date + '\'' +
               ", time='" + time + '\'' +
               ", description='" + description + '\'' +
               ", price='" + price + '\'' +
               '}';
    }
}