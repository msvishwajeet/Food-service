package com.foodservice.controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodservice.model.Address;
import com.foodservice.persistence.AddressPersistence;

@Service
public class AddressController {
	private final Scanner scanner = new Scanner(System.in);
	
	@Autowired
	private AddressPersistence addressPersistence;

	public void addAddress() {
		scanner.nextLine();
		System.out.println("Enter City Name");
		String cityName = scanner.nextLine();
		System.out.println("Enter State Name");
		String stateName = scanner.nextLine();
		System.out.println("Enter PinNo");
		int pinNo = scanner.nextInt();
		
		Address address = new Address();
		address.setCityName(cityName);
		address.setPinNo(pinNo);
		address.setStateName(stateName);
		addressPersistence.createAddress(address);
	}
}
