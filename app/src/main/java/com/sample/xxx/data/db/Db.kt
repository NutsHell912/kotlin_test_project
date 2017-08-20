package com.sample.xxx.data.db

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.sample.xxx.util.Cursors
import com.sample.xxx.data.model.Album as AlbumModel
import com.sample.xxx.data.model.Photo as PhotoModel
import com.sample.xxx.data.model.Post as PostModel
import com.sample.xxx.data.model.User as UserModel


object Db {
    fun insertOrReplace(db: SQLiteDatabase, table: String, values: ContentValues): Long {
        val rowId = db.insertWithOnConflict(table,
                null,
                values,
                SQLiteDatabase.CONFLICT_REPLACE)

        if (rowId == -1L) {
            throw IllegalStateException("Не удалось сохранить $values.")
        }

        return rowId
    }

    fun insertOrThrow(db: SQLiteDatabase, table: String, values: ContentValues): Long {
        val rowId = db.insertOrThrow(table, null, values)

        if (rowId == -1L) {
            throw IllegalStateException("Не удалось сохранить $values.")
        }

        return rowId
    }

    fun clearTable(db: SQLiteDatabase, table: String) {
        db.delete(table, null, null)
    }

    object User {
        const val TABLE_NAME = "user"

        const val COL_USER_ID = "user_id"
        const val COL_USER_NAME = "user_name"
        const val COL_USER_ADDRESS = "user_address"
        const val COL_USER_EMAIL = "user_email"


        const val SQL_CREATE = "CREATE TABLE IF NOT EXISTS ${TABLE_NAME} ( " +
                "${COL_USER_ID} INTEGER NOT NULL PRIMARY KEY, " +
                "${COL_USER_NAME} TEXT NULL, " +
                "${COL_USER_ADDRESS} TEXT NULL , " +
                "${COL_USER_EMAIL} TEXT NULL " +
                ");"

        val PROJECTION = arrayOf(COL_USER_ID,
                COL_USER_NAME,
                COL_USER_ADDRESS,
                COL_USER_EMAIL)

        fun UserModel.toContentValues(values: ContentValues? = null): ContentValues {
            val cv = values ?: ContentValues()
            cv.clear()

            cv.put(COL_USER_ID, id)
            cv.put(COL_USER_NAME, name)
            cv.put(COL_USER_ADDRESS, address)
            cv.put(COL_USER_EMAIL, email)
            return cv
        }

        fun parseCursor(cursor: Cursor): UserModel {
            val userId = Cursors.getInt(cursor, COL_USER_ID)
            val userPhone = Cursors.getString(cursor, COL_USER_NAME)
            val userAddress = Cursors.getString(cursor, COL_USER_ADDRESS)
            val userEmail = Cursors.getString(cursor, COL_USER_EMAIL)

            return UserModel(userId,
                    userPhone,
                    userAddress,
                    userEmail)
        }
    }

    object Album {
        const val TABLE_NAME = "album"

        const val COL_ALBUM_ID = "album_id"
        const val COL_USER_ID = "user_id"
        const val COL_ALBUM_TITLE = "album_titles"


        const val SQL_CREATE = "CREATE TABLE IF NOT EXISTS ${TABLE_NAME} ( " +
                "${COL_ALBUM_ID} INTEGER NOT NULL PRIMARY KEY, " +
                "${COL_USER_ID} INTEGER NOT NULL, " +
                "${COL_ALBUM_TITLE} TEXT NULL " +
                ");"

        val PROJECTION = arrayOf(COL_ALBUM_ID,
                COL_USER_ID,
                COL_ALBUM_TITLE)

        fun AlbumModel.toContentValues(values: ContentValues? = null): ContentValues {
            val cv = values ?: ContentValues()
            cv.clear()

            cv.put(COL_ALBUM_ID, id)
            cv.put(COL_USER_ID, userId)
            cv.put(COL_ALBUM_TITLE, title)
            return cv
        }

        fun parseCursor(cursor: Cursor): AlbumModel {
            val albumId = Cursors.getInt(cursor, COL_ALBUM_ID)
            val userId = Cursors.getInt(cursor, COL_USER_ID)
            val albumTitle = Cursors.getString(cursor, COL_ALBUM_TITLE)

            return AlbumModel(albumId,
                    userId,
                    albumTitle)
        }
    }

    object Post {
        const val TABLE_NAME = "post"

        const val COL_ID = "id"
        const val COL_POST_USER_ID = "post_id"
        const val COL_POST_BODY = "post_body"
        const val COL_POST_TITLE = "post_titles"


        const val SQL_CREATE = "CREATE TABLE IF NOT EXISTS ${TABLE_NAME} ( " +
                "${COL_ID} INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "${COL_POST_USER_ID} INTEGER NOT NULL, " +
                "${COL_POST_BODY} TEXT NOT NULL, " +
                "${COL_POST_TITLE} TEXT NOT NULL " +
                ");"

        val PROJECTION = arrayOf(COL_POST_USER_ID,
                COL_POST_BODY,
                COL_POST_TITLE)

        fun PostModel.toContentValues(values: ContentValues? = null): ContentValues {
            val cv = values ?: ContentValues()
            cv.clear()
            cv.put(COL_POST_USER_ID, userId)
            cv.put(COL_POST_TITLE, title)
            cv.put(COL_POST_BODY, body)
            return cv
        }

        fun parseCursor(cursor: Cursor): PostModel {
            val postId = Cursors.getInt(cursor, COL_POST_USER_ID)
            val postBody = Cursors.getString(cursor, COL_POST_BODY)
            val postTitle = Cursors.getString(cursor, COL_POST_TITLE)

            return PostModel(postId,
                    postTitle,
                    postBody
            )
        }
    }

    object Photo {
        const val TABLE_NAME = "photo"

        const val COL_ID = "id"
        const val COL_PHOTO_ALBUM_ID = "album_id"
        const val COL_PHOTO_URL = "photo_url"
        const val COL_POST_TITLE = "post_titles"


        const val SQL_CREATE = "CREATE TABLE IF NOT EXISTS ${TABLE_NAME} ( " +
                "${COL_ID} INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "${COL_PHOTO_ALBUM_ID} INTEGER NOT NULL, " +
                "${COL_PHOTO_URL} TEXT NOT NULL, " +
                "${COL_POST_TITLE} TEXT NOT NULL " +
                ");"

        val PROJECTION = arrayOf(COL_PHOTO_ALBUM_ID,COL_PHOTO_URL,
                COL_POST_TITLE)

        fun PhotoModel.toContentValues(values: ContentValues? = null): ContentValues {
            val cv = values ?: ContentValues()
            cv.clear()
            cv.put(COL_PHOTO_ALBUM_ID, albumId)
            cv.put(COL_PHOTO_URL, url)
            cv.put(COL_POST_TITLE, title)
            return cv
        }

        fun parseCursor(cursor: Cursor): PhotoModel {
            val albumId = Cursors.getInt(cursor, COL_PHOTO_ALBUM_ID)
            val photoUrl = Cursors.getString(cursor, COL_PHOTO_URL)
            val photoTitle = Cursors.getString(cursor, COL_POST_TITLE)

            return PhotoModel(
                    albumId = albumId,
                    title = photoTitle,
                    url = photoUrl
            )
        }
    }


}