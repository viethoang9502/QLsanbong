package vhoang.qlsanbong.myapp.ui.Fragment.nhanvien;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import vhoang.qlsanbong.myapp.R;
import vhoang.qlsanbong.myapp.ui.adapter.AdapterListView_HoaDon;
import vhoang.qlsanbong.myapp.database.entities.PhieuDatSan;

public class HoaDonFragment extends Fragment {

     ListView lisCs;
     FloatingActionButton floatCs;
     EditText tvtentimkiem;
     ImageView imgtimkiem;


    PhieuDatSan hd;

    ArrayList<PhieuDatSan> listhoadon = new ArrayList<>();
    AdapterListView_HoaDon adapterListView_hoaDon;



    TextView soluong;


    public HoaDonFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HoaDonFragment newInstance() {
        HoaDonFragment fragment = new HoaDonFragment();

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
        return inflater.inflate(R.layout.fragment_hoa_don, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
      //  loadDataa();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}