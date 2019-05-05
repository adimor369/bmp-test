package com.adi.springresttasksbmp.utils;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class TextUtils {
	
	@Autowired
	ResourceLoader resourceLoader;
	
	//Calculate word occurrence count in the given file for each input word
	public Map<String, Integer> calculateWordCounts(String location, Set<String> words) throws IOException {
		
		Map<String, Integer> wordCountsMap;
		
		
		try (Scanner scanner = new Scanner(resourceLoader.getResource(location).getInputStream())){
			scanner.useDelimiter(Constants.REGEX_NON_WORD_CHAR_SEQUENCE);
			wordCountsMap = new LinkedHashMap<String, Integer>();

			for(String inputWord: words) {
				wordCountsMap.put(inputWord.toUpperCase(), 0);
			}
			
			String wordFromFile;
			while(scanner.hasNext()) {
				wordFromFile = scanner.next().toUpperCase();
				if(wordCountsMap.containsKey(wordFromFile)) {
					wordCountsMap.replace(wordFromFile, wordCountsMap.get(wordFromFile)+1);
				}			
			}
		}
		
		return wordCountsMap;
	}
	
	//Calculate word occurrence count for each distinct word in the given file
	public Map<String, Integer> getWordsWithTheirCounts(String location) throws IOException {
		
		Map<String, Integer> wordCountsMap;

		try (Scanner scanner = new Scanner(resourceLoader.getResource(location).getInputStream())) {
			scanner.useDelimiter(Constants.REGEX_NON_WORD_CHAR_SEQUENCE);
			wordCountsMap = new HashMap<String, Integer>();
			String word;

			while (scanner.hasNext()) {
				word = scanner.next().toUpperCase();

				if (wordCountsMap.containsKey(word)) {
					wordCountsMap.put(word, wordCountsMap.get(word) + 1);
				} else {
					wordCountsMap.put(word, 1);
				}
			}
			return wordCountsMap;
		}
	}

	/*
	 * Identify <n> words with maximum occurrence count in the given file. Sort them
	 * in the descending order of their count value
	 */
	
	public Map<String,Integer> getWordsWithTheirCounts(String location, int top) throws IOException{
		
		if(top <= 0) {
			return null;
		}
		
		Map<String, Integer> wordCountsMap = getWordsWithTheirCounts(location);

		Comparator<Entry<String,Integer>> compareByValue = new Comparator<Entry<String,Integer>>() {
			@Override
			public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
				int result;
				result = e2.getValue().compareTo(e1.getValue());
				if(result == 0) {
					result = e1.getKey().compareTo(e2.getKey());
				}
				return result;
			}		
		};
		
		Set<Entry<String,Integer>> wordCountsSortedSet = new TreeSet<Entry<String,Integer>>(compareByValue);
		wordCountsSortedSet.addAll(wordCountsMap.entrySet());

		int i=0;
		LinkedHashMap<String,Integer> wordsWithHighestCountsMap = new LinkedHashMap<String,Integer>();
		for(Entry<String,Integer> wordCount: wordCountsSortedSet) {
			wordsWithHighestCountsMap.put(wordCount.getKey(), wordCount.getValue());
			if(++i >= top) {
				break;
			}
		}
		
		return wordsWithHighestCountsMap;
	}
}
