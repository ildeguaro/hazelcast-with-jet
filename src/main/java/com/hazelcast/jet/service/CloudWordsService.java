package com.hazelcast.jet.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hazelcast.jet.JetInstance;
import com.hazelcast.jet.stream.IStreamMap;

@Service
public class CloudWordsService {
	
	private static final Logger log = LoggerFactory.getLogger(CloudWordsService.class);	
		
	@SuppressWarnings("unused")
	@Autowired
	private final JetInstance jetInstance;		
	
	private IStreamMap<String, String> words;
    
    	
	public CloudWordsService(JetInstance jetInstance){
		this.jetInstance= jetInstance;
		this.words = jetInstance.getMap("words");
		log.info("Inicializada Cache");
	
	}	
	public void addWord(String key, String word){		
		this.words.put(key, word);
		log.info("Added Word: "+word);
	}
	
	public String getWord(String key){	
		log.info("Getting Word: "+key);
		return this.words.get(key);
	}
	
	public List<String> geAlltWords(){
		List<String> list = new ArrayList<>();
		this.words.forEach((k,v)->{
			list.add(v);
		});
		log.info("Got Words: "+list);
		return list;
	}
	
	
}
