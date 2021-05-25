package it.polito.tdp.rivers.model;

import java.util.LinkedList;
import java.util.List;

public class testModel {

	public static void main(String[] args) {
		Model m = new Model();
		List<River> r = new LinkedList(m.loadRivers());
//		System.out.println(m.getFlows(r.get(2)));
//		System.out.println(m.getMedia(m.getFlows(r.get(2))));
		
		
		List<String> s = m.simula(0.5, 50.0, r.get(2));
		for(String s1 :s) {
			System.out.println(s1.toString());
		}

	}

}
