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

     public JsonNode searchByArtistAndFormat(String artist, String format) throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String encodedArtist = URLEncoder.encode(artist, StandardCharsets.UTF_8.toString());
            String encodedFormat = URLEncoder.encode(format, StandardCharsets.UTF_8.toString());
            String searchUrl = baseUrl + "/database/search?artist=" + encodedArtist + "&format=" + encodedFormat + "&type=release" + "&type=release&per_page=200";
            HttpGet request = new HttpGet(searchUrl);
            request.setHeader("User-Agent", "TrackThat/1.0");
            request.setHeader("Authorization", "Discogs key=" + consumerKey + ", secret=" + consumerSecret);

            String response = EntityUtils.toString(httpClient.execute(request).getEntity());
            return objectMapper.readTree(response);
        }
    }

    public JsonNode searchByAlbumAndFormat(String album, String format) throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String encodedAlbum = URLEncoder.encode(album, StandardCharsets.UTF_8.toString());
            String encodedFormat = URLEncoder.encode(format, StandardCharsets.UTF_8.toString());
            String searchUrl = baseUrl + "/database/search?release_title=" + encodedAlbum + "&format=" + encodedFormat + "&type=release";
            HttpGet request = new HttpGet(searchUrl);
            request.setHeader("User-Agent", "TrackThat/1.0");
            request.setHeader("Authorization", "Discogs key=" + consumerKey + ", secret=" + consumerSecret);
    
            String response = EntityUtils.toString(httpClient.execute(request).getEntity());
            return objectMapper.readTree(response);
        }
    }
}

