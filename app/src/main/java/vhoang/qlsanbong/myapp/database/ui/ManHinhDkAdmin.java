package vhoang.qlsanbong.myapp.database.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vhoang.qlsanbong.myapp.R;
import vhoang.qlsanbong.myapp.database.entities.Admin;
import vhoang.qlsanbong.myapp.database.ApplicationDatabase;

public class ManHinhDkAdmin extends AppCompatActivity {

     EditText dkemail;
    EditText dkmatkhau;
    EditText dkrematkhau;
    EditText dkhoten;


     Button btnMhDkDangky, btnhuydk;
 Admin admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky);


        dkemail =  findViewById(R.id.dkemail);
        dkmatkhau =  findViewById(R.id.dkmatkhau);
        dkrematkhau =  findViewById(R.id.dkrematkhau);
        dkhoten =  findViewById(R.id.dkhoten);
        btnMhDkDangky = (Button) findViewById(R.id.btn_mhDk_dangky);
        btnhuydk = findViewById(R.id.btn_huy_dangky);


        btnMhDkDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emaill = dkemail.getText().toString();
                String matkhauu = dkmatkhau.getText().toString();
                String hotenn = dkhoten.getText().toString();

                admin = new Admin(emaill,matkhauu,hotenn,null);
                //Add hv vào database
                ApplicationDatabase.getInstance(ManHinhDkAdmin.this).dao_admin().insertad(admin);
                Toast.makeText(ManHinhDkAdmin.this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnhuydk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ManHinhDkAdmin.this, "Đã Hủy", Toast.LENGTH_SHORT).show();
              finish();
            }
        });

    }
}