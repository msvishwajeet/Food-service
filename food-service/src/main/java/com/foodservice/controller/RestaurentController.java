package com.foodservice.controller;

import java.util.Scanner;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foodservice.model.Restaurent;
import com.foodservice.persistence.JdbiRestaurentPersistence;
import com.foodservice.persistence.RestaurentPersistence;

@Service
public class RestaurentController {
	private final Scanner scanner = new Scanner(System.in);
	@Autowired
	JdbiRestaurentPersistence jdbiRestaurentPersistence;
	@Autowired
	private RestaurentPersistence restaurentPersistence;
	@Autowired
	ValidationController validationController;
	public void addRestaurents() {
		//scanner.nextLine();
		System.out.println("Enter restaurent Name");
		String restaurentName = scanner.nextLine();
		System.out.println("Enter Contact Number");
		String contactNumber = validationController.validMobileNumber();
		System.out.println("pin Number");
		String pin = scanner.nextLine();
		while(pin.length()!=6) {
			System.out.println("please Enter correct 6 Digit Pin");
			pin = scanner.nextLine();
		}
		System.out.println("Enter city Name ");
		String cityName = scanner.nextLine();
		System.out.println("Enter State Name");
		String stateName = scanner.nextLine();
		System.out.println("Enter Menu Id");
		String menuId = scanner.nextLine();
		Restaurent restaurent = new Restaurent();
		restaurent.setPin(pin);
		restaurent.setContactNumber(contactNumber);
		restaurent.setMenuId(menuId);
		restaurent.setCityName(cityName);
		restaurent.setStateName(stateName);
		restaurent.setName(restaurentName);
		//restaurentPersistence.createRestaurent(restaurent);
		jdbiRestaurentPersistence.insertRestaurent(restaurent);
	}
	
	public void getAllRestaurent() {
		System.out.println("Please Enter your pin");
		String pin = scanner.nextLine();
		Set<Restaurent> set = restaurentPersistence.getAllRestaurentByPin(pin);
		for (Restaurent restaurent : set) {
			System.out.println("---------------------------------------------------------");
			System.out.println("Restaurent Name: "+restaurent.getName()+"  Contact Number: "+restaurent.getContactNumber());
			System.out.println("---------------------------------------------------------");
		}
	}
	public void printAllRestaurent() {
		Set<Restaurent> set =restaurentPersistence.getTotalRestaurent();
		for (Restaurent restaurent : set) {
			System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
			System.out.println("Name: "+restaurent.getName()+"----Contact Number "+restaurent.getContactNumber()+
					"-----City Name: "+restaurent.getCityName()+"----Pin: "+
					restaurent.getPin()+"---State: "+restaurent.getStateName());
			System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
		}
	}
	public Restaurent getRestaurent(String restaurentName, Set<Restaurent> set) {
		for(Restaurent restaurent1: set) {
		if(restaurentName.equalsIgnoreCase(restaurent1.getName())) {
			//System.out.println("Found");
			return restaurent1;
		}
		}
		return null;
	}
}
