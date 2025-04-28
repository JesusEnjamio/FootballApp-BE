package dev.jesus.proyecto_final.favorites;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(FavoriteMatchController.class)
class FavoriteMatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private FavoriteMatchService service;

    @Test
    void testGetAllFavorites() throws Exception {
        mockMvc.perform(get("/api/favorite-matches"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetFavoriteByIdNotFound() throws Exception {
        when(service.getById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/favorite-matches/1"))
                .andExpect(status().isOk()); // Sigue siendo 200, simplemente no hay contenido dentro
    }

    @Test
    void testAddFavorite() throws Exception {
        FavoriteMatch match = new FavoriteMatch();
        match.setHomeTeamName("Test Home");
        match.setAwayTeamName("Test Away");

        when(service.save(any(FavoriteMatch.class))).thenReturn(match);

        mockMvc.perform(post("/api/favorite-matches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"homeTeamName\":\"Test Home\",\"awayTeamName\":\"Test Away\"}"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testDeleteFavorite() throws Exception {
        doNothing().when(service).delete(1L);

        mockMvc.perform(delete("/api/favorite-matches/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testUpdateFavoriteComment() throws Exception {
        FavoriteMatch match = new FavoriteMatch();
        match.setComment("Nuevo comentario");

        when(service.updateComment(eq(1L), anyString())).thenReturn(match);

        mockMvc.perform(patch("/api/favorite-matches/1/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"Nuevo comentario\""))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
