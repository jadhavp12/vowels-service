package com.example.vowelsservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FileHandlerImpl {


public Map<Integer, List<String>> readWordsFromFile(String filepath) {


    Map<Integer, List<String>> wordsMap = new HashMap<>();
   try(Scanner input = new Scanner(new File(filepath))) {

       while (input.hasNext()) {
           String word = input.next().replaceAll("[^a-zA-Z0-9]", "");

           if (wordsMap.containsKey(word.length())) {
               List<String> previousWords = new ArrayList<>(wordsMap.get(word.length()));
               previousWords.add(word);
               wordsMap.put(word.length(), previousWords);
           } else {
               wordsMap.put(word.length(), Collections.singletonList(word));
           }
       }
   }catch (IOException ex){
log.error("IOException occurred in readWordsFromFile {} ",ex.getMessage());
   }catch (Exception e){
       log.error("Exception occurred in readWordsFromFile {} ",e.getMessage());
   }
    return wordsMap;
}


    public void writeFile(List<String> strLine, String outputFilePath) throws IOException {

        File file=new File(outputFilePath);
            if (file.createNewFile()) {
                log.info("File is created!");
            } else {
                log.info("File already exists.");
            }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
           for (String s: strLine) {
                writer.write(s+System.lineSeparator());
            }
        }catch (IOException ex){
            log.error("IOException occurred in writeFile {}",ex.getMessage());
        }catch (Exception e){
            log.error("Exception occurred in writeFile {}",e.getMessage());
        }
    }

}
