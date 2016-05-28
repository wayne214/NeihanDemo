package com.wayne.mydemo.model;

/**
 * Project:MyDemo
 * Author:wayne
 * Date:2016/5/27
 */

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 段子的数据结构
 */
public class TextEssay {
    private String content;

    private String userName;

    private String avatarUrl;

    public void parseJson(JSONObject json) throws JSONException {
        if (json != null) {

            JSONObject group=json.getJSONObject("group");
            content=group.getString("content");
            JSONObject user=group.getJSONObject("user");
            userName=user.getString("name");
            avatarUrl=user.getString("avatar_url");
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getContent() {
        return content;
    }
    public String getAvatarUrl()
    {
        return avatarUrl;
    }
}
