package vhoang.qlsanbong.myapp.database.ui.Fragment.thongke;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vhoang.qlsanbong.myapp.R;
import vhoang.qlsanbong.myapp.database.entities.PhieuDatSan;
import vhoang.qlsanbong.myapp.database.ApplicationDatabase;


public class TongDoanhThuFragment extends Fragment {
    List<PhieuDatSan> list = new ArrayList<>();
    private Button btnTongDoanhThu;
    private TextView tvDoanhthu;
    long tongthu = 0;
    public TongDoanhThuFragment() {
        // Required empty public constructor
    }


    public static TongDoanhThuFragment newInstance() {
        TongDoanhThuFragment fragment = new TongDoanhThuFragment();

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
        return inflater.inflate(R.layout.fragment_tong_doanh_thu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        tvDoanhthu = (TextView) view.findViewById(R.id.tv_doanhthu);
        list = ApplicationDatabase.getInstance(getActivity()).dao_phieudatsan().getAllHOADON();
        for (int i = 0; i < list.size(); i++) {

            tongthu = tongthu + list.get(i).getTongtien();

        }
        if (String.valueOf(tongthu).length() > 9){
            Toast.makeText(getActivity(), String.valueOf(tongthu).length()+"" , Toast.LENGTH_SHORT).show();
            tongthu = (long) (Math.floor((tongthu / 1000000000)*100)/100);
            tvDoanhthu.setText(tongthu+" Tỷ");
        }else if (String.valueOf(tongthu).length() > 6){
            tongthu = (long) (Math.floor((tongthu / 1000000)*100)/100);
            tvDoanhthu.setText(tongthu+" Tr");
        }else{
            tvDoanhthu.setText(tongthu+" :VND");
        }
//                tvDoanhThu.setText(String.valueOf(tongthu));



    }
}