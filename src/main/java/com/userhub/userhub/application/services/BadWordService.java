package com.userhub.userhub.application.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import com.fasterxml.jackson.core.type.TypeReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BadWordService {

    private static final Logger logger = LoggerFactory.getLogger(BadWordService.class);

    private final List<String> badWords;
    private final String filePath = "C:\\Users\\pedro.barros\\Desktop\\Estudos\\userhub\\src\\main\\resources\\config\\badwords.json";

    public BadWordService() throws IOException {
        this.badWords = loadBadWords(filePath);
    }

    private List<String> loadBadWords(String filePath) throws IOException {
        String content = Files.readString(Path.of(filePath));
        logger.debug("BadWordService - File Content: {}", content); // Log do conteúdo do arquivo
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, List<String>> map = objectMapper.readValue(content, new TypeReference<Map<String, List<String>>>() {
        });
        logger.debug("BadWordService - Parsed Map: {}", map); // Log do mapa resultante
        List<String> badWordsList = map.get("badwords");
        logger.debug("BadWordService - Bad Words List: {}", badWordsList); // Log da lista de palavras proibidas
        return badWordsList;
    }

    public List<String> getBadWords() {
        return badWords;
    }
}