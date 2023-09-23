package vhoang.qlsanbong.myapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import vhoang.qlsanbong.myapp.database.entities.San;
@Dao
public interface DAO_SAN {

    @Insert
    void insertSan(San san);
    @Update
    void updataSan(San san);
    @Delete
    void deleteSan(San san);
    @Query("select * from San")
    List<San> getAllSan();

    @Query("select * from San where id_san = :masan")
    San getSanByMaSan(int masan);
}
