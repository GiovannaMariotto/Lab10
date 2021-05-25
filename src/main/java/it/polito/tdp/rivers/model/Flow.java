package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Flow implements Comparable {
	private LocalDate day;
	private double flow;
	private River river;

	public Flow(LocalDate day, double flow, River river) {
		this.day = day;
		this.flow = flow;
		this.river = river;
	}

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public double getFlow() {
		return flow;
	}

	public void setFlow(double flow) {
		this.flow = flow;
	}

	@Override
	public String toString() {
		return "Flow [day=" + day + ", flow=" + flow + ", river=" + river + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((river == null) ? 0 : river.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flow other = (Flow) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (river == null) {
			if (other.river != null)
				return false;
		} else if (!river.equals(other.river))
			return false;
		return true;
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof Flow) {
			return this.getDay().compareTo(((Flow) o).getDay());
		}
		return this.compareTo(o);
	}

	

	
	
	

	

	
}
