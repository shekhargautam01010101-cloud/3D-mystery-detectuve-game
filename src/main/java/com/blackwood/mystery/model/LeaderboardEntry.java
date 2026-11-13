package com.blackwood.mystery.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "leaderboard_entries")
public class LeaderboardEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private AppUser player;

    @Column(nullable = false)
    private String playerName;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false)
    private String rankTitle;

    @Column(nullable = false)
    private int elapsedSeconds;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public AppUser getPlayer() { return player; }
    public void setPlayer(AppUser player) { this.player = player; }
    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public String getRankTitle() { return rankTitle; }
    public void setRankTitle(String rankTitle) { this.rankTitle = rankTitle; }
    public int getElapsedSeconds() { return elapsedSeconds; }
    public void setElapsedSeconds(int elapsedSeconds) { this.elapsedSeconds = elapsedSeconds; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
