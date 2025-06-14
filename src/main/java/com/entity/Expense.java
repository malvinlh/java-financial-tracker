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
    private String price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Expense() { }

    /**
     * Konstruktor lengkap.
     */
    public Expense(
            String title,
            String date,
            String time,
            String description,
            String price,
            User user
    ) {
        this.title       = title;
        this.date        = date;
        this.time        = time;
        this.description = description;
        this.price       = price;
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

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    @Override
    public String toString() {
        return "Expense{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", date='" + date + '\'' +
               ", time='" + time + '\'' +
               ", description='" + description + '\'' +
               ", price='" + price + '\'' +
               ", user=" + (user != null ? user.getId() : "null") +
               '}';
    }
}