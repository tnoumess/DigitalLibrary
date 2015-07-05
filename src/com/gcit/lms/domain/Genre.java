package com.gcit.lms.domain;


import com.gcit.lms.dao.GenreDAO;

public class Genre {
	
	private int genreId;
	private String genreName;
	/**
	 * @return the genreId
	 */
	public int getGenreId() {
		return genreId;
	}
	/**
	 * @param genreId the genreId to set
	 */
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	/**
	 * @return the genreName
	 */
	public String getGenreName() {
		return genreName;
	}
	/**
	 * @param genreName the genreName to set
	 */
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	
   @Override
	public String toString() {
		return "Genre [genreId=" + genreId + ", genreName=" + genreName + "]";
	}
   
   @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + genreId;
		result = prime * result + ((genreName == null) ? 0 : genreName.hashCode());
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
		Genre other = (Genre) obj;
		if (genreId != other.genreId)
			return false;
		if (genreName == null) {
			if (other.genreName != null)
				return false;
		} else if (!genreName.equals(other.genreName))
			return false;
		return true;
	}
	
//       public static void main(String[] args) throws Exception {
//		
//		Genre g=new Genre();
//		g.setGenreName("Science");
//		
//		GenreDAO gd=new GenreDAO();
//		
//		//gd.create(g);
//		System.out.println(gd.readAll().toString());
//		System.out.println(gd.readOne(1));
//		
//	}

}
