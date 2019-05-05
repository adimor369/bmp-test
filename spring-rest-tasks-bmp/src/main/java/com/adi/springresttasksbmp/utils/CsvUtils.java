package com.adi.springresttasksbmp.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CsvUtils {

	public static void writeMapToCsv(PrintWriter writer, Map<String, Integer> wordCountsMap) throws IOException {
		try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter('|'))) {
		      for (Entry<String, Integer> wordCount : wordCountsMap.entrySet()) {
		        List<String> data = Arrays.asList(
		        	wordCount.getKey(),
		            wordCount.getValue().toString()
		          );
		        
		        csvPrinter.printRecord(data);
		      }
		      csvPrinter.flush();
		    }
	}
}
