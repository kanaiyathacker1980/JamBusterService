package com.joomsite.jambuster.operator.repository;

import static com.joomsite.jambuster.operator.util.Utility.getDateObject;
import static com.joomsite.jambuster.operator.util.Utility.getSQLDate;
import static com.joomsite.jambuster.operator.util.Utility.getTodaysDateSQL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.joomsite.jambuster.operator.bean.Vehicle;
import com.joomsite.jambuster.operator.util.Utility;

@Repository
public class VehicleRepository {
	
	@Autowired DataSource dataSource;
	
	public Vehicle getVehicleByVehicleNo(String vehicleNo) {
		String SELECT_SQL = "SELECT * FROM vehicle WHERE vehicle_no = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Vehicle> query = jdbcTemplate.query(SELECT_SQL, new Object[]{vehicleNo}, new VehicleRowMapper());
		Vehicle retVal = null;
		if(!query.isEmpty()) {
			retVal = query.get(0);
		}
		return retVal;
	}
	
	public Vehicle getVehicleByPucNo(String pucNo) {
		String SELECT_SQL = "SELECT * FROM vehicle WHERE puc_no = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Vehicle> query = jdbcTemplate.query(SELECT_SQL, new Object[]{pucNo}, new VehicleRowMapper());
		Vehicle retVal = null;
		if(!query.isEmpty()) {
			retVal = query.get(0);
		}
		return retVal;
	}
	
	public Vehicle getVehicleByVehicleId(long vehicleId) {
		String SELECT_SQL = "SELECT * FROM vehicle WHERE vehicle_id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Vehicle> query = jdbcTemplate.query(SELECT_SQL, new Object[]{vehicleId}, new VehicleRowMapper());
		Vehicle retVal = null;
		if(!query.isEmpty()) {
			retVal = query.get(0);
		}
		return retVal;
	}
	
	public List<Vehicle> getVehiclesByCustomerId(long customerId) {
		// TODO Auto-generated method stub
		String SELECT_SQL = "SELECT * FROM vehicle WHERE customer_id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query(SELECT_SQL, new Object[]{customerId}, new VehicleRowMapper());
	}
	
	public class VehicleRowMapper implements RowMapper<Vehicle> {

		public Vehicle mapRow(ResultSet rs, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			long vehicleId = rs.getLong("vehicle_id");
			long customerId = rs.getLong("customer_id");
			String vehicleType = rs.getString("vehicle_type");
			String vehicleNo = rs.getString("vehicle_no");
			String pucNo = rs.getString("puc_no");
			Date pucStartDate = rs.getDate("puc_start_date");
			Date pucEndDate = rs.getDate("puc_end_date");
			String createdDate = rs.getString("created_date");
			String createdBy = rs.getString("created_by");
			String updatedDate = rs.getString("updated_date");
			String updatedBy = rs.getString("updated_by");
			String insuranceNo = rs.getString("insurance_no");
			Date insuranceStartDate = rs.getDate("insurance_start_date");
			Date insuranceEndDate = rs.getDate("insurance_end_date");

			Vehicle v = new Vehicle();
			v.vehicleId = vehicleId;
			v.vehicleNo = vehicleNo;
			v.customerId = customerId;
			v.vehicleType = vehicleType;
			v.pucNo = pucNo;
			v.pucStartDate = Utility.getSQLDateAsString(pucStartDate,"dd-MM-yyyy");
			v.pucEndDate = Utility.getSQLDateAsString(pucEndDate,"dd-MM-yyyy");;
			v.createdDate = createdDate;
			v.insuranceNo = insuranceNo;
			v.insuranceStartDate = Utility.getSQLDateAsString(insuranceStartDate,"dd-MM-yyyy");;
			v.insuranceEndDate = Utility.getSQLDateAsString(insuranceEndDate,"dd-MM-yyyy");;
			return v;
		}
	}

}
