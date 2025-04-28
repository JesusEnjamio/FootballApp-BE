package dev.jesus.proyecto_final.favorites;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "favorite_matches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer fixtureId;

    private String leagueName;

    private String homeTeamName;
    private String awayTeamName;

    private Integer goalsHome;
    private Integer goalsAway;

    private String fixtureDate;

    private String comment;

    private LocalDateTime createdAt;
}
