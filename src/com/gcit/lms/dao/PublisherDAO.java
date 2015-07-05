package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.gcit.lms.domain.Publisher;
import java.sql.Connection;


/**
 * 
 * @Publisher Thierry Edson Noumessi
 * @
 * @date Jul 2, 2015
 * @8:32:49 PM
 * @PublisherDAO.java
 */
public class PublisherDAO extends BaseDAO<Publisher>{
	
	public PublisherDAO(Connection connection) throws Exception {
		super(connection);
		// TODO Auto-generated constructor stub
	}


	public void create(Publisher Publisher) throws Exception {
		
		save("insert into tbl_publisher(publisherName,publisherAddress,publisherPhone) values(?,?,?)",
				new Object[] { Publisher.getPublisherName(), Publisher.getPublisherAddress(),Publisher.getPublisherPhone() });
	}

	public void update(Publisher Publisher) throws Exception {
		save("update tbl_publisher set publisherName=?,publisherAddress=?, publisherPhone=? where publisherId = ?",
				new Object[] { Publisher.getPublisherName(),Publisher.getPublisherAddress(),Publisher.getPublisherPhone(),Publisher.getPublisherId() });

	}

	public void delete(Publisher Publisher) throws Exception {
		save("delete from tbl_publisher where publisherId = ?",
				new Object[] { Publisher.getPublisherId() });
	}
	
	public void deleteById(int Id) throws Exception {
		save("delete from tbl_publisher where publisherId = ?",
				new Object[] { Id });
	}

	public List<Publisher> readAll() throws Exception{
		return (List<Publisher>) read("select * from tbl_publisher", null);
		
	}

	public Publisher readOne(int PublisherId) throws Exception {
		List<Publisher> Publishers = (List<Publisher>) read("select * from tbl_publisher where publisherId=?", new Object[] {PublisherId});
		if(Publishers!=null && Publishers.size()>0){
			return Publishers.get(0);
		}
		return null;
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws Exception {
		List<Publisher> Publishers =  new ArrayList<Publisher>();
		
		while(rs.next()){
			Publisher a = new Publisher();
			a.setPublisherId(rs.getInt("publisherId"));
			a.setPublisherName(rs.getString("publisherName"));		
			a.setPublisherAddress(rs.getString("publisherAddress"));
			a.setPublisherPhone(rs.getString("publisherPhone"));		
			Publishers.add(a);
			System.out.println(a);
		}
		return Publishers;
	}


	@Override
	public List<Publisher> extractDataFirstLevel(ResultSet rs) throws Exception {
		List<Publisher> publishers = new ArrayList<Publisher>();
		PublisherDAO pdao = new PublisherDAO(getConnection());
		AuthorDAO aDao = new AuthorDAO(getConnection());
		//GenreDAO gD
		while(rs.next()){
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("bookId"));
			p.setPublisherName(rs.getString("bookId"));
			p.setPublisherAddress(rs.getString("bookId"));
			p.setPublisherPhone(rs.getString("bookId"));
			publishers.add(p);
		}
		
		return publishers;
	}

}
