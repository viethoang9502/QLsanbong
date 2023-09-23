package vhoang.qlsanbong.myapp.database.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "PhieuDatSan", foreignKeys = {
        @ForeignKey(entity = KhungGio.class, parentColumns = "id_khunggio", childColumns = "id_khunggio", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = San.class, parentColumns = "id_san", childColumns = "id_san", onDelete = ForeignKey.CASCADE),
})
public class PhieuDatSan implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id_phieudatsan;
    String tenkh;
    String sdtkh;
    @ColumnInfo(name = "id_san")
    int id_san;
    String tensan;
    String giasan;
    @ColumnInfo(name = "id_khunggio")
    int id_khunggio;
    String khunggio;
    int tongtien;
    String ngaythue;
    String trangthai;

    @ColumnInfo(name = "employee_id")
    Integer employeeId;

    public PhieuDatSan() {
    }

    public PhieuDatSan(String tenkh, String sdtkh, int id_san, String tensan, String giasan, int id_khunggio, String khunggio, int tongtien , String ngaythue, String trangthai,Integer employeeId) {

        this.tenkh = tenkh;
        this.sdtkh = sdtkh;
        this.id_san = id_san;
        this.tensan = tensan;
        this.giasan = giasan;
        this.id_khunggio = id_khunggio;
        this.khunggio = khunggio;
        this.tongtien = tongtien;
        this.ngaythue = ngaythue;
        this.trangthai = trangthai;
        this.employeeId= employeeId;
    }
    public String getSdtkh() {
        return sdtkh;
    }

    public void setSdtkh(String sdtkh) {
        this.sdtkh = sdtkh;
    }

    public String getNgaythue() {
        return ngaythue;
    }

    public void setNgaythue(String ngaythue) {
        this.ngaythue = ngaythue;
    }


    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public int getId_san() {
        return id_san;
    }

    public void setId_san(int id_san) {
        this.id_san = id_san;
    }

    public String getTensan() {
        return tensan;
    }

    public void setTensan(String tensan) {
        this.tensan = tensan;
    }

    public String getGiasan() {
        return giasan;
    }

    public void setGiasan(String giasan) {
        this.giasan = giasan;
    }

    public int getId_khunggio() {
        return id_khunggio;
    }

    public void setId_khunggio(int id_khunggio) {
        this.id_khunggio = id_khunggio;
    }

    public String getKhunggio() {
        return khunggio;
    }

    public void setKhunggio(String khunggio) {
        this.khunggio = khunggio;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
