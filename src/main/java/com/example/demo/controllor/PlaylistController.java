package com.example.demo.controllor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Playlist;
import com.example.demo.entity.Song;
import com.example.demo.service.PlaylistService;
import com.example.demo.service.SongService;


@Controller
public class PlaylistController {
	@Autowired
	SongService service;
	@Autowired
	PlaylistService playlistService;

	@GetMapping("/createPlaylist")
	public String createPlaylist(Model model) {
		List<Song> songList = service.fetchAllSong();
		model.addAttribute("songs", songList);
		return "createPlaylist";
	}

	@PostMapping("/addPlaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
		//updating the play list table
		playlistService.addPlaylist(playlist);
		
		//updating song table
		List<Song> songList=playlist.getSongs();
		for(Song s:songList) {
			s.getPlaylists().add(playlist);
			//update song object in db
			service.updateSong(s);
		}
		return "adminHome";
	}
	@GetMapping("/viewPlaylist")
	public String viewPlaylist(Model model) {
		List<Playlist> playList = playlistService.fetchAllPlaylist();
		model.addAttribute("allplaylist", playList);
		return "playlist";
	}

}
