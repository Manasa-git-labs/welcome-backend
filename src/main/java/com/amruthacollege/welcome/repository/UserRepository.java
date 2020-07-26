package com.amruthacollege.welcome.repository;

import com.amruthacollege.welcome.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JPA Repository interface for User Database Operations
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findOneByUserName( final String userName );

    boolean existsByUserName( final String userName );

}
