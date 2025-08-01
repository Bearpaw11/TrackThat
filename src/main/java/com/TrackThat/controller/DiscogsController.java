package com.TrackThat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import com.TrackThat.service.UserService;
import com.TrackThat.entity.User;
import com.TrackThat.entity.UserRecord;
import com.TrackThat.entity.UserWishRecord;

import com.TrackThat.service.DiscogsServiceImpl;

@Controller
public class DiscogsController {

    @Autowired
    private DiscogsServiceImpl discogsService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public String search(@RequestParam ("query") String query,
                         @RequestParam("searchType") String searchType,
                         Model model,
                         HttpSession session) {
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

            // Add user's collection and wish list to the model
            User user = (User) session.getAttribute("loggedInUser");
            if (user != null) {
                
                int userId = user.getId();
                List<UserRecord> userRecords = userService.getUserRecords(userId);
                List<UserWishRecord> userWishRecords = userService.getUserWishRecords(userId);
                model.addAttribute("userRecords", userRecords);
                model.addAttribute("userWishRecords", userWishRecords);

            }
        } catch (Exception e) {
            model.addAttribute("searchError", "Error searching Discogs: " + e.getMessage());
        }
        
        return "mainUser";
    }


}
