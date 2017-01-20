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

import com.joomsite.jambuster.operator.bean.CaseAttachment;
import com.joomsite.jambuster.operator.util.Utility;

@Repository
public class CaseAttachmentRepository {

	@Autowired DataSource dataSource;

	public long createCaseAttachment(final long vehicleCaseId , final String fileName , final String filePath) {
		final String INSERT_SQL = " INSERT INTO vehicle_case_attachment " +
				  " (file_name , file_path , vehicle_case_id , created_date ) " +
				  " VALUES ( ? , ? , ? , ? )";
				
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				KeyHolder keyHolder = new GeneratedKeyHolder();
				jdbcTemplate.update(new PreparedStatementCreator() {
						
				      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				          PreparedStatement pst = con.prepareStatement(INSERT_SQL, new String[] {"vehicle_case_attachment_id"});
				          pst.setString(1, fileName);
				          pst.setString(2, filePath);
				          pst.setLong(3, vehicleCaseId);
				          pst.setDate(4, Utility.getTodaysDateSQL());
				          return pst;
				      }
				  },
				  keyHolder);
				return keyHolder.getKey().longValue();
	}
	
	public void editCaseAttachment(long caseAttachmentId , CaseAttachment ca) {
		// TODO Auto-generated method stub
	}
	
	public CaseAttachment getCaseAttachmentByCaseAttachmentId(long vehicleCaseAttachmentId) {
		String SELECT_SQL = "SELECT * FROM vehicle_case_attachment WHERE vehicle_case_attachment_id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<CaseAttachment> query = jdbcTemplate.query(SELECT_SQL, new Object[]{vehicleCaseAttachmentId}, new CaseAttachmentRowMapper());
		CaseAttachment retVal = null;
		if(!query.isEmpty()) {
			retVal = query.get(0);
		}
		return retVal;
	}
	
	public List<CaseAttachment> getCaseAttachmentByCaseId(long vehicleCaseId) {
		String SELECT_SQL = " SELECT * FROM vehicle_case_attachment WHERE vehicle_case_id = ? ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query(SELECT_SQL, new Object[]{vehicleCaseId}, new CaseAttachmentRowMapper());
	}
	
	public class CaseAttachmentRowMapper implements RowMapper<CaseAttachment> {

		public CaseAttachment mapRow(ResultSet rs, int arg1) throws SQLException {
			long caseAttachmentId = rs.getLong("vehicle_case_attachment_id");
			String fileName = rs.getString("file_name");
			String filePath = rs.getString("file_path");
			long caseId = rs.getLong("vehicle_case_id");
			String createdDate = rs.getString("created_date");

			CaseAttachment ca = new CaseAttachment();
			ca.caseAttachmentId = caseAttachmentId;
			ca.fileName = fileName;
			ca.filePath = filePath;
			ca.caseId = caseId;
			ca.createdDate = createdDate;

			return ca;
		}
	}
}
