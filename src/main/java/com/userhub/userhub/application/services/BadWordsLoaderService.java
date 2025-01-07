package com.userhub.userhub.application.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userhub.userhub.domain.entities.badwords.BadWordsList;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BadWordsLoaderService {
    private final ObjectMapper objectMapper;

    public BadWordsLoaderService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<String> loadBadWords(String filePath) throws IOException {
        BadWordsList badWordsList = objectMapper.readValue(Paths.get(filePath).toFile(), BadWordsList.class);
        return badWordsList.getBadwords();
    }
}
