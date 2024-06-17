package org.example.repository;

import org.example.Model.UserSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSearchRepository extends JpaRepository<UserSearch, Long> {
    Optional<UserSearch> findByEmailAndChem(String email, String chem);
}
