package vhoang.qlsanbong.myapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import vhoang.qlsanbong.myapp.database.dao.AdminDAO;
import vhoang.qlsanbong.myapp.database.dao.DAO_PHIEUDATSAN;
import vhoang.qlsanbong.myapp.database.dao.DAO_KHUNGGIO;
import vhoang.qlsanbong.myapp.database.dao.DAO_TAIKHOAN;
import vhoang.qlsanbong.myapp.database.dao.DAO_SAN;
import vhoang.qlsanbong.myapp.database.entities.Admin;
import vhoang.qlsanbong.myapp.database.entities.PhieuDatSan;
import vhoang.qlsanbong.myapp.database.entities.KhungGio;
import vhoang.qlsanbong.myapp.database.entities.TaiKhoan;
import vhoang.qlsanbong.myapp.database.entities.San;


@Database(entities = {Admin.class, TaiKhoan.class, San.class, KhungGio.class, PhieuDatSan.class}, version = 1)
public abstract class ApplicationDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "db.db1";
    private static ApplicationDatabase instance;
    public static synchronized ApplicationDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), ApplicationDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract DAO_SAN dao_san();
    public abstract DAO_KHUNGGIO dao_khunggio();
    public abstract DAO_TAIKHOAN dao_nv();
    public  abstract DAO_PHIEUDATSAN dao_phieudatsan();
    public  abstract AdminDAO dao_admin();

}