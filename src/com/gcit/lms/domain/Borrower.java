package com.gcit.lms.domain;

import com.gcit.lms.dao.BorrowerDAO;

/**
 * 
 * @author Thierry Edson Noumessi
 * @
 * @date Jul 2, 2015
 * @4:48:15 PM
 * @Borrower.java
 */
public class Borrower {
	
	private int borrowerId;
	private String borrowerName;
	private String borrowerAddress;
	private String borrowerPhone;
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((borrowerAddress == null) ? 0 : borrowerAddress.hashCode());
		result = prime * result + borrowerId;
		result = prime * result + ((borrowerName == null) ? 0 : borrowerName.hashCode());
		result = prime * result + ((borrowerPhone == null) ? 0 : borrowerPhone.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Borrower other = (Borrower) obj;
		if (borrowerAddress == null) {
			if (other.borrowerAddress != null)
				return false;
		} else if (!borrowerAddress.equals(other.borrowerAddress))
			return false;
		if (borrowerId != other.borrowerId)
			return false;
		if (borrowerName == null) {
			if (other.borrowerName != null)
				return false;
		} else if (!borrowerName.equals(other.borrowerName))
			return false;
		if (borrowerPhone == null) {
			if (other.borrowerPhone != null)
				return false;
		} else if (!borrowerPhone.equals(other.borrowerPhone))
			return false;
		return true;
	}



	public int getBorrowerId() {
		return borrowerId;
	}



	public void setBorrowerId(int borrowerId) {
		this.borrowerId = borrowerId;
	}



	public String getBorrowerName() {
		return borrowerName;
	}



	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}



	public String getBorrowerAddress() {
		return borrowerAddress;
	}



	public void setBorrowerAddress(String borrowerAddress) {
		this.borrowerAddress = borrowerAddress;
	}



	public String getBorrowerPhone() {
		return borrowerPhone;
	}



	public void setBorrowerPhone(String borrowerPhone) {
		this.borrowerPhone = borrowerPhone;
	}



	@Override
	public String toString() {
		return "Borrower [borrowerId=" + borrowerId + ", borrowerName=" + borrowerName + ", borrowerAddress="
				+ borrowerAddress + ", borrowerPhone=" + borrowerPhone + "]";
	}
	
	
//	public static void main(String[] arg) throws Exception{
//		
////		Borrower a=new Borrower();
////		a.setBorrowerName("JP");
////		a.setBorrowerAddress("Faifax co");
////		a.setBorrowerPhone("001-567-5632");
//		BorrowerDAO ad=new BorrowerDAO();
//		//ad.create(a);
//	//	ad.deleteById(4);
//		//	System.out.println(ad.readAll().toString());
//		System.out.println(ad.readOne(1));
//		
//	}
	

}
