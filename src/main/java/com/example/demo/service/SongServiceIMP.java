package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Song;
import com.example.demo.repo.SongRepo;

@Service
public class SongServiceIMP implements SongService {
	@Autowired
	SongRepo songRepo;

	public SongServiceIMP(SongRepo songRepo) {
		super();
		this.songRepo = songRepo;
	}

	@Override
	public void addSong(Song song) {
		songRepo.save(song);
	}

	@Override
	public List<Song> fetchAllSong() {
		return songRepo.findAll();
	}

	@Override
	public boolean songExists(String name) {
		Song song=songRepo.findByName(name);
		if(song==null) {
			return false;
		}else {
			return true;
		}
		
	}

	@Override
	public void updateSong(Song song) {
		songRepo.save(song);
		
	}
}
