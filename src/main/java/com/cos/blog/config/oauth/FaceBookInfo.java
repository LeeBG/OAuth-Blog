package com.cos.blog.config.oauth;

import java.util.Map;

public class FaceBookInfo extends OAuth2UserInfo {

	public FaceBookInfo(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getId() {
		return (String)attributes.get("id");
	}

	@Override
	public String getName() {
		return (String)attributes.get("name");
	}

	@Override
	public String getEmail() {
		return (String)attributes.get("email");
	}

	@Override
	public String getImageUrl() {
		return "";
	}

	@Override
	public String getUsername() {
		return "Facebook_"+(String)attributes.get("id");
	}
	
	
}
