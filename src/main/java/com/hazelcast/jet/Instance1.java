package com.hazelcast.jet;

import com.hazelcast.jet.stream.IStreamMap;

public class Instance1 {
	
	 public static void main(String[] args) {
	        JetInstance instance1 = Jet.newJetInstance();	       
	        
	        IStreamMap<Integer, String> map = instance1.getMap("lines");
	        
	        map.put(0, "It was the best of times,");
	        map.put(1, "it was the worst of times,");
	        map.put(2, "it was the age of wisdom,");
	        map.put(3, "it was the age of foolishness,");
	        map.put(4, "it was the epoch of belief,");
	        map.put(5, "it was the epoch of incredulity,");
	        map.put(6, "it was the season of Light,");
	        map.put(7, "it was the season of Darkness");
	        map.put(8, "it was the spring of hope,");
	        map.put(9, "it was the winter of despair,");
	        map.put(10, "we had everything before us,");
	        map.put(11, "we had nothing before us,");
	        map.put(12, "we were all going direct to Heaven,");
	        map.put(13, "we were all going direct the other way --");
	        map.put(14, "in short, the period was so far like the present period, that some of "
	           + "its noisiest authorities insisted on its being received, for good or for "
	           + "evil, in the superlative degree of comparison only.");
	        
	    }

}
