package de.hhu.propra.sharingplatform.dao;

import de.hhu.propra.sharingplatform.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {
    Optional<User> findByAccountName(String accountName);
}
