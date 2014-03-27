package edu.jhu.cs.oose.fall2013.group14.xtraveler.main;

public final class JsonKeyValues {

	/*
	 * ==================== Client -> Server====================
	 */

	// FROM_PAGE
	public static final String LOGIN_ACTIVITY = "login activity";
	public static final String MENU = "menu";
	public static final String MAIN_PAGE = "main page";
	public static final String CITY_ATTRACTION = "city attraction";

	// CURRENT_USER
	// NONE, based on user_input

	// PURPOSE:
	public static final String SHOW_COMMON_FEATURE_PLACE = "show the similar place with same feature";
	
	public static final String GET_PLACE_DETAILS = "getPlaceDetails";
	// ask for attractions in a city
	public static final String GET_CITY_ATTRACTION = "getCityAttraction";
	// ask for personal information of a user
	public static final String GET_PERSONAL_INFO = "getPersonalInfo";
	// PURPOSE: user wants to sign up
	public static final String SIGNUP = "signup";
	
	public static final String LOGOUT = "log out";
	// PURPOSE: user wants to login
	public static final String LOGIN = "login";

	public static final String GET_VISITED_LIST = "get visited list";

	public static final String GET_WISH_LIST = "get wish list";
	
	//purpose: delete a friend
	public static final String DELETE_FRIEND = "delete your friend";
	
	//purpose: delete a visited place
	


	public static final String LOG_OUT_SUC = "log out successfully";
	public static final String LOG_OUT_FAIL = "log out fail";
	
	
	
	public static final String WRITE_PLACE_COMMENT = "write place comment";
	// PURPOSE: add an attraction to wish list
	public static final String ADD_TO_WISHLIST = "add to wish list";

	// PURPOSE: add an attraction to visited list
	public static final String ADD_TO_VISITED = "add to visited list";

	public static final String GET_WISH_FAIL = "failed to get wish list";
	
	//purpose: add friend
	public static final String ADD_FRIEND = "add your friends";
	
	// add friend
	public static final String ADD_FRIEND_SUC = "add friend successful";
	public static final String ADD_FRIEND_FAIL = "add friend fail";
	
	
	
	//delete friend
	public static final String DELETE_FRIEND_SUC = "delete friend successful";
	public static final String DELETE_FRIEND_FAIL = "delete friend fail";
	

	// PURPOSE: list detail of a place
	public static final String LIST_PLACE_DETAIL = "list the detail of the place";
	
	public static final String WRITE_YOUR_PLAN = "write my plan";
	public static final String WRITE_YOUR_PLAN_SUC = "write my plan successful";
	public static final String WRITE_YOUR_PLAN_FAIL = "write my plan fail";
	
	
	public static final String DELETE_VISITED_PLACE = "delete visited place";
	public static final String DELETE_VISITED_SUC = "delete visited place successful";
	public static final String DELETE_VISITED_FAIL = "delete visited place fail";
	
	public static final String DELETE_WISH_PLACE = "delete wish list place";
	public static final String DELETE_WISH_SUC = "delete wish list place succefful";
	public static final String DELETE_WISH_FAIL = "delete wish list place failed";
	
	public static final String LIST_FIREND = "list your friend";
	public static final String LIST_FIREND_FAIL = "list your friend failed";
	
	public static final String PLAN_COMMENT = "insert plan comment";
	public static final String PLAN_COMMENT_SUC = "insert plan comment successful";
	public static final String PLAN_COMMENT_FAIL = "insert plan comment fail";

	/*
	 * ==================== Server -> Client====================
	 */
	/* Login */
	// login successful
	public static final String LOGIN_SUC = "true";
	// login fail
	public static final String LOGIN_FAIL = "login fail";

	/* Signup */
	// sign up success
	public static final String SIGN_UP_SUC = "true";
	// sign up username duplicate
	public static final String SIGN_UP_USERNAME_DUP = "duplicated username";
	// sign up invalid input
	public static final String SIGN_UP_INVALID_INPUT = "invalid input";

	/* Personal Info */
	// return personal information
	public static final String PERSONAL_PAGE_SUC = "load personal page successful";
	public static final String PERSONAL_PAGE_FAIL = "load personal page failed";

	/* add visited place */
	public static final String ADD_TO_VISITEDLIST_SUC = "add to visited list successfully";
	public static final String ADD_TO_VISITEDLIST_FAIL = "add to visited list fail";

	/* add wish place */
	public static final String ADD_TO_WISH_SUC = "add to wish list successfully";
	public static final String ADD_TO_WISH_FAIL = "add to wish list fail";

	public static final String SEARCH_PLACE_DETAIL_FAIL = "search place failed";
	
	/* List Detail of the place */
	public static final String LIST_PLACE_DETAIL_FAIL = "list detail failed";
	public static final String LIST_PLACE_DETAIL_SUC = "list detail successfully";

	
	public static final String LIST_FEATURE_PLACE_FAIL = "List feature place failed";
	/* Universal Use */
	public static final String TIME_OUT = "time out";

}