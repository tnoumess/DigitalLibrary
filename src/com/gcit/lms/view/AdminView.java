package com.gcit.lms.view;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Publisher;
import com.gcit.lms.service.AdministrativeService;

public class AdminView {
	private Scanner sc=new Scanner(System.in);
	private AdministrativeService as=new AdministrativeService();
	public void Menu() throws Exception{
		boolean exit_Main=false;
		System.out.println("");
		do{
			System.out.println("1) Add/Update/Delete  Genre, Publisher or Book");System.out.println();
			System.out.println("2) Add/Update/Delete Author");System.out.println();
			System.out.println("3) Add/Update/Delete Library Branches");System.out.println();
			System.out.println("4) Add/Update/Delete Borrowers");System.out.println();
			System.out.println("5) Over-ride Due Date for a Book Loan"); System.out.println();
			System.out.println("6) Exit");	

			String option=sc.nextLine();
			if(option.length()>0){
				switch(option.charAt(0)){

				case '1':
					 Menu1(); break;
				case '2':
				Menu2();
					break;
				case '3':
					 break;
				case '4':
					break;
				case '5':
					 break;
				case '6':
					exit_Main=true;break;
				default :
					exit_Main=false;break;		
				}}
		}while(!exit_Main);
		
	}
	
	
	private  void Menu2() throws Exception{
		boolean exit_Menu1=false;
		System.out.println("Pick the operation to carry out");
		do{
			System.out.println("1) Add Author");
			System.out.println("2) Update Author");
			System.out.println("3) Delete Author");
			System.out.println("4) Exit");
			

			String option=sc.nextLine();
			if(option.length()>0){
				switch(option.charAt(0)){

				case '1':
					AddAuthor();
					break;
				case '2':
					UpdateAuthor();
					break;
				case '3':
					DeleteAuthor();
					break;
				case '4':
					exit_Menu1=true;break;
				default :
					exit_Menu1=false;break;		
				}}
		}while(!exit_Menu1);
		
	}
	
