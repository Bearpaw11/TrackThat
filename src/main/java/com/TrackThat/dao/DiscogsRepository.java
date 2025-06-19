package com.TrackThat.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class DiscogsRepository {

    @Value("${discogs.consumer.key}")
    private String consumerKey;

    @Value("${discogs.consumer.secret}")
    private String consumerSecret;

    private final String baseUrl = "https://api.discogs.com";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonNode search(String query, String type) throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
            String searchUrl = baseUrl + "/database/search?q=" + encodedQuery + "&type=" + type;
            HttpGet request = new HttpGet(searchUrl);
            request.setHeader("User-Agent", "TrackThat/1.0");
            request.setHeader("Authorization", "Discogs key=" + consumerKey + ", secret=" + consumerSecret);
    
            String response = EntityUtils.toString(httpClient.execute(request).getEntity());
            return objectMapper.readTree(response);
        }
    }

    public JsonNode getArtistReleases(String artistId) throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String url = baseUrl + "/artists/" + artistId + "/releases";
            HttpGet request = new HttpGet(url);
            request.setHeader("User-Agent", "TrackThat/1.0");
            request.setHeader("Authorization", "Discogs key=" + consumerKey + ", secret=" + consumerSecret);
    
            String response = EntityUtils.toString(httpClient.execute(request).getEntity());
            return objectMapper.readTree(response);
        }
    }
}

