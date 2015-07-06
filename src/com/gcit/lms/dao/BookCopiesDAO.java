package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookCopies;
import com.gcit.lms.domain.Borrower;

public class BookCopiesDAO extends BaseDAO<BookCopies>{
	public BookCopiesDAO(Connection connection) throws Exception {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	

	public void create(BookCopies bookCopies) throws Exception {
		
		save("insert into tbl_book_copies(bookId,branchId,noOfCopies) values(?,?,?)",
				new Object[] { bookCopies.getBookId(), bookCopies.getBranchId(),bookCopies.getNoOfCopies()});
	}
/**
 * Increment when already exists in the library
 * @param bookCopies
 * @throws Exception
 */
	public void increment1(BookCopies bookCopies) throws Exception {
		save("UPDATE tbl_book_copies SET noOfCopies=noOfCopies+ 1 where (bookId=? and branchId =? )",
				new Object[] { bookCopies.getBookId(),bookCopies.getBranchId()});

	}

	
	/**
	 * Increment when not exists yet in the library
	 * @param bookCopies
	 * @throws Exception
	 */
		public void increment2(BookCopies bookCopies) throws Exception {
			save("insert into tbl_book_copies values(?,?,?)",
					new Object[] { bookCopies.getBookId(),bookCopies.getBranchId(),1});

		}
	
		/**
		 *  Set the new quantity by updating the previous value.
		 * @param qt
		 * @param bookId
		 * @param branchId
		 * @throws Exception
		 */
	public void setNumbCopies1(int qt,int bookId,int branchId) throws Exception{
		
	save("update tbl_book_copies set noOfCopies=? where (bookId=? and branchId=?)",
			new Object[] { qt,bookId,branchId,});
	
	}
	/**
	 *  Set the new Quantity by creating a new record.
	 * @param qt
	 * @param bookId
	 * @param branchId
	 * @throws Exception
	 */
	public void setNumbCopies2(int qt,int bookId,int branchId) throws Exception{
		
		save("insert into tbl_book_copies values (?,?,?)",
				new Object[] { bookId,branchId,qt});
		
		}
		
	public void delete(BookCopies bookCopies) throws Exception {
		save("delete from tbl_book_copies where bookId = ? and branchId=? and noOfCopies= ?",
				new Object[] { bookCopies.getBookId(),bookCopies.getBranchId(),bookCopies.getNoOfCopies()});
	}
	
	
	public List<BookCopies> readAll() throws Exception{
		return (List<BookCopies>) read("select * from tbl_book_copies", null);
		
	}
	
	/**
	 * 
	 * @param id
	 * @param LibId
	 * @return
	 * @throws Exception
	 */
	public int getBookNum(int id,int LibId) throws Exception{
		return (int)getQuantity("select noOfCopies from tbl_book_copies where bookId=? and branchId=?", 
				new Object[] {id,LibId});
	}

//	public BookCopies readOne(int BookCopiesId) throws Exception {
//		List<BookCopies> BookCopiess = (List<BookCopies>) read("select * from tbl_BookCopies where bookId=?", new Object[] {BookCopiesId});
//		if(BookCopiess!=null && BookCopiess.size()>0){
//			return BookCopiess.get(0);
//		}
//		return null;
//	}

	@Override
	public List<BookCopies> extractData(ResultSet rs) throws Exception {
		List<BookCopies> book_copies =  new ArrayList<BookCopies>();
		//	BookDAO bDao = new BookDAO(getConnection());
			
			while(rs.next()){
				BookCopies a = new BookCopies();
				a.setBookId(rs.getInt("bookId"));
				a.setBranchId(rs.getInt("branchId"));
				a.setNoOfCopies(rs.getInt("noOfCopies"));
							
				book_copies.add(a);
			}
			return book_copies;
		
	}

public BookCopies findBookCopies1(BookCopies bookcopie) throws Exception{
	/**
	 * Check if already exists before incrementing
	 */
	
		List<BookCopies> bookcopies = (List<BookCopies>) read("select * from tbl_book_copies where bookId=? and branchId=? ", new Object[] {bookcopie.getBookId(),bookcopie.getBranchId()});
		if(bookcopies!=null && bookcopies.size()>0){
			return bookcopies.get(0);
		}
		return null;
	}



	@Override
	public List<BookCopies> extractDataFirstLevel(ResultSet rs) throws Exception {
		List<BookCopies> book_copies =  new ArrayList<BookCopies>();
	//	BookDAO bDao = new BookDAO(getConnection());
		
		while(rs.next()){
			BookCopies a = new BookCopies();
			a.setBookId(rs.getInt("bookId"));
			a.setBranchId(rs.getInt("branchId"));
			a.setNoOfCopies(rs.getInt("noOfCopies"));
						
			book_copies.add(a);
		}
		return book_copies;
	}

}
