package Controller;

import java.util.Vector;

import Model.Wishlist;
import View.ViewWishlist;

public class WishlistHandler {

	public WishlistHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public static Vector<Wishlist> getAll(int userID){
		return Wishlist.getAll(userID);
	}

	public static boolean insert(int userID, int jobID) {
		if(Wishlist.insert(userID, jobID))
			return true;
		return false;
	}
	
	public static boolean delete(int wishlistID) {
		return Wishlist.delete(wishlistID);
	}
	
	public static void ViewWishlist(int userID) {//rute ke ViewWishlist
		new ViewWishlist(userID);
	}
}
