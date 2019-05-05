package com.adi.springresttasksbmp.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adi.springresttasksbmp.beans.Counter;
import com.adi.springresttasksbmp.bo.CounterBo;
import com.adi.springresttasksbmp.utils.Constants;
import com.adi.springresttasksbmp.utils.PropertyFilterUtils;
import com.adi.springresttasksbmp.utils.CsvUtils;

@RestController
public class CounterController {

	@Autowired
	CounterBo counterBo;
	
	@PostMapping("/counter-api/search")
	public MappingJacksonValue inputWordCounts(@RequestBody Counter counter) throws IOException {

		counterBo.retrieveWordCounts(counter);
		
		//Filter out the properties not required in response	
		Set<String> propertiesToRetain = new HashSet<String>();
		propertiesToRetain.add(Constants.COUNTER_COUNTS);		
		return PropertyFilterUtils.getFilteredBean(counter, Constants.FILTER_COUNTER, propertiesToRetain);
	}
	
	@GetMapping(value="/counter-api/top/{top}", produces = "text/csv")
	public void wordsWithTheirCounts(HttpServletResponse response, @PathVariable int top) throws IOException {

		Map<String, Integer> wordCountsMap =  counterBo.retrieveWordsWithTheirCounts(top);
		CsvUtils.writeMapToCsv(response.getWriter(), wordCountsMap);
	}

}
