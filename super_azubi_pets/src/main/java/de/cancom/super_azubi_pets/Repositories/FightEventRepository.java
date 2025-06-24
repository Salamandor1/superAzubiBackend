package de.cancom.super_azubi_pets.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.cancom.super_azubi_pets.EmbeddedIds.FightEventId;
import de.cancom.super_azubi_pets.Models.FightEvent;

@Repository
public interface FightEventRepository extends JpaRepository<FightEvent, FightEventId> {
}
