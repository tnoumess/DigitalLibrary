package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Genre;
/**
 * 
 * @author Thierry Edson Noumessi
 * @
 * @date Jul 2, 2015
 * @5:40:05 PM
 * @GenreDAO.java
 */
public  class GenreDAO  extends BaseDAO<Genre>{
	
	
	
	public GenreDAO(Connection connection) throws Exception {
		super(connection);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param Genre
	 * @throws Exception
	 */
	public void create(Genre Genre) throws Exception {
		save("insert into tbl_genre (genre_name) values(?)",
				new Object[] { Genre.getGenreName() });
	}
    /**
     * 
     * @param Genre
     * @throws Exception
     */
	public void update(Genre Genre) throws Exception {
		save("update tbl_genre set genre_name = ? where genre_id = ?",
				new Object[] { Genre.getGenreName(), Genre.getGenreId() });

	}
    /**
     * 
     * @param Genre
     * @throws Exception
     */
	public void delete(Genre Genre) throws Exception {
		save("delete from tbl_genre where genre_id = ?",
				new Object[] { Genre.getGenreId() });
	}
    /**
     * 
     * @return
     * @throws Exception
     */
	public List<Genre> readAll() throws Exception{
		return (List<Genre>) read("select * from tbl_genre", null);
		
	}
    /**
     * 
     * @param GenreId
     * @return
     * @throws Exception
     */
	public Genre readOne(int GenreId) throws Exception {
		List<Genre> Genres = (List<Genre>) read("select * from tbl_genre where genre_Id=?", new Object[] {GenreId});
		if(Genres!=null && Genres.size()>0){
			return Genres.get(0);
		}
		return null;
	}
	
    
	@Override
	public List<Genre> extractData(ResultSet rs) throws Exception {
		List<Genre> Genres =  new ArrayList<Genre>();
		
		while(rs.next()){
			Genre a = new Genre();
			a.setGenreId(rs.getInt("genre_id"));
			a.setGenreName(rs.getString("genre_name"));
			
			Genres.add(a);
		}
		return Genres;
	}
	@Override
	public List<Genre> extractDataFirstLevel(ResultSet rs) throws Exception {
		List<Genre> genres =  new ArrayList<Genre>();
			
			while(rs.next()){
				Genre g = new Genre();
				g.setGenreId(rs.getInt("genre_id"));
				g.setGenreName(rs.getString("genre_name"));
						
				genres.add(g);
			}
			return genres;
	}

}
