package com.gcit.lms.domain;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.PublisherDAO;

public class Publisher {
	private int publisherId;
	private String publisherName;
	private String publisherAddress;
	private String publisherPhone;
	
	/**
	 * @return the publisherId
	 */
	public int getPublisherId() {
		return publisherId;
	}
	/**
	 * @param publisherId the publisherId to set
	 */
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	/**
	 * @return the publisherName
	 */
	public String getPublisherName() {
		return publisherName;
	}
	/**
	 * @param publisherName the publisherName to set
	 */
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	/**
	 * @return the publisherAddress
	 */
	public String getPublisherAddress() {
		return publisherAddress;
	}
	/**
	 * @param publisherAddress the publisherAddress to set
	 */
	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}
	/**
	 * @return the publisherPhone
	 */
	public String getPublisherPhone() {
		return publisherPhone;
	}
	/**
	 * @param publisherPhone the publisherPhone to set
	 */
	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((publisherAddress == null) ? 0 : publisherAddress.hashCode());
		result = prime * result + publisherId;
		result = prime * result + ((publisherName == null) ? 0 : publisherName.hashCode());
		result = prime * result + ((publisherPhone == null) ? 0 : publisherPhone.hashCode());
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
		Publisher other = (Publisher) obj;
		if (publisherAddress == null) {
			if (other.publisherAddress != null)
				return false;
		} else if (!publisherAddress.equals(other.publisherAddress))
			return false;
		if (publisherId != other.publisherId)
			return false;
		if (publisherName == null) {
			if (other.publisherName != null)
				return false;
		} else if (!publisherName.equals(other.publisherName))
			return false;
		if (publisherPhone == null) {
			if (other.publisherPhone != null)
				return false;
		} else if (!publisherPhone.equals(other.publisherPhone))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Publisher [publisherId=" + publisherId + ", publisherName=" + publisherName + ", publisherAddress="
				+ publisherAddress + ", publisherPhone=" + publisherPhone + "]";
	}
//	public static void main(String[] args) throws Exception {
//		
//		Publisher p=new Publisher();
//		PublisherDAO ad=new PublisherDAO();
//		p.setPublisherName("FINRA");
//		p.setPublisherPhone("571-47-4846");
//		p.setPublisherAddress("25465 Fairfax lake");
//		
//		//	System.out.println(ad.readAll().toString());
//		System.out.println(ad.readOne(1));
//		
//	}
	
}
