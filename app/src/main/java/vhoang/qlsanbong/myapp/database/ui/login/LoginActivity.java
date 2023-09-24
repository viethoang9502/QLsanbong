package vhoang.qlsanbong.myapp.database.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;

import vhoang.qlsanbong.myapp.R;
import vhoang.qlsanbong.myapp.cache.CacheConstant;
import vhoang.qlsanbong.myapp.cache.SharedPrefUtils;
import vhoang.qlsanbong.myapp.database.entities.Admin;
import vhoang.qlsanbong.myapp.database.entities.TaiKhoan;
import vhoang.qlsanbong.myapp.database.ApplicationDatabase;
import vhoang.qlsanbong.myapp.database.ui.admin.AdminActivity;
import vhoang.qlsanbong.myapp.database.ui.ManHinhDkAdmin;
import vhoang.qlsanbong.myapp.util.GsonUtils;

public class LoginActivity extends AppCompatActivity {
 ImageView fb,gmail,tw;
     AppCompatButton btn_dangNhap;
     AppCompatButton taotkadmin;
    CheckBox ckluuMK;
    private EditText edUser_login,edPass_login;
    private Spinner spinner;
    ArrayList<TaiKhoan> listCheckNV;
    ArrayList<Admin> listCheckHV;
    SharedPreferences sharedPreferences;
    TextView quenmk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        quenmk = findViewById(R.id.textView5);
        quenmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Chức Năng Đang Phát Triển", Toast.LENGTH_SHORT).show();
            }
        });
        spinner = findViewById(R.id.spin_mhlogin_vaitro);
        btn_dangNhap = findViewById(R.id.btn_mhlogin_dangnhap);
        edUser_login = findViewById(R.id.txt_edt_mhlogin_username);
        fb = findViewById(R.id.imageView5);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Chức Năng Đang Phát Triển", Toast.LENGTH_SHORT).show();
            }
        });
        gmail = findViewById(R.id.imageView6);
        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Chức Năng Đang Phát Triển", Toast.LENGTH_SHORT).show();
            }
        });
        tw = findViewById(R.id.imageView7);
        tw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Chức Năng Đang Phát Triển", Toast.LENGTH_SHORT).show();
            }
        });
        edPass_login = findViewById(R.id.txt_edt_mhlogin_pass);
        ckluuMK=findViewById(R.id.chk_luumk);
        taotkadmin = findViewById(R.id.taotkAdmin);
        // Lấy mật khẩu
        sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        edUser_login.setText(sharedPreferences.getString("user_name",""));
        edPass_login.setText(sharedPreferences.getString("pass",""));
        ckluuMK.setChecked(sharedPreferences.getBoolean("ck",false));
        //set item cho spiner vai trò
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Vai_tro, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        int pos = adapter.getPosition(sharedPreferences.getString("tk",""));
        spinner.setSelection(pos);

        taotkadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Đăng Kí Chỉ Dành Cho Admin", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, ManHinhDkAdmin.class);
                startActivity(intent);
            }
        });


        btn_dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    String permission = spinner.getSelectedItem().toString();
                    Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("value", permission);
                    intent.putExtras(bundle);
                    if(permission.equalsIgnoreCase("ADMIN")){
                        String user = edUser_login.getText().toString();
                        String pass = edPass_login.getText().toString();
                        listCheckHV = new ArrayList<>();
                        listCheckHV = (ArrayList<Admin>) ApplicationDatabase.getInstance(LoginActivity.this).dao_admin().CheckLogin(user, pass);
                        if(listCheckHV.size() == 1){
                            String tenHV = listCheckHV.get(0).getHoten();


                            String tensan = listCheckHV.get(0).getTensan();
                            int idHV = listCheckHV.get(0).getId_admin();
                            String userHV = listCheckHV.get(0).getEmail();
                            bundle.putString("tenHV", tenHV);
                            bundle.putString("idHV", String.valueOf(idHV));
                            bundle.putString("tensanHV", tensan);

                            bundle.putString("userHV", userHV);
                            intent.putExtras(bundle);
                            Toast.makeText(LoginActivity.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();

                            SharedPrefUtils.saveData(getApplicationContext(), CacheConstant.CACHE_USER, GsonUtils.toJson(listCheckHV.get(0)));
                            SharedPrefUtils.saveData(getApplicationContext(), CacheConstant.CACHE_USER_IS_ADMIN, true);
                            if (ckluuMK.isChecked()){
                                editor.putString("user_name",edUser_login.getText().toString());
                                editor.putString("pass",edPass_login.getText().toString());
                                editor.putString("tk",permission);
                                editor.putBoolean("ck",true);
                                editor.commit();
                            }else {
                                editor.remove("user_name");
                                editor.remove("pass");
                                editor.remove("tk");
                                editor.remove("ck");
                                editor.commit();
                            }
                            startActivity(intent);
                        } else if(edUser_login.getText().toString().trim().equalsIgnoreCase("1") && edPass_login.getText().toString().trim().equalsIgnoreCase("1")){
                            if (ckluuMK.isChecked()){
                                editor.putString("user_name",edUser_login.getText().toString());
                                editor.putString("pass",edPass_login.getText().toString());
                                editor.putString("tk",permission);
                                editor.putBoolean("ck",true);
                                editor.commit();
                            }else {
                                editor.remove("user_name");
                                editor.remove("pass");
                                editor.remove("tk");
                                editor.remove("ck");
                                editor.commit();
                            }
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
                        }

                    }else if(permission.equals("Nhân Viên")){
                        String user = edUser_login.getText().toString();
                        String pass = edPass_login.getText().toString();
                        listCheckNV = new ArrayList<>();
                        listCheckNV = (ArrayList<TaiKhoan>) ApplicationDatabase.getInstance(LoginActivity.this).dao_nv().CheckLogin(user, pass);
                        if(listCheckNV.size() == 1){
                            int id_nv = listCheckNV.get(0).getId_NV();
                            String tk_nv = listCheckNV.get(0).getTk_NV();
                            String ten_nv = listCheckNV.get(0).getTen_NV();
                            String sdt = listCheckNV.get(0).getSdt_NV();

                            bundle.putInt("idnv",id_nv);
                            bundle.putString("tknv", tk_nv);
                            bundle.putString("tennv", ten_nv);
                            bundle.putString("sdtnv",sdt);

                            intent.putExtras(bundle);
                            Toast.makeText(LoginActivity.this, "Đăng Nhập Thành Công Nhân Viên", Toast.LENGTH_SHORT).show();
                            SharedPrefUtils.saveData(getApplicationContext(), CacheConstant.CACHE_USER, GsonUtils.toJson(listCheckNV.get(0)));
                            SharedPrefUtils.saveData(getApplicationContext(), CacheConstant.CACHE_USER_IS_ADMIN, false);


                            if (ckluuMK.isChecked()){
                                editor.putString("user_name",edUser_login.getText().toString());
                                editor.putString("pass",edPass_login.getText().toString());
                                editor.putString("tk",permission);
                                editor.putBoolean("ck",true);
                                editor.commit();
                            }else {
                                editor.remove("user_name");
                                editor.remove("pass");
                                editor.remove("tk");
                                editor.remove("ck");
                                editor.commit();
                            }
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    Log.d("zzz", "onClick: " + permission);

            }
        });
    }
}