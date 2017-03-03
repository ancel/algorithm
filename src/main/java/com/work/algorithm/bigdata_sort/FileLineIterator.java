package com.work.algorithm.bigdata_sort;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileLineIterator implements Iterator<String>,Closeable{
	public static final Logger LOGGER = LoggerFactory.getLogger(FileLineIterator.class);
	
	private String fileName;
	private BufferedReader reader;
	private String nextLine;
	private boolean hasNext;
	public FileLineIterator(String fileName) throws IOException {
		this.fileName = fileName;
		this.reader = new BufferedReader(new FileReader(fileName)); 
		nextLine = this.reader.readLine();
		hasNext = (null!=nextLine);
	}

	@Override
	public boolean hasNext() {
		return hasNext;
	}

	@Override
	public String next() {
		String line = nextLine;
		try {
			nextLine = this.reader.readLine();
		} catch (IOException e) {
			LOGGER.error("readline error-"+fileName,e);
			System.exit(1);
		}
		hasNext = (null!=nextLine);
		return line;
	}

	@Override
	public void remove() {
		
	}

	@Override
	public void close() throws IOException {
		if(reader!=null){
			reader.close();
		}
	}

}
