package com.example.finunsize.presentation.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseImage extends SQLiteOpenHelper {

    public static final String DB_NAME = "dbImg";

    public DatabaseImage(Context context) {
        super(context, "dbImg", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase dbSql) {
        dbSql.execSQL("CREATE TABLE Image(id INTEGER primary key autoincrement, imagePath TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbSql, int i, int i1) {
        dbSql.execSQL("DROP TABLE IF EXISTS Image");
    }

    public Boolean insertImage(String imagePath) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("imagePath", imagePath);

        long result = db.insert("Image", null, values);

        return result != -1;
    }

    public String getImagePath() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Image ORDER BY id DESC LIMIT 1", null);

        String imagePath = null;
        if (cursor.moveToFirst()) {
            int imagePathIndex = cursor.getColumnIndex("imagePath");
            if (imagePathIndex != -1) {
                imagePath = cursor.getString(imagePathIndex);
            }
        }

        cursor.close();
        return imagePath;
    }
}
