package sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Computer;

public class ComputerDao {
    private SQLiteDatabase db;

    public ComputerDao(Context context) {
        DBHelper helper = new DBHelper(context);

        db = helper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<Computer>get(String sqlComputer, String ...selectArgs) {
        List<Computer> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sqlComputer, selectArgs);

        while (cursor.moveToNext()) {
            Computer cmp = new Computer();
            cmp.setIdComputer(cursor.getString(cursor.getColumnIndex("idComputer")));
            cmp.setIdCategory(cursor.getString(cursor.getColumnIndex("idCategory")));
            cmp.setName(cursor.getString(cursor.getColumnIndex("name")));
            cmp.setPrice(cursor.getInt(cursor.getColumnIndex("price")));

            list.add(cmp);

        }
        return list;
    }
    public List<Computer> getAll() {
        String sql = "SELECT * FROM Computer";

        return get(sql);
    }

    public Computer getById(String id) {
        String sql = "SELECT * FROM Computer WHERE idComputer = ?";

        List<Computer> list = get(sql, id);
        return list.get(0);
    }

    public long insert(Computer cmp) {
        ContentValues values = new ContentValues();
        values.put("idComputer", cmp.getIdComputer());
        values.put("idCategory", cmp.getIdCategory());
        values.put("name", cmp.getName());
        values.put("price", cmp.getPrice());

        return db.insert("Computer", null, values);
    }

    public long update(Computer cmp) {
        ContentValues values = new ContentValues();
        values.put("idCategory", cmp.getIdCategory());
        values.put("name", cmp.getName());
        values.put("price", cmp.getPrice());

        return db.update("Computer", values, "idComputer", new String[]{cmp.getIdComputer()});
    }

    public int delete(String id) {
        return db.delete("Computer", "idComputer", new String[]{id});
    }

}
