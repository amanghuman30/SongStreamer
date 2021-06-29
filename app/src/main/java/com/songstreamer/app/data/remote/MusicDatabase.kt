package com.songstreamer.app.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.songstreamer.app.data.entities.Song
import com.songstreamer.app.util.Constants
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class MusicDatabase {

    private val firestore = FirebaseFirestore.getInstance()
    private val songsCollection = firestore.collection(Constants.FIRESTONE_SONGS_COLLECTION)

    suspend fun getSongs() : List<Song> {
        return try {
            songsCollection.get().await().toObjects(Song::class.java)
        } catch (e : Exception) {
            e.printStackTrace()
            emptyList()
        }

    }

}