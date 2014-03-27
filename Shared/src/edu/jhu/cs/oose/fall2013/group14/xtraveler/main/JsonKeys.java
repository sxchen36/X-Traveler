package edu.jhu.cs.oose.fall2013.group14.xtraveler.main;

public final class JsonKeys {

	/*
	 * ================ Client -> Server================
	 */
	public static final String FROM_PAGE = "fromPage";
	public static final String CURRENT_USER = "currentUser";// the current user
	public static final String PURPOSE = "purpose"; // purpose of the request
	public static final String PASSWORD = "keyword"; // password of a user
	public static final String STATE = "state"; // state
	public static final String CITY = "city"; // city

	public static final String AGE = "age"; // user age on sign up
	public static final String EMAIL = "email"; // user email on sign up
	public static final String SEX = "sex"; // user
	public static final String FRIEND_NAME = "friendName";
	public static final String PLAN_COMMENT_CONTENT = "plan comment content";
	
	public static final String PLACE_COMMENT_CONTENT = "place comment content";
	// public static final String SESSION_KEY = "sessionKey"; //sessionKey to
	// validate identity

	/*
	 * ================= Server -> Client=================
	 */
	// For LoginActivity Page
	public static final String RES_LOGIN_ACT = "responseto LoginActivity";
	public static final String ATTRACTION_NAME = "attraction name"; // name of
																	// the
																	// attraction

	/*
	 * ================= Server -> Client=================
	 */
	// For SignupActivity Page
	public static final String RES_SIGN_UP = "responseto SignUpActivity";

	// For Authentication Purpose
	public static final String SESSION_KEY = "sessionKey";

	/*
	 * ================= Server -> Client=================
	 */
	// For Open Personal Page Purpose
	// Status
	public static final String RES_PLAN_COMMENT = "write plan comment";
	public static final String RES_LOG_OUT = "response to log out";
	public static final String RES_PERSONAL_PAGE = "responseto personal page";
	public static final String RES_ADD_VISIT = "responseto add visited place";
	public static final String RES_ADD_WISH = "responseto add wish place";
	public static final String RES_SHOW_COMMON_VISITED_PLACE = "show the place of same visited type feature";

	public static final String RES_PERSONAL_INFO = "responseto Personal information";
	
	public static final String RES_LIST_COMMON_USER = "list two user";
	public static final String RES_LIST_COMMON_PLACE = "list two places";
	
	
	public static final String RES_SEARCH_PLACE = "responseto search place";

	public static final String RES_WISH_LIST = "wish list"; // wish to go list,
															// array of JSONs
	public static final String RES_VISITED_LIST = "visited list"; // list of
																	// visited
																	// places,
																	// array of
																	// JSONs

	public static final String RES_ADD_FRIEND = "add friend";
	public static final String RES_DELETE_FRIEND = "delete friend";
	public static final String RES_DELETE_VISITED = "delete your visited place";
	public static final String RES_DELETE_WISH = "delete your wish place";
	
	public static final String RES_LIST_DETAIL_PLACE = "list detail of the place";

	public static final String RES_LIST_FRIEND = "list your friend";
	public static final String RES_WRITE_PLAN = "write a plan";

	// For wish list or visited list, each place is a JSON

	public static final String PLACE_FEATURE = "place feature"; // place name
	public static final String PLACE_STATE = "place state name"; // place name
	public static final String PLACE_CITY = "place city name"; // place name
	public static final String PLACE_RATE = "place rate"; // place name
	public static final String PLACE_NAME = "place name"; // place name
	public static final String PLACE_DES = "place description"; // place
																// description
	public static final String PLACE_URI = "place url"; // place url
	public static final String TRAVEL_PLAN = "travel plan"; // travel plan,
															// array of JSONs
	// For Each Travel Plan, each travel plan is a JSON
	public static final String PLAN_ID = "plan id"; // travel plan id
	public static final String PLAN_NAME = "plan name"; // travel plan name
	public static final String PLAN_BUDGET = "plan budget"; // travel plan
															// budget
	public static final String PLAN_DESCRIPTION = "plan description"; // travel
																		// plan
																		// description

	// For main page
	public static final String RES_GET_PERSON_INFO_ACT = "responseto get personal information";

	/*
	 * =========== Only for Client ==========/
	 */
	public static final String USER_INFOS = "USERInfos";
	public static final String NAME = "NAME";
	public static final String INFO_BETWEEN_PAGE = "Information for next server";
	public static final String PLAN_DAYS = "plan days";
	public static final String PLAN_PEOPLE = "plan people";
}
