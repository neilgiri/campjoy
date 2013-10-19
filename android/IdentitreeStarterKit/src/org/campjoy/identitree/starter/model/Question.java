package org.campjoy.identitree.starter.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Question {

	private String id, text, nextid, treeid;
	private String[] imageArray = new String[2];
	
	public Question(JSONObject question) {
		parseQuestion(question);
	}

	private void parseQuestion(JSONObject question) {
		try {
			id = question.getString("id");
			JSONArray choices = question.getJSONArray("choices");
			parseTableData(choices);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	private void parseTableData(JSONArray choices) {
		for (int i = 0; i < choices.length(); i++) {
			try {
				JSONObject menuObject = choices.getJSONObject(i);
				text = menuObject.getString("text");
				nextid = menuObject.getString("nextid");
				
				JSONArray images = menuObject.getJSONArray("images");
				
				for (int j = 0; j < images.length(); j++) {
					imageArray[j] = images.getString(j);
				}
	
				treeid = menuObject.getString("treeid");
			} catch (JSONException e) {
				e.printStackTrace();
			}		
		}
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append(id);
		result.append(", ");
		result.append(text);
		result.append(", ");
		result.append(nextid);
		result.append(", ");
		result.append(imageArray[0]);
		result.append(", ");
		result.append(imageArray[1]);
		result.append(", ");
		result.append(treeid);
		result.append("\n");
		
		return result.toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getNextid() {
		return nextid;
	}

	public void setNextid(String nextid) {
		this.nextid = nextid;
	}

	public String getTreeid() {
		return treeid;
	}

	public void setTreeid(String treeid) {
		this.treeid = treeid;
	}

	public String[] getImageArray() {
		return imageArray;
	}

	public void setImageArray(String[] imageArray) {
		this.imageArray = imageArray;
	}
}