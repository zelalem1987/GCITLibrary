package com.gcit.training.library.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.library.entity.BaseEntity;
import com.gcit.training.library.entity.Book;

@SuppressWarnings("hiding")
public abstract class BaseDAO<BaseEntity> {

	protected Connection conn = null;

	private Integer pageNo = 1;

	private Integer pageSize = 10;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public BaseDAO(Connection conn) {
		this.conn = conn;
	}

	public abstract void create(BaseEntity be) throws SQLException;

	public abstract void update(BaseEntity be) throws SQLException;

	public abstract void delete(BaseEntity be) throws SQLException;

	public abstract BaseEntity read(int pk) throws SQLException;

	public abstract BaseEntity readbyName(String entityName) throws SQLException;

	public abstract BaseEntity readbyId(int entityId) throws SQLException;

	public abstract List<BaseEntity> readall(Integer pageNo, Integer pageSize, String searchString) throws SQLException;

	public abstract void save(BaseEntity be) throws SQLException;

	public abstract List<BaseEntity> readResult(ResultSet rs) throws SQLException;

	public abstract Integer getCount() throws SQLException;

	public abstract Integer getCountByName(String searchString) throws SQLException;

	public BaseEntity readFromDB(String sql, Object[] vals) throws SQLException {
		ResultSet rst = executeStatement(sql, vals);
		return readResult(rst).get(0);
	}

	public List<BaseEntity> readAllFromDB(String sql, Object[] vals) throws SQLException {
		System.out.println("hELLO " + sql);
		ResultSet rst = executeStatement(sql, vals);
		return readResult(rst);
	}

	protected void saveToDB(String sql, Object[] vals) throws SQLException {
		PreparedStatement stmt = prepareStatement(sql, vals);
		stmt.executeUpdate();
	}

	public List<Book> showBooks(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	private PreparedStatement prepareStatement(String sql, Object[] vals) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(sql);
		int indx = 1;
		for (Object o : vals) {
			stmt.setObject(indx, o);
			indx++;
		}
		return stmt;
	}

	private ResultSet executeStatement(String sql, Object[] vals) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rst = null;

		try {
			pageNo = getPageNo();
			pageSize = getPageSize();

			if (pageNo > 0) {
				int index = (pageNo - 1) * pageSize;
				sql += " LIMIT " + index + ", " + pageSize;
			}

			stmt = conn.prepareStatement(sql);

			int count = 1;
			if (vals != null && vals.length > 0) {
				for (Object obj : vals) {
					stmt.setObject(count, obj);
					count++;
				}
			}

			rst = stmt.executeQuery();
			System.out.println("Executed query");

			return rst;

		} finally {
			// if(rst != null) rst.close();
			// if(stmt != null) stmt.close();
		}
	}

	protected int saveAndGetId(String sql, Object[] vals) throws SQLException {

		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		ResultSet rs = null;

		int lastInsertId = -1;

		int idx = 1;

		for (Object o : vals) {

			stmt.setObject(idx, o);

			idx++;

		}

		stmt.executeUpdate();

		rs = stmt.getGeneratedKeys();

		if (rs.next()) {

			lastInsertId = rs.getInt(1);

		}

		return lastInsertId;

	}

	public List<Book> showBooksAuthorsPublishers() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getCount(String sql, Object[] vals) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rst = null;

		try {
			pstmt = conn.prepareStatement(sql);
			int indx = 1;
			if (vals != null) {
				for (Object o : vals) {
					pstmt.setObject(indx, o);
					indx++;
				}
			}

			rst = pstmt.executeQuery();
			while (rst.next()) {
				return rst.getInt(1);
			}
		} finally {
			// if(rst != null) rst.close();
			// if(pstmt != null) pstmt.close();
		}

		return -1;

	}

}
