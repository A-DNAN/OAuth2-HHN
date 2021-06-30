package de.hsheilbronn.EgypttoursRServer.dao;

import de.hsheilbronn.EgypttoursRServer.model.user.UEmail;
import de.hsheilbronn.EgypttoursRServer.model.user.UProfile;
import de.hsheilbronn.EgypttoursRServer.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UEmailRepository extends JpaRepository<UEmail,String> {
    public Optional<UEmail> findByUser(User user);

}
