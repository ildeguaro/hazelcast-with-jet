package com.hazelcast.jet;

import com.hazelcast.jet.stream.IStreamMap;

public class Instance2 {
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException {
        JetInstance instance1 = Jet.newJetInstance();
        for (int i=1;i<20;i++){
        	Thread.currentThread().sleep(1000);
        	IStreamMap<Integer, String> map = instance1.getMap("lines");
        	System.out.println(" Obteniendo "+map.get(i));
        }
	}

}
