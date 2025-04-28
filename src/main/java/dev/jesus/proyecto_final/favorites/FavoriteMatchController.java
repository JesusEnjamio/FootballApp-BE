package dev.jesus.proyecto_final.favorites;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/favorite-matches")
@CrossOrigin("*")
public class FavoriteMatchController {

    private final FavoriteMatchService service;

    public FavoriteMatchController(FavoriteMatchService service) {
        this.service = service;
    }

    @GetMapping
    public List<FavoriteMatch> getAllFavorites() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<FavoriteMatch> getFavorite(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public FavoriteMatch addFavorite(@RequestBody FavoriteMatch match) {
        return service.save(match);
    }

    @DeleteMapping("/{id}")
    public void removeFavorite(@PathVariable Long id) {
        service.delete(id);
    }

    @PatchMapping("/{id}/comment")
    public FavoriteMatch updateFavoriteComment(@PathVariable Long id, @RequestBody String comment) {
        return service.updateComment(id, comment);
    }
}