package it.polito.tdp.rivers.db;

import java.util.ArrayList;
import java.util.LinkedList;

import com.sun.tools.javac.util.List;

import it.polito.tdp.rivers.model.River;

public class TestRiversDAO {

	public static void main(String[] args) {
		RiversDAO dao = new RiversDAO();
		System.out.println(dao.getAllRivers());
		LinkedList<River> rivers;
		rivers = new LinkedList<River>(dao.getAllRivers());
		System.out.println(dao.getMisurazioni(rivers.get(3)));
		
	}

}