	private   void AddAuthor() throws Exception{
		boolean exit_Add=false;
        
		String name;
		int pubId;
		Set<Book> set=as.listbook();
		Author a=new Author();
		Publisher p;
		
		Set<Integer> choices=new HashSet<Integer>();
			
		System.out.println("\nBook ID     |     Book Title ");
		System.out.println("----------------------------------------------------------------------");
		Set<Integer> sk=new HashSet<Integer>();
		Hashtable<Integer,Book> m=new Hashtable<Integer, Book>();// used to retrieve later one book
		
		for(Book s: set){
			sk.add(s.getBookId());
			m.put(s.getBookId(), s);
			System.out.println(s.getBookId()+"\t      "+s.getBookId()+"\t      ");
		}
		do{ boolean exit=false;
			do{
				
			System.out.println();
			System.out.println("Enter the Book Id from this Author:");
			  try{
				int	bookId =Integer.parseInt(sc.nextLine().trim());
				
				   if(sk.contains(bookId)){
					   choices.add(bookId);
				   }
					System.out.println("Press 'q' to stop adding or any key to continue adding");
										
					String option=(sc.nextLine());
					if(option.length()>0){
						switch(option.charAt(0)){			
						case 'q':
							exit=true;break;

						default :
							exit=false;break;		
						}
					}
					
			  }catch(Exception e){
				  System.out.println(" Bad Id");
				  
			  }
			
			}while(!exit);
	       
        try{
			
			System.out.println("Enter the Author Name:");
			name = sc.nextLine();
	         
	        if(name != null && name.length()>0&&name.length()<=45) {
				if(choices.size()>0)
				{	

					a.setAuthorName(name);
					for(Map.Entry<Integer, Book> map: m.entrySet()){
						if(choices.contains(map.getKey())){
							a.addBook(map.getValue());
						}
					}
					as.createAuthor(a);
					System.out.println("Author added successfuly");
					exit_Add=true;
				}else{					
                      a.setAuthorName(name);
                      as.createAuthor(a);
					exit_Add=true;
					System.out.println("Author with no book added successfully ");	
					
				}}else{			
					System.out.println();
					System.out.println("Error: The Name cannot be empty or more than 45 characters");	
				}
        }catch(SQLException e){
        	System.out.println(e);
        	e.printStackTrace();
        }
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}

	
	private  void UpdateAuthor() throws SQLException{
		boolean exit_Add=false;

		int id;
		Map<Integer, String> m=as.listAuthorsFirstLevel2();
		List<Author> list=as.listAuthorsFirstLevel();
		Author b=new Author();
		System.out.println("\nAuthor ID        |       Author Name        ");
		System.out.println("----------------------------------------------------------");
		Set<Integer> sk=new HashSet<>();
		for(Map.Entry<Integer, String> map: m.entrySet()){
			int val=map.getKey();
			sk.add(val);
			    System.out.println(val+"  \t\t "+map.getValue());
			}
		do{

			System.out.println("Enter the Author Id you want to Update:");			         

				
			try{         
				id = Integer.parseInt(sc.nextLine().trim());
				 
				if(m.containsKey(id))
				{
					UpdateAuthor2(id);	
					
				}else{
					System.out.println("This Id does not exist.");	
				}
		}catch(Exception e){				
			System.out.println("INFO:Should be an integer!");				

		}
			
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}
	
	
	private  void UpdateAuthor2(int AuthorId) throws SQLException{
		boolean exit_Add=false;

		String name;
		do{

			System.out.println("Enter the new Author Name you want to Update:");			         

				
			try{          

				name = sc.nextLine().trim();
				Map<Integer, String> m=as.listAuthorsFirstLevel2();
				if(name!=null && name.length()>0 && name.length()<=45 && m.containsKey(AuthorId))
				{
					Author a=new Author();
					a.setAuthorName(name);
					a.setAuthorId(AuthorId);
					as.updateAuthor(a);	
					System.out.println(" Author Updated Successfully");
				}else{
					System.out.println("This Id does not exist or is more than 45 caracters long.");	
				}
		}catch(Exception e){				
			System.out.println("INFO:An error has occured!");				

		}
			
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}
	
	private  void DeleteAuthor() throws SQLException{
		boolean exit_Add=false;

		int id;
		Map<Integer, String> m=as.listAuthorsFirstLevel2();
		List<Author> list=as.listAuthorsFirstLevel();
		Author b=new Author();
		System.out.println("\nAuthor ID        |       Author Name        ");
		System.out.println("----------------------------------------------------------");
		Set<Integer> sk=new HashSet<>();
		for(Map.Entry<Integer, String> map: m.entrySet()){
			int val=map.getKey();
			sk.add(val);
			    System.out.println(val+"  \t\t "+map.getValue());
			}
		do{

			System.out.println("Enter the Author Id you want to delete:");			         

				
			try{         
				id = Integer.parseInt(sc.nextLine().trim());
				 
				if(m.containsKey(id))
				{
					as.deleteAuthor(as.findAuthor(id));	
					
				}else{
					System.out.println("This Id does not exist.");	
				}
		}catch(Exception e){				
			System.out.println("INFO:Should be an integer!");				

		}
			
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}
	
	private  void Menu1() throws Exception{
		boolean exit_Menu1=false;
		System.out.println("Pick the element to process");
		do{
			System.out.println("1) Genre");
			System.out.println("2) Publisher");
			System.out.println("3) Book");
			System.out.println("4) Exit");	

			String option=sc.nextLine();
			if(option.length()>0){
				switch(option.charAt(0)){

				case '1':
					Genre1(); break;
				case '2':
					Publisher1();
					break;
				case '3':
     				 Book1();
					break;
			
				case '4':
					exit_Menu1=true;break;
				default :
					exit_Menu1=false;break;		
				}}
		}while(!exit_Menu1);
		System.out.println("You logged out");
	}
	
	
	private  void Book1() throws Exception{
		boolean exit_Menu1=false;
		System.out.println("Pick the operation to carry out");
		do{
			System.out.println("1) Add book");
			System.out.println("2) Update book");
			System.out.println("3) Delete book");
			System.out.println("4) Exit");
			

			String option=sc.nextLine();
			if(option.length()>0){
				switch(option.charAt(0)){

				case '1':
					Addbook(); break;
				case '2':
					Updatebook();break;
				case '3':
					Deletebook(); break;
				case '4':
					exit_Menu1=true;break;
				default :
					exit_Menu1=false;break;		
				}}
		}while(!exit_Menu1);
		
	}
	
	
	private   void Addbook() throws Exception{
		boolean exit_Add=false;
        
		String name;
		int pubId;
		Set<Publisher> set=as.listpublisher3();
		Book b=new Book();
		Publisher p;
			
		System.out.println("\nPublisher ID | Publisher Name | Publisher Address | Publisher Phone ");
		System.out.println("----------------------------------------------------------------------");
		Set<Integer> sk=new HashSet<Integer>();
		Map<Integer,Publisher> m=new HashMap<Integer, Publisher>();// used to retrieve later one publisher
		
		for(Publisher s: set){
			sk.add(s.getPublisherId());
			m.put(s.getPublisherId(), s);
			System.out.println(s.getPublisherId()+"\t      "+s.getPublisherName()+"\t      "+s.getPublisherAddress()+ "\t          "+s.getPublisherPhone());
		}
		do{
			System.out.println();
			System.out.println("Enter the Publisher Id from the list above:");			         
	       
        try{
			pubId =Integer.parseInt(sc.nextLine().trim());
			System.out.println("Enter the Title of the book:");
			name = sc.nextLine();
	         
	        if(name != null && name.length()>0&&name.length()<=45) {
				if(sk.contains(pubId))
				{	b.setTitle(name);
				 	b.setPublisher(m.get(pubId));
					as.createBook(b);
					System.out.println("Book added successfuly");
					exit_Add=true;
				}else{					
//					b.setBookTitle(name);
//				   	dao.Insertbook(b);
					exit_Add=false;
					System.out.println("Error: This publisher does not exist!!");	
					
				}}else{			
					System.out.println();
					System.out.println("Error: Title cannot be empty or more than 45 characters");	
				}
        }catch(SQLException e){
        	System.out.println(e);
        	e.printStackTrace();
        }
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}

	
	private  void Updatebook() throws SQLException{
		boolean exit_Add=false;

		int id;
		Map<Integer, Book> m=as.listBooksFirstLevel2();
		Book b=new Book();
		System.out.println("\nBook ID        |       Title        |      Publisher ID");
		System.out.println("----------------------------------------------------------");
		Set<Integer> sk=new HashSet<>();
		for(Map.Entry<Integer, Book> map: m.entrySet()){
			b=map.getValue();
			if(b.getPublisher()!=null){
		     System.out.println(map.getKey()+"  \t\t "+b.getTitle()+"  \t\t "+b.getPublisher().getPublisherId());
			}else{System.out.println(map.getKey()+"  \t\t "+b.getTitle()+"  \t\t NULL");
			}
		}
		do{

			System.out.println("Enter the Book Id you want to Update:");			         

				
			try{          

				id = Integer.parseInt(sc.nextLine().trim());
				 
				if(m.containsKey(id))
				{
					Updatebook2(id);		
				}else{
					System.out.println("This Id does not exist.");	
				}
		}catch(Exception e){				
			System.out.println("INFO:Should be an integer!");				

		}
			
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}
	private  void Updatebook2(int id) throws SQLException{
	    boolean exit_Add=false;
	        
			String title;
			int pubId;
			Map<Integer,Book> m=as.listBooksFirstLevel2();
			Set<Publisher> set=as.listpublisher3();
			Book b=new Book();
			System.out.println("\nPublisher ID | Publisher Name | Publisher Address | Publisher Phone ");
			System.out.println("---------------------------------------");
			Set<Integer> sk=new HashSet<>();
			Map<Integer,Publisher> map= new HashMap<Integer, Publisher>();
			for(Publisher s: set){
				sk.add(s.getPublisherId());
				map.put(s.getPublisherId(), s);
				System.out.println(s.getPublisherId()+"\t "+s.getPublisherName()+"\t "+s.getPublisherAddress()+ "\t "+s.getPublisherPhone());
			}
			do{
				System.out.println();
				System.out.println("Enter the New Publisher Id from the list above:\n");			         
		       
	        try{
				pubId =Integer.parseInt(sc.nextLine().trim());
				System.out.println("Enter the New Title:\n");
				title = sc.nextLine();
		        if(title != null && title.length()>0&&title.length()<=45){
					if(sk.contains(pubId))
					{	// if valid publisher ID
						b.setBookId(id);
					   b.setTitle(title);
					
					   b.setPublisher(map.get(pubId));
						as.updatebook2(b);
						System.out.println("Book updated successfully");
						exit_Add=true;
					}					
					else{
						// If invalid publisher ID, just update the title
						
						b.setBookId(id);
						b.setTitle(title);
						//b.setPublisherId();
							as.updatebook(b);
						System.out.println("----------------------------------");
						System.out.println("Not a valid Publisher Id!");
						System.out.println("Only Book tilte updated ");
							exit_Add=true;
					}
							
					}else{			
						System.out.println();
						System.out.println("Error:  Name cannot be empty");	
					}
		        }catch(Exception e){				
						System.out.println("INFO:Should be an integer!");				

					}
				
				
				if(!exit_Add){
					System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
					String option=(sc.nextLine());
					if(option.length()>0){
						switch(option.charAt(0)){			
						case 'q':
							exit_Add=true;break;

						default :
							exit_Add=false;break;		
						}}}


			}while(!exit_Add);

		}
	
	
	
	private  void Deletebook() throws SQLException{
		boolean exit_Add=false;

		int id;
		Map<Integer, Book> m=as.listBooksFirstLevel2();
		Book b=new Book();
		System.out.println("\nBook ID        |       Title        |      Publisher ID");
		System.out.println("----------------------------------------------------------");
		Set<Integer> sk=new HashSet<>();
		for(Map.Entry<Integer, Book> map: m.entrySet()){
			b=map.getValue();
			if(b.getPublisher()!=null){
		System.out.println(map.getKey()+"  \t\t "+b.getBookId()+"  \t\t\t "+b.getPublisher().getPublisherId());
			}else{
				System.out.println(map.getKey()+"  \t\t "+b.getBookId()+"  \t\t\t NULL");
				
			}
			}
		do{

			System.out.println("Enter the Book Id you want to delete:");			         

				
			try{          

				id = Integer.parseInt(sc.nextLine().trim());
				 
				if(m.containsKey(id))
				{
					b.setBookId(id);
					as.deletebook(b);
					System.out.println("Deletion successfull");
				}else{
					System.out.println("This Id does not exist.");	
				}
		}catch(Exception e){				
			System.out.println("INFO:Should be an integer!");				

		}
			
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);


	}
	
	private  void Publisher1() throws SQLException{
		boolean exit_Menu1=false;
		System.out.println("Pick the operation to carry out");
		do{
			System.out.println("1) Add Publisher");
			System.out.println("2) Update Publisher");
			System.out.println("3) Delete Pulisher");
			System.out.println("4) Exit");
				

			String option=sc.nextLine();
			if(option.length()>0){
				switch(option.charAt(0)){

				case '1':
					AddPublisher(); break;
				case '2':
					UpdatePublisher();break;
				case '3':
					Deletepublisher(); 
					break;
				case '4':
					exit_Menu1=true;break;
				default :
					exit_Menu1=false;break;		
				}}
		}while(!exit_Menu1);
		
	}
	
	private   void AddPublisher() throws SQLException{
		boolean exit_Add=false;
        
		String name,Address,phone;
		Set<Publisher> s=as.listpublisher3();
		Publisher p=new Publisher();
		do{

			System.out.println("Enter the Publisher Name:");
			name = sc.nextLine();
			System.out.println("Enter the Publisher Address:");			         
	        Address = sc.nextLine();
	        System.out.println("Enter the Publisher Phone:");			         
	        phone = sc.nextLine();
	        p.setPublisherName(name);
	        p.setPublisherAddress(Address);
	        p.setPublisherPhone(phone);
	        if(name != null && name.length()>0&&name.length()<=45) {
				if(!s.contains(p))
				{	
					try {
						as.createPublisher(p);
						System.out.println("Operation successful!");
						exit_Add=true;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					
				}else{
					System.out.println("Error: This publisher already exists.");	
				}}else{			
					System.out.println();
					System.out.println("Error:  Name cannot be empty or more than 45 characters");	
				}
			
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}

	
	private  void UpdatePublisher() throws SQLException{
		boolean exit_Add=false;

		int id;
		Set<Publisher> set=as.listpublisher3();
		System.out.println("\nPublisher ID | Publisher Name | Publisher Address | Publisher Phone ");
		System.out.println("----------------------------------------------------------------------");
		Set<Integer> sk=new HashSet<Integer>();
		for(Publisher s: set){
			sk.add(s.getPublisherId());
			System.out.println(s.getPublisherId()+"\t      "+s.getPublisherName()+"\t      "+s.getPublisherAddress()+ "\t          "+s.getPublisherPhone());
		}
		do{

			System.out.println("Enter the publisher Id you want to Update:");			         

				
			try{          

				id = Integer.parseInt(sc.nextLine().trim());
				 
				if(sk.contains(id))
				{
					Updatepublisher2(id);		
				}else{
					System.out.println("This Id does not exist.");	
				}
		}catch(Exception e){				
			System.out.println("INFO:Should be an integer!");				

		}
			
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}
	
	private  void Updatepublisher2(int id) throws SQLException{
    boolean exit_Add=false;
        
		String name,Address,phone;
		Set<Publisher> s=as.listpublisher3();
		Publisher p=new Publisher();
		do{

			System.out.println("Enter the new Publisher Name:");
			name = sc.nextLine();
			System.out.println("Enter the new Publisher Address:");			         
	        Address = sc.nextLine();
	        System.out.println("Enter the new Publisher Phone:");			         
	        phone = sc.nextLine();
	        p.setPublisherId(id);
	        p.setPublisherName(name);
	        p.setPublisherAddress(Address);
	        p.setPublisherPhone(phone);
	        if(name != null && name.length()>0&&name.length()<=45) {
				if(!s.contains(p))
				{	as.updatePublisher(p);
				System.out.println("Update successful");
					exit_Add=true;
				}else{
					//If the information entered are identical to the original
					
					System.out.println("Update successful");	
				}}else{			
					System.out.println();
					System.out.println("Error:  Name cannot be empty or more than 45 caracter");	
				}
			
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}
	
	private  void Deletepublisher() throws SQLException{
		boolean exit_Add=false;

		int id;
		Set<Publisher> set=as.listpublisher3();
		System.out.println("\nPublisher ID | Publisher Name | Publisher Address | Publisher Phone ");
		System.out.println("----------------------------------------------------------------------");
		Set<Integer> sk=new HashSet<Integer>();
		for(Publisher s: set){
			sk.add(s.getPublisherId());
			System.out.println(s.getPublisherId()+"\t      "+s.getPublisherName()+"\t      "+s.getPublisherAddress()+ "\t          "+s.getPublisherPhone());
		}
		do{
			System.out.println();
			System.out.println("Enter the Publisher Id you want to delete:");			         

				
			try{          

				id = Integer.parseInt(sc.nextLine().trim());
				 
				if(sk.contains(id))
				{
					Publisher p= new Publisher();
					p.setPublisherId(id);as.deletePublisher(p);	
					System.out.println("Deletion successful!");
				}else{
					System.out.println("This Id does not exist exists.");	
				}
		}catch(Exception e){				
			System.out.println("INFO:Should be an integer!");				

		}
			
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}
	
	
	
	private  void Genre1() throws Exception{
		boolean exit_Menu1=false;
		System.out.println("Pick the operation to carry out");
		do{
			System.out.println("1) Add genre");
			System.out.println("2) Update genre");
			System.out.println("3) Delete delete");
			System.out.println("4) Exit");
				

			String option=sc.nextLine(); 
			if(option.length()>0){
				switch(option.charAt(0)){

				case '1':
					Addgenre(); break;
				case '2':
					Updategenre();
					break;
				case '3':
					Deletegenre(); 
					break;
				case '4':
					exit_Menu1=true;break;
				default :
					exit_Menu1=false;break;		
				}}
		}while(!exit_Menu1);
		
	}
	
	private  void Addgenre() throws Exception{
		boolean exit_Add=false;

		String name;
		
		
		Map<Integer,String> m=as.listGenre2();
		do{

			System.out.println("Enter the Genre Name:");			         

	        name = sc.nextLine();
				 
				if(!m.containsValue(name))
				{	Genre g=new Genre();
				g.setGenreName(name); 
				try{
				as.createGenre(g);
				System.out.println("Added successfully");
				System.out.println("---------------------------------------------");
					exit_Add=true;
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
				}else{
					System.out.println("This Genre already exists.");	
				}
			
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}
	
	private  void Updategenre() throws SQLException{
		boolean exit_Add=false;

		int id;
		Map<Integer,String> m=as.listGenre2();
		System.out.println("\nGenre ID | Genre Name ");
		System.out.println("---------------------------------------");
		for(Map.Entry<Integer, String> map: m.entrySet()){
			
			System.out.println(map.getKey()+"\t  "+map.getValue());
		}
		do{
			System.out.println();
			System.out.println("Enter the Genre Id you want to Update:");			         

				
			try{          

				id = Integer.parseInt(sc.nextLine().trim());
				 
				if(m.containsKey(id))
				{
					Updategenre2(id);		
				}else{
					System.out.println("This Id does not exist exists.");	
				}
		}catch(Exception e){				
			System.out.println("INFO:Should be an integer!");				

		}
			
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}
	
	private  void Updategenre2(int id) throws SQLException{
		boolean exit_Add=false;
		Map<Integer,String> m=as.listGenre2();
		String name;
		do{
            System.out.println();
			System.out.println("Enter the new Genre Name:");			         

	        name = sc.nextLine();
				 
				if(!m.containsKey(name))
				{	Genre g=new Genre();
				g.setGenreId(id);
				g.setGenreName(name);
				try{
				as.updateGenre(g);
				System.out.println("Update successful");
				exit_Add=true;
				}catch(Exception e){
					System.out.println(e.getMessage());	
				}				
					
				}else{
					System.out.println("Update successful");	
				}
			
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}
	
	private  void Deletegenre() throws SQLException{
		boolean exit_Add=false;

		int id;
		Map<Integer,String> m=as.listGenre2();
		System.out.println("\nGenre ID | Genre Name ");
		System.out.println("---------------------------------------");
		for(Map.Entry<Integer, String> map: m.entrySet()){
			
			System.out.println(map.getKey()+"\t  "+map.getValue());
		}
		do{

			System.out.println("Enter the Genre Id you want to delete:");			         

				
			try{          

				id = Integer.parseInt(sc.nextLine().trim());
				 
				if(m.containsKey(id))
				{
					Genre g=new Genre();
					g.setGenreId(id);as.deleteGenre(g);		
					System.out.println("Deleted successfuly");
				}else{
					System.out.println("This Id does not exist.");	
				}
		}catch(Exception e){				
			System.out.println("INFO:Should be an integer!");				

		}
			
			
			if(!exit_Add){
				System.out.println();System.out.println();  System.out.println("Press ' q '  to return or any other key to continue");
				String option=(sc.nextLine());
				if(option.length()>0){
					switch(option.charAt(0)){			
					case 'q':
						exit_Add=true;break;

					default :
						exit_Add=false;break;		
					}}}


		}while(!exit_Add);

	}
	

}
