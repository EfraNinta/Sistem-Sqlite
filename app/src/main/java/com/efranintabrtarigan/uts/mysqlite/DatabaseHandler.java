package com.efranintabrtarigan.uts.mysqlite;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favorit.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tb_favorit (nama TEXT)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVervison) {
        String sql = " DROP TABLE IF EXISTS tb_favorit";
        db.execSQL(sql);
        onCreate(db);
    }
    //perintah untuk menambah data ke tabel tb_favorit
    public boolean simpan(String nama){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = String.format("INSERT INTO tb_favorit (nama) VALUES ('%s')", nama);
                db.execSQL(sql);
        return true;
    }
    public List<String> tampilsemua(){
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM tb_favorit";
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            list.add(cursor.getString(0));
        }
        cursor.close();
        return list;
    }
    // Digunakan untuk mengupdate data dalam tabel tb_favorit
    public void update(String oldName, String newName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = String.format("UPDATE tb_favorit SET nama = '%s' WHERE nama = '%s'", newName, oldName);

    }
    // Digunakan untuk menghapus data dari tabel tb_favorit
    public void delete(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = String.format("DELETE FROM tb_favorit WHERE nama = '%s'", name);
        db.execSQL(sql);
    }

}

