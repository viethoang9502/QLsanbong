package vhoang.qlsanbong.myapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import vhoang.qlsanbong.myapp.R;
import vhoang.qlsanbong.myapp.ui.adapter.AdapterListView_HoaDon;
import vhoang.qlsanbong.myapp.database.entities.PhieuDatSan;
import vhoang.qlsanbong.myapp.database.ApplicationDatabase;

public class HoaDonHomNay extends AppCompatActivity {
    ListView lisCs;
    FloatingActionButton floatCs;
    EditText tvtentimkiem;
    ImageView imgtimkiem;
    PhieuDatSan hd;
    TextView tvngaylist;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
    int myear,mmonth,mday;
    ArrayList<PhieuDatSan> listhoadon = new ArrayList<>();
    AdapterListView_HoaDon adapterListView_hoaDon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_listhoadon);
        imgtimkiem = findViewById(R.id.imgtimkiemten);
        tvtentimkiem = findViewById(R.id.lis_edttenhdtimkiem);
        lisCs = (ListView) findViewById(R.id.lis_cs);
        tvngaylist = findViewById(R.id.tvngaylist);
        Calendar calendar = Calendar.getInstance();
        myear = calendar.get(Calendar.YEAR);
        mmonth = calendar.get(Calendar.MONTH);
        mday = calendar.get(Calendar.DAY_OF_MONTH);
        Calendar c = Calendar.getInstance();
        tvngaylist.setText("Ngày: "+simpleDateFormat.format(c.getTime()));

        lisCs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                hd = listhoadon.get(i);

                    Dialog dialog = new Dialog(HoaDonHomNay.this);
                    dialog.setContentView(R.layout.dialog_hoadon_chitiet1);
                    TextView tvtenkh = (TextView) dialog.findViewById(R.id.tenchitiet);
                    TextView tvsdtkh = (TextView) dialog.findViewById(R.id.sdtchitiet);
                    TextView tvngaythue = (TextView) dialog.findViewById(R.id.ngaythuechitiet);
                    TextView khunggio = (TextView) dialog.findViewById(R.id.khunggiochitiet);
                    TextView tensan = (TextView) dialog.findViewById(R.id.tensanchitiet);
                    TextView tongtien = (TextView) dialog.findViewById(R.id.tongtienchitiet);
                    TextView trangthai = (TextView) dialog.findViewById(R.id.trangthaichitiet);
                    tvtenkh.setText(hd.getTenkh());
                    tvsdtkh.setText(hd.getSdtkh());
                    tvngaythue.setText(hd.getNgaythue());
                    khunggio.setText(hd.getKhunggio());
                    tensan.setText(hd.getTensan());
                    tongtien.setText(String.valueOf(hd.getTongtien()));
                    trangthai.setText(hd.getTrangthai());
                    Toast.makeText(HoaDonHomNay.this, "Bạn đang xem hóa đơn khách hàng: " + hd.getTenkh(), Toast.LENGTH_SHORT).show();
                    dialog.show();

            }
        });
        listhoadon = (ArrayList<PhieuDatSan>) ApplicationDatabase.getInstance(HoaDonHomNay.this).dao_phieudatsan().gettten(simpleDateFormat.format(c.getTime()));
        loadData();
    }
    public void loadData() {
        Calendar c = Calendar.getInstance();
        listhoadon = (ArrayList<PhieuDatSan>) ApplicationDatabase.getInstance(HoaDonHomNay.this).dao_phieudatsan().gettten(simpleDateFormat.format(c.getTime()));

        // Chỉ lấy ra các hóa đơn có trạng thái là "Đã Thanh Toán"
        ArrayList<PhieuDatSan> filteredList = new ArrayList<>();
        for (PhieuDatSan hoadon : listhoadon) {
            if (hoadon.getTrangthai().equals("Chưa Thanh Toán")) {
                filteredList.add(hoadon);
            }
        }

        adapterListView_hoaDon = new AdapterListView_HoaDon(HoaDonHomNay.this, this::loadData);
        adapterListView_hoaDon.setdata(filteredList);  // Sử dụng danh sách đã lọc
        lisCs.setAdapter(adapterListView_hoaDon);
    }

}