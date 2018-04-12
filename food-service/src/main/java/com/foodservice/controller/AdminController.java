package com.foodservice.controller;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodservice.model.Admin;
import com.foodservice.persistence.AdminPersistence;
@Service
public class AdminController {
	private final Scanner scanner = new Scanner(System.in);
	@Autowired
	AdminPersistence adminPersistence;
	@Autowired
	RestaurentController restaurentController;
	@Autowired
	MenuController menuController;
	@Autowired
	ValidationController validationController;
	public void resgister() {
		System.out.println("Enter your login credential");
		System.out.println("Enter you Name:");
		String name = scanner.nextLine();
		System.out.println("Enter you Mobile Number");
		String contactNumber = validationController.validMobileNumber();
		System.out.println("Enter you Email id");
		String email = validationController.validEmail();
		System.out.println("Enter your Password");
		String password = scanner.nextLine();
		Admin admin = new Admin();
		admin.setName(name);
		admin.setContactNumber(contactNumber);
		admin.setEmail(email);
		admin.setPassword(password);
		adminPersistence.addAdmin(admin);
		System.out.println("Success");
	}
	
	public void login() {
		System.out.println("Enter your EmailId:");
		String email = validationController.validEmail();
		System.out.println("Enter your Pasword");
		String password = scanner.nextLine();
		while(adminPersistence.login(email, password).length()==0) {
			System.out.println("wrong Email or PassWord ");
			System.out.println("press 1 to reEnter Your login credential");
			System.out.println("press 2. to go to main menu");
			int input = validationController.validInt();
			switch (input) {
			case 1:
				System.out.println("Enter your Email");
				email = scanner.nextLine();
				System.out.println("Enter your Password");
				password = scanner.nextLine();
				break;
			case 2:
				return;
			default:
				break;
			}
			
		}
		System.out.println("Welcome Mr. "+adminPersistence.login(email, password));
		start();
		
	}
	public void start() {
		System.out.println("Enter your Option");
		System.out.println("Press 1. To create Restaurent");
		System.out.println("Press 2. To add veg Menu");
		System.out.println("Press 3. To add Non-Veg Item");
		System.out.println("press 0 To log Out......");
		int input = validationController.validInt();
		execute(input);
	}
	public void execute(int input) {
		switch (input) {
		case 1:
			restaurentController.addRestaurents();
			start();
			break;
		case 2:
			menuController.addMenu();
			start();
			break;
		case 3:
			menuController.addNonVegMenu();
			start();
			break;
		case 0:
			return;
		default:
			System.out.println("Wrong input");
			start();
			break;
		}
	}
}
