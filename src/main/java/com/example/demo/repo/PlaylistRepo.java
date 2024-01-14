package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Playlist;

public interface PlaylistRepo extends JpaRepository<Playlist, Integer> {

}
