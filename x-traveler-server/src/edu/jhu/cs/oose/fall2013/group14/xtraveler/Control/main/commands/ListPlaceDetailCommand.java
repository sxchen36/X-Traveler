package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;

/**
 * List the place detail of the one attraction including city name, state name,
 * url and place feature.
 * 
 * @version java7
 * @author iamrick86
 * 
 */
public class ListPlaceDetailCommand extends SessionCommand {

	public ListPlaceDetailCommand(JSONObject j) {
		super(j);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 *            ExecutionContext include needed DAO class and sessionFactory
	 *            needed
	 * @exception CommandException
	 *                , JSONException need to be thrown if command is null or
	 *                when JSONObject is null
	 * @return listR JSONObject which will be sent to client side
	 */
	@Override
	public JSONObject execute(ExecutionContext context)
			throws CommandException, JSONException {
		JSONObject listR = new JSONObject();
		if (super.executeAuthenticated()) {
			PlaceDAO placedao = context.getPlaceDAO();
			String placeName = j.getString(JsonKeys.PLACE_NAME);
			String description = placedao.getPlaceDescription(placeName);
			String url = placedao.getPlaceImage(placeName);
			int rate = placedao.getPlaceRate(placeName);
			String r = String.valueOf(rate);
			String state = placedao.getPlaceState(placeName);
			String feature = placedao.getPlaceFeature(placeName);
			String city = placedao.getPlaceCity(placeName);
			listR.put(JsonKeys.RES_LIST_DETAIL_PLACE,
					JsonKeyValues.LIST_PLACE_DETAIL_SUC);
			listR.put(JsonKeys.PLACE_CITY, city);
			listR.put(JsonKeys.PLACE_NAME, placeName);
			listR.put(JsonKeys.PLACE_URI, url);
			listR.put(JsonKeys.PLACE_DES, description);
			listR.put(JsonKeys.PLACE_RATE, r);

			listR.put(JsonKeys.PLACE_STATE, state);
			listR.put(JsonKeys.PLACE_FEATURE, feature);

		} else {
			listR.put(JsonKeys.RES_LIST_DETAIL_PLACE,
					JsonKeyValues.LIST_PLACE_DETAIL_FAIL);
		}
		// TODO Auto-generated method stub
		return listR;
	}

}
