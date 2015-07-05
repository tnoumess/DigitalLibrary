package com.gcit.lms.service;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Library;
import com.gcit.lms.domain.Publisher;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministrativeService {

	private static Connection conn; 
	static{
		ConnectionUtil c = new ConnectionUtil();
		try {
			conn =c.createConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createAuthor(Author author) throws Exception {

		try {
			if (author == null || author.getAuthorName() == null
					|| author.getAuthorName().length() == 0
					|| author.getAuthorName().length() > 45) {
				throw new Exception(
						"Author Name cannot be empty or more than 45 Chars");
			} else {
				AuthorDAO adao = new AuthorDAO(conn);
				adao.create(author);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void createBook(Book book) throws Exception {

		try {
			if (book == null || book.getTitle() == null
					|| book.getTitle().length() == 0
					|| book.getTitle().length() > 45) {
				throw new Exception(
						"Book Title cannot be empty or more than 45 Chars");
			} else {
				BookDAO adao = new BookDAO(conn);
				adao.create(book);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void createGenre(Genre genre) throws Exception {

		try {
			if (genre == null || genre.getGenreName() == null
					|| genre.getGenreName().length() == 0
					|| genre.getGenreName().length() > 45) {
				throw new Exception(
						"Genre Name cannot be empty or more than 45 Chars");
			} else {
				GenreDAO gdao=new GenreDAO(conn);
				gdao.create(genre);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void createPublisher(Publisher publisher) throws Exception {

		try {
			if (publisher == null || publisher.getPublisherName() == null
					|| publisher.getPublisherName().length() == 0
					|| publisher.getPublisherName().length() > 45) {
				throw new Exception(
						"Publisher name cannot be empty or more than 45 Chars");
			} else {
				PublisherDAO pdao = new PublisherDAO(conn);
				pdao.create(publisher);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void createBorrower(Borrower borrower) throws Exception {

		try {
			if (borrower == null || borrower.getBorrowerName() == null
					|| borrower.getBorrowerName().length() == 0
					|| borrower.getBorrowerName().length() > 45) {
				throw new Exception(
						"Borrower name cannot be empty or more than 45 Chars");
			} else {
				BorrowerDAO bdao = new BorrowerDAO(conn);
				bdao.create(borrower);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void createLibrary(Library library) throws Exception {

		try {
			if (library == null || library.getBranchName() == null
					|| library.getBranchName().length() == 0
					|| library.getBranchName().length() > 45) {
				throw new Exception(
						"Publisher Name cannot be empty or more than 45 Chars");
			} else {
				LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
				ldao.create(library);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	
	/**
	 * 
	 * @return Return list of book along with
	 *  the details(Authors,Publishers,Genres) about the books
	 */
	public Book findBook(int bookId){

		try {
			return (new BookDAO(conn).readOne(bookId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @return Return list of book along with
	 *  the details(Authors,Publishers,Genres) about the books
	 */
	public List<Book> listBooks(){

		try {
			return (new BookDAO(conn).readAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
			return null;
		}
	}
/**
 * 
 * @return Return list of book without revealing 
 * the details(Authors,Publishers,Genres) about the books
 */

	public List<Book> listBooksFirstLevel(){

		try {
			return (new BookDAO(conn).readAllFirstLevel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @param book
	 */
	public void updatebook(Book book){

		try {
			new BookDAO(conn).updateBook(book);
			conn.commit();// A must if you initially set auto commit to false
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
						
		}
		
	}
	
	public void deletebook(Book book){

		try {
			new BookDAO(conn).deleteBook(book);
			conn.commit();// A must if you initially set auto commit to false
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
						
		}
		
	}

	
	public Author findAuthor(int authorId){

		try {
			return (new AuthorDAO(conn).readOne(authorId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<Author> listAuthors(){

		try {
			return (new AuthorDAO(conn).readAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Author> listAuthorsFirstLevel(){

		try {
			return (new AuthorDAO(conn).readAllFirstLevel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void updateAuthor(Author author){

		try {
			new AuthorDAO(conn).updateAuthor(author);
			conn.commit();// A must if you initially set auto commit to false
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
						
		}
		
	}
	
	public void deleteAuthor(Author author){

		try {
			new AuthorDAO(conn).deleteAuthor(author);
			conn.commit();// A must if you initially set auto commit to false
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
						
		}
		
	}
	public static void main(String[] args) throws Exception {





		Library l= new Library();
		l.setBranchName("NJU");
		l.setBranchAddress("NJ");


		//		Publisher p=new Publisher();
		//		p.setPublisherName("MessaPress");
		//		p.setPublisherAddress("8945 norh Shield");
		//		p.setPublisherPhone("111-222-555");

		//		Borrower b=new Borrower();
		//		b.setBorrowerName("Tapigue");
		//		b.setBorrowerPhone("555-555-6666");
		//		b.setBorrowerAddress("5412 Chantily");
		//		Genre g=new Genre();
		//		g.setGenreName("Cloud");
				Book a=new Book();
				a.setBookId(11);
				
				
				Book a1=new Book();				
				a1.setBookId(14);
				
//                Publisher p=new Publisher();
//                //p.setPublisherName("Bloombust");
//                p.setPublisherId(17);
//                Genre g=new Genre();
                //g.setGenreName("Sports");
//                g.setGenreId(1);
                Author au=new Author();
                au.setAuthorId(11);
                au.setAuthorName("Tawo");
                au.addBook(a);
                au.addBook(a1);
                //au.setAuthorId(3);
//                a.setAuthors(new ArrayList<Author>(){{
//            				    add(au);}});
//                a.setGenres(new ArrayList<Genre>(){{
//            				    add(g);}});
//				a.setPublisher(p);
		//		AuthorDAO ado=new AuthorDAO(conn);
		//		GenreDAO adog=new GenreDAO(conn);

		//		a.setAuthors(new ArrayList<Author>(){{
		//		    add(ado.readOne(1));}});
		//		
		//		a.setGenres(new ArrayList<Genre>(){{
		//		    add(adog.readOne(1));}});
//				Book b=new Book();
//				b.setBookId(13);
				
		AdministrativeService as=new AdministrativeService();
		as.deleteAuthor(au);
		//System.out.println(as.findAuthor(3));
//		List<Book> books=as.listBooksFirstLevel();
    	//System.out.println(as.listAuthors());
//		for(Book book:books){
//
//			System.out.println(book);
//		}
	}

}