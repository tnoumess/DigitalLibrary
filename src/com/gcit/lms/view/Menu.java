package com.gcit.lms.view;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
	
	private Scanner sc=new Scanner(System.in);
	/**
	 * This is the Main menu of our Application
	 * @throws SQLException 
	 */
	private AdminView admin=new AdminView();
	public void Menu() throws Exception{
		boolean exit_Main=false;
		do{
			System.out.println("1) Librarian");
			System.out.println("2) Administrator");
			System.out.println("3) Borrower");
			System.out.println("4) Exit");	

			String option=(new Scanner(System.in).nextLine());
			if(option.length()>0){
				switch(option.charAt(0)){

				case '1':
					exit_Main=false; /*Lib1();*/break;
				case '2':
					exit_Main=false;admin.Menu(); break;
				case '3':
					exit_Main=false; /*Borr(dao.list_Borrower());*/break;
				case '4':
					exit_Main=true;break;
				default :
					exit_Main=false;break;		
				}}
		}while(!exit_Main);
		System.out.println("You logged out");
	}

}
