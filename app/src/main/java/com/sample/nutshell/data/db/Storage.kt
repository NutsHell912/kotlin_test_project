package com.sample.nutshell.data.db

import android.content.ContentValues
import com.google.gson.Gson
import com.sample.nutshell.data.db.Db.Album.toContentValues
import com.sample.nutshell.data.db.Db.Photo.toContentValues
import com.sample.nutshell.data.db.Db.Post.toContentValues
import com.sample.nutshell.data.db.Db.User.toContentValues
import com.sample.nutshell.data.model.Album
import com.sample.nutshell.data.model.Photo
import com.sample.nutshell.data.model.Post
import com.sample.nutshell.data.model.User
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Storage
@Inject
constructor(private val dbOpenHelper: DbOpenHelper, private val gson: Gson) {

    fun getUsers(): List<User> {
        val db = dbOpenHelper.readableDatabase

        db.query(Db.User.TABLE_NAME, Db.User.PROJECTION,
                null, null, null, null,
                Db.User.COL_USER_ID
        ).use { cursor ->
            val users = ArrayList<User>(cursor.count)

            while (cursor.moveToNext()) {
                val user = Db.User.parseCursor(cursor)
                users.add(user)
            }

            return users
        }
    }

    fun putUsers(users: List<User>) {
        val db = dbOpenHelper.writableDatabase
        db.beginTransaction()

        try {
            db.delete(Db.User.TABLE_NAME, null, null)

            val cv = ContentValues()

            for (user in users) {
                user.toContentValues(cv)
                Db.insertOrReplace(db, Db.User.TABLE_NAME, cv)
            }

            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

    fun getAlbums(userId: Int): List<Album> {
        val db = dbOpenHelper.readableDatabase

        db.query(Db.Album.TABLE_NAME, Db.Album.PROJECTION,
                Db.Album.COL_USER_ID + "=?", arrayOf(userId.toString()), null, null,
                Db.Album.COL_ALBUM_ID
        ).use { cursor ->
            val albums = ArrayList<Album>(cursor.count)

            while (cursor.moveToNext()) {
                val album = Db.Album.parseCursor(cursor)
                albums.add(album)
            }

            return albums
        }
    }

    fun putAlbums(albums: List<Album>) {
        val db = dbOpenHelper.writableDatabase
        db.beginTransaction()

        try {
            db.delete(Db.Album.TABLE_NAME, null, null)

            val cv = ContentValues()

            for (album in albums) {
                album.toContentValues(cv)
                Db.insertOrReplace(db, Db.Album.TABLE_NAME, cv)
            }

            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

    fun getPhotos(albumId: Int): List<Photo> {
        val db = dbOpenHelper.readableDatabase

        db.query(Db.Photo.TABLE_NAME, Db.Photo.PROJECTION,
                Db.Photo.COL_PHOTO_ALBUM_ID + "=?", arrayOf(albumId.toString()), null, null,
                Db.Photo.COL_ID
        ).use { cursor ->
            val photos = ArrayList<Photo>(cursor.count)

            while (cursor.moveToNext()) {
                val photo = Db.Photo.parseCursor(cursor)
                photos.add(photo)
            }

            return photos
        }
    }

    fun putPhotos(photos: List<Photo>) {
        val db = dbOpenHelper.writableDatabase
        db.beginTransaction()

        try {
            db.delete(Db.Photo.TABLE_NAME, null, null)

            val cv = ContentValues()

            for (photo in photos) {
                photo.toContentValues(cv)
                Db.insertOrReplace(db, Db.Photo.TABLE_NAME, cv)
            }

            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

    fun getPosts(userId: Int): List<Post> {
        val db = dbOpenHelper.readableDatabase

        db.query(Db.Post.TABLE_NAME, Db.Post.PROJECTION,
                Db.Post.COL_POST_USER_ID + "=?", arrayOf(userId.toString()), null, null,
                Db.Post.COL_ID
        ).use { cursor ->
            val posts = ArrayList<Post>(cursor.count)

            while (cursor.moveToNext()) {
                val post = Db.Post.parseCursor(cursor)
                posts.add(post)
            }

            return posts
        }
    }

    fun putPosts(posts: List<Post>) {
        val db = dbOpenHelper.writableDatabase
        db.beginTransaction()

        try {
            db.delete(Db.Post.TABLE_NAME, null, null)

            val cv = ContentValues()

            for (post in posts) {
                post.toContentValues(cv)
                Db.insertOrReplace(db, Db.Post.TABLE_NAME, cv)
            }

            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }


}
