package com.TrackThat.service;

import com.TrackThat.dao.DiscogsRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DiscogsServiceImpl implements DiscogsService {

    @Autowired
    private DiscogsRepository discogsRepository;

    @Override
    public List<Map<String, Object>> searchByArtist(String artist) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> resultsList = new ArrayList<>();

        // Step 1: Search for the artist to get the artist ID
        JsonNode response = discogsRepository.search(artist, "artist");
        String artistId = null;
        if (response.has("results")) {
            for (JsonNode node : response.get("results")) {
                String artistName = node.get("title").asText();
                if (artistName != null && artistName.equalsIgnoreCase(artist)) {
                    artistId = node.get("id").asText();
                    break;
                }
            }
        }
        // Step 2: If artist found, get their releases
        if (artistId != null) {
            JsonNode releasesResponse = discogsRepository.getArtistReleases(artistId);
            if (releasesResponse.has("releases")) {
                for (JsonNode release : releasesResponse.get("releases")) {
                    // Optionally, filter by artist name again if needed
                    String artistField = release.has("artist") ? release.get("artist").asText() : null;
                    if (artistField != null && artistField.equalsIgnoreCase(artist)) {
                        resultsList.add(mapper.convertValue(release, Map.class));
                    }
                }
            }
        }
        return resultsList;
    }

    @Override
    public List<Map<String, Object>> searchByAlbum(String album) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    List<Map<String, Object>> resultsList = new ArrayList<>();

    JsonNode response = discogsRepository.search(album, "release");
    if (response.has("results")) {
        for (JsonNode node : response.get("results")) {
            String fullTitle = node.has("title") ? node.get("title").asText() : null;
            if (fullTitle != null && fullTitle.contains(" - ")) {
                String[] parts = fullTitle.split(" - ", 2);
                String albumTitle = parts[1].trim();
                if (albumTitle.equalsIgnoreCase(album)) {
                    resultsList.add(mapper.convertValue(node, Map.class));
                }
            }
        }
    }
    return resultsList;
}
}