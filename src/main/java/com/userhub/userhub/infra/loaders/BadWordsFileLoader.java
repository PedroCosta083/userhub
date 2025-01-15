package com.userhub.userhub.infra.loaders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userhub.userhub.domain.entities.badwords.BadWordsList;
import com.userhub.userhub.domain.services.BadWordsLoader;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BadWordsFileLoader implements BadWordsLoader {
    private final ObjectMapper objectMapper;

    public BadWordsFileLoader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public List<String> loadBadWords(String filePath) throws IOException {
        BadWordsList badWordsList = objectMapper.readValue(Paths.get(filePath).toFile(), BadWordsList.class);
        return badWordsList.getBadwords();
    }
}
