package vhoang.qlsanbong.myapp.ui.Fragment.thongke;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import vhoang.qlsanbong.myapp.R;
import vhoang.qlsanbong.myapp.database.entities.PhieuDatSan;


public class ThongKeTheoNgayFragment extends Fragment {
//luu huu phuouc
    Button btntungay,btndenngay,btndoanhthu;
    EditText txttungay,txtdenngay;
    TextView tvdoanhthu;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
    int myear,mmonth,mday;
    PhieuDatSan phieuDatSan =new PhieuDatSan();
    long tongThuDate = 0;
    List<PhieuDatSan> listDoanhThu = new ArrayList<>();
    public ThongKeTheoNgayFragment() {
        // Required empty public constructor
    }


    public static ThongKeTheoNgayFragment newInstance() {
        ThongKeTheoNgayFragment fragment = new ThongKeTheoNgayFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thong_ke__doanh_thu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    }
