package com.example.contoh.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.contoh.R;
import com.example.contoh.model.Penjualan;

import java.util.List;

public class PenjualanAdapter extends BaseAdapter {
    private final Activity activity;
    private LayoutInflater inflater;
    private final List<Penjualan> lists;
    public PenjualanAdapter(Activity activity, List<Penjualan> lists) {
        this.activity = activity;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null && inflater != null) {
            convertView = inflater.inflate(R.layout.list_penjualan, null);
        }

        if (convertView != null) {
            TextView tanggal = convertView.findViewById(R.id.tanggal);
            TextView produk = convertView.findViewById(R.id.produk);

            Penjualan penjualan = lists.get(position);
            tanggal.setText(penjualan.getTanggal());
            produk.setText("Pembelian pulsa "+ penjualan.getProduk()+" oleh "+penjualan.getPembeli()+", total harga = Rp." + penjualan.getTotal());
        }
        return convertView;
    }
}
