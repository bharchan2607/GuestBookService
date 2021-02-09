package com.guestbook.GuestbookService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class GuestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String comments;

    public GuestEntity(String name, String comments) {
        this.name = name;
        this.comments = comments;
    }
    public GuestEntity(){

    }

    public String getName() {
        return name;
    }

    public String getComments() {
        return comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuestEntity that = (GuestEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, comments);
    }
}
