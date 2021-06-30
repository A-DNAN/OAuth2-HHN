package de.hsheilbronn.EgypttoursRServer.dao;

import de.hsheilbronn.EgypttoursRServer.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
