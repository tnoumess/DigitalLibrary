package com.gcit.lms.service;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookCopies;
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
	
	
	//////////////////////////////////////////////// borrower
	
	public Borrower findBorrower(int borrowerId){

		try {
			return (new BorrowerDAO(conn).readOne(borrowerId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<Borrower> listborrower(){

		try {
			return (new BorrowerDAO(conn).readAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void updateBorrower(Borrower borrower){

		try {
			new BorrowerDAO(conn).update(borrower);
			conn.commit();// A must if you initially set auto commit to false
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
						
		}
		
	}
	
	public void deleteBorrower(Borrower borrower){

		try {
			new BorrowerDAO(conn).delete(borrower);
			conn.commit();// A must if you initially set auto commit to false
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
						
		}
		
	}
	
	/////////////////////////// library
	
	public Library findLibrary(int libraryId){

		try {
			return (new LibraryBranchDAO(conn).readOne(libraryId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<Library> listLibrary(){

		try {
			return (new LibraryBranchDAO(conn).readAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void updateLibrary(Library library){

		try {
			new LibraryBranchDAO(conn).update(library);
			conn.commit();// A must if you initially set auto commit to false
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
						
		}
		
	}
	
	public void deleteLibrary(Library library){

		try {
			new LibraryBranchDAO(conn).delete(library);
			conn.commit();// A must if you initially set auto commit to false
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
						
		}
		
	}
	////////////////////////////// publisher
	
	public Publisher findpublisher(int publisherId){

		try {
			return (new PublisherDAO(conn).readOne(publisherId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<Publisher> listpublisher(){

		try {
			return (new PublisherDAO(conn).readAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void updatePublisher(Publisher publisher){

		try {
			new PublisherDAO(conn).update(publisher);
			conn.commit();// A must if you initially set auto commit to false
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
						
		}
		
	}
	
	public void deletePublisher(Publisher publisher){

		try {
			new PublisherDAO(conn).delete(publisher);
			conn.commit();// A must if you initially set auto commit to false
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
						
		}
	}
	
	/////////////////////////   genre
	
	public Genre findGenre(int genreId){

		try {
			return (new GenreDAO(conn).readOne(genreId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<Genre> listGenre(){

		try {
			return (new GenreDAO(conn).readAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void updateGenre(Genre genre){

		try {
			new GenreDAO(conn).update(genre);
			conn.commit();// A must if you initially set auto commit to false
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
						
		}
		
	}
	
	public void deleteGenre(Genre genre){

		try {
			new GenreDAO(conn).delete(genre);
			conn.commit();// A must if you initially set auto commit to false
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
						
		}
		
	}
	//////////////////book_copies
	
	public void CreateBookCopies(BookCopies bookcopies){

		try {
			new BookCopiesDAO(conn).create(bookcopies);
			conn.commit();// A must if you initially set auto commit to false
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
						
		}
		
	}
	
	public void Increment(BookCopies bookcopies){

		try {
			/**
			 * check for update or insert
			 */
			
			if(new BookCopiesDAO(conn).findBookCopies1(bookcopies)!=null){
			new BookCopiesDAO(conn).increment1(bookcopies);
			System.out.println("in incr1");
			}else{System.out.println("in incr2");
				new BookCopiesDAO(conn).increment2(bookcopies);
			}
			conn.commit();// A must if you initially set auto commit to false
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
						
		}
		
	}
	
	
	public void SetNumberCopies(BookCopies bookcopie){

		try {
			/**
			 * check for update or insert
			 */
			
			if (bookcopie == null || bookcopie.getBookId() <1
					|| bookcopie.getBranchId() <1
					|| bookcopie.getNoOfCopies()<0) {
				throw new Exception(
						"Invalid bookcopies");
			} else {
			if(new BookCopiesDAO(conn).findBookCopies1(bookcopie)!=null){
			new BookCopiesDAO(conn).setNumbCopies1(bookcopie.getNoOfCopies(), bookcopie.getBookId(), bookcopie.getBranchId());
			conn.commit();// A must if you initially set auto commit to false
			}else{
				new BookCopiesDAO(conn).setNumbCopies2(bookcopie.getNoOfCopies(), bookcopie.getBookId(), bookcopie.getBranchId());
				conn.commit();
			}
			
			}
			} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
						
		}
		
	}
	
	public void deleteBookCopies(BookCopies bookcopies){

		try {
			/**
			 * 
			 */
			new BookCopiesDAO(conn).delete(bookcopies);
			conn.commit();// A must if you initially set auto commit to false
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
						
		}
		
	}
	
	
	public List<BookCopies> ListBookCopies() throws SQLException{

		try {
			/**
			 * check for update or insert
			 */
		return	new BookCopiesDAO(conn).readAll();
			// A must if you initially set auto commit to false
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
			return null;
						
		}finally{
			conn.commit();
		}
		
	}
	
	
	public int getNumCopies(BookCopies bookcopies) throws SQLException{
		
		try {
			/**
			 * check for update or insert
			 */
		return	new BookCopiesDAO(conn).getBookNum(bookcopies.getBookId(), bookcopies.getBranchId());
			// A must if you initially set auto commit to false
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured");
			e.printStackTrace();
			return -1;
						
		}finally{
			conn.commit();
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
				Genre g=new Genre();
				g.setGenreName("Technology");
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
                au.setAuthorId(7);
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
	//	as.createGenre(g);
		//as.findGenre(5);
		//System.out.println(as.findAuthor(3));
//		List<Book> books=as.listBooksFirstLevel();
		
    	//System.out.println(as.listGenre());
//		for(Book book:books){
//          
		BookCopies bc=new BookCopies();
		bc.setBookId(4);
		bc.setBranchId(3);
		bc.setNoOfCopies(0);
		
		System.out.println(as.getNumCopies(bc));
//		}
	}

}
