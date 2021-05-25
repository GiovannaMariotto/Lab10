package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import it.polito.tdp.rivers.model.*;


import javafx.event.EventType;

public class Simulatore {

	private Model model;
	
	public  Simulatore(){
		this.model = new Model();
	}
	
	
	//Coda degli eventi
	private PriorityQueue<Flow> queue;
	
	
	//Modelo del mondo
	private Double C; //C
	private List<Double> capacity = new ArrayList<Double>();
	private List<Flow> flows;
	private double differenzaFlusso;
	private Double Q;  //=Q --> in metri cubici
	static Double F_MIN;
	private Double fmed;
	private Double fOut;
	
	
	
	//Parametri di input
	private Double k; //fattore di scala
	
	
	//Parametri di output
	private Integer nGiorniDisservizio; //giorni senza erogazione minima
	private Double Cmed; //occupazione media
	List<String> result;
	private Integer giorniNonPieno;
	
	public void init(double k, double fmedio, List<Flow> flows) {
		//inizializa coda degli eventi e modelo del mondo
		queue = new PriorityQueue();
		
		this.fmed=fmedio*3600*24; //m^3/s---> m^3/giorno
		this.Q=k*fmed*30;
		this.k=k;
	
		this.fOut=0.0;
		this.C=this.Q/2;
		System.out.println("C iniziale:"+this.C+"\n");
		this.F_MIN=(0.8*fmed); //F_OUT
		this.flows= new LinkedList(flows);
		
		System.out.println("PARAMETRI INIZIALIZATI:"+"\n"+"Q:"+this.Q+"\n"+"C: "+this.C+"\n"+"Fmedio:"+this.fmed+"\n");
		 
		//Inizializza i parametri di output
		this.nGiorniDisservizio=0; 
		this.Cmed=C;
		this.giorniNonPieno=0;
		
		//inietta gli eventi di input(ARRIVAL)
		for(Flow f : flows) {
			if(f!=null) {
				this.queue.add(f);
			}

		}
		
	}
	
	public List<String> run() {
		result= new LinkedList<>();
		int count=0;
		double FLUSSO_OUT=0.0;
		double FLUSSO_IN = 0;
		
		while(!queue.isEmpty()) {
			
			Flow f = queue.poll();
			System.out.println("Date:"+f.getDay()+"\n");
			 FLUSSO_IN = f.getFlow()*3600*24;
		
		double i = Math.random();
		
		if(i<=0.05) {
			 FLUSSO_OUT=F_MIN*10; //10X +flusso
		}else {
			 FLUSSO_OUT=F_MIN;
		}
			C+=FLUSSO_IN;
			if(C>Q) { //CASO: capacita max
				C=Q;
			}
		
			if(C<FLUSSO_OUT) {
				//Non riesco garantire la quantità minima
				nGiorniDisservizio++;
				C=0.0;
			} else {
				C-=FLUSSO_OUT;
			}
			
			System.out.println("C: "+C+"\n");
			capacity.add(C); //lista della capacita gionaliera del bacino

			
		}
		System.out.println("Flusso out:"+FLUSSO_OUT+"\n");
		System.out.println("Flusso in: "+FLUSSO_IN+"\n");
		double Cavg=0;
		for(Double d : capacity) {
			Cavg+=d;
		}
		Cavg/=capacity.size();
		
		result.add("Numero di Flows: "+this.flows.size());
		result.add("Cmedio= "+Cavg+"\n");
		result.add("Numero di giorni senza servizio: "+this.nGiorniDisservizio);
	
		
		return result;
	}
	
	public List<String> getResult(){
		return result;
	}

	
	
	
}
