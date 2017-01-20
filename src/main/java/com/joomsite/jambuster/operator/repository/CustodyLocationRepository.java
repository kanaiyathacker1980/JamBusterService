package com.joomsite.jambuster.operator.repository;

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

import com.joomsite.jambuster.operator.bean.CustodyLocation;
import com.joomsite.jambuster.operator.util.Utility;

@Repository
public class CustodyLocationRepository {

	@Autowired DataSource dataSource;

	public long createCustodyLocation(final CustodyLocation cl) {
		final String INSERT_SQL = " INSERT INTO custody_location " +
				  " (address_line1 , address_line2 , city , state , country , "+
				  " created_date ) " +
				  " VALUES ( ? , ? , ? , ? , ? , ? )";
				
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				KeyHolder keyHolder = new GeneratedKeyHolder();
				jdbcTemplate.update(new PreparedStatementCreator() {
						
				      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				          PreparedStatement pst = con.prepareStatement(INSERT_SQL, new String[] {"custody_location_id"});
				          pst.setString(1, cl.addressLine1);
				          pst.setString(2, cl.addressLine2);
				          pst.setString(3, cl.city);
				          pst.setString(4, cl.state);
				          pst.setString(5, cl.country);
				          pst.setDate(6, Utility.getTodaysDateSQL());
				          return pst;
				      }
				  },
				  keyHolder);
				return keyHolder.getKey().longValue();
		
	}
	
	
	public List<CustodyLocation> getAllCustodyLocation() {
		// TODO Auto-generated method stub
		String SELECT_SQL = "SELECT * FROM custody_location";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query(SELECT_SQL, new CustodyLocationRowMapper());
	}
	
	public CustodyLocation getCustodyLocationByCustodyLocationId(long custodyLocationId) {
		// TODO Auto-generated method stub
		String SELECT_SQL = "SELECT * FROM custody_location WHERE custody_location_id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<CustodyLocation> query = jdbcTemplate.query(SELECT_SQL, new Object[]{custodyLocationId}, new CustodyLocationRowMapper());
		CustodyLocation retVal = null;
		if(!query.isEmpty()) {
			retVal = query.get(0);
		}
		return retVal;
	}
	
	public class CustodyLocationRowMapper implements RowMapper<CustodyLocation> {

		public CustodyLocation mapRow(ResultSet rs, int arg1) throws SQLException {
			long custodyLocationId = rs.getLong("custody_location_id");
			String addressLine1 = rs.getString("address_line1");
			String addressLine2 = rs.getString("address_line2");
			String city = rs.getString("city");
			String state = rs.getString("state");
			String country = rs.getString("country");
			String createdDate = rs.getString("created_date");
			String createdBy = rs.getString("created_by");
			
			CustodyLocation c = new CustodyLocation();
			c.custodyLocationId = custodyLocationId;
			c.addressLine1 = addressLine1;
			c.addressLine2 = addressLine2;
			c.city = city;
			c.state = state;
			c.country = country;
			c.createdDate = createdDate;
			c.createdBy = createdBy;
			return c;
		}
	}

	public void editCustodyLocation(final long custodyLocationId, CustodyLocation cl) {
		// TODO Auto-generated method stub
		String UPDATE_SQL = " UPDATE custody_location SET " +
				" address_line1 = ? , address_line2 = ?  , city = ? , state = ? , country = ? , " +
				" updated_date = ? WHERE custody_location_id = ? ";
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				jdbcTemplate.update(UPDATE_SQL, new Object[]{cl.addressLine1 , cl.addressLine2  , 
										 cl.city , cl.state , cl.country ,
										 Utility.getTodaysDateSQL() , custodyLocationId});
	}
	

}
