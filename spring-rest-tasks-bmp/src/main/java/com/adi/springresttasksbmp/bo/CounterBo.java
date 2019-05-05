package com.adi.springresttasksbmp.bo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.adi.springresttasksbmp.beans.Counter;
import com.adi.springresttasksbmp.utils.Constants;
import com.adi.springresttasksbmp.utils.TextUtils;

@Service
public class CounterBo {

	@Autowired
	TextUtils textUtils;

	@Autowired
	private Environment env;

	public void retrieveWordCounts(Counter counter) throws IOException {

		Map<String, Integer> wordCountsMap = textUtils.calculateWordCounts("classpath:" + env.getProperty("sample.text.file"),
				new LinkedHashSet<String>(counter.getSearchText()));

		// Format the result as per service response format
		List<Map<String, Integer>> counts = new ArrayList<Map<String, Integer>>();
		for (Entry<String, Integer> wordCount : wordCountsMap.entrySet()) {
			Map<String, Integer> wordCountMap = new HashMap<String, Integer>();
			wordCountMap.put(wordCount.getKey(), wordCount.getValue());
			counts.add(wordCountMap);
		}

		counter.setCounts(counts);
	}

	public Map<String, Integer> retrieveWordsWithTheirCounts(int top) throws IOException {

		return textUtils.getWordsWithTheirCounts("classpath:" + env.getProperty("sample.text.file"), top);
	}

}
