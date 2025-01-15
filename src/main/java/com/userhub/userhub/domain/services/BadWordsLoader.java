package com.userhub.userhub.domain.services;

import java.io.IOException;
import java.util.List;

public interface BadWordsLoader {
    List<String> loadBadWords(String filePath) throws IOException;
}
