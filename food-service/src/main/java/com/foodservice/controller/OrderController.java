package com.foodservice.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foodservice.model.Menu;
import com.foodservice.model.Order;
import com.foodservice.model.Restaurent;
import com.foodservice.persistence.MenuPersistence;
import com.foodservice.persistence.OrderPersistence;
import com.foodservice.persistence.RestaurentPersistence;

@Service
public class OrderController {
	private final Scanner scanner = new Scanner(System.in);
	@Autowired
	private OrderPersistence orderPersistence;
	@Autowired
	RestaurentController restaurentController;
	@Autowired
	RestaurentPersistence restaurentPersistence;
	@Autowired
	MenuPersistence menuPersistence;
	@Autowired
	MenuController menuController;
	@Autowired
	ValidationController validationController;
	
	public void placeOrder()  {
		int bill=0;
		Order order = new Order();
		String orderItem = "";
		String restName = "";
		Set<Menu> menuSet=new HashSet<Menu>();
		System.out.println("Enter Your Pin Number");
		String pin = scanner.nextLine();
		while(restaurentPersistence.getAllRestaurentByPin(pin).isEmpty()) {
			System.out.println("Enter correct Your Pin Number");
			pin = scanner.nextLine();
		}
		System.out.println("-*-*-*-*-*-( Resaturent list )-*-*-*-*-*-");
		Set<Restaurent> set = restaurentPersistence.getAllRestaurentByPin(pin);
		for (Restaurent restaurent : set) {
			System.out.println("Resturent Name: "+restaurent.getName()+" ------ Contact Number: "+restaurent.getContactNumber());
		}
		//restaurentController.getAllRestaurentByPin(pin);
		//Restaurent Printed on console//
		boolean bol=true;
		while(bol) {
			bol=false;
		//scanner.nextLine();
		System.out.println("Enter Selected Restaurent Name ");
		String restaurentName = scanner.nextLine();
		while (restaurentController.getRestaurent(restaurentName, set)==null) {
			System.out.println("Enter Correct Restaurent Name ");
			restaurentName = scanner.nextLine();
		}
		restName = restaurentName;
		String menuId = restaurentController.getRestaurent(restaurentName, set).getMenuId();
		System.out.println("Enter 1. for veg Menu");
		System.out.println("Enter 2. for Non-veg Menu");
		System.out.println("press 0 to goto main menu");
		int input = validationController.validInt();
		while (input > 2 || input < 0) {
			System.out.println("Please Enter From available Options only");
			input = validationController.validInt();
		}//////////////////////////////////////////////////////////////////////////////////
		switch (input) {
		case 0:
			return;
		case 1:
			menuSet=menuPersistence.getMenu(menuId);
			if (menuSet.isEmpty()) {
				System.out.println("No item Available");
				System.out.println("Please Select another");
				bol=true;
				break;
				//return;
			}
			menuController.getVeg(menuId);
			break;
		case 2:
			menuController.getNonVeg(menuId);
			menuSet=menuPersistence.getNonVegMenu(menuId);
			break;
		default:
			break;
		}
		}	
		//****Food adding to cart for order*****
			boolean option = true;
			int input1;
			while (option) {
				System.out.println("Press 1 To add Food to your cart");
				System.out.println("press 2. to continue for payment");
				System.out.println("press any other Number for main menu");
				input1 =  validationController.validInt();
				switch(input1) {
				case 1:
					String result=foodCart(menuSet);
					if (result.equals("@")) {
						break;
					}
					else {
						String arr[]=result.split(" ");
						bill+=Integer.valueOf(arr[0]);
						orderItem+=arr[1]+"/";
					}
					break;

				case 2:
					option= payment(bill);
					break;

				default:
					option = false;
					return;
				}
			}
			
			//Confirmation Of order********
			//System.out.println(rest);
			orderItem = restName+"/"+orderItem;
			confirmation(order, bill, orderItem);
		
	}
	public String foodCart(Set<Menu> menuSet) {
		String ret = "";
		int bill = 0;
		String orderItem = "";
		System.out.println("Enter your Selected Food");
		String dishName = scanner.nextLine();
		while(getDish(menuSet, dishName)) {
			System.out.println("Sorry No such Dish available");
			System.out.println("Press 1. to Re-Enter Dish name");
			System.out.println("Press other number for main menu");
			int input = validationController.validInt();
			switch (input) {
			case 1:
				dishName = scanner.nextLine();
				break;

			default:
				return "@";
			}
			
		}
		for(Menu menu1:menuSet) {
			if (menu1.getDishName().equalsIgnoreCase(dishName)) {
				System.out.println("Please Enter Quantity");
				int quantity = validationController.validInt();
				int price = quantity* Integer.valueOf(menu1.getDishPrice());
				bill += price;
				orderItem += dishName+"_(x"+quantity+")";
				ret += bill+" "+orderItem;
				System.out.println("Added");
				return ret;
			}
		}
		return "@" ;
	}
	
	
	public boolean payment(int bill) {
		if (bill == 0) {
			System.out.println("Nothing selected");
			return true;
		}
		System.out.println("Please pay Rs. "+bill+"/- to confirm");
		int payment = validationController.validInt();
		while(payment!=bill) {
			System.out.println("Please Enter correct ammount to confirm");
			System.out.println("Please pay Rs. "+bill+"/- to confirm");
			payment = validationController.validInt();
		}
		return false;
	}

	public void confirmation(Order order, int bill,String orderItem) {
		//scanner.nextLine();
		System.out.println("Enter Your Name");
		order.setName(scanner.nextLine());
		System.out.println("Enter your conatct Number");
		String mobileNumber = validationController.validMobileNumber();
		order.setContactNumber(mobileNumber);
		order.setDate(new Date());
		order.setBill(bill);
		String orderId = order.getContactNumber()+"@"+
		new SimpleDateFormat().format(new Date());
		order.setOrderId(orderId);
		order.setOrderedItem(orderItem);
		orderPersistence.orderInsert(order);
		System.out.println("Your order id is: "+orderId);
		System.out.println("success");
	}
	
	public void getOrderDatails() {
		System.out.println("Please Enter your Mobile Number");
		String contactNumber = validationController.validMobileNumber();
		if (orderPersistence.getOrderDetails(contactNumber).getName()==null) {
			System.out.println("No Order Available for your Number");
			return;
		}
		System.out.println("Your recent order is: ");
		System.out.println("******************************************");
		Order order=orderPersistence.getOrderDetails(contactNumber);
		System.out.println("Name:-"+order.getName());
		System.out.println("Date Of order:-"+order.getOrderId());
		String []arr= order.getOrderedItem().split("/");
		System.out.println("Restaurent Name: [--"+arr[0].toUpperCase()+"--]");
		for (int i = 1; i<arr.length; i++) {
			System.out.println("_________________");
			System.out.println("--->"+arr[i]);
			System.out.println("_________________");
		}
		System.out.println("-----------------------------------");
		System.out.println("   Total Bill:- Rs. "+order.getBill()+"/.");
		System.out.println("----------------/-----------------");
	}
	
	public boolean getDish(Set<Menu> menuSet,String dishName) {
		for (Menu menu : menuSet) {
			if (menu.getDishName().equalsIgnoreCase(dishName)) {
				return false;
			}
		}
		return true;
	}
}
