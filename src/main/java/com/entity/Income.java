package com.entity;

import jakarta.persistence.*;

/**
 * Entitas untuk mencatat pemasukan (Income).
 */
@Entity
@Table(name = "income")
public class Income {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Income() { }

    /**
     * Konstruktor lengkap.
     */
    public Income(
            String title,
            String date,
            String time,
            String description,
            double amount,
            User user
    ) {
        this.title       = title;
        this.date        = date;
        this.time        = time;
        this.description = description;
        this.amount      = amount;
        this.user        = user;
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

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    @Override
    public String toString() {
        return "Income{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", date='" + date + '\'' +
               ", time='" + time + '\'' +
               ", description='" + description + '\'' +
               ", amount=" + amount +
               ", user=" + (user != null ? user.getId() : "null") +
               '}';
    }
}