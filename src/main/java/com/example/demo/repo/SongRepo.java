package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Song;

public interface SongRepo extends JpaRepository<Song, Integer> {

	public Song findByName(String name);

}
