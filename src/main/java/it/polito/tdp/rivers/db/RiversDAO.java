package it.polito.tdp.rivers.db;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
	public List<Flow> getMisurazioni(River r) {
		
		String sql = "SELECT f.id, f.day, f.flow "
				+ "FROM flow f, river r "
				+ "WHERE f.river=r.id AND r.id=? "
				+ "ORDER BY f.day, f.id, f.flow ";
		List<Flow> flows = new ArrayList<>();
		
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,r.getId());
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
			flows.add( new Flow(rs.getTimestamp("day").toLocalDateTime().toLocalDate() ,rs.getDouble("flow"),r));	
			}
			
			rs.close();
			st.close();
			conn.close();
			return flows;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	
}
