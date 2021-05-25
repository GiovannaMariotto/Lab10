package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	private RiversDAO dao;
	private List<River> rivers;
	private Map<Integer,River> idMap;
	
	
	public Model() {
		dao= new  RiversDAO();
		rivers = new ArrayList<River>();
		idMap = new HashMap<Integer,River>();
	}
	
	public List<River> loadRivers() {
		rivers.addAll(dao.getAllRivers());
		for(River r : rivers) {
			if(r!=null) {
				idMap.put(r.getId(), r);
			}
		}
		return  rivers;
	}
	
	public List<Flow> getFlows(River r){
		return dao.getMisurazioni(r);
	}
	
	public River getRiver( int id) {
		return idMap.get(id);
	}
	
	public double getMedia(List<Flow> f) {
		double media =0;
		for(Flow f1 : f) {
			if(f1!=null) {
				media+=f1.getFlow();
			}
		}
	
		return (media/f.size());
	}
	
	public List<String> simula(Double k,Double fmedio , River r){
		List<Flow> flows = new LinkedList(dao.getMisurazioni(r));
		double media=0;
////		for(Flow f : flows) {
////			media+=f.getFlow();
////		}
//		double fmed=(media)/this.getFlows(r).size();
		List<String> result=null;
		Simulatore s = new Simulatore();
		s.init(k, fmedio,flows);
		s.run();
		result=new LinkedList<String>(s.getResult());
		return result;
		
	}
	
	
}
