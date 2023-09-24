package vhoang.qlsanbong.myapp.database.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import vhoang.qlsanbong.myapp.R;
import vhoang.qlsanbong.myapp.database.ui.InteLoadData;
import vhoang.qlsanbong.myapp.database.ui.adapter.AdapterListView_HoaDon;
import vhoang.qlsanbong.myapp.database.entities.PhieuDatSan;
import vhoang.qlsanbong.myapp.database.entities.KhungGio;
import vhoang.qlsanbong.myapp.database.ApplicationDatabase;

public class TimKiem extends AppCompatActivity {
    InteLoadData in;
    ImageView imgngay,imgtimkiem;
    int myear,mmonth,mday;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy/MM/dd");
    TextView tvngaythue;
    ArrayList<PhieuDatSan> listngay = new ArrayList<>();
    AdapterListView_HoaDon adapterListView_hoaDon;
    ListView list;
    ArrayList<KhungGio> listkhunggioo = new ArrayList<>();
    ArrayList<PhieuDatSan> listkhungio = new ArrayList<>();

    Spinner spnkhungio,spntthd;
    ImageView imgkhunggio;
    ImageView imgtthd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tim_kiem);

        imgngay = findViewById(R.id.imgngaytimkiem);
        imgtimkiem = findViewById(R.id.imgtimkiemngay);
        list = findViewById(R.id.lis_timkiemhoadon);
        tvngaythue = findViewById(R.id.tvngaytimkiem);
        spnkhungio =findViewById(R.id.spntimkiemkhunggio);
        imgkhunggio =findViewById(R.id.imgtimkiemkhunggio);
        imgkhunggio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,Object> hs2 = (HashMap<String, Object>) spnkhungio.getSelectedItem();
                int makg1 = (int) hs2.get("makhunggio");
                listngay = (ArrayList<PhieuDatSan>) ApplicationDatabase.getInstance(TimKiem.this).dao_phieudatsan().gettimkiemkhunggio(makg1);
                loadData();
            }
        });

        SimpleAdapter simpleAdapter2 = new SimpleAdapter(TimKiem.this, getDSKhungGio(), android.R.layout.simple_list_item_1, new String[]{"khunggio"}, new int[]{android.R.id.text1});
        spnkhungio.setAdapter(simpleAdapter2);


        imgtimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TimKiem.this, "da click vao ", Toast.LENGTH_SHORT).show();
                String a = tvngaythue.getText().toString();
                listngay =(ArrayList<PhieuDatSan>) ApplicationDatabase.getInstance(TimKiem.this).dao_phieudatsan().gettten(a);
                loadData();
            }
        });
        imgngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                myear = calendar.get(Calendar.YEAR);
                mmonth = calendar.get(Calendar.MONTH);
                mday = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(TimKiem.this,0,mdate,myear,mmonth,mday);
                dialog.show();
            }
        });
    }
    DatePickerDialog.OnDateSetListener mdate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myear = year;
            mmonth = month;
            mday = dayOfMonth;
            GregorianCalendar calendar = new GregorianCalendar(myear,mmonth,mday);
            tvngaythue.setText(simpleDateFormat.format(calendar.getTime()));
        }
    };
    private void loadData() {
        //listhoadon = (ArrayList<PhieuDatSan>) DataBaSe.getInstance(this).dao_phieudatsan().getAllHOADON();
        adapterListView_hoaDon = new AdapterListView_HoaDon(TimKiem.this, this::loadData);
        adapterListView_hoaDon.setdata(listngay);
        list.setAdapter(adapterListView_hoaDon);
    }
    private ArrayList<HashMap<String, Object>> getDSKhungGio() {

        listkhunggioo = (ArrayList<KhungGio>) ApplicationDatabase.getInstance(TimKiem.this).dao_khunggio().getAllkhunggio();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (KhungGio khungGio : listkhunggioo) {
            HashMap<String, Object> hs2 = new HashMap<>();
            hs2.put("makhunggio", khungGio.getId_khunggio());
            hs2.put("khunggio",khungGio.getKhunggio());
            listHM.add(hs2);
        }
        return  listHM;

    }

}
