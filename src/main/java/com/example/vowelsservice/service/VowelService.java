package com.example.vowelsservice.service;

import com.example.vowelsservice.service.impl.FileHandlerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component("vowelService")
@Slf4j
public class VowelService {
    @Value("${input-file-path}")
    String inputFilePath;

    @Value("${output-file-path}")
    String outputFilePath;

    @Autowired
    FileHandlerImpl fileHandler;
    public void getVowels() throws IOException {
        List<Integer> vowelsAscii= new ArrayList<>(Arrays.asList(97,101,105,111,117));
        List<String> strLine=new ArrayList<>();

        //read input file
        Map<Integer, List<String>> wordMap= fileHandler.readWordsFromFile(inputFilePath);

        //calculate vowels set , vowels count and average vowels
        wordMap.entrySet().forEach(e->{

            List <Character> charList= String.join("",e.getValue()).toLowerCase().chars().filter(p -> vowelsAscii.contains(p)).mapToObj(m->(char)m).collect(Collectors.toList());
            Set<Character> vowelsSet = new TreeSet<>(charList);
          float avgVowels=  (float)charList.size()/(float)e.getValue().size();

            strLine.add("(" +vowelsSet+" , "+e.getKey()+" ) -> "+avgVowels);
        });

        //write a file
        fileHandler.writeFile(strLine,outputFilePath);
        log.info("Successfully wrote to the file.");
        }

}
