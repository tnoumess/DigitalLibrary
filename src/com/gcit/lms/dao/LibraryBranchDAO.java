package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Library;



/**
 * 
 * @author Thierry Edson Noumessi
 * @
 * @date Jul 4, 2015
 * @8:38:30 PM
 * @LibraryBranchDAO.java
 */
public class LibraryBranchDAO extends BaseDAO{

	public LibraryBranchDAO(Connection connection) throws Exception {
		super(connection);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param Library
	 * @throws Exception
	 */
	public void create(Library library) throws Exception {
		save("insert into tbl_library_branch (branchName,branchAddress) values(?,?)",
				new Object[] { library.getBranchName(),library.getBranchAddress() });
	}
    /**
     * 
     * @param Library
     * @throws Exception
     */
	public void update(Library library) throws Exception {
		save("update tbl_library_branch set branchName = ? branchAddress=? where branchId = ?",
				new Object[] { library.getBranchName(), library.getBranchAddress(),library.getBranchId() });

	}
    /**
     * 
     * @param Library
     * @throws Exception
     */
	public void delete(Library library) throws Exception {
		save("delete from tbl_library_branch where branchId = ?",
				new Object[] { library.getBranchId() });
	}
    /**
     * 
     * @return
     * @throws Exception
     */
	public List<Library> readAll() throws Exception{
		return (List<Library>) read("select * from tbl_library_branch", null);
		
	}
    /**
     * 
     * @param LibraryId
     * @return
     * @throws Exception
     */
	public Library readOne(int LibraryId) throws Exception {
		List<Library> libraries = (List<Library>) read("select * from tbl_library_branch where branchId=?", new Object[] {LibraryId});
		if(libraries!=null && libraries.size()>0){
			return libraries.get(0);
		}
		return null;
	}
	
    
	@Override
	public List<Library> extractData(ResultSet rs) throws Exception {
		List<Library> libraries =  new ArrayList<Library>();
		
		while(rs.next()){
			Library l = new Library();
			l.setBranchId(rs.getInt("branchId"));
			l.setBranchName(rs.getString("branchName"));
			l.setBranchAddress(rs.getString("branchAddress"));			
			libraries.add(l);
		}
		return libraries;
	}
	
	@Override
	public List<Library> extractDataFirstLevel(ResultSet rs) throws Exception {
		List<Library> libraries =  new ArrayList<Library>();
			
			while(rs.next()){
				Library l = new Library();
				l.setBranchId(rs.getInt("branchId"));
				l.setBranchName(rs.getString("branchName"));
				l.setBranchAddress(rs.getString("branchAddress"));								
				libraries.add(l);
			}
			return libraries;
	}

}
