package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;

/**
 * SearchPlaceCommand list the attraction of the city for the user
 * 
 * @author iamrick86
 * @version java7
 */
public class SearchPlaceCommand extends SessionCommand {

	public SearchPlaceCommand(JSONObject j) {
		super(j);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JSONObject execute(ExecutionContext context)
			throws CommandException, JSONException {
		// TODO Auto-generated method stub
		JSONObject searchRes = new JSONObject();
		if (super.executeAuthenticated()) {
			PlaceDAO placedao = context.getPlaceDAO();
			String cityName = j.getString(JsonKeys.CITY);
			System.out.println(cityName);
			ArrayList<ArrayList<String>> placeDetail = new ArrayList<ArrayList<String>>();

			placeDetail = placedao.listPlaces(cityName);
			System.out.println(placeDetail.get(0).get(0));
			searchRes.put(JsonKeys.RES_SEARCH_PLACE, placeDetail);
		} else {
			searchRes.put(JsonKeys.RES_SEARCH_PLACE,
					JsonKeyValues.SEARCH_PLACE_DETAIL_FAIL);
		}
		System.out.println("session");
		return searchRes;
	}
}
