package com.sind.projectx.repository;

import com.sind.projectx.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dmytro Bekuzarov
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

}
