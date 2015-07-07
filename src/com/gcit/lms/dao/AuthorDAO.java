package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;

/**
 * 
 * @author Thierry Edson Noumessi
 * @
 * @date Jul 2, 2015
 * @3:07:55 PM
 * @AuthorDAO.java
 */
public  class AuthorDAO extends BaseDAO<Author>{

	public AuthorDAO(Connection connection) throws Exception {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	public void create(Author author) throws Exception {
				
		int authorId = saveWithID("insert into tbl_author (authorName) values(?)",
				new Object[] { author.getAuthorName()});
		
		
		for(Book b: author.getListBook()){System.out.println(authorId+"|"+b.getBookId());
			save("insert into tbl_book_authors (bookId, authorId) values (?,?)", 
				new Object[]{b.getBookId(),authorId});
		}
		
	}

	public void update(Author author) throws Exception {
		save("update tbl_author set authorName = ? where authorId = ?",
				new Object[] { author.getAuthorName(),author.getAuthorId() });

	}

	public void delete(Author author) throws Exception {
		save("delete from tbl_author where authorId = ?",
				new Object[] { author.getAuthorId() });
	}

	public List<Author> readAll() throws Exception{
		return (List<Author>) read("select * from tbl_author", null);
	}

	public Author readOne(int authorId) throws Exception {
		List<Author> authors = (List<Author>) read("select * from tbl_author where authorId=?", new Object[] {authorId});
		if(authors!=null && authors.size()>0){
			return authors.get(0);
		}
		return null;
	}
	
	public List<Author> readAllFirstLevel() throws Exception{
		return (List<Author>) readFirstLevel("select * from tbl_author", null);
		
	}
	
	public boolean updateAuthor(Author author){

					
			try {
				save("UPDATE tbl_author SET authorName=? where (authorId=?)",new Object[] {author.getAuthorName(),author.getAuthorId()});
				System.out.println("update executed");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("An error has occured");
				e.printStackTrace();
				return false;
			}
			
			return true;
		}

	@Override
	public List<Author> extractData(ResultSet rs) throws Exception {
		List<Author> authors =  new ArrayList<Author>();
		BookDAO bDao = new BookDAO(getConnection());
		//GenreDAO gD
		while(rs.next()){
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			//System.out.println(rs.getInt("authorId"));
			List<Book> books = (List<Book>) bDao.readFirstLevel("select * from tbl_book where bookId In"
					+ "(select bookId from tbl_book_authors where authorId=?)",	new Object[] {rs.getInt("authorId")});
			a.setListBook(books);
			authors.add(a);
		}
		return authors;
	}


	@Override
	public List<Author> extractDataFirstLevel(ResultSet rs) throws Exception {
		List<Author> authors =  new ArrayList<Author>();
		BookDAO bDao = new BookDAO(getConnection());
		
		while(rs.next()){
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			
			authors.add(a);
		}
		return authors;
	}
	
	
public boolean deleteAuthor(Author author){
		
		try {
			save("DELETE FROM tbl_author  where (authorId=?)",new Object[] {author.getAuthorId()});
			System.out.println(" Author deletion completed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
}
