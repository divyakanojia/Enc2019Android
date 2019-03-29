package com.auribises.enc2019a.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.auribises.enc2019a.model.Util;

/*
Ishants-Macbook-Air:platform-tools ishantkumar$ pwd
/Users/ishantkumar/Library/Android/sdk/platform-tools
Ishants-Macbook-Air:platform-tools ishantkumar$ ./adb root
adbd is already running as root
Ishants-Macbook-Air:platform-tools ishantkumar$ ./adb shell
generic_x86:/ # cd data/data/com.auribises.enc2019a/databases
generic_x86:/data/data/com.auribises.enc2019a/databases # ls
Customers.db Customers.db-journal
127|generic_x86:/data/data/com.auribises.enc2019a/databases # sqlite3 Customers.db
SQLite version 3.19.4 2017-08-18 19:28:12
Enter ".help" for usage hints.
sqlite> .tables
Customer          android_metadata
sqlite> select * from Customer;
1|John|9988776655|john@example.com
2|Fionna|9090909090|fionna@example.com
3|Kia|9090909090|kia@example.com
sqlite> update Customer set name='John Watson' where _ID = 1;
sqlite> select * from Customer;
1|John Watson|9988776655|john@example.com
2|Fionna|9090909090|fionna@example.com
3|Kia|9090909090|kia@example.com
sqlite>


 */

public class MyContentProvider extends ContentProvider {

    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        String tabName = uri.getLastPathSegment();
        int i = sqLiteDatabase.delete(tabName, selection, null);
        return i;

    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        String tabName = uri.getLastPathSegment();
        long id = sqLiteDatabase.insert(tabName,null,values);

        Uri dummyUri = Uri.parse("dummy://someuri/"+id);

        return dummyUri;
    }

    @Override
    public boolean onCreate() {

        dbHelper = new DBHelper(getContext(), Util.DB_NAME, null, Util.DB_VERSION);
        sqLiteDatabase = dbHelper.getWritableDatabase();

        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        String tabName = uri.getLastPathSegment();
        Cursor cursor = sqLiteDatabase.query(tabName,projection,null,null,null,null,null);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        String tabName = uri.getLastPathSegment();
        int i = sqLiteDatabase.update(tabName, values, selection, null);
        return i;
    }

    // Nested Class

    class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            // super call will create a database along with version for us
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Create Table
            db.execSQL(Util.CREATE_TAB_QUERY);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
