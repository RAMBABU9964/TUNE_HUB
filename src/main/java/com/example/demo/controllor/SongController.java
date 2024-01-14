package com.example.demo.controllor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.entity.Song;
import com.example.demo.service.SongService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SongController {
	@Autowired
	SongService songService;

	public SongController(SongService songService) {
		super();
		this.songService = songService;
	}

	@PostMapping("/addSong")
	public String addSong(@ModelAttribute Song song) {
		boolean songStatus = songService.songExists(song.getName());
		if (songStatus == false) {
			songService.addSong(song);
			System.out.println("Song Add Successfully");
		} else {
			System.out.println("Song Is already Existus");
		}
		return "adminHome";
	}

	@GetMapping("/viewsong")
	public String viewsong(Model model) {
		List<Song> songList = songService.fetchAllSong();
		model.addAttribute("songs", songList);
		return "displaySongs";
	}

	
	@GetMapping("/playsong")
	public String playsong(Model model) {
		boolean premiumUser=true;
		
		if(premiumUser==true) {
			List<Song> songList = songService.fetchAllSong();
			model.addAttribute("songs", songList);
			return "displaySongs";
		}else {
			return"makePayment";
		}
		
	}
}
