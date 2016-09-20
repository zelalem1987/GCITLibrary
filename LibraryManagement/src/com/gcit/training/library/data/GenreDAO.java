package com.gcit.training.library.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.library.entity.Genre;

public class GenreDAO extends BaseDAO<Genre>{

	private static final String SELECT_BY_NAME = "select * from tbl_genre where genre_name = ?";
	private static final String SELECT_ALL = "SELECT * FROM tbl_genre";
	private static final String SELECT = "SELECT * FROM tbl_genre WHERE genre_id = ?";
	private static final String DELETE = "DELETE FROM tbl_genre WHERE genre_id = ? ";
	private static final String UPDATE = "UPDATE tbl_genre SET genre_name = ? WHERE genre_id = ?";
	private static final String INSERT = "INSERT INTO tbl_genre genre_name VALUES ?";
	private static final String GET_COUNT = "select count(*) from tbl_genre";
	private static final String SELECT_ALL_SEARCH = "select * from tbl_genre where genre_name like ?";
	private static final String GET_COUNT_BY_NAME = "select count(*) from tbl_genre where genre_name like ?";
	
	public GenreDAO(Connection conn) {
		
		super(conn);
	}

	@Override
	public void create(Genre be) throws SQLException {
		
		saveToDB(INSERT, new Object[] {be.getGenreName()});
		
	}

	@Override
	public void update(Genre be) throws SQLException {
		
		saveToDB(UPDATE, new Object[] {be.getGenreName(), be.getGerneId()});		
	}

	@Override
	public void delete(Genre be) throws SQLException {

		saveToDB(DELETE, new Object[] {be.getGerneId()});
	}

	@Override
	public Genre read(int pk) throws SQLException {
		
		return readFromDB(SELECT, new Object[] {pk});
	}

	@Override
	public List<Genre> readall(Integer pageNo, Integer pageSize, String searchString) throws SQLException {
		
		setPageNo(pageNo);
		setPageSize(pageSize);
		
		if(searchString != null && !"".equals(searchString)){
			searchString = "%" + searchString + "%";
			return readAllFromDB(SELECT_ALL_SEARCH, new Object[]{searchString});
		} 
		else {
			return readAllFromDB(SELECT_ALL, new Object[] {});
		}
		
		
	}

	@Override
	public List<Genre> readResult(ResultSet rs) throws SQLException {

		List<Genre> list = new ArrayList<>();
		
		while(rs.next()){		
			Genre genre = new Genre();
			genre.setGerneId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			list.add(genre);
		}
		return list;
	}

	@Override
	public Genre readbyName(String genreName) throws SQLException {

		return readFromDB(SELECT_BY_NAME, new Object[] {genreName});
	}


	@Override
	public void save(Genre be) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Genre readbyId(int entityId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCount() throws SQLException {
		
		return getCount(GET_COUNT, null);
	}

	@Override
	public Integer getCountByName(String searchString) throws SQLException {
		searchString = "%" + searchString + "%";
		return getCount(GET_COUNT_BY_NAME, new Object[]{searchString});
	}
}
