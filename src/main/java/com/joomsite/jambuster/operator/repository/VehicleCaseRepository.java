package com.joomsite.jambuster.operator.repository;

import java.math.BigDecimal;
import java.sql.Connection;
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

import com.joomsite.jambuster.operator.bean.VehicleCase;
import com.joomsite.jambuster.operator.util.Utility;

@Repository
public class VehicleCaseRepository {
	
	@Autowired DataSource dataSource;

	public long createCase(final VehicleCase c) {
		// TODO Auto-generated method stub
		final String INSERT_SQL = " INSERT INTO vehicle_case " +
				  " (vehicle_no , vehicle_type , case_desc , case_open_date , case_close_date , "+
				  " contact_number , charges , pickup_location_lat , pickup_location_lon , " +
				  " pickup_location , custody_location_id , case_open_time , operator_id , status)" +
				  " VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
				
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				KeyHolder keyHolder = new GeneratedKeyHolder();
				jdbcTemplate.update(new PreparedStatementCreator() {
						
				      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				          PreparedStatement pst = con.prepareStatement(INSERT_SQL, new String[] {"vehicle_case_id"});
				          pst.setString(1, c.vehicleNo);
				          pst.setString(2, c.vehicleType);
				          pst.setString(3, c.caseDesc);
//				          pst.setDate(4, Utility.getSQLDate(Utility.getDateObject(c.caseOpenDate , "dd-MM-yyyy")));
//				          pst.setDate(5, Utility.getSQLDate(Utility.getDateObject(c.caseCloseDate , "dd-MM-yyyy")));
				          
				          pst.setDate(4, Utility.getTodaysDateSQL());
				          pst.setDate(5, Utility.getTodaysDateSQL());
				          
				          pst.setString(6, c.contactNumber);
				          pst.setBigDecimal(7, c.charges);
				          pst.setString(8, c.pickupLocationLat);
				          pst.setString(9, c.pickupLocationLon);
				          pst.setString(10, c.pickupLocation);
				          pst.setLong(11, c.custodyLocationId);
				          pst.setString(12, Utility.getTodaysTimeSQL());
				          pst.setLong(13, c.operatorId);
				          pst.setString(14, c.status);
				          return pst;
				      }
				  },
				  keyHolder);
				return keyHolder.getKey().longValue();
	}
	
	public List<VehicleCase> getCaseByCaseOpenDate(String fromDate , String toDate) {
		// TODO Auto-generated method stub
		String SELECT_SQL = "SELECT * FROM vehicle_case WHERE case_open_date = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query(SELECT_SQL, new Object[]{fromDate , toDate}, new CaseRowMapper());
	}
	
	public VehicleCase getCaseByCaseId(long caseId) {
		// TODO Auto-generated method stub
		String SELECT_SQL = "SELECT * FROM vehicle_case WHERE vehicle_case_id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<VehicleCase> query = jdbcTemplate.query(SELECT_SQL, new Object[]{caseId}, new CaseRowMapper());
		VehicleCase retVal = null;
		if(!query.isEmpty()) {
			retVal = query.get(0);
		}
		return retVal;
	}
	
	public List<VehicleCase> getCaseByVehicleNo(String vehicleNo) {
		// TODO Auto-generated method stub
		String SELECT_SQL = "SELECT * FROM vehicle_case WHERE vehicle_no = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query(SELECT_SQL, new Object[]{vehicleNo}, new CaseRowMapper());
	}
	
	public class CaseRowMapper implements RowMapper<VehicleCase> {

		public VehicleCase mapRow(ResultSet rs, int arg1) throws SQLException {
			long caseId = rs.getLong("vehicle_case_id");
			String vehicleNo = rs.getString("vehicle_no");
			String vehicleType = rs.getString("vehicle_type");
			String caseDesc = rs.getString("case_desc");
			String caseOpenDate = rs.getString("case_open_date");
			String caseOpenTime = rs.getString("case_open_time");
			String caseCloseDate = rs.getString("case_close_date");
			String contactNumber = rs.getString("contact_number");
			BigDecimal charges = rs.getBigDecimal("charges");
			String pickupLocationLat = rs.getString("pickup_location_lat");
			String pickupLocationLon = rs.getString("pickup_location_lon");
			String pickupLocation = rs.getString("pickup_location");
			long custodyLocationId = rs.getLong("custody_location_id");
			String createdDate = rs.getString("created_date");	
			String createdBy = rs.getString("created_by");	
			long operatorId = rs.getLong("operator_id");	
			String status = rs.getString("status");

			VehicleCase c = new VehicleCase();
			c.vehicleCaseId = caseId;
			c.operatorId = operatorId;
			c.vehicleNo = vehicleNo;
			c.vehicleType = vehicleType;
			c.caseDesc = caseDesc;
			c.caseOpenDate = caseOpenDate;
			c.caseOpenTime = caseOpenTime;
			c.caseCloseDate = caseCloseDate;
			c.contactNumber = contactNumber;
			c.charges = charges;
			c.pickupLocationLat = pickupLocationLat;
			c.pickupLocationLon = pickupLocationLon;
			c.pickupLocation = pickupLocation;
			c.custodyLocationId = custodyLocationId;
			c.status = status;
			return c;
		}
	}

	public void editCase(long caseId, VehicleCase c) {
		String UPDATE_SQL = " UPDATE vehicle_case SET " +
				" vehicle_no = ? , vehicle_type = ?  , case_desc = ? , " +
				" contact_number = ? , charges = ?  , pickup_location_lat = ? , pickup_location_lon = ? , pickup_location = ? , " +
				" updated_date = ? , status = ? WHERE vehicle_case_id = ? ";
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				jdbcTemplate.update(UPDATE_SQL, new Object[]{c.vehicleNo , c.vehicleType , c.caseDesc , 
										 					 c.contactNumber , c.charges , c.pickupLocationLat , c.pickupLocationLon ,
										 					 c.pickupLocation ,
															 Utility.getTodaysDateSQL() , c.status , caseId});
	}

	public List<VehicleCase> getAllCases() {
				String SELECT_SQL = "SELECT * FROM vehicle_case ";
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				return jdbcTemplate.query(SELECT_SQL , new CaseRowMapper());
	}

	public List<VehicleCase> getCaseByOperatorId(long operatorId) {
		// TODO Auto-generated method stub
		String SELECT_SQL = "SELECT * FROM vehicle_case WHERE operator_id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query(SELECT_SQL, new Object[]{operatorId}, new CaseRowMapper());
	}

}
