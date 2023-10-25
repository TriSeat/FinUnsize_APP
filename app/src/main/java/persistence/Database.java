package persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public static final String DB_NAME = "dbUser";


    public Database(Context context) {
        super(context, "dbUser", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase dbSql) {
        dbSql.execSQL("CREATE TABLE User(email TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbSql, int i, int i1) {
        dbSql.execSQL("DROP TABLE IF EXISTS User");
    }

    public Boolean insertDb(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("email", email);
        values.put("password", password);

        long result = db.insert("User", null, values);

        if(result == -1) return false;
        else return true;
    }

    /*public Boolean insertDb1(String empresa, String segmento,String telefoneemp,String rendaatual,String despesaatual,String vendas) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("empresa", empresa);
        values.put("segmento", segmento);
        values.put("telefoneemp", telefoneemp);
        values.put("rendaatual", rendaatual);
        values.put("despesaatual", despesaatual);
        values.put("vendas", vendas);

        long result = db.insert("User", null, values);

        if(result == -1) return false;
        else return true;
    } */

    public Boolean viewName (String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM  User WHERE email=?", new String[] {email});
        if(cursor.getCount()>0) return true;
        else return false;
    }

    public Boolean viewAll (String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM  User WHERE email=? AND password=?", new String[] {email, password });
        if(cursor.getCount()>0) return true;
        else return false;
    }
}
