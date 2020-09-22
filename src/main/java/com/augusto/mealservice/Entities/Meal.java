package com.augusto.mealservice.Entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="meals")
@EntityListeners(AuditingEntityListener.class)
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name= "name" , nullable = false)
    private String name;

    @Column(name= "category" , nullable = false)
    private String category;

    @Column(name="calories", nullable = false)
    private long calories;

    @Column(name= "user_id" , nullable = false)
    private long userId;

    @CreationTimestamp
    @Column(name= "created_at" , nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    @Column(name= "meal_date", nullable = false)
    private Date mealDate;

    @UpdateTimestamp
    @Column(name= "updated_at" , nullable = false)
    private Date updatedAt;

    public long getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public Date getMealDate(){return mealDate;}

    public void setMealDate(Date mealDate){this.mealDate = mealDate;}

    public String getName(){return name;}

    public void setName(String name) {this.name = name;}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {this.category = category;}

    public long getCalories() {
        return calories;
    }

    public void setCalories(long calories) {
        this.calories = calories;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}



