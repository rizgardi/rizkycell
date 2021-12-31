package com.example.contoh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.example.contoh.adapter.PenjualanAdapter;
import com.example.contoh.helper.Database;
import com.example.contoh.model.Penjualan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Dashboard extends AppCompatActivity {
    ListView listView;
    AlertDialog.Builder dialog;
    List<Penjualan> lists = new ArrayList<>();
    PenjualanAdapter penjualanAdapter;
    Database db = new Database(this);
    Button tambahPenjualan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        listView = findViewById(R.id.list_item_penjualan);
        penjualanAdapter = new PenjualanAdapter(Dashboard.this,lists);
        listView.setAdapter(penjualanAdapter);
        getData();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {
                final String id = lists.get(position).getId();
                final String produk = lists.get(position).getProduk();
                final String pembeli = lists.get(position).getPembeli();
                final String total = lists.get(position).getTotal();
                final CharSequence[] dialogItem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(Dashboard.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent intent = new Intent(Dashboard.this, PenjualanEditor.class);
                                intent.putExtra("id", id);
                                intent.putExtra("produk", produk);
                                intent.putExtra("pembeli", pembeli);
                                intent.putExtra("total", total);
                                startActivity(intent);
                                break;
                            case 1:
                                Penjualan penjualan = new Penjualan(id,null, produk, pembeli, total);
                                db.deletePenjualan(penjualan);
                                lists.clear();
                                getData();
                                break;

                        }
                    }
                }).show();
                return false;
            }
        });
        tambahPenjualan=findViewById(R.id.tambahPenjualan);
        tambahPenjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this, PenjualanEditor.class);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        ArrayList<HashMap <String,String>> rows = db.getAllPenjualan();
        for(int i=0;i<rows.size();i++) {
            String id = rows.get(i).get("id");
            String tanggal = rows.get(i).get("tanggal");
            String produk = rows.get(i).get("produk");
            String pembeli = rows.get(i).get("pembeli");
            String total = rows.get(i).get("total");

            Penjualan penjualan = new Penjualan(id, tanggal, produk, pembeli, total);
            lists.add(penjualan);
        }
        penjualanAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        lists.clear();
        getData();
    }
}