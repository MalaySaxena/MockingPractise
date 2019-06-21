package BO;

import java.util.Iterator;
import java.util.List;

public interface MainBO
{
	boolean addCustomer(String name) throws BOException;

	boolean addProduct(String name, int price, int quant) throws BOException;

	boolean editCustomer(int d, String name) throws BOException;

	boolean editProduct(int id, String name, int price, int quant) throws BOException;

	boolean delCustomer(int n) throws BOException;

	boolean delProduct(int n) throws BOException;

	// Customer function

	boolean findCustomer(int ci2) throws BOException;

//	boolean lop() throws BOException;

	//boolean transaction() throws BOException;

	boolean addProductToCart(int ci2, int cartid, int qi, Order ord, int confirmationno) throws BOException;

	boolean updateProduct(Product pro, int qi) throws BOException;

	boolean addProduct(int ci2, int cartid) throws BOException;

	boolean myCart(int confno) throws BOException;

	boolean orderDetails(int confno) throws BOException;

}
