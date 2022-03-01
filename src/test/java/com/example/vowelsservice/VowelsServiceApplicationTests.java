package com.example.vowelsservice;

import com.example.vowelsservice.service.FileHandler;
import com.example.vowelsservice.service.VowelService;
import com.example.vowelsservice.service.impl.FileHandlerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@SpringBootTest
class VowelsServiceApplicationTests {
	@Value("${input-file-path}")
	String inputFilePath;

	@Value("${output-file-path}")
	String outputFilePath;

	@Autowired @MockBean
	VowelService vowelService;

	@org.springframework.beans.factory.annotation.Autowired(required=true)
	FileHandlerImpl fileHandler;

	@Test
	void contextLoads() throws IOException {
		File file =new File(inputFilePath);
		assertThat(file).exists();


		final String filename="Input.txt";
		final File expectedInput = Paths.get("src","test","resources","files",filename).toFile();

		final String outputFileName="Output.txt";
		final File expectedOutput = Paths.get("src","test","resources","files",outputFileName).toFile();

		assertThat(file.getName()).matches(expectedInput.getName());
		this.vowelService.getVowels();
		List<String> fileLines=Files.readAllLines(Paths.get(String.valueOf(new File(outputFilePath))));
		List<String> reqLines=Files.readAllLines(Paths.get(String.valueOf(expectedOutput)));
		assertThat(fileLines).isNotEmpty();
		assertEquals(fileLines,reqLines);

	}





}
