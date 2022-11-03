package dgoon.mobile.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import model.Computer;
import sqlite.ComputerDao;

public class addoreditActivity extends AppCompatActivity
    implements View.OnClickListener {

    private EditText edtidComputer, edtidCategory, edtName, edtPrice;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addoredit);

        edtidCategory = findViewById(R.id.edtidCategory);
        edtidComputer = findViewById(R.id.edtidComputer);
        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);

        btnSave =  findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        findViewById(R.id.btnList).setOnClickListener(this);

        readComputer();
    }
    private void readComputer() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if(bundle == null ) {
            return;
        }
        String id = bundle.getString("idComputer");

        ComputerDao dao = new ComputerDao(this);
        Computer cmp = dao.getById(id);

        edtidComputer.setText(cmp.getIdComputer());
        edtidCategory.setText(cmp.getIdCategory());
        edtName.setText(cmp.getName());
        edtPrice.setText(""+ cmp.getPrice());

        btnSave.setText("Update");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                ComputerDao dao = new ComputerDao(this);
                Computer cmp = new Computer();

                cmp.setIdComputer(edtidComputer.getText().toString());
                cmp.setIdCategory(edtidCategory.getText().toString());
                cmp.setName(edtName.getText().toString());
                cmp.setPrice(Integer.parseInt(edtPrice.getText().toString()));

                if(btnSave.getText().equals("Save")) {
                    dao.insert(cmp);
                }else {
                    dao.update(cmp);
                }

                dao.insert(cmp);
                Toast.makeText(this, "New Computer has been saved", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}