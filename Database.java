package com.example.contoh.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.contoh.model.Penjualan;
import com.example.contoh.model.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "penjualan.db";
    private static final int DATABASE_VERSION = 1;
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE, email TEXT, password TEXT);");
        sqLiteDatabase.execSQL("CREATE TABLE penjualan(id INTEGER PRIMARY KEY AUTOINCREMENT, tanggal DATETIME, produk TEXT, pembeli TEXT, total TEXT)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS penjualan;");
        onCreate(sqLiteDatabase);
    }
    public void register(User user){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", user.getName());
        contentValues.put("email", user.getEmail());
        contentValues.put("password", user.getPassword());
        this.getWritableDatabase().insertOrThrow("users","", contentValues);
    }

    public User login (User user){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM users WHERE email='"+user.getEmail()+"' AND password='" + user.getPassword() +"'", null);
        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            user.setId(cursor.getString(0));
            user.setName(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setPassword(cursor.getString(3));
            return user;
        }
        return null;
    }

    public ArrayList<HashMap<String, String>> getAllPenjualan() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String query = "SELECT * FROM penjualan";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("tanggal", cursor.getString(1));
                map.put("produk", cursor.getString(2));
                map.put("pembeli", cursor.getString(3));
                map.put("total", cursor.getString(4));
                list.add(map);
            } while(cursor.moveToNext());
            cursor.close();
        }
        return list;
    }

    public void insertPenjualan(Penjualan penjualan) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();
        String todayAsString = df.format(today);
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO penjualan(tanggal, produk, pembeli, total) VALUES('" + todayAsString + "', '" + penjualan.getProduk() + "','" + penjualan.getPembeli() + "','" + penjualan.getTotal() + "')";
        db.execSQL(query);
    }

    public void updatePenjualan(Penjualan penjualan) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE penjualan SET produk='" + penjualan.getProduk() + "',pembeli='" + penjualan.getPembeli() + "', total='" + penjualan.getTotal() + "' WHERE id='" + penjualan.getId() +"'";
        db.execSQL(query);
    }

    public void deletePenjualan(Penjualan penjualan) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM penjualan WHERE id='" + penjualan.getId() + "'";
        db.execSQL(query);
    }
}
