package main;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import entity.Customer;
import entity.Order;
import entity.Product;

public class Main {
	static Session session = HibernateUtil.getSessionFactory().openSession();
	static int ci, confirmationno=1001;

	static Transaction tx = null;
	@SuppressWarnings("resource")
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		//session.beginTransaction();
		// Query conf = session.createQuery("SELECT max(CONF_NO) FROM Order");
//doubt:- how to convert query to integer?
		// confirmationno=(int)((org.hibernate.query.Query) conf).uniqueResult();
		System.out.println("Welcome TO Maven Mart");
		System.out.println("---------------------------------");

		System.out.println("---------------------------------");
		System.out.println("Please enter your Choice(1-2):");
		System.out.println("1. Are you a Customer?");
		System.out.println("2. Are you a User?");
		System.out.println("---------------------------------");
		System.out.println("---------------------------------");
		int mch = in.nextInt();

		switch (mch) {
		case 1:
			System.out.println("Welcome Customer");
			System.out.println("---------------------------------");
			System.out.println("Enter your id:");
			ci = in.nextInt();
			findCustomer(ci);
			System.out.println("---------------------------------");

			System.out.println("List of Products at Maven Mart are");
			lop();
			transaction();
			break;

		case 2:
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			char ch;
			System.out.println("Welcome User");
			System.out.println("---------------------------------");
			do {
				System.out.println("---------------------------------");
				System.out.println("Please enter your Choice(1-3):");
				System.out.println("1. ADD (customer/product)");
				System.out.println("2. EDIT (customer/product)");
				System.out.println("3. DELETE (customer/product)");
				System.out.println("---------------------------------");
				System.out.println("---------------------------------");
				int fc = in.nextInt();
				switch (fc) {
				case 1: {
					System.out.println("You opted to ADD, select (customer/product)");
					System.out.println("1. customer");
					System.out.println("2. product");
					System.out.println("---------------------------------");
					int ic = in.nextInt();
					switch (ic) {
					case 1:
						System.out.println("Enter Customer name to be added");
						String cn = in.next();
						System.out.println("---------------------------------");
						System.out.println("---------------------------------");
						addCustomer(cn);
						break;
					case 2:
						System.out.println("Enter Product Name to be added");
						String pn = in.next();
						System.out.println("Enter Product Price to be added");
						// to be change in double
						int pp = in.nextInt();
						System.out.println("Enter Product quantity to be added");
						int pq = in.nextInt();
						System.out.println("---------------------------------");
						System.out.println("---------------------------------");
						addProduct(pn, pp, pq);
						break;
					}
					break;
				}
				case 2: {
					System.out.println("You opted to EDIT, select (customer/product)");
					System.out.println("1. customer");
					System.out.println("2. product");
					System.out.println("---------------------------------");
					int ic = in.nextInt();
					switch (ic) {
					case 1:
						System.out.println("Enter Customer id to be edited");
						int cid = in.nextInt();
						System.out.println("Enter Customer name to be edited");
						String cn = in.next();
						System.out.println("---------------------------------");
						System.out.println("---------------------------------");
						editCustomer(cid, cn);
						break;
					case 2:
						System.out.println("Enter Product id to be edited");
						int pid = in.nextInt();
						System.out.println("Enter Product Name to be edited");
						String pn = in.next();
						System.out.println("Enter Product Price to be edited");
						// to be change in double
						int pp = in.nextInt();
						System.out.println("Enter Product quantity to be edited");
						int pq = in.nextInt();
						System.out.println("---------------------------------");
						System.out.println("---------------------------------");
						editProduct(pid, pn, pp, pq);
						break;
					}
					break;
				}
				case 3: {
					System.out.println("You opted to DELETE, select (customer/product)");
					System.out.println("1. customer");
					System.out.println("2. product");
					System.out.println("---------------------------------");
					int ic = in.nextInt();
					switch (ic) {
					case 1:
						System.out.println("Enter Customer ID to be deleted");
						int c = in.nextInt();
						System.out.println("---------------------------------");
						System.out.println("---------------------------------");
						delCustomer(c);
						break;
					case 2:
						System.out.println("Enter Product ID to be deleted");
						int p = in.nextInt();
						System.out.println("---------------------------------");
						System.out.println("---------------------------------");
						delProduct(p);
						break;
					}
					break;
				}
				default:
					System.out.println("Pls. Enter Choice between (1-3");
					break;

				}
				System.out.println("Do, you wish to continue(y/n):");
				String str = in.next();
				ch = str.charAt(0);

			} while (ch == 'y' || ch == 'Y');

			session.getTransaction().commit();
			session.close();
			break;
		}
	}

	// user_functions
	private static void addCustomer(String name) {
		Customer cus = new Customer();
		cus.setUsername(name);
		session.save(cus);

	}

	private static void addProduct(String name, int price, int quant) {
		Product pro = new Product();
		pro.setPrName(name);
		pro.setPrice(price);
		pro.setQuantity(quant);

		session.save(pro);
	}

	private static void editCustomer(int d, String name) {

		Customer cus = (Customer) session.get(Customer.class, d);
		if (cus != null) {
			cus.setUsername(name);
			session.save(cus);
		}
	}

	private static void editProduct(int id, String name, int price, int quant) {
		Product pro = (Product) session.get(Product.class, id);
		pro.setPrName(name);
		pro.setPrice(price);
		pro.setQuantity(quant);

		session.save(pro);
	}

	private static void delCustomer(int n) {
		// Finding customer

		Customer customer = (Customer) session.get(Customer.class, n);

		// Deleting customer info
		if (customer != null) {
			session.delete(customer);
			System.out.println("Customer ID " + n + " is deleted, under name " + customer.getUsername() + ".");
		}

	}

	private static void delProduct(int n) {
		// Finding product

		Product product = (Product) session.get(Product.class, n);

		// Deleting customer info
		if (product != null) {
			session.delete(product);
			System.out.println("Product ID " + n + " is deleted, under name " + product.getPrName() + " with price of "
					+ product.getPrice() + " and quantity " + product.getQuantity() + " .");
		}

	}

	// Customer function

	@SuppressWarnings("null")
	private static void findCustomer(int ci2) {
		// TODO Auto-generated method stub
		Customer cus = (Customer) session.get(Customer.class, ci2);
		if (cus != null) {
			System.out.println("Welcome back " + cus.getUsername() + " !");
		} else {
			System.out.println("Customer No.: " + ci2 + " Doesn't exist in our database. Pls. enter your Name.");
			String name = in.next();
			cus = new Customer();
			cus.setUsername(name);
			cus.setUserid(ci2);
			System.out.println("Welcome " + name + " ! Your new customer id is:" + cus.getUserid());

			session.save(cus);
		}
	}

	private static void lop() {
		tx =session.beginTransaction();
			@SuppressWarnings("rawtypes")
			List products = session.createQuery("FROM Product").list();
			for (@SuppressWarnings("rawtypes")
			Iterator iterator = products.iterator(); iterator.hasNext();) {
				Product product = (Product) iterator.next();
				System.out.println("---------------------------------------------");
				System.out.print("Product Id: " + product.getPrId());
				System.out.print("  Product Name: " + product.getPrName());
				System.out.println("  Product Price: " + product.getPrice());
				System.out.println("  Product Quantity: " + product.getQuantity());
				System.out.println("---------------------------------------------");
			}
			tx.commit();
		
	}

	private static void transaction() {
		// TODO Auto-generated method stub

		System.out.println("Want to add any product to cart");
		System.out.println("(y/n)");
		String input = in.next();
		char inputch = input.charAt(0);
		char inputch2 = 0;
		if (inputch == 'y') {
			do {
				System.out.println("Enter product id to add to cart.");
				int cartid = in.nextInt();
				addProduct(ci, cartid);

				System.out.println("1. Want to add more product.");
				System.out.println("2. My Cart");
				System.out.println("3. View Order");
				System.out.println("Select from(1-3).");

				int choice1 = in.nextInt();
				switch (choice1) {
				case 1:
					System.out.println(" Want to add more product.");
					lop();
					++confirmationno;
					transaction();
					break;

				case 2:
					System.out.println("Enter your Confirmation Id.");

					confirmationno = in.nextInt();

					myCart(confirmationno);
					break;
				case 3:
					System.out.println("Enter your Confirmation Id.");

					confirmationno = in.nextInt();
					orderDetails(confirmationno);
					break;
				default:
					System.out.println("Thank you for visiting us.");
				}
				System.out.println("Want to add more product to cart/see cart/place order. Press(y/n)");

				String input1 = in.next();
				inputch2 = input1.charAt(0);

			} while (inputch2 == 'y');
		} else {
			System.out.println("1. My Cart");
			System.out.println("2. View Order");
			int cartid2 = in.nextInt();
			switch (cartid2) {
			case 1:
				System.out.println("Enter your Confirmation Id.");

				confirmationno = in.nextInt();

				myCart(confirmationno);
				break;
			case 2:
				System.out.println("Enter your Confirmation Id.");

				confirmationno = in.nextInt();
				orderDetails(confirmationno);
				break;
			default:
				System.out.println("Thank you for visiting us.");
			}
		}

	}

	private static void addProductToCart(int ci2, int cartid, int qi, Order ord, int confirmationno) {
		ord.setConfirmationno(confirmationno);
		ord.setCid(ci2);
		ord.setPid(cartid);
		ord.setPrquant(qi);
		session.save(ord);
	}

	private static void updateProduct(Product pro, int qi) {
		pro.setQuantity(pro.getQuantity() - qi);
		session.save(pro);
	}

	private static void addProduct(int ci2, int cartid) {
		/*
		 * try { tx = session.beginTransaction();
		 */
		Product pro = (Product) session.get(Product.class, cartid);

		System.out.println("Enter quantity:");
		int qi = in.nextInt();
		if (qi <= pro.getQuantity()) {
			Order ord = new Order();
			addProductToCart(ci2, cartid, qi, ord, confirmationno);
			updateProduct(pro, qi);

			System.out.println("Your Current Bill: " + pro.getPrice() + " * " + qi + " = " + pro.getPrice() * qi
					+ "\n Your Confirmation no. is " + ord.getConfirmationno());

		} else
			System.out.println("Sorry " + pro.getPrName() + " is out of Stock");
		/*
		 * tx.commit(); }catch (Exception e) { if (tx != null) tx.rollback();
		 * e.printStackTrace(); } finally { session.close(); }
		 */

	}

	private static void myCart(int confno) {

		/*
		 * try { tx = session.beginTransaction();
		 */
		int sum = 0;
		Product pro;
		@SuppressWarnings("rawtypes")
		List orders = session.createQuery("FROM Order WHERE CONF_NO=" + confno).list();
		Order order = null;

		for (@SuppressWarnings("rawtypes")
		Iterator iterator = orders.iterator(); iterator.hasNext();) {
			order = (Order) iterator.next();
			System.out.println("---------------------------------------------");
			System.out.print("Product Id: " + order.getPid());
			pro = (Product) session.get(Product.class, order.getPid());

			System.out.print("  Product Name: " + pro.getPrName());
			System.out.println("  Product Price: " + pro.getPrice());
			System.out.println("  Product Quantity: " + order.getPrquant());

			sum += pro.getPrice() * order.getPrquant();
			System.out.println("---------------------------------------------");
		}
		System.out.println(" Cart Value is:  " + sum);
		System.out.println(" Your ID is  " + order.getConfirmationno());

		/*
		 * tx.commit(); } catch (HibernateException e) { if (tx != null) tx.rollback();
		 * e.printStackTrace(); } finally { session.close(); }
		 */
	}

	private static void orderDetails(int confno) {

		if (confno != 0) {
			System.out.println("Thanks for buying at Maven Mart  ");
			myCart(confno);

		} else {
			System.out.println("You haven't buy items yet. if you want to buy, choose(y/n) ");
			String input = in.next();
			char inputch = input.charAt(0);
			if (inputch == 'y') {
				System.out.println("Enter product id to add to cart.");
				int cartid = in.nextInt();
				addProduct(ci, cartid);

			} else
				System.out.println("Have a nice Day");

		}

	}

}
