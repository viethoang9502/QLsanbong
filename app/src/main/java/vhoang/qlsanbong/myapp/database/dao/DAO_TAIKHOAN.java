package vhoang.qlsanbong.myapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import vhoang.qlsanbong.myapp.database.entities.TaiKhoan;

@Dao
public interface DAO_TAIKHOAN {
    @Insert
    void insertNV(TaiKhoan nv);
    @Update
    void updataNV(TaiKhoan nv);
    @Delete
    void deleteHV(TaiKhoan nv);

    @Query("select * from TaiKhoan")
    List<TaiKhoan> getAllNV();
    @Query("select * from TaiKhoan where tk_NV= :user and mk_NV = :pass")
   List<TaiKhoan> CheckLogin(String user, String pass);

    @Query("select * from TaiKhoan where tk_NV= :user")
    List<TaiKhoan> getNVtheoUser(String user);
    @Query("select * from TaiKhoan where id_NV= :id")
    List<TaiKhoan> getNVtheoId(int id);


    @Query("select * from TaiKhoan where tk_NV= :user")
    List<TaiKhoan> getHVtheoUser(String user);





}
