package com.example.katalogfilm.datastore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

object SessionManager {

    private val KEY_USER_ID = intPreferencesKey("user_id")

    suspend fun saveLoginSession(context: Context, userId: Int) {
        context.dataStore.edit { prefs ->
            prefs[KEY_USER_ID] = userId
        }
    }

    suspend fun getUserId(context: Context): Int? {
        return context.dataStore.data
            .map { prefs -> prefs[KEY_USER_ID] }
            .first()
    }

    suspend fun clearSession(context: Context) {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }

    // ✅ Tambahkan ini
    fun getLoginState(context: Context) = context.dataStore.data
        .map { prefs ->
            val userId = prefs[KEY_USER_ID]
            (userId != null) to userId
        }
}
