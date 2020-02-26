package restclient;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class User implements IUserManagement {

	public UserDetails getUserDetails(String userId) {
		UserDetails output = null;
		try {
			String url = "http://192.168.1.22:8080/user/" + userId;
			String type = "application/json";
			Gson gson = new Gson();

			Client client = Client.create();

			WebResource webResource = client.resource(url);

			// Get method response
			ClientResponse response = webResource.accept(type).get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			String json = response.getEntity(String.class);

			// Deserialize json and Getting UserDetails object
			output = gson.fromJson(json, UserDetails.class);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return output;
	}

	public Result insertUserDetails(UserDetails userDetails) {

		Result result = null;

		// Check user details
		if (userDetails.getAge() <= 0 || userDetails.getName() == null || userDetails.getId() == null) {
			System.err.println("UserDetails fields cann't be null or All fields must be valid detail!");
			return null;
		}

		try {
			String url = "http://192.168.1.22:8080/user";
			String type = "application/json";

			Client client = Client.create();

			WebResource webResource = client.resource(url);

			Gson gson = new Gson();

			// Serialize userDetails
			String input = gson.toJson(userDetails);

			// Post method response
			ClientResponse response = webResource.type(type).post(ClientResponse.class, input);

			String json = response.getEntity(String.class);

			// Deserialize json and Getting Result object
			result = gson.fromJson(json, Result.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return result;

	}

	public Result updateUserDetails(UserDetails userDetails) {

		Result result = null;

		// Check user details
		if (userDetails.getAge() <= 0 || userDetails.getName() == null || userDetails.getId() == null) {
			System.err.println("UserDetails fields cann't be null or All fields must be valid detail!");
			return null;
		}

		try {
			String url = "http://192.168.1.22:8080/user";
			String type = "application/json";

			Client client = Client.create();

			WebResource webResource = client.resource(url);

			Gson gson = new Gson();

			// Serialize userDetails
			String input = gson.toJson(userDetails);

			// Put method response
			ClientResponse response = webResource.type(type).put(ClientResponse.class, input);

			String json = response.getEntity(String.class);

			// Deserialize json and Getting Result object
			result = gson.fromJson(json, Result.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return result;
	}

	public Result deleteUserDetails(String userId) {
		Result result = null;
		try {
			String url = "http://192.168.1.22:8080/user/" + userId;
			String type = "application/json";
			Client client = Client.create();

			WebResource webResource = client.resource(url);

			ClientResponse response = webResource.accept(type).delete(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			String json = response.getEntity(String.class);
			Gson gson = new Gson();

			// Deserialize json and Getting Result object
			result = gson.fromJson(json, Result.class);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return result;
	}

	public UserDetails[] getAllUserDetails() {

		try {
			String url = "http://192.168.1.22:8080/users";
			String type = "application/json";
			Client client = Client.create();

			WebResource webResource = client.resource(url);

			ClientResponse response = webResource.accept(type).get(ClientResponse.class);
			System.out.println("Getting from the server...........");
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			String json = response.getEntity(String.class);

			System.out.println(json);

			Gson gson = new Gson();

			// Deserialize json and Getting UserDetails objects of array
			UserDetails userDetails[] = gson.fromJson(json, UserDetails[].class);

			return userDetails;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

}
