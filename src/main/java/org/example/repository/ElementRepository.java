package org.example.repository;

import org.example.Model.Element;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementRepository extends JpaRepository<Element, Long> {
}
