package vhoang.qlsanbong.myapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import vhoang.qlsanbong.myapp.R;
import vhoang.qlsanbong.myapp.ui.adapter.AdapterListView_KhungGio;
import vhoang.qlsanbong.myapp.database.ApplicationDatabase;

public class KhungGio extends AppCompatActivity {
    ListView lisCs;
    FloatingActionButton floatCs;
    TextView khunggio;
    Button huykhunggio;
    Button themkhunggio;
    vhoang.qlsanbong.myapp.database.entities.KhungGio khungGio;
    AdapterListView_KhungGio adapterListView_khungGio;
    ArrayList<vhoang.qlsanbong.myapp.database.entities.KhungGio> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_khung_gio);

        lisCs = (ListView) findViewById(R.id.lis_cs);
        floatCs = (FloatingActionButton)findViewById(R.id.float_cs);
        loadData();
        floatCs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(KhungGio.this);
                dialog.setContentView(R.layout.dialog_add_khunggio);

                khunggio = (TextInputEditText) dialog.findViewById(R.id.khunggio_add);

                themkhunggio = (Button) dialog.findViewById(R.id.btnAddkhunggio);
                huykhunggio = (Button) dialog.findViewById(R.id.btnHuyAddkhunggio);


                themkhunggio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        if (validate()){}
                        String khunggio1 = khunggio.getText().toString();


                        //set thuộc tính HV
                        khungGio  = new vhoang.qlsanbong.myapp.database.entities.KhungGio(khunggio1);
                        //Add hv vào database
                        ApplicationDatabase.getInstance(KhungGio.this).dao_khunggio().insertKHUNGGIO(khungGio);
                        //View list hv lên màn hình
                        loadData();
                        Log.d("zzz", "onViewCreated: " + list.size());
                        dialog.dismiss();

                    }
                });
                huykhunggio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });

    }

    public void loadData() {
        list = (ArrayList<vhoang.qlsanbong.myapp.database.entities.KhungGio>) ApplicationDatabase.getInstance(KhungGio.this).dao_khunggio().getAllkhunggio();
        adapterListView_khungGio = new AdapterListView_KhungGio(KhungGio.this,this::loadData);
        adapterListView_khungGio.setdata(list);
        lisCs.setAdapter(adapterListView_khungGio);
    }
    }
