package com.TrackThat.service;

import com.TrackThat.dao.DiscogsRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class DiscogsServiceImpl implements DiscogsService {

    @Autowired
    private DiscogsRepository discogsRepository;

    @Override
    public List<Map<String, Object>> searchByArtist(String artist) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> resultsList = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        // Directly search for releases by artist and vinyl format
        JsonNode response = discogsRepository.searchByArtistAndFormat(artist, "Vinyl");
        if (response.has("results")) {
            for (JsonNode node : response.get("results")) {
                // 1. Parse title into artist and album
                String fullTitle = node.has("title") ? node.get("title").asText() : null;
                if (fullTitle != null && fullTitle.contains(" - ")) {
                    String[] parts = fullTitle.split(" - ", 2);
                    String artistName = parts[0].trim();
                    String albumTitle = parts[1].trim();
                    String year = node.has("year") ? node.get("year").asText() : "";
                    // Only add if artist name matches (case-insensitive, partial match allowed)
                    if (artistName.toLowerCase().contains(artist.toLowerCase())) {
                        String thumb = node.has("thumb") ? node.get("thumb").asText() : null;
                        if (thumb == null || thumb.isEmpty()) continue; // Skip if no image

                        String uniqueKey = artistName + "|" + albumTitle + "|" + year;
                        if (seen.contains(uniqueKey)) continue; // Skip duplicates
                        seen.add(uniqueKey);

                        Map<String, Object> result = mapper.convertValue(node, Map.class);
                        result.put("albumTitle", albumTitle);
                        result.put("artistName", artistName);
                        resultsList.add(result);
                    }
                }
            }
        }
        resultsList.sort((a, b) -> {
            String artistA = (a.get("artistName") != null) ? ((String) a.get("artistName")).toLowerCase() : "";
            String artistB = (b.get("artistName") != null) ? ((String) b.get("artistName")).toLowerCase() : "";
            boolean aHasParen = artistA.matches(".*\\(\\d+\\)$");
            boolean bHasParen = artistB.matches(".*\\(\\d+\\)$");
            if (aHasParen && !bHasParen)
                return 1; // a goes after b
            if (!aHasParen && bHasParen)
                return -1; // a goes before b
            return artistA.compareTo(artistB); // fallback: alphabetical
        });
        return resultsList;
    }

    @Override
    public List<Map<String, Object>> searchByAlbum(String album) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    List<Map<String, Object>> resultsList = new ArrayList<>();

    JsonNode response = discogsRepository.searchByAlbumAndFormat(album, "release");
    if (response.has("results")) {
        for (JsonNode node : response.get("results")) {
            // 1. Filter for Vinyl format
            boolean isVinyl = false;
            if (node.has("format")) {
                for (JsonNode formatNode : node.get("format")) {
                    if (formatNode.asText().equalsIgnoreCase("Vinyl")) {
                        isVinyl = true;
                        break;
                    }
                }
            }
            if (!isVinyl) continue;

            // 2. Parse title into artist and album
            String fullTitle = node.has("title") ? node.get("title").asText() : null;
            if (fullTitle != null && fullTitle.contains(" - ")) {
                String[] parts = fullTitle.split(" - ", 2);
                String artistName = parts[0].trim();
                String albumTitle = parts[1].trim();
                // Only add if album title matches the search (case-insensitive)
                if (albumTitle.equalsIgnoreCase(album)) {
                    Map<String, Object> result = mapper.convertValue(node, Map.class);
                    result.put("albumTitle", albumTitle);
                    result.put("artistName", artistName);
                    resultsList.add(result);
                }
            }
        }
    }
        return resultsList;
    }
}