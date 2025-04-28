package dev.jesus.proyecto_final.favorites;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteMatchService {

    private final FavoriteMatchRepository repository;

    public FavoriteMatchService(FavoriteMatchRepository repository) {
        this.repository = repository;
    }

    public List<FavoriteMatch> getAll() {
        return repository.findAll();
    }

    public Optional<FavoriteMatch> getById(Long id) {
        return repository.findById(id);
    }

    public FavoriteMatch save(FavoriteMatch match) {
        match.setCreatedAt(LocalDateTime.now());
        return repository.save(match);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public FavoriteMatch updateComment(Long id, String comment) {
        FavoriteMatch match = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado"));
        match.setComment(comment);
        return repository.save(match);
    }
}