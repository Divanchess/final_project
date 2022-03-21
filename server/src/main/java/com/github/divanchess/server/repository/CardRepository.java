package com.github.divanchess.server.repository;

import com.github.divanchess.server.entity.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends CrudRepository<Card, String> {
    Optional<Card> findByPan(String pan);
    Optional<Card> findByPanAndPin(String pan, int pin);
}
