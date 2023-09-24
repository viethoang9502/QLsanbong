package vhoang.qlsanbong.myapp.database.ui.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import vhoang.qlsanbong.myapp.R;
import vhoang.qlsanbong.myapp.cache.CacheConstant;
import vhoang.qlsanbong.myapp.cache.SharedPrefUtils;
import vhoang.qlsanbong.myapp.database.entities.TaiKhoan;
import vhoang.qlsanbong.myapp.database.ui.adapter.AdapterListView_HoaDon;
import vhoang.qlsanbong.myapp.database.entities.PhieuDatSan;
import vhoang.qlsanbong.myapp.database.entities.KhungGio;
import vhoang.qlsanbong.myapp.database.entities.San;
import vhoang.qlsanbong.myapp.database.ApplicationDatabase;
import vhoang.qlsanbong.myapp.util.GsonUtils;

public class
ThemHoaDonActivity extends AppCompatActivity {
    //wexrdfgvhbuinuytvrerxctyvbun
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
    int myear, mmonth, mday;

    PhieuDatSan hd = new PhieuDatSan();
    ImageView imgngay;
    San san = new San();
    KhungGio kg;
    TextInputEditText tenkh, sdtkh;
    Spinner spnkhunggio;
    Spinner spntensan;
    TextView giasanset;
    Button tongtienbutton;
    TextView texttongtien;
    Spinner spntrangthai1;
    Button btnAddhdd;
    TextView edtngaythue;

    Button btnHuyAddhdd;
    ListView lv_BT;
    ArrayList<San> listsan;
    ArrayList<KhungGio> listkhunggio = new ArrayList<>();
    ArrayList<PhieuDatSan> listhoadon = new ArrayList<>();
    AdapterListView_HoaDon adapterListView_hoaDon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hoa_don);
        imgngay = findViewById(R.id.imgngay);
        lv_BT = findViewById(R.id.lis_BT);
        tenkh = (TextInputEditText) findViewById(R.id.tenkh);
        sdtkh = (TextInputEditText) findViewById(R.id.sdtkh);
        spnkhunggio = (Spinner) findViewById(R.id.spnkhunggio);
        spntensan = (Spinner) findViewById(R.id.spntensan);
        tongtienbutton = (Button) findViewById(R.id.tongtien);
        spntrangthai1 = (Spinner) findViewById(R.id.spntrangthai1);
        btnAddhdd = (Button) findViewById(R.id.btnAddhdd);
        btnHuyAddhdd = (Button) findViewById(R.id.btnHuyAddhdd);
        edtngaythue = (TextView) findViewById(R.id.edtngaythue);
        texttongtien = (TextView) findViewById(R.id.texttongtien);

        AdapterListView_HoaDon adapterListView_hoaDon;
        listsan = new ArrayList<>();
        listkhunggio = new ArrayList<>();
        //set item cho spiner vai trò
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Vai_tro1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spntrangthai1.setAdapter(adapter);


        if (listsan.size() == 0) {
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                    R.array.Vai_tro2, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spntensan.setAdapter(adapter1);
        } else {

            SimpleAdapter sanAdapter = new SimpleAdapter(this, getDSSan(), android.R.layout.simple_list_item_1, new String[]{"tensan"}, new int[]{android.R.id.text1});
            spntensan.setAdapter(sanAdapter);

            spntensan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    HashMap<String, Object> hssan = (HashMap<String, Object>) spntensan.getSelectedItem();
                    String tensan = (String) hssan.get("tensan");
                    String giasan = (String) hssan.get("giasan");

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        if (getDSKhungGio().size() == 0) {
            ArrayAdapter<CharSequence> adapter12 = ArrayAdapter.createFromResource(this,
                    R.array.Vai_tro3, android.R.layout.simple_spinner_item);
            adapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnkhunggio.setAdapter(adapter12);
        } else {
            SimpleAdapter simpleAdapter2 = new SimpleAdapter(this, getDSKhungGio(), android.R.layout.simple_list_item_1, new String[]{"khunggio"}, new int[]{android.R.id.text1});
            spnkhunggio.setAdapter(simpleAdapter2);

            edtngaythue.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    listsan.clear();
                    List<PhieuDatSan> datSan = ApplicationDatabase.getInstance(ThemHoaDonActivity.this).dao_phieudatsan().getSanTrong(listkhunggio.get(spnkhunggio.getSelectedItemPosition()).getKhunggio(), edtngaythue.getText().toString());
                    List<San> san = ApplicationDatabase.getInstance(ThemHoaDonActivity.this).dao_san().getAllSan();
                    for (San item : san) {
                        item.setTrangthai2(false);  // Đặt trạng thái ban đầu thành false

                        for (PhieuDatSan itemDS : datSan) {
                            if (item.getId_san() == itemDS.getId_san()) {
                                item.setTrangthai2(true);  // Nếu có sự khớp, đặt trạng thái thành true
                                break;
                            }
                        }

                        if (!item.getTrangthai2()) {
                            listsan.add(item);
                        }
                    }
                    SimpleAdapter sanAdapter = new SimpleAdapter(ThemHoaDonActivity.this, getDSSan(), android.R.layout.simple_list_item_1, new String[]{"tensan"}, new int[]{android.R.id.text1});
                    spntensan.setAdapter(sanAdapter);
                    sanAdapter.notifyDataSetChanged();
                }
            });
            spnkhunggio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    int position = 0;
                    if (i > 0) {
                        position = spnkhunggio.getSelectedItemPosition();
                    }
                    listsan.clear();
                    List<PhieuDatSan> datSan = ApplicationDatabase.getInstance(ThemHoaDonActivity.this).dao_phieudatsan().getSanTrong(listkhunggio.get(position).getKhunggio(), edtngaythue.getText().toString());
                    List<San> san = ApplicationDatabase.getInstance(ThemHoaDonActivity.this).dao_san().getAllSan();

                    for (San item : san) {
                        item.setTrangthai2(false);  // Đặt trạng thái ban đầu thành false

                        for (PhieuDatSan itemDS : datSan) {
                            if (item.getId_san() == itemDS.getId_san()) {
                                item.setTrangthai2(true);  // Nếu có sự khớp, đặt trạng thái thành true
                                break;
                            }
                        }

                        if (!item.getTrangthai2()) {
                            listsan.add(item);
                        }
                    }
                    SimpleAdapter sanAdapter = new SimpleAdapter(ThemHoaDonActivity.this, getDSSan(), android.R.layout.simple_list_item_1, new String[]{"tensan"}, new int[]{android.R.id.text1});
                    spntensan.setAdapter(sanAdapter);
                    sanAdapter.notifyDataSetChanged();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            imgngay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar calendar = Calendar.getInstance();
                    myear = calendar.get(Calendar.YEAR);
                    mmonth = calendar.get(Calendar.MONTH);
                    mday = calendar.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog dialog = new DatePickerDialog(ThemHoaDonActivity.this
                            , 0, mdatetungay, myear, mmonth, mday);
                    dialog.show();
                }
            });
        }


        tongtienbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                HashMap<String, Object> hs1 = (HashMap<String, Object>) spntensan.getSelectedItem();
                int masan = (int) hs1.get("masan");
                String tensan = (String) hs1.get("tensan");
                String giasan = (String) hs1.get("giasan");
                int giasan1 = Integer.parseInt(giasan);
                texttongtien.setText(String.valueOf(giasan1));
            }
        });


        lv_BT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        btnHuyAddhdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnAddhdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9]" +
                        ")|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)" +
                        "?(\\d{3})(\\s|\\.)?(\\d{3})$";
                String t = tenkh.getText().toString();
                String a = sdtkh.getText().toString();
                String b = edtngaythue.getText().toString();
                String tenkh1 = tenkh.getText().toString();
                String sdtkh1 = sdtkh.getText().toString();
                String edtngay = edtngaythue.getText().toString();
                String trangthai1 = spntrangthai1.getSelectedItem().toString();
                Integer employeeId = null;
                boolean isAdmin = SharedPrefUtils.getBooleanData(getApplicationContext(),CacheConstant.CACHE_USER_IS_ADMIN);
                if (!isAdmin){
                    String employeeData = SharedPrefUtils.getStringData(getApplicationContext(), CacheConstant.CACHE_USER);
                    TaiKhoan employee = GsonUtils.fromJson(employeeData,TaiKhoan.class);
                    employeeId = employee.getId_NV();
                }

                if (getDSSan().size() > 0) {
                    HashMap<String, Object> hs1 = (HashMap<String, Object>) spntensan.getSelectedItem();
                    int masan = (int) hs1.get("masan");
                    String tensan = (String) hs1.get("tensan");
                    String giasan = (String) hs1.get("giasan");
                    int giasan1 = Integer.parseInt(giasan);
                    texttongtien.setText(String.valueOf(giasan1));

                    if (getDSKhungGio().size() > 0) {
                        HashMap<String, Object> hs2 = (HashMap<String, Object>) spnkhunggio.getSelectedItem();
                        int makg = (int) hs2.get("makhunggio");
                        String khunggio = (String) hs2.get("khunggio");

                        listhoadon = (ArrayList<PhieuDatSan>) ApplicationDatabase.getInstance(getApplicationContext()).dao_phieudatsan().checkadd(tensan, edtngay, khunggio);
                        if (listhoadon.isEmpty()) {
                            if (getDSKhungGio().size() == 0) {
                                Toast.makeText(ThemHoaDonActivity.this, "Bạn Cần Thiết Lập Khung Giờ Trước", Toast.LENGTH_SHORT).show();
                            } else if (getDSSan().size() == 0) {
                                Toast.makeText(ThemHoaDonActivity.this, "Bạn Cần Thiết Lập Sân Trước", Toast.LENGTH_SHORT).show();
                            } else if (a.trim().isEmpty()) {
                                Toast.makeText(ThemHoaDonActivity.this, "Bạn chưa nhập tên khách hàng xin hãy nhập?", Toast.LENGTH_SHORT).show();
                            } else if (a.length() < 9 || a.length() > 13 || a.matches(reg)) {
                                Toast.makeText(ThemHoaDonActivity.this, "Không đúng định dạng số điện thoại vui lòng kiểm tra lại!", Toast.LENGTH_SHORT).show();
                            } else if (t.trim().isEmpty()) {
                                Toast.makeText(ThemHoaDonActivity.this, "Bạn chưa nhập số điện thoại  khách hàng xin hãy nhập?", Toast.LENGTH_SHORT).show();
                            } else if (b.trim().isEmpty()) {
                                Toast.makeText(ThemHoaDonActivity.this, "Bạn chưa chọn ngày cho hóa đơn xin hãy chọn?", Toast.LENGTH_SHORT).show();
                            } else {
                                hd = new PhieuDatSan(tenkh1, sdtkh1, masan, tensan, giasan, makg, khunggio, giasan1, edtngay, trangthai1,employeeId);
                                //Add hd vào database
                                ApplicationDatabase.getInstance(getApplicationContext()).dao_phieudatsan().insertHOADON(hd);
                                //View list hd lên màn hình
                                // Khởi tạo Fragment
                                Intent intent = new Intent(ThemHoaDonActivity.this, ChiTietPhieuDatSan.class);
                                startActivity(intent);
                            }
                        } else {
                            Toast.makeText(ThemHoaDonActivity.this, "Sân đã đặt vui lòng kiểm tra lại: ", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ThemHoaDonActivity.this, "Bạn Cần Thiết Lập Khung Giờ", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(ThemHoaDonActivity.this, "Bạn Cần Thiết Lập Sân ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    public void loadData() {

        adapterListView_hoaDon = new AdapterListView_HoaDon(getApplicationContext(), this::loadData);
        adapterListView_hoaDon.setdata(listhoadon);
        lv_BT.setAdapter(adapterListView_hoaDon);
    }

    public void reFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_ly_mhAdmin, fragment);
        transaction.commit();
    }

    private ArrayList<HashMap<String, Object>> getDSKhungGio() {

        listkhunggio = (ArrayList<KhungGio>) ApplicationDatabase.getInstance(this).dao_khunggio().getAllkhunggio();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (KhungGio khungGio : listkhunggio) {
            HashMap<String, Object> hs2 = new HashMap<>();
            hs2.put("makhunggio", khungGio.getId_khunggio());
            hs2.put("khunggio", khungGio.getKhunggio());
            listHM.add(hs2);
        }
        return listHM;

    }

    DatePickerDialog.OnDateSetListener mdatetungay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            myear = i;
            mmonth = i1;
            mday = i2;
            GregorianCalendar c = new GregorianCalendar(myear, mmonth, mday);
            edtngaythue.setText(simpleDateFormat.format(c.getTime()));
        }
    };

    private ArrayList<HashMap<String, Object>> getDSSan() {
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (San san : listsan) {
            HashMap<String, Object> hs1 = new HashMap<>();
            hs1.put("masan", san.getId_san());
            hs1.put("tensan", san.getTensan());
            hs1.put("giasan", san.getGiasan());
            listHM.add(hs1);
        }
        return listHM;

    }
}


