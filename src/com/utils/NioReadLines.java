package com.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class NioReadLines {

	public List<String> getList() {
	String filePath = "doc/key.txt";
	List<String> arrLines = readFileAllLines(Paths.get(filePath));
	return arrLines;
}
public List<String> readFileAllLines(Path path) {
	List<String> lineList = Collections.emptyList();
	try {
		lineList = java.nio.file.Files.readAllLines(path, Charset.forName("gbk"));
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return lineList;
	}
}