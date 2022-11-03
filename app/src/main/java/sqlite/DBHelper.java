package sqlite;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "ComputerManager";
    public static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlComputer = "CREATE TABLE Computer(idComputer text primary key, name text not null, " +
                "price integer not null, idCategory text , Foreign key (idCategory) references Category(idCategory))";
        sqLiteDatabase.execSQL(sqlComputer);
        String sqlCategory = "CREATE TABLE Category(idCategory text primary key, nameCategory text not null)";
        sqLiteDatabase.execSQL(sqlCategory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sqlComputer = "DROP TABLE IF EXISTS Computer";
        sqLiteDatabase.execSQL(sqlComputer);
        String sqlCategory = "DROP TABLE IF EXISTS Category";
        sqLiteDatabase.execSQL(sqlCategory);
        onCreate(sqLiteDatabase);
    }
}
