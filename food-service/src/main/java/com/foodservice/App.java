package com.foodservice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.foodservice.controller.AdminController;
import com.foodservice.controller.MenuController;
import com.foodservice.controller.OrderController;
import com.foodservice.controller.RestaurentController;
import com.foodservice.controller.ValidationController;
import com.foodservice.persistence.JdbiRestaurentPersistence;

public class App {
	private static ValidationController validationController;
	private static ApplicationContext context;
    public static void main( String[] args )
    {
    	context = new AnnotationConfigApplicationContext("com.foodservice.*");
    	validationController = context.getBean(ValidationController.class);
    	try {
	    	start1();
    	} finally {
//    		if(context != null)
//    		context.d();
    	}
    }
    public static void start1() {
    	System.out.println("Enter your option");
    	System.out.println("Press 1. To login as Admin");
    	System.out.println("Press 2. To register As admin");
    	System.out.println("Press 3.To use App as Guest");
    	System.out.println("Press any other key to Close App...");
    	int input1 = validationController.validInt();
    	execute1(input1);
    }
	private static void execute1(int input1) {
		switch (input1) {
		case 1:
			context.getBean(AdminController.class).login();
			start1();
			break;
		case 2:
			context.getBean(AdminController.class).resgister();
			start1();
			break;
		case 3:
			start();
			start1();
			break;

		default:
			System.err.println("App closed");
			break;
		}
		
	}
	public static void start() {
		System.out.println("Enter your Option");
		System.out.println("Press 1. for NearBy Restaurent");
		System.out.println("Press 2. for ALL RESTAURENT");
		System.out.println("Press 3. for veg Menu");
		System.out.println("Press 4. for Non-Veg Menu");
		System.out.println("Press 5. for all Menu");
		System.out.println("Press 6. To Order food");
		System.out.println("Press 7. To Get Your Recent order details");
		System.out.println("Press -1. T0 main app");
		int input = validationController.validInt();
		execute(input);
	}

	private static void execute(int input) {
		switch (input) {

		case 1:
			context.getBean(RestaurentController.class).getAllRestaurent();
			start();
			break;
		case 2:
			context.getBean(JdbiRestaurentPersistence.class).getAllRestaurent();
			//context.getBean(RestaurentController.class).printAllRestaurent();
			start();
			break;
		case 3:
			context.getBean(MenuController.class).getOnlyVeg();
			start();
			break;
		case 4:
			context.getBean(MenuController.class).getOnlyNonVeg();
			start();
			break;
		case 5:
			context.getBean(MenuController.class).getAllMenu();
			start();
			break;
		case 6:
			context.getBean(OrderController.class).placeOrder();
			start();
			break;
		case 7:
			context.getBean(OrderController.class).getOrderDatails();
			start();
			break;
		case -1:
			System.err.println("MAIN APP !!!!!");
			break;
		default:
			System.out.println("Wrong input");
			start();
			break;
		}
		
	}
}
