package com.revature.repositories;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Reinbursement;
import com.revature.util.ConnectionUtil;

public class FMDAOImpl implements FMDAO{

	private static Logger logger = Logger.getLogger(EmployeeDAOImpl.class);
	
	public boolean viewAllRequests() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Reinbursement> viewAllPastRequests() {
		List<Reinbursement> re = new ArrayList<Reinbursement>();
		Timestamp reimb_submitted;
		Timestamp reimb_resolved;
		Blob reimb_receipt;
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "Select * from ers_reimbursement;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int reimb_id = rs.getInt("reimb_id");
				double reimb_amount = rs.getInt("reimb_amount");
				if(rs.getTimestamp("reimb_submitted") != null) {
				reimb_submitted = rs.getTimestamp("reimb_submitted");
				}
				else { reimb_submitted = null;}
				if(rs.getTimestamp("reimb_resolved") != null){
				reimb_resolved = rs.getTimestamp("reimb_resolved");}
				else { reimb_resolved = null;
				}
				String reimb_description = rs.getString("reimb_description");
				if(rs.getBlob("reimb_receipt") != null) {
					reimb_receipt = rs.getBlob("reimb_receipt");
				} else {
					reimb_receipt = null;
				}
				int reimb_author = rs.getInt("reimb_author");
				int reimb_resolver = rs.getInt("reimb_resolver");
				int reimb_status_id = rs.getInt("reimb_status_id");
				int reimb_type_id = rs.getInt("reimb_type_id");

				Reinbursement reimb= new Reinbursement(reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description,
						reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id);
				re.add(reimb);
			}
			return re;
		} catch (SQLException e) {

			logger.warn("Something went wrong", e);
		}
		return re;
	}

	public boolean changeRequest(int reimb_id, int requestChange) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "UPDATE ers_reimbursement set reimb_status_id = ? where reimb_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, requestChange);
			stmt.setInt(2, reimb_id);

			return true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		return false;
	}


}
