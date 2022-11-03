package dgoon.mobile.sqlite;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import adapter.ComputerAdapter;
import model.Computer;
import sqlite.ComputerDao;
import sqlite.DBHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ComputerAdapter computerAdapter;
    private ListView lvComputer;
    private String idComputer;
    private List<Computer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DBHelper dbHelper = new DBHelper(this);
//        SQLiteDatabase database = dbHelper.getReadableDatabase();
//        database.close();
        findViewById(R.id.btnEdit).setOnClickListener(this);
        findViewById(R.id.btnInsert).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);

        lvComputer = findViewById(R.id.lvComputer);
        ComputerDao dao = new ComputerDao(this);
        list = dao.getAll();
        computerAdapter = new ComputerAdapter(this, list);
        lvComputer.setAdapter(computerAdapter);
        lvComputer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Computer cmp = list.get(i);
                idComputer = cmp.getIdComputer();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onResume() {
        super.onResume();

        ComputerDao dao = new ComputerDao(this);
        List<Computer> updateList = dao.getAll();

        list.clear();
        updateList.forEach(item->list.add(item));

        computerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, addoreditActivity.class);
        switch (view.getId()) {
            case R.id.btnInsert:
                startActivity(intent);
                break;
            case R.id.btnEdit:

                if(idComputer == null) {
                    Toast.makeText(this, "idComputer must be selected", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("idComputer", idComputer);
                intent.putExtra("data",bundle);
                startActivity(intent);
                break;
            case R.id.btnDelete:
                if(idComputer == null) {
                    Toast.makeText(this, "Computer id must be selected", Toast.LENGTH_SHORT).show();
                    return;
                }

                ComputerDao dao = new ComputerDao(this);
                dao.delete(idComputer);
                idComputer = null;
                onResume();
                Toast.makeText(this, "Computer was deleted", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}