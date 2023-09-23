package vhoang.qlsanbong.myapp.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity(tableName = "TaiKhoan")
public class TaiKhoan {
    @PrimaryKey(autoGenerate = true)
    public int id_NV;
    private String tk_NV;
    public String ten_NV;
    private String mk_NV;
    private String sdt_NV;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] avatar_PT;

    public TaiKhoan() {
    }

    public TaiKhoan(String tk_NV, String mk_NV, String ten_NV, String sdt_NV, byte[] avatar_PT) {
        this.tk_NV = tk_NV;
        this.ten_NV = ten_NV;
        this.mk_NV = mk_NV;
        this.sdt_NV = sdt_NV;
        this.avatar_PT = avatar_PT;
    }

    public int getId_NV() {
        return id_NV;
    }

    public void setId_NV(int id_NV) {
        this.id_NV = id_NV;
    }

    public String getTk_NV() {
        return tk_NV;
    }

    public void setTk_NV(String tk_NV) {
        this.tk_NV = tk_NV;
    }

    public String getTen_NV() {
        return ten_NV;
    }

    public void setTen_NV(String ten_NV) {
        this.ten_NV = ten_NV;
    }

    public String getMk_NV() {
        return mk_NV;
    }

    public void setMk_NV(String mk_NV) {
        this.mk_NV = mk_NV;
    }

    public String getSdt_NV() {
        return sdt_NV;
    }

    public void setSdt_NV(String sdt_NV) {
        this.sdt_NV = sdt_NV;
    }

    public byte[] getAvatar_PT() {
        return avatar_PT;
    }

    public void setAvatar_PT(byte[] avatar_PT) {
        this.avatar_PT = avatar_PT;
    }

    public String toString(){
        return "Họ tên: " + ten_NV + "\n"+ "User: "+ tk_NV+"\n" + "Pass: " +mk_NV;
    }
}