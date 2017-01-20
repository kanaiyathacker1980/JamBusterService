package com.joomsite.jambuster.operator.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.joomsite.jambuster.operator.bean.ChangePassword;
import com.joomsite.jambuster.operator.bean.Login;
import com.joomsite.jambuster.operator.bean.Operator;
import com.joomsite.jambuster.operator.util.Utility;

@Repository
public class OperatorRepository {
	
	@Autowired DataSource dataSource;

	public Operator login(Login login) {
		String SELECT_SQL = "SELECT * FROM operator WHERE mobile = ? and pin = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Operator> query = jdbcTemplate.query(SELECT_SQL, new Object[]{login.mobile , login.password}, new OperatorRowMapper());
		Operator retVal = null;
		if(!query.isEmpty()) {
			retVal = query.get(0);
		}
		return retVal;
	}

	public class OperatorRowMapper implements RowMapper<Operator> {

		public Operator mapRow(ResultSet rs, int arg1) throws SQLException {
			long operatorId = rs.getLong("operator_id");
			String firstName = rs.getString("first_name");
			String middleName = rs.getString("middle_name");
			String lastName = rs.getString("last_name");
			String email = rs.getString("email");
			String dob = rs.getString("dob");
			String addressLine1 = rs.getString("address_line1");
			String addressLine2 = rs.getString("address_line2");
			String city = rs.getString("city");
			String state = rs.getString("state");
			String country = rs.getString("country");
			String mobile = rs.getString("mobile");
			String status = rs.getString("status");	
			String createdDate = rs.getString("created_date");	

			Operator operator = new Operator();
			operator.operatorId = operatorId;
			operator.firstName = firstName;
			operator.middleName = middleName;
			operator.lastName = lastName;
			operator.email = email;
			operator.dateOfBirth = dob;
			operator.addressLine1 = addressLine1;
			operator.addressLine2 = addressLine2;
			operator.city = city;
			operator.state = state;
			operator.country = country;
			operator.mobile = mobile;
			operator.status = status;
			operator.createdDate = createdDate;
			
			return operator;
		}
	}

	public void editCustomer(long operatorId, Operator o) {
		// TODO Auto-generated method stub
		String UPDATE_SQL = " UPDATE operator SET " +
				" first_name = ? , middle_name = ?  , last_name = ? , dob = ? , address_line1 = ? , address_line2 = ? , " + 
				" city = ? , state = ?  , country = ? , mobile = ? , status = ? , " +
				" updated_date = ? , email = ? WHERE operator_id = ? ";
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				jdbcTemplate.update(UPDATE_SQL, new Object[]{o.firstName , o.middleName , o.lastName  , 
										 Utility.getSQLDate(Utility.getDateObject(o.dateOfBirth , "dd-MM-yyyy")) ,
										 o.addressLine1 , o.addressLine2 , o.city , o.state , o.country ,
										 o.mobile , o.status ,
										 Utility.getTodaysDateSQL() , o.email , operatorId});
	}
	
	public Operator getOperatorByOperatorId(long operatorId) {
		String SELECT_SQL = "SELECT * FROM operator WHERE operator_id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Operator> query = jdbcTemplate.query(SELECT_SQL, new Object[]{operatorId}, new OperatorRowMapper());
		Operator retVal = null;
		if(!query.isEmpty()) {
			retVal = query.get(0);
		}
		return retVal;
	}
	
	public List<Operator> getAllOperator() {
		String SELECT_SQL = "SELECT * FROM operator";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query(SELECT_SQL, new OperatorRowMapper());
	}
	
	public List<Operator> getOperatorByStatus(String status) {
		String SELECT_SQL = "SELECT * FROM operator WHERE status = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query(SELECT_SQL, new Object[]{status}, new OperatorRowMapper());
	}

	public void changePassword(long operatorId, ChangePassword changePassword) {
		String UDPATE_SQL = "UPDATE operator set pin = ? , updated_date = ? where operator_id = ? ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(UDPATE_SQL, new Object[]{ changePassword.newPin , Utility.getTodaysDateSQL() , operatorId});
	}

	public String getUserPasswordByOperatorId(long operatorId) {
		String SELECT_SQL = "SELECT * FROM operator WHERE operator_id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		@SuppressWarnings("unchecked")
		List<String> query = jdbcTemplate.query(SELECT_SQL, new Object[]{operatorId}, new RowMapper() {
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				String pin = rs.getString("pin");
				return pin;
			}
		});
		String retVal = null;
		if(!query.isEmpty()) {
			retVal = query.get(0);
		}
		return retVal;
	}

	public Operator getOperatorByMobile(String mobile) {
		String SELECT_SQL = "SELECT * FROM operator WHERE mobile = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Operator> query = jdbcTemplate.query(SELECT_SQL, new Object[]{mobile}, new OperatorRowMapper());
		Operator retVal = null;
		if(!query.isEmpty()) {
			retVal = query.get(0);
		}
		return retVal;
	}

	public String getUserPasswordByMobile(String mobile) {
		String SELECT_SQL = "SELECT * FROM operator WHERE mobile = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		@SuppressWarnings("unchecked")
		List<String> query = jdbcTemplate.query(SELECT_SQL, new Object[]{mobile}, new RowMapper() {
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				String pin = rs.getString("pin");
				return pin;
			}
		});
		String retVal = null;
		if(!query.isEmpty()) {
			retVal = query.get(0);
		}
		return retVal;
	}

	public void changePasswordByMobile(String mobile, ChangePassword changePassword) {
		String UDPATE_SQL = "UPDATE operator set pin = ? , updated_date = ? where mobile = ? ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(UDPATE_SQL, new Object[]{ changePassword.newPin , Utility.getTodaysDateSQL() , mobile});
		
	}

}
