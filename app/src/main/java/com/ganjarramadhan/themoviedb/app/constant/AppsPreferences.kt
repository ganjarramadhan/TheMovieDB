package com.ganjarramadhan.themoviedb.app.constant

import android.content.SharedPreferences

/**
 * Created by ganjarramadhan on 10/04/18.
 * ganjar.ramadhan05@gmail.com
 */
class AppsPreferences(sharedPreferences: SharedPreferences) {

    private var pref: SharedPreferences = sharedPreferences
    private var editor: SharedPreferences.Editor = pref.edit()

    var requestToken: String
    set(value) {
        editor.putString(KEY_REQUEST_TOKEN, value)
        editor.commit()
    }
    get() {
        return pref.getString(KEY_REQUEST_TOKEN, "")
    }

    var sessionId: String
        set(value) {
            editor.putString(KEY_SESSION_ID, value)
            editor.commit()
        }
        get() {
            return pref.getString(KEY_SESSION_ID, "")
        }

    companion object {
        const val KEY_REQUEST_TOKEN = "requestToken"
        const val KEY_SESSION_ID = "sessionId"
    }

}