package com.example.vowelsservice.service;

import java.util.List;
import java.util.Map;

public interface FileHandler {

    public Map<Integer, List<String>> readWordsFromFile(String filepath);
    public void writeFile(List<String> strLine, String outputFilePath);
}
