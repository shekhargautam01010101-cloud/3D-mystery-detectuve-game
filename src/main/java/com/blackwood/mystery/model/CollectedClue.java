package com.blackwood.mystery.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "game_sessions")
public class GameSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private AppUser player;

    @Column(nullable = false)
    private LocalDateTime startedAt;

    @Column(nullable = false)
    private LocalDateTime lastProgressAt;

    @Column(nullable = false)
    private int accusationChances = 3;

    @Column(nullable = false)
    private int wrongAccusations = 0;

    @Column(nullable = false)
    private int hintsUsed = 0;

    @Column(nullable = false)
    private int score = 1000;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GameStatus status = GameStatus.ACTIVE;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CollectedClue> collectedClues = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        if (startedAt == null) {
            startedAt = LocalDateTime.now();
        }
        if (lastProgressAt == null) {
            lastProgressAt = startedAt;
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public AppUser getPlayer() { return player; }
    public void setPlayer(AppUser player) { this.player = player; }
    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }
    public LocalDateTime getLastProgressAt() { return lastProgressAt; }
    public void setLastProgressAt(LocalDateTime lastProgressAt) { this.lastProgressAt = lastProgressAt; }
    public int getAccusationChances() { return accusationChances; }
    public void setAccusationChances(int accusationChances) { this.accusationChances = accusationChances; }
    public int getWrongAccusations() { return wrongAccusations; }
    public void setWrongAccusations(int wrongAccusations) { this.wrongAccusations = wrongAccusations; }
    public int getHintsUsed() { return hintsUsed; }
    public void setHintsUsed(int hintsUsed) { this.hintsUsed = hintsUsed; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public GameStatus getStatus() { return status; }
    public void setStatus(GameStatus status) { this.status = status; }
    public List<CollectedClue> getCollectedClues() { return collectedClues; }
    public void setCollectedClues(List<CollectedClue> collectedClues) { this.collectedClues = collectedClues; }

    public boolean hasCollectedClue(String clueId) {
        return collectedClues.stream().anyMatch(clue -> clue.getClueId().equals(clueId));
    }

    public void addCollectedClue(CollectedClue clue) {
        clue.setSession(this);
        collectedClues.add(clue);
        lastProgressAt = LocalDateTime.now();
    }
}
