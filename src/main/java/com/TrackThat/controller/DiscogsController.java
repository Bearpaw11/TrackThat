package com.TrackThat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.TrackThat.service.DiscogsServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class DiscogsController {

    @Autowired
    private DiscogsServiceImpl discogsService;

    @GetMapping("/search")
    public String search(@RequestParam("query") String query,
                         @RequestParam("searchType") String searchType,
                         Model model) {
        try {
            List<Map<String, Object>> resultsList;
            if ("artist".equalsIgnoreCase(searchType)) {
                resultsList = discogsService.searchByArtist(query);
            } else {
                resultsList = discogsService.searchByAlbum(query);
            }
            model.addAttribute("searchResults", resultsList);
            model.addAttribute("searchType", searchType);
            model.addAttribute("searchQuery", query);
        } catch (Exception e) {
            model.addAttribute("searchError", "Error searching Discogs: " + e.getMessage());
        }
        return "mainUser";
    }

}
