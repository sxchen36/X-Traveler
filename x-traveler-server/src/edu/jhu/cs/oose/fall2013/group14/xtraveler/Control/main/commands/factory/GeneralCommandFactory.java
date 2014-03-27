package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.factory;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.Command;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;

/**
 * CommandFactory can get the concrete command factory through a map.
 * 
 * @version java7
 * @author iamrick86
 */

public class GeneralCommandFactory {
	JSONObject jsonObject;
	/**
	 * FACTORISE contains all concrete command factories
	 */
	private static final CommandFactory[] FACTORIES = {
			new LoginCommandFactory(), new SignUpCommandFactory(),
			new LogOutCommandFactory(), new AddVisitedPlaceCommandFactory(),
			new AddWishListPlaceCommandFactory(),
			new SearchPlaceCommandFactory(),
			new GetVisitedListCommandFactory(),
			new GetWishListCommandFactory(), new PersonalPageCommandFactory(),
			new ListPlaceDetailCommandFactory(), new ListFriendFactory(),
			new AddFriendFactory(), new DeleteFriendFactory(),
			new ShowCommonInterestUserFactory(), new UpdateUserInfoFactory(),
			new WritePlanFactory(), new PlanCommentFactory(),
			new ShowCommonWishUserFactory(),
			new ShowCommonFeaturePlaceFactory() };

	private Map<String, CommandFactory> factoryMap;

	public GeneralCommandFactory() {
		this.factoryMap = new HashMap<>();
		for (CommandFactory factory : FACTORIES) {
			this.factoryMap.put(factory.getCommandName(), factory);
		}
	}

	/**
	 * pass the json object to each concrete command factory
	 * 
	 * @param jsonObject
	 * @return Command
	 * @throws JSONException
	 * @throws CommandException
	 */

	public Command create(JSONObject jsonObject) throws JSONException,
			CommandException {
		Command command = null;
		String commandName = jsonObject.getString(JsonKeys.PURPOSE);
		System.out.println(commandName);
		CommandFactory factory = this.factoryMap.get(commandName);

		if (factory == null) {
			// TODO: replace with an exception type that the caller can handle
			// (probably ProtocolException)
			System.out.println("void");
			throw new CommandException("no such command exist");

		} else {
			command = factory.makeCommand(jsonObject);
			return command;
		}
	}
}