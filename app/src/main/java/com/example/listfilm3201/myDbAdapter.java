package com.example.listfilm3201;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class myDbAdapter {
    myDbHelper myhelper;

    public myDbAdapter(Context context) {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String name) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();

        Cursor c=dbb.rawQuery("SELECT * FROM "+myDbHelper.TABLE_NAME+" WHERE "+myDbHelper.NAME+" = '"+name+"'", null);
        long id;
        if(c.moveToFirst())
        {
            id=0;
            return id;
        }
        else
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put(myDbHelper.NAME, name);
            id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
            return id;
        }
    }

    public long removeData(String name) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        String selection = myDbHelper.NAME + " LIKE ? ";
        String[] selectionArgs = {name};
        long id = dbb.delete(myDbHelper.TABLE_NAME, selection, selectionArgs);
        return id;
    }

    public Cursor getData() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor list_film = db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME, null);
        return list_film;
    }

    // Konfigurasi SQLite
    static class myDbHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "myTable";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String NAME = "Name";    //Column II
        private static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME +
                " ("+ UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ NAME +" VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Toast.makeText(context,"" + e,
                        Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Toast.makeText(context,"OnUpgrade",
                        Toast.LENGTH_LONG).show();
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                Toast.makeText(context,"" + e,
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}

