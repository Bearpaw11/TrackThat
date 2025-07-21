package com.TrackThat;

import com.TrackThat.controller.DiscogsController;
import com.TrackThat.entity.User;
import com.TrackThat.entity.UserRecord;
import com.TrackThat.entity.UserWishRecord;
import com.TrackThat.service.DiscogsServiceImpl;
import com.TrackThat.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.ui.Model;
import org.springframework.mock.web.MockHttpSession;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DiscogsControllerTest {

    @InjectMocks
    private DiscogsController discogsController;

    @Mock
    private DiscogsServiceImpl discogsService;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchByArtist_withLoggedInUser() throws Exception {
        String query = "The Beatles";
        String searchType = "artist";
        List<Map<String, Object>> mockResults = Collections.singletonList(new HashMap<>());
        User mockUser = new User();
        mockUser.setId(1);
        List<UserRecord> userRecords = new ArrayList<>();
        List<UserWishRecord> userWishRecords = new ArrayList<>();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("loggedInUser", mockUser);

        when(discogsService.searchByArtist(query)).thenReturn(mockResults);
        when(userService.getUserRecords(1)).thenReturn(userRecords);
        when(userService.getUserWishRecords(1)).thenReturn(userWishRecords);

        String view = discogsController.search(query, searchType, model, session);

        assertEquals("mainUser", view);
        verify(model).addAttribute("searchResults", mockResults);
        verify(model).addAttribute("searchType", searchType);
        verify(model).addAttribute("searchQuery", query);
        verify(model).addAttribute("userRecords", userRecords);
        verify(model).addAttribute("userWishRecords", userWishRecords);
    }

    @Test
    void testSearchByAlbum_noLoggedInUser() throws Exception {
        String query = "Abbey Road";
        String searchType = "album";
        List<Map<String, Object>> mockResults = Collections.singletonList(new HashMap<>());
        MockHttpSession session = new MockHttpSession();

        when(discogsService.searchByAlbum(query)).thenReturn(mockResults);

        String view = discogsController.search(query, searchType, model, session);

        assertEquals("mainUser", view);
        verify(model).addAttribute("searchResults", mockResults);
        verify(model).addAttribute("searchType", searchType);
        verify(model).addAttribute("searchQuery", query);
        verify(model, never()).addAttribute(eq("userRecords"), any());
        verify(model, never()).addAttribute(eq("userWishRecords"), any());
    }

    @Test
    void testSearch_handlesException() throws Exception {
        String query = "error";
        String searchType = "artist";
        MockHttpSession session = new MockHttpSession();

        when(discogsService.searchByArtist(query)).thenThrow(new RuntimeException("Discogs API error"));

        String view = discogsController.search(query, searchType, model, session);

        assertEquals("mainUser", view);
        verify(model).addAttribute(eq("searchError"), contains("Discogs API error"));
    }
}
