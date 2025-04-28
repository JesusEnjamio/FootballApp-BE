package dev.jesus.proyecto_final.favorites;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteMatchRepository extends JpaRepository<FavoriteMatch, Long> {
    boolean existsByFixtureId(Integer fixtureId);
}