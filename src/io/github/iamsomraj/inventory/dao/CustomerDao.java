package io.github.iamsomraj.inventory.dao;

import java.sql.*;

import io.github.iamsomraj.inventory.database.DatabaseUtil;
import io.github.iamsomraj.inventory.model.Customer;

public class CustomerDao {

	Connection conn = DatabaseUtil.createConnection();
	private static String TABLE_NAME = "customers";

	public void insertCustomer(Customer customer) {

		try {
			PreparedStatement pstmt = cn.prepareStatement("insert into " + TABLE_NAME + " values(?,?,?,?,?)");

			pstmt.executeUpdate();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	// Statement PreparedStatement CallableStatement

	public Employee getEmployeeById(int id) {
		Employee e = null;
		try {
			Statement stmt = cn.createStatement();
			String qry = "select * from employees where empid=" + id;
			ResultSet rs = stmt.executeQuery(qry);
			if (rs.next()) {
				e = new Employee();
				e.setEmpid(rs.getInt(1));
				e.setEmpname(rs.getString(2));
				e.setDeptId(rs.getString(3));
				e.setJoinDate(rs.getDate(4));
				e.setSalary(rs.getLong(5));
			}

		} catch (Exception ex) {
		}
		return e;
	}

	public List<Employee> getEmployees() {
		return null;
		// fetch all employees from db;
	}

	public void updateEmployee(Employee e) {
		// code to update Employee object
	}

	public void deleteEmployeeById(int id) {
		// delete employee from DB with matching id

	}

}
