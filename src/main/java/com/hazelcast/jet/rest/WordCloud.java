package com.hazelcast.jet.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.jet.service.CloudWordsService;

@RestController
public class WordCloud {
	
	@Autowired
	private CloudWordsService service;	
	
	@RequestMapping("/words")
    public ResponseEntity<List<String>> getWords() {		
        return ResponseEntity.ok(service.geAlltWords());
    }
	
	@RequestMapping("/words/{word}")
    public String getWord(@PathVariable String word) {
        return service.getWord(word);
    }
	
	@RequestMapping(method=RequestMethod.POST,value="/words")
    public String addWord(@RequestBody String word) {
        service.addWord(word, word);
        return service.getWord(word);
    }

}
