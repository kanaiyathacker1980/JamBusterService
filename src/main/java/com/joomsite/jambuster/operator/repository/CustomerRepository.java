package com.joomsite.jambuster.operator.repository;

import static com.joomsite.jambuster.operator.util.Utility.*;

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

import com.joomsite.jambuster.operator.bean.ChangePassword;
import com.joomsite.jambuster.operator.bean.Customer;
import com.joomsite.jambuster.operator.bean.Login;
import com.joomsite.jambuster.operator.util.Utility;

@Repository
public class CustomerRepository {
	
	@Autowired DataSource dataSource;
	
	public long createCustomer(final Customer c , final String pin) {
		final String INSERT_SQL = " INSERT INTO customer " +
								  " (first_name , middle_name , last_name , dob , address_line1 , address_line2 , " +
								  " city , state , country , email , mobile , driving_licence_number , " +
								  " driving_licence_start_date , driving_licence_end_date , status , pin , " +
								  " created_date ) " +
								  " VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				KeyHolder keyHolder = new GeneratedKeyHolder();
				jdbcTemplate.update(
				    new PreparedStatementCreator() {
						
				        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				            PreparedStatement pst =
				                con.prepareStatement(INSERT_SQL, new String[] {"customer_id"});
				            pst.setString(1, c.firstName);
				            pst.setString(2, c.middleName);
				            pst.setString(3, c.lastName);
				            pst.setDate(4, Utility.getSQLDate(Utility.getDateObject(c.dateOfBirth , "dd-MM-yyyy")));
				            pst.setString(5, c.addressLine1);
				            pst.setString(6, c.addressLine2);
				            pst.setString(7, c.city);
				            pst.setString(8, c.state);
				            pst.setString(9, c.country);
				            pst.setString(10, c.email);
				            pst.setString(11, c.mobile);
				            pst.setString(12, c.drivingLicenceNumber);
				            pst.setDate(13, getSQLDate(getDateObject(c.drivingLicenceStartDate, "dd-MM-yyyy")));
				            pst.setDate(14,  getSQLDate(getDateObject(c.drivingLicenceEndDate, "dd-MM-yyyy")));
				            pst.setString(15, "A");
				            pst.setString(16, pin);
				            pst.setDate(17, getTodaysDateSQL());
				            return pst;
				        }
				    },
				    keyHolder);
				return keyHolder.getKey().longValue();
	}
	
	public Customer getCustomerByEmail(String email) {
		// TODO Auto-generated method stub
		String SELECT_SQL = "SELECT * FROM customer WHERE email = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Customer> query = jdbcTemplate.query(SELECT_SQL, new Object[]{email}, new CustomerRowMapper());
		Customer retVal = null;
		if(!query.isEmpty()) {
			retVal = query.get(0);
		}
		return retVal;
	}
	
	public class CustomerRowMapper implements RowMapper<Customer> {

		public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			long customerId = rs.getLong("customer_id");
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
			String drivingLicenceNumber = rs.getString("driving_licence_number");
			Date drivingLicenceStartDate = rs.getDate("driving_licence_start_date");			
			Date drivingLicenceEndDate = rs.getDate("driving_licence_end_date");
			String status = rs.getString("status");	
			String createdDate = rs.getString("created_date");
			String deviceType = rs.getString("device_type");
			String deviceId = rs.getString("device_id");

			Customer customer = new Customer();
			customer.customerId = customerId;
			customer.firstName = firstName;
			customer.middleName = middleName;
			customer.lastName = lastName;
			customer.email = email;
			customer.dateOfBirth = dob;
			customer.addressLine1 = addressLine1;
			customer.addressLine2 = addressLine2;
			customer.city = city;
			customer.state = state;
			customer.country = country;
			customer.mobile = mobile;
			customer.drivingLicenceNumber = drivingLicenceNumber;
			
			customer.drivingLicenceStartDate = Utility.getSQLDateAsString(drivingLicenceStartDate,"dd-MM-yyyy");
			customer.drivingLicenceEndDate = Utility.getSQLDateAsString(drivingLicenceEndDate,"dd-MM-yyyy");;
			customer.status = status;
			customer.createdDate = createdDate;
			customer.deviceType = deviceType;
			customer.deviceId = deviceId;
			
			return customer;
			
		}
	}

	public void editCustomer(long customerId, Customer c) {
		String UPDATE_SQL = " UPDATE customer SET " +
							" first_name = ? , middle_name = ?  , last_name = ? , dob = ? , address_line1 = ? , address_line2 = ? , " + 
							" city = ? , state = ?  , country = ? , mobile = ? , driving_licence_number = ? , " +
							" driving_licence_start_date = ? , driving_licence_end_date = ? , " +
							" updated_date = ? , email = ? WHERE customer_id = ? ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(UPDATE_SQL, new Object[]{c.firstName , c.middleName , c.lastName  , 
													 getSQLDate(getDateObject(c.dateOfBirth , "dd-MM-yyyy")) ,
													 c.addressLine1 , c.addressLine2 , c.city , c.state , c.country ,
													 c.mobile ,  c.drivingLicenceNumber , 
													 getSQLDate(getDateObject(c.drivingLicenceStartDate, "dd-MM-yyyy")) ,
													 getSQLDate(getDateObject(c.drivingLicenceEndDate, "dd-MM-yyyy")) ,
													 Utility.getTodaysDateSQL() , c.email , customerId});
	}

	public Customer getCustomerByCustomerId(long customerId) {
		String SELECT_SQL = "SELECT * FROM customer WHERE customer_id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Customer> query = jdbcTemplate.query(SELECT_SQL, new Object[]{customerId}, new CustomerRowMapper());
		Customer retVal = null;
		if(!query.isEmpty()) {
			retVal = query.get(0);
		}
		return retVal;
	}
	
	public List<Customer> getAllCustomer() {
		String SELECT_SQL = "SELECT * FROM customer";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query(SELECT_SQL, new CustomerRowMapper());
	}
	
	public List<Customer> getCustomerByStatus(String status) {
		String SELECT_SQL = "SELECT * FROM customer WHERE status = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query(SELECT_SQL, new Object[]{status}, new CustomerRowMapper());
	}

	public Customer login(Login login) {
		String SELECT_SQL = "SELECT * FROM customer WHERE mobile = ? and pin = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Customer> query = jdbcTemplate.query(SELECT_SQL, new Object[]{login.mobile , login.password}, new CustomerRowMapper());
		Customer retVal = null;
		if(!query.isEmpty()) {
			retVal = query.get(0);
		}
		return retVal;
	}

	public void changePassword(long customerId, ChangePassword changePassword) {
		String UDPATE_SQL = "UPDATE customer set pin = ? , updated_date = ? where customer_id = ? ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(UDPATE_SQL, new Object[]{ changePassword.newPin , Utility.getTodaysDateSQL() , customerId});
	}

	public String getUserPasswordByCustomerId(long customerId) {
		String SELECT_SQL = "SELECT * FROM customer WHERE customer_id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		@SuppressWarnings("unchecked")
		List<String> query = jdbcTemplate.query(SELECT_SQL, new Object[]{customerId}, new RowMapper() {
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

	public Customer getCustomerByMobile(String mobile) {
		String SELECT_SQL = "SELECT * FROM customer WHERE mobile = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Customer> query = jdbcTemplate.query(SELECT_SQL, new Object[]{mobile}, new CustomerRowMapper());
		Customer retVal = null;
		if(!query.isEmpty()) {
			retVal = query.get(0);
		}
		return retVal;
	}

	public String getUserPasswordByMobile(String mobile) {
		String SELECT_SQL = "SELECT * FROM customer WHERE mobile = ?";
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
		String UDPATE_SQL = "UPDATE customer set pin = ? , updated_date = ? where mobile = ? ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(UDPATE_SQL, new Object[]{ changePassword.newPin , Utility.getTodaysDateSQL() , mobile});
		
	}

	public String getPin(String mobile) {
		// TODO Auto-generated method stub
		return null;
	}
}
