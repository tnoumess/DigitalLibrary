package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import com.gcit.lms.domain.Borrower;

/**
 * 
 * @author Thierry Edson Noumessi
 * @
 * @date Jul 2, 2015
 * @9:32:52 PM
 * @BorrowerDAO.java
 */
public class BorrowerDAO  extends BaseDAO<Borrower>{

	public BorrowerDAO(Connection connection) throws Exception {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	

	public void create(Borrower Borrower) throws Exception {
		
		save("insert into tbl_borrower(name,address,phone) values(?,?,?)",
				new Object[] { Borrower.getBorrowerName(), Borrower.getBorrowerAddress(),Borrower.getBorrowerPhone() });
	}

	public void update(Borrower Borrower) throws Exception {
		save("update tbl_borrower set name=?,address=?, phone=? where cardNo = ?",
				new Object[] { Borrower.getBorrowerName(),Borrower.getBorrowerAddress(),Borrower.getBorrowerPhone(),Borrower.getBorrowerId() });

	}

	public void delete(Borrower Borrower) throws Exception {
		save("delete from tbl_borrower where cardNo = ?",
				new Object[] { Borrower.getBorrowerId() });
	}
	
	public void deleteById(int Id) throws Exception {
		save("delete from tbl_borrower where cardNo = ?",
				new Object[] { Id });
	}

	public List<Borrower> readAll() throws Exception{
		return (List<Borrower>) read("select * from tbl_borrower", null);
		
	}

	public Borrower readOne(int BorrowerId) throws Exception {
		List<Borrower> Borrowers = (List<Borrower>) read("select * from tbl_borrower where cardNo=?", new Object[] {BorrowerId});
		if(Borrowers!=null && Borrowers.size()>0){
			return Borrowers.get(0);
		}
		return null;
	}

	@Override
	public List<Borrower> extractData(ResultSet rs) throws Exception {
		List<Borrower> Borrowers =  new ArrayList<Borrower>();
		
		while(rs.next()){
			Borrower a = new Borrower();
			a.setBorrowerId(rs.getInt("cardNo"));
			a.setBorrowerName(rs.getString("name"));		
			a.setBorrowerAddress(rs.getString("address"));
			a.setBorrowerPhone(rs.getString("phone"));		
			Borrowers.add(a);
			
		}
		return Borrowers;
	}

	@Override
	public List<Borrower> extractDataFirstLevel(ResultSet rs) throws Exception {
		List<Borrower> authors =  new ArrayList<Borrower>();
	//	BookDAO bDao = new BookDAO(getConnection());
		
		while(rs.next()){
			Borrower a = new Borrower();
			a.setBorrowerId(rs.getInt("cardNo"));
			a.setBorrowerName(rs.getString("name"));
			a.setBorrowerAddress(rs.getString("address"));
			a.setBorrowerPhone(rs.getString("phone"));			
			authors.add(a);
		}
		return authors;
	}
	
	

	
	
}
 