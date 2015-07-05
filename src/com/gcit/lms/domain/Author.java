package com.gcit.lms.domain;

import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BorrowerDAO;
/**
 * 
 * @author Thierry Edson Noumessi
 * @
 * @date Jul 2, 2015
 * @3:06:55 PM
 * @Author.java
 */
public class Author {
	
	
	private int authorId;
	private String authorName;
	
	
	List<Book> listBook=new ArrayList<Book>();
	
	/**
	 * @return the listBook
	 */
	public List<Book> getListBook() {
		return listBook;
	}
	/**
	 * @param listBook the listBook to set
	 */
	public void setListBook(List<Book> listBook) {
		this.listBook = listBook;
	}
	
	public void addBook(Book book){
		listBook.add(book);
	}
	/**
	 * @return the authorId
	 */
	public int getAuthorId() {
		return authorId;
	}
	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}
	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	/**
	 * 
	 */
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + authorId;
		result = prime * result + ((authorName == null) ? 0 : authorName.hashCode());
		return result;
	}
    /**
     * 
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (authorId != other.authorId)
			return false;
		if (authorName == null) {
			if (other.authorName != null)
				return false;
		} else if (!authorName.equals(other.authorName))
			return false;
		return true;
	}
	
	

@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", authorName=" + authorName + ", listBook=" + listBook + "]";
	}
public static void main(String[] args) throws Exception {
		
	
		Author a=new Author();
		a.addBook(new Book());
		
		//BorrowerDAO ad=new BorrowerDAO();
		//ad.create(a);
			System.out.println(a);
		//System.out.println(ad.readOne(1));
		
	}
}
