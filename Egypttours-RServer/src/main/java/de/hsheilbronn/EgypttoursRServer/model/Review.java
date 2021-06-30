/**
 *  Copyright (C) 2021  the original author or authors.
 *
 * 		This program is free software: you can redistribute it and/or modify
 * 		it under the terms of the GNU General Public License as published by
 * 		the Free Software Foundation, either version 3 of the License, or
 * 		(at your option) any later version.
 *
 * 		This program is distributed in the hope that it will be useful,
 * 		but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 		MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 		GNU General Public License for more details.
 *
 * 		You should have received a copy of the GNU General Public License
 * 		along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package de.hsheilbronn.EgypttoursRServer.model;

import de.hsheilbronn.EgypttoursRServer.model.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double rating;
    private LocalDateTime createdAt;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private Angebot angebot;

    public Review() {
    }

    public Review(Long id, String description, Double rating, LocalDateTime createdAt, User createdBy, Angebot angebot) {
        this.id = id;
        this.description = description;
        this.rating = rating;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.angebot = angebot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Angebot getAngebot() {
        return angebot;
    }

    public void setAngebot(Angebot angebot) {
        this.angebot = angebot;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id) && Objects.equals(description, review.description) && Objects.equals(rating, review.rating) && Objects.equals(createdAt, review.createdAt) && Objects.equals(createdBy, review.createdBy)
                && Objects.equals(angebot, review.angebot)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, rating, createdAt, createdBy
                ,angebot
        );
    }
}
