package vhoang.qlsanbong.myapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import vhoang.qlsanbong.myapp.database.entities.KhungGio;


@Dao
public interface DAO_KHUNGGIO {



    @Insert
    void insertKHUNGGIO(KhungGio khungGio);
    @Update
    void updataKHUNGGIO(KhungGio khungGio);
    @Delete
    void deleteKHUNGGIO(KhungGio khungGio);
    @Query("select * from KhungGio")
    List<KhungGio> getAllkhunggio();

}
