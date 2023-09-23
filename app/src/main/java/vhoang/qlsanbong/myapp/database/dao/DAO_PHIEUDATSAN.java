package vhoang.qlsanbong.myapp.database.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import vhoang.qlsanbong.myapp.database.entities.PhieuDatSan;

@Dao
public interface DAO_PHIEUDATSAN {
    @Insert
    void insertHOADON(PhieuDatSan phieuDatSan);
    @Update
    void updataHOADON(PhieuDatSan trangThaiPhieuDatSan);
    @Delete
    void deleteHOADON(PhieuDatSan trangThaiPhieuDatSan);
    @Query("select*from PhieuDatSan where tensan = :ten and ngaythue = :ngay and khunggio = :gio")
    List<PhieuDatSan> checkadd(String ten , String ngay, String gio);
    @Query("select * from PhieuDatSan")
    List<PhieuDatSan> getAllHOADON();
    @Query( "Select * from PhieuDatSan where ngaythue = :ngay and tenkh = :ten")
    List<PhieuDatSan> timkiemlist(String ngay , String ten);

    @Query("Select * from PhieuDatSan where tenkh = :ten")
    List<PhieuDatSan> gettenhoadon(String ten);
    @Query("SELECT * FROM PhieuDatSan WHERE id_khunggio= :khunggio ")
    List<PhieuDatSan> gettimkiemkhunggio(int khunggio);
    @Query("select *from PhieuDatSan where khunggio = :khunggio")
    List<PhieuDatSan> getkhunggio(String khunggio);
    @Query("Select * from PhieuDatSan Where ngaythue = :tenkhachhang")
    List<PhieuDatSan> gettten(String tenkhachhang);
    @Query("SELECT COUNT(*) FROM PhieuDatSan")
    int getCount();

    @Query("select * from PhieuDatSan where ngaythue between :tuNgay and :denNgay")
    List<PhieuDatSan> getDoanhThu(String tuNgay, String denNgay);
    @Query( "Select * from PhieuDatSan where khunggio = :khunggio AND ngaythue = :ngaythue")
    List<PhieuDatSan> getSanTrong(String khunggio, String ngaythue);
    @Query("SELECT * FROM PhieuDatSan WHERE trangthai = 'Đã thanh toán' AND ngaythue = :ngay")
    List<PhieuDatSan> getDaThanhToan(String ngay);


}
