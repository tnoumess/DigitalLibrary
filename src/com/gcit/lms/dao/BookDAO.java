package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Publisher;

public class BookDAO extends BaseDAO<Book>  {
	
	public BookDAO(Connection connection) throws Exception {
		super(connection);
		// TODO Auto-generated constructor stub
	}
/**
 * 
 * @param book
 * @throws Exception
 */
	public void create(Book book) throws Exception {
		/**
		 * Insert into the book table
		 * Precondition: pubId should be different 
		 * from null and should be key in the publisher table
		 */
		int bookId = saveWithID("insert into tbl_book (title,pubId) values(?,?)",
				new Object[] { book.getTitle(),book.getPublisher().getPublisherId()});
		
		/**
		 * Precondition: If Authors!= null each authorId should be different 
		 * from null and should be a key in the author table
		 */
		for(Author a: book.getAuthors()){
			save("insert into tbl_book_authors (bookId, authorId) values (?,?)", 
				new Object[]{bookId, a.getAuthorId()});
		}
		
		/**
		 * Precondition: If Genres!= null each genreId should be different 
		 * from null and should be a key from the genre table
		 */
		for(Genre g: book.getGenres()){
			save("insert into tbl_book_genres (bookId, genre_id) values (?,?)", 
				new Object[]{bookId, g.getGenreId()});
		}
	}
	
	public Book readOne(int bookId) throws Exception {
		List<Book> books = (List<Book>) read("select * from tbl_book where bookId=?", new Object[] {bookId});
		if(books!=null && books.size()>0){
			return books.get(0);
		}
		return null;
	}
	
	/**
	 * 
	 * @return Return list of books along with 
	 * the details(Authors,Publishers,Genres) about the books
	 * @throws Exception
	 */
	public List<Book> readAll() throws Exception{
		return (List<Book>) read("select * from tbl_book", null);
		
	}
	
	/**
	 * 
	 * @return Return list of books without revealing the details(Authors,Publishers,Genres) about the books
	 * @throws Exception
	 */
	public List<Book> readAllFirstLevel() throws Exception{
		return (List<Book>) readFirstLevel("select * from tbl_book", null);
		
	}
	
	@Override
	public List<Book> extractData(ResultSet rs) throws Exception {
		List<Book> books = new ArrayList<Book>();
		PublisherDAO pdao = new PublisherDAO(getConnection());
		AuthorDAO aDao = new AuthorDAO(getConnection());
		//GenreDAO gD
		while(rs.next()){
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			b.setPublisher(pdao.readOne(rs.getInt("pubId")));	
			@SuppressWarnings("unchecked")
	
			List<Author> authors = (List<Author>) aDao.readFirstLevel("select * from tbl_author where authorId In"
					+ "(select authorId from tbl_book_authors where bookId=?)", new Object[] {rs.getInt("bookId")});
			b.setAuthors(authors);
			books.add(b);
		}
		System.out.println(books);
		return books;
	}
	
	@Override
	public List<Book> extractDataFirstLevel(ResultSet rs) throws Exception {
		List<Book> books = new ArrayList<Book>();
//		PublisherDAO pdao = new PublisherDAO(getConnection());
//		AuthorDAO aDao = new AuthorDAO(getConnection());
		//GenreDAO gD
		while(rs.next()){
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			books.add(b);
		}
		
		return books;
	}
	
	public boolean updateBook(Book book){
		
		try {
			save("UPDATE tbl_book SET title=? where (bookId=?)",new Object[] {book.getTitle(),book.getBookId()});
			System.out.println("update executed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
public boolean deleteBook(Book book){
		
		try {
			save("DELETE FROM tbl_book  where (bookId=?)",new Object[] {book.getBookId()});
			System.out.println("deletion completed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
