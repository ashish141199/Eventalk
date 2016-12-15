package in.eventalk.eventalk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FBID = "fbId";
    public static final String COLUMN_FULLNAME = "fullName";
    public static final String COLUMN_EMAIL= "email";
    public static final String COLUMN_DOB = "dob";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_DP = "dp";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_ISVERIFIED = "is_verified";

    public UserDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY " +
                COLUMN_FBID + " TEXT " +
                COLUMN_FULLNAME + " TEXT " +
                COLUMN_EMAIL + " TEXT " +
                COLUMN_DOB + " TEXT " +
                COLUMN_GENDER + " TEXT " +
                COLUMN_DP + " TEXT " +
                COLUMN_LOCATION + " TEXT " +
                COLUMN_ISVERIFIED + " INTEGER " + ");";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_USERS);
        onCreate(sqLiteDatabase);
    }

    //Add a new row to the database
    public void addUser(User u) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, u.getId());
        values.put(COLUMN_FULLNAME, u.getFullName());
        values.put(COLUMN_FBID, (String) u.getFbId());
        values.put(COLUMN_DOB, (String) u.getDob());
        values.put(COLUMN_GENDER, u.getGender());
        values.put(COLUMN_LOCATION, u.getLocation());
        values.put(COLUMN_ISVERIFIED, u.getIsVerified());
        values.put(COLUMN_DP, (String) u.getDp());
        values.put(COLUMN_EMAIL, u.getEmail());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    //Delete a user row from the database
    public void deleteUser(User u) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + "=\"" + u.getEmail() + "\"");
    }

    public String getUserByEmail(String email) {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM users WHERE email = \"" + email + "\"";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            dbString += c.getString(c.getColumnIndex("email"));
        }
        c.close();
        return dbString;
    }

    public Boolean checkUserExists(String email) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM users WHERE email= \"" + email + "\"";
        Cursor c = db.rawQuery(query, null);
        Log.i("loggy", c.toString());
        if (c != null) {
            if (c.getCount() > 0) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }
    //Print out the database as a string
    public String dbToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE 1 ";
        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("email")) != null) {
                dbString += c.getString(c.getColumnIndex("email"));
                dbString += "\n";
            }
        }
        db.close();
        return dbString;
    }


}
