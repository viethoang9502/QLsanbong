package vhoang.qlsanbong.myapp.database.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import vhoang.qlsanbong.myapp.R;
import vhoang.qlsanbong.myapp.database.entities.TaiKhoan;
import vhoang.qlsanbong.myapp.database.ui.adapter.AdapterListView_HoaDon;
import vhoang.qlsanbong.myapp.database.entities.PhieuDatSan;
import vhoang.qlsanbong.myapp.database.ApplicationDatabase;

public class ChiTietPhieuDatSan extends AppCompatActivity {

    ListView lisCs;
    FloatingActionButton floatCs;
    EditText tvtentimkiem;
    ImageView imgtimkiem;
    PhieuDatSan hd;

    ArrayList<PhieuDatSan> listhoadon = new ArrayList<>();
    AdapterListView_HoaDon adapterListView_hoaDon;


    TextView soluong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_hoa_don);

        floatCs = (FloatingActionButton) findViewById(R.id.float_cs);
        lisCs = (ListView) findViewById(R.id.lis_cs);
        imgtimkiem = findViewById(R.id.imgtimkiemten);
        tvtentimkiem = findViewById(R.id.lis_edttenhdtimkiem);
        soluong = findViewById(R.id.sonv1);
        loadDataa();
        imgtimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listhoadon = (ArrayList<PhieuDatSan>) ApplicationDatabase.getInstance(ChiTietPhieuDatSan.this).dao_phieudatsan().gettenhoadon(tvtentimkiem.getText().toString());
                loadData();
            }
        });



        soluong.setText(Integer.toString(demsoluong()));
        lisCs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dialog dialog = new Dialog(ChiTietPhieuDatSan.this);
                dialog.setContentView(R.layout.dialog_hoadon_chitiet);
                hd = listhoadon.get(i);
                 List<TaiKhoan> listEmployee = ApplicationDatabase.getInstance(getApplicationContext()).dao_nv().getNVtheoId(hd.getEmployeeId());
                TextView tvtenkh = (TextView) dialog.findViewById(R.id.tenchitiet);
                TextView tvsdtkh = (TextView) dialog.findViewById(R.id.sdtchitiet);
                TextView tvngaythue = (TextView) dialog.findViewById(R.id.ngaythuechitiet);
                TextView khunggio = (TextView) dialog.findViewById(R.id.khunggiochitiet);
                TextView tensan = (TextView) dialog.findViewById(R.id.tensanchitiet);
                TextView tongtien = (TextView) dialog.findViewById(R.id.tongtienchitiet);
                TextView trangthai = (TextView) dialog.findViewById(R.id.trangthaichitiet);
                TextView tvEmployee = (TextView) dialog.findViewById(R.id.tvEmployee);
                tvtenkh.setText(hd.getTenkh());
                tvsdtkh.setText(hd.getSdtkh());
                tvngaythue.setText(hd.getNgaythue());
                khunggio.setText(hd.getKhunggio());
                tensan.setText(hd.getTensan());
                trangthai.setText(hd.getTrangthai());
                tongtien.setText(String.valueOf(hd.getTongtien()));
                if (listEmployee.size()!=0){
                    tvEmployee.setText(listEmployee.get(0).getTen_NV());
                }
                Toast.makeText(ChiTietPhieuDatSan.this, "Bạn đang xem hóa đơn khách hàng: "+ hd.getTenkh(), Toast.LENGTH_SHORT).show();
                dialog.show();
            }

        });

        loadDataa();
        floatCs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChiTietPhieuDatSan.this, ThemHoaDonActivity.class));

            }
        });

    }

    public void loadData() {
        adapterListView_hoaDon = new AdapterListView_HoaDon(ChiTietPhieuDatSan.this,this::loadData);
        adapterListView_hoaDon.setdata(listhoadon);
        lisCs.setAdapter(adapterListView_hoaDon);
    }
    public void loadDataa() {
        listhoadon = (ArrayList<PhieuDatSan>) ApplicationDatabase.getInstance(ChiTietPhieuDatSan.this).dao_phieudatsan().getAllHOADON();
        adapterListView_hoaDon = new AdapterListView_HoaDon(ChiTietPhieuDatSan.this,this::loadDataa);
        adapterListView_hoaDon.setdata(listhoadon);
        lisCs.setAdapter(adapterListView_hoaDon);
    }
    public int demsoluong(){
        int x = 0;
        listhoadon =(ArrayList<PhieuDatSan>) ApplicationDatabase.getInstance(ChiTietPhieuDatSan.this).dao_phieudatsan().getAllHOADON();
        for(int i  = 0 ; i<listhoadon.toArray().length;i++){
            x = i+1;
        }
        return x;

    }
}