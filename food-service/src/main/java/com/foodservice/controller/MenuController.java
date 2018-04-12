package com.foodservice.controller;

import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foodservice.model.Menu;
import com.foodservice.model.Restaurent;
import com.foodservice.persistence.MenuPersistence;
import com.foodservice.persistence.RestaurentPersistence;

@Service
public class MenuController {
	private final Scanner scanner = new Scanner(System.in);
	
	@Autowired
	private MenuPersistence menuPersistence;
	@Autowired
	private RestaurentPersistence restaurentPersistence;
	@Autowired
	private RestaurentController restaurentController;
	@Autowired
	ValidationController validationController;

	public void addMenu() {
		System.out.println("Enter Veg Dish Name");
		String dishName = scanner.nextLine();
		while(dishName.length()<3) {
			System.out.println("Please Enter corrrect Name");
			dishName = scanner.nextLine();
		}
		System.out.println("Enter Dish Price");
		int dishPrice = validationController.validInt();
		System.out.println("Enter menu ID");
		int menuId = validationController.validInt();
		Menu menu = new Menu();
		menu.setDishName(dishName);
		menu.setDishPrice(dishPrice+"");
		menu.setMenuId(menuId+"");
		menuPersistence.createMenu(menu);
	}
	public void addNonVegMenu() {
		System.out.println("Enter Non-Veg Dish Name");
		String dishName = scanner.nextLine();
		while(dishName.length()<3) {
			System.out.println("Please Enter corrrect Name");
			dishName = scanner.nextLine();
		}
		System.out.println("Enter Dish Price");
		int dishPrice = validationController.validInt();
		System.out.println("Enter menu ID");
		int menuId = validationController.validInt();
		Menu menu = new Menu();
		menu.setDishName(dishName);
		menu.setDishPrice(dishPrice+"");
		menu.setMenuId(menuId+"");
		menuPersistence.createNonVegMenu(menu);
	}
	public void getOnlyVeg() {
		System.out.println("Please Provide you Pin number to get available Restaurent");
		restaurentController.getAllRestaurent();
		String menuId = getMenuId();
		while (menuId == null) {
			menuId = getMenuId();
		}
		getVeg(menuId);
	}
	public void getOnlyNonVeg() {
		System.out.println("Please Provide you Pin number to get available Restaurent");
		restaurentController.getAllRestaurent();
		String menuId = getMenuId();
		while (menuId == null) {
			menuId = getMenuId();
		}
		getNonVeg(menuId);
	}
	public void getAllMenu() {
		System.out.println("Please Provide you Pin number to get available Restaurent");
		restaurentController.getAllRestaurent();
		String menuId = getMenuId();
		while (menuId == null) {
			menuId = getMenuId();
		}
		getVeg(menuId);
		System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
		getNonVeg(menuId);
	}
	public void getVeg(String menuId) {
		Set<Menu> set = menuPersistence.getMenu(menuId);
		if (set.isEmpty()) {
			System.out.println("No Veg item available");
			return;
		}
		System.out.println("***Veg Item Available Here:***");
		System.out.println();
		for(Menu menu : set) {
			System.out.println("----------------------------");
			System.out.println(menu.getDishName()+" Price: "+menu.getDishPrice()+"/-");
			System.out.println("----------------------------");

		}
	}
	public void getNonVeg(String menuId) {
		Set<Menu> set = menuPersistence.getNonVegMenu(menuId);
		if (set.isEmpty()) {
			System.out.println("No Non-veg item available");
			return;
		}
		System.out.println("***Non-Veg Item Available Here:***");
		System.out.println();
		for(Menu menu : set) {
			System.out.println("----------------------------");
			System.out.println(menu.getDishName()+" Price: "+menu.getDishPrice()+"/-");
			System.out.println("----------------------------");

		}
	}
	public String getMenuId() {
		System.out.println("Enter Restaurent Name: ");
		String restaurentName = scanner.nextLine();
		Set<Restaurent> set = restaurentPersistence.getTotalRestaurent();
		for (Restaurent restaurent : set) {
			if (restaurent.getName().equalsIgnoreCase(restaurentName)) {
				return restaurent.getMenuId();
			}
		}
		return null;
	}
}
