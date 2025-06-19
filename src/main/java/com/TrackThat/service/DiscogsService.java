package com.TrackThat.service;

import java.util.List;
import java.util.Map;

public interface DiscogsService {
    
    List<Map<String, Object>> searchByArtist(String artist) throws Exception;
    List<Map<String, Object>> searchByAlbum(String album) throws Exception;
}
