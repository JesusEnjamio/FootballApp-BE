// src/test/java/dev/jesus/proyecto_final/favorites/FavoriteMatchServiceTest.java
package dev.jesus.proyecto_final.favorites;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class FavoriteMatchServiceTest {

    @Mock
    private FavoriteMatchRepository favoriteMatchRepository;

    @InjectMocks
    private FavoriteMatchService favoriteMatchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveFavorite() {
        FavoriteMatch favorite = FavoriteMatch.builder()
                .fixtureId(123)
                .homeTeamName("Barcelona")
                .awayTeamName("Real Madrid")
                .build();

        when(favoriteMatchRepository.save(any(FavoriteMatch.class))).thenReturn(favorite);

        FavoriteMatch saved = favoriteMatchService.save(favorite);

        assertNotNull(saved);
        assertEquals("Barcelona", saved.getHomeTeamName());
        verify(favoriteMatchRepository).save(favorite);
    }

    @Test
    void testGetAllFavorites() {
        FavoriteMatch fav1 = FavoriteMatch.builder().fixtureId(1).build();
        FavoriteMatch fav2 = FavoriteMatch.builder().fixtureId(2).build();

        when(favoriteMatchRepository.findAll()).thenReturn(Arrays.asList(fav1, fav2));

        var favorites = favoriteMatchService.getAll();

        assertEquals(2, favorites.size());
        verify(favoriteMatchRepository).findAll();
    }

    @Test
    void testGetFavoriteByIdFound() {
        FavoriteMatch fav = FavoriteMatch.builder().fixtureId(999).build();
        when(favoriteMatchRepository.findById(1L)).thenReturn(Optional.of(fav));

        var result = favoriteMatchService.getById(1L);

        assertTrue(result.isPresent());
        assertEquals(999, result.get().getFixtureId());
        verify(favoriteMatchRepository).findById(1L);
    }

    @Test
    void testGetFavoriteByIdNotFound() {
        when(favoriteMatchRepository.findById(1L)).thenReturn(Optional.empty());

        var result = favoriteMatchService.getById(1L);

        assertTrue(result.isEmpty());
        verify(favoriteMatchRepository).findById(1L);
    }

    @Test
    void testDeleteFavorite() {
        Long id = 5L;
        doNothing().when(favoriteMatchRepository).deleteById(id);

        favoriteMatchService.delete(id);

        verify(favoriteMatchRepository).deleteById(id);
    }
}
