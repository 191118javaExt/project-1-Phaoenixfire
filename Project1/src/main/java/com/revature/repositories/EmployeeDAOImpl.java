package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.models.ReimbursementTemplate;
import com.revature.models.Reinbursement;
import com.revature.util.ConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	private static Logger logger = Logger.getLogger(EmployeeDAOImpl.class);

	public boolean createRequest(ReimbursementTemplate rt) {
		Reinbursement reimb = new Reinbursement(rt.getAmount(), Timestamp.valueOf(LocalDateTime.now()), null,
				rt.getDescription(), rt.getUserId(), 1, rt.getType());
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO ers_reimbursement (reimb_amount, reimb_submitted, reimb_description, reimb_author,reimb_status_id, reimb_type_id, reimb_receipt) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?);";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, reimb.getReimb_amount());
			stmt.setTimestamp(2, reimb.getReimb_submitted());
			stmt.setString(3, reimb.getReimb_description());
			stmt.setInt(4, rt.getUserId());
			stmt.setInt(5, reimb.getReimb_status_id());
			stmt.setInt(6, rt.getType());
			stmt.setBytes(7, Base64.getEncoder().encode(rt.getReceiptDataUrl().getBytes()));

			stmt.execute();

		} catch (SQLException ex) {
			// logger.warn("Unable to insert reimbursement.", ex);
			System.out.println(ex);
		}
		return true;
	}

	public List<Reinbursement> viewPastRequests(int userId) {
		List<Reinbursement> re = new ArrayList<Reinbursement>();
		Timestamp reimb_submitted;
		Timestamp reimb_resolved;
		String reimb_receipt;
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "Select * from ers_reimbursement where REIMB_AUTHOR = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int reimb_id = rs.getInt("reimb_id");
				double reimb_amount = rs.getInt("reimb_amount");
				if (rs.getTimestamp("reimb_submitted") != null) {
					reimb_submitted = rs.getTimestamp("reimb_submitted");
				} else {
					reimb_submitted = null;
				}
				if (rs.getTimestamp("reimb_resolved") != null) {
					reimb_resolved = rs.getTimestamp("reimb_resolved");
				} else {
					reimb_resolved = null;
				}
				String reimb_description = rs.getString("reimb_description");
				if (rs.getString("reimb_receipt") != null) {
					reimb_receipt = new String(Base64.getDecoder().decode(rs.getBytes("reimb_receipt")));
				} else {
					reimb_receipt = null;
				}
				int reimb_author = rs.getInt("reimb_author");
				int reimb_resolver = rs.getInt("reimb_resolver");
				int reimb_status_id = rs.getInt("reimb_status_id");
				int reimb_type_id = rs.getInt("reimb_type_id");

				Reinbursement reimb = new Reinbursement(reimb_id, reimb_amount, reimb_submitted, reimb_resolved,
						reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id);
				re.add(reimb);
			}
			return re;
		} catch (SQLException e) {
			logger.warn("Something went wrong", e);
		}
		return null;
	}

	public List<Reinbursement> viewPendingRequests(int userId) {

		List<Reinbursement> re = new ArrayList<Reinbursement>();
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "Select * from ers_reimbursement where REIMB_AUTHOR = ? and REIMB_STATUS_ID = 1";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int reimb_id = rs.getInt("reimb_id");
				double reimb_amount = rs.getInt("reimb_amount");
				Timestamp reimb_submitted = rs.getTimestamp("reimb_submitted");
				Timestamp reimb_resolved = rs.getTimestamp("reimb_resolved");
				String reimb_description = rs.getString("reimb_description");
				String reimb_receipt = new String(Base64.getDecoder().decode(rs.getBytes("reimb_receipt")));
				int reimb_author = rs.getInt("reimb_author");
				int reimb_resolver = rs.getInt("reimb_resolver");
				int reimb_status_id = rs.getInt("reimb_status_id");
				int reimb_type_id = rs.getInt("reimb_type_id");

				Reinbursement reimb = new Reinbursement(reimb_id, reimb_amount, reimb_submitted, reimb_resolved,
						reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id);
				re.add(reimb);
			}

		} catch (SQLException e) {
			logger.warn("Something went wrong", e);
		}
		return re;
	}

	public List<Employee> findAllEmployees() {
		List<Employee> empList = new ArrayList<Employee>();
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "Select * from ers_users";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("ers_users_id");
				String username = rs.getString("ers_username");
				String password = rs.getString("ers_password");
				String firstName = rs.getString("user_first_name");
				String lastName = rs.getString("user_last_name");
				String email = rs.getString("user_email");
				int roleId = rs.getInt("user_role_id");

				Employee e = new Employee(id, username, password, firstName, lastName, email, roleId);
				empList.add(e);
			}
//			for(Employee f : empList) {
//				System.out.println(f);
//			}

		} catch (SQLException e) {
			logger.warn("something went wrong", e);
		}
		return empList;

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
				int id = rs.getInt("ers_users_id");
				String ers_username = rs.getString("ers_username");
				String password = rs.getString("ers_password");
				String firstName = rs.getString("USER_first_name");
				String lastName = rs.getString("user_last_name");
				String email = rs.getString("user_email");
				int roleId = rs.getInt("user_role_id");

				e = new Employee(id, ers_username, password, firstName, lastName, email, roleId);

			}
			System.out.println(e);
			return e;

		} catch (SQLException e) {
			logger.warn("something went wrong", e);
		}

		return null;
	}

}
