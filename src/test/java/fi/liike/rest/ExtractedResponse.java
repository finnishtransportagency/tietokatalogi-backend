package fi.liike.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtractedResponse {
	private final Logger LOG = LoggerFactory.getLogger(ExtractedResponse.class);

	private Response response;

	public ExtractedResponse(Response response) {
		this.response = response;
	}

	public JSONArray getItems() {
		JSONObject json = getJson(response);
		try {
			return (JSONArray) json.get("items");
		} catch (JSONException e) {
			return null;
		}
	}

	private JSONObject getJson(Response response) {
		return (JSONObject) response.getEntity();
	}

	public Response getResponse() {
		return response;
	}

	public List<Object> getValues(String outerKey, String innerKey) {
		List<Object> list = new ArrayList<Object>();
		JSONObject json = getJson(response);
		JSONArray items;
		try {
			items = (JSONArray) json.get(outerKey);
			for (int i = 0; i < items.length(); i++) {
				list.add(items.getJSONObject(i).get(innerKey));
			}
		} catch (JSONException e) {
			LOG.error("There was an error: " + e.getMessage());
		}
		return list;
	}

	public boolean contains(List<Object> ids, Object value) {
		return ids.contains(value);
	}

	public Object getValue(String key) {
		JSONObject json = getJson(response);
		try {
			return json.get(key);
		} catch (JSONException e) {
			LOG.error("There was an error: " + e.getMessage());
		}
		return null;
	}

	public int getStatus() {
		return response.getStatus();
	}

}

