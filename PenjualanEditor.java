package com.example.contoh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contoh.helper.Database;
import com.example.contoh.model.Penjualan;

public class PenjualanEditor extends AppCompatActivity {
    EditText produkPenjualan, pembeliPenjualan, totalPenjualan;
    Database db = new Database(this);
    Button savePenjualan;
    String id, produk, pembeli, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjualan_editor);
        produkPenjualan = (EditText)findViewById(R.id.produkPenjualan);
        pembeliPenjualan = (EditText)findViewById(R.id.pembeliPenjualan);
        totalPenjualan = (EditText)findViewById(R.id.totalPenjualan);
        savePenjualan = findViewById(R.id.savePenjualan);

        id = getIntent().getStringExtra("id");
        produk = getIntent().getStringExtra("produk");
        pembeli = getIntent().getStringExtra("pembeli");
        total = getIntent().getStringExtra("total");
        if (id == null || id.equals("")) {
            setTitle("Tambah Penjualan");
        } else {
            setTitle("Edit Penjualan");
            produkPenjualan.setText(produk);
            pembeliPenjualan.setText(pembeli);
            totalPenjualan.setText(total);
        }
        savePenjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id == null || id.equals("")) {
                    Penjualan penjualan = new Penjualan(null,null, produkPenjualan.getText().toString(),pembeliPenjualan.getText().toString(), totalPenjualan.getText().toString());
                    db.insertPenjualan(penjualan);
                    Toast.makeText(getApplicationContext(),"Penjualan tersimpan",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PenjualanEditor.this,Dashboard.class));
                } else {
                    Penjualan penjualan = new Penjualan(id,null, produkPenjualan.getText().toString(),pembeliPenjualan.getText().toString(), totalPenjualan.getText().toString());
                    db.updatePenjualan(penjualan);
                    Toast.makeText(getApplicationContext(),"Penjualan terubah",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PenjualanEditor.this,Dashboard.class));
                }

            }
        });


    }
}