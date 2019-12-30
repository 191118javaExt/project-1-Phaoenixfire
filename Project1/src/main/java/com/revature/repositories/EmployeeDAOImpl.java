package com.revature.repositories;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.models.Reinbursement;
import com.revature.util.ConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	private static Logger logger = Logger.getLogger(EmployeeDAOImpl.class);

	public Reinbursement submitRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	public Reinbursement viewPastRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	public Reinbursement viewPendingRequests(int ERS_USERS_ID) {
		Reinbursement re = null;
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "Select * from ers_reimbursement where REIMB_AUTHOR = ? and REIMB_STATUS_ID = 1";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ERS_USERS_ID);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
			int reimb_id = rs.getInt("reimb_id");
			double reimb_amount = rs.getInt("reimb_amount");
			LocalDateTime reimb_submitted = rs.getTimestamp("reimb_submitted").toLocalDateTime();
			LocalDateTime reimb_resolved = rs.getTimestamp("reimb_resolved").toLocalDateTime();
			String reimb_description = rs.getString("reimb_description");
			Blob reimb_receipt = rs.getBlob("reimb_receipt");
			int reimb_author = rs.getInt("reimb_author");
			int reimb_resolver = rs.getInt("reimb_resolver");
			int reimb_status_id = rs.getInt("reimb_status_id");
			int reimb_type_id = rs.getInt("reimb_type_id");
			
			re = new Reinbursement(reimb_id,reimb_amount,reimb_submitted,reimb_resolved,
					reimb_description,reimb_receipt,reimb_author,reimb_resolver,reimb_status_id,reimb_type_id);
			
			}
			System.out.println(re);
			return re;
		} catch (SQLException e) {
			logger.warn("Something went wrong", e);
		}
		return null;
	}

	public List<Employee> findAllEmployees() {

		try (Connection conn = ConnectionUtil.getConnection()) {

			List<Employee> empList = new ArrayList<Employee>();
			String sql = "Select * from ers_users";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int ers_users_id = rs.getInt("ers_users_id");
				String ers_username = rs.getString("ers_username");
				String ers_password = rs.getString("ers_password");
				String user_first_name = rs.getString("user_first_name");
				String user_last_name = rs.getString("user_last_name");
				String user_email = rs.getString("user_email");
				int user_role_id = rs.getInt("user_role_id");

				Employee e = new Employee(ers_users_id, ers_username, ers_password, user_first_name, user_last_name,
						user_email, user_role_id);
				empList.add(e);
			}
			for(Employee f : empList) {
				System.out.println(f);
			}
			return empList;

		} catch (SQLException e) {
			logger.warn("something went wrong", e);
		}

		return null;
	}

	@Override
	public Employee findByUsername(String username) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			Employee e = null;
			String sql = "Select * from ers_users where ers_username = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int ers_users_id = rs.getInt("ers_users_id");
				String ers_username = rs.getString("ers_username");
				String ers_password = rs.getString("ers_password");
				String user_first_name = rs.getString("USER_first_name");
				String user_last_name = rs.getString("user_last_name");
				String user_email = rs.getString("user_email");
				int user_role_id = rs.getInt("user_role_id");

				e = new Employee(ers_users_id, ers_username, ers_password, user_first_name, user_last_name, user_email,
						user_role_id);
				
			}
			System.out.println(e);
			return e;

		} catch (SQLException e) {
			logger.warn("something went wrong", e);
		}

		return null;
	}

}
