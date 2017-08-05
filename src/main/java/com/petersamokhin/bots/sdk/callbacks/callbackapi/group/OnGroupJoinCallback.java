package com.petersamokhin.bots.sdk.callbacks.callbackapi.group;

import com.petersamokhin.bots.sdk.callbacks.Callback;
import org.json.JSONObject;

/**
 * See more: <a href="https://vk.com/dev/callback_api">link</a>.
 */
public interface OnGroupJoinCallback extends Callback {

    void callback (JSONObject object);
}