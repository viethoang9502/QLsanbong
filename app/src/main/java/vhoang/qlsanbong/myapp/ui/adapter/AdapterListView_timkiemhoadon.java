package vhoang.qlsanbong.myapp.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import vhoang.qlsanbong.myapp.R;
import vhoang.qlsanbong.myapp.database.entities.PhieuDatSan;
import vhoang.qlsanbong.myapp.database.entities.KhungGio;
import vhoang.qlsanbong.myapp.database.entities.San;
import vhoang.qlsanbong.myapp.database.ApplicationDatabase;
import vhoang.qlsanbong.myapp.ui.InteLoadData;

public class AdapterListView_timkiemhoadon extends BaseAdapter {
    ArrayList<PhieuDatSan> list = new ArrayList<>();
    ArrayList<San> listSan = new ArrayList<>();
    ArrayList<KhungGio> listkhunggio = new ArrayList<>();

    Context context;
    InteLoadData intels;

    ArrayList<HashMap<String,Object>> listHM = new ArrayList<>();
    EditText itemngaythue;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
    int myear,mmonth,mday;
    public AdapterListView_timkiemhoadon(Context context, InteLoadData intels) {
        this.context = context;
        this.intels = intels;
    }

    public void setdata(ArrayList<PhieuDatSan> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;

    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        PhieuDatSan hd = list.get(i);
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_item_hoadon, null);


            viewHolder.itemTenkh = (TextView)  view.findViewById(R.id.item_tenkh);
            viewHolder.itemHdtensan = (TextView)  view.findViewById(R.id.item_hdtensan);
            viewHolder.itemHdkhunggio = (TextView)  view.findViewById(R.id.item_hdkhunggio);
            viewHolder.itemHdtrangthai = (TextView)  view.findViewById(R.id.item_hdtrangthai);
            viewHolder.itemHdtongtien = (TextView)  view.findViewById(R.id.item_hdtongtien);
            viewHolder.itemsua = (ImageView)  view.findViewById(R.id.itemsua);
            viewHolder.itemxoa = (ImageView)  view.findViewById(R.id.itemxoa);
            viewHolder.itemngaythue =(TextView) view.findViewById(R.id.item_ngaythue);


            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.itemTenkh.setText("Khách Hàng:"+hd.getTenkh());
        viewHolder.itemHdtongtien.setText("Tổng Tiền:"+hd.getTongtien());
        ViewHolder finalViewHolder = viewHolder;

        viewHolder.itemxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("DELETE");
                builder.setMessage("Do you want delete ?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ApplicationDatabase.getInstance(context).dao_phieudatsan().deleteHOADON(hd);
                        Toast.makeText((context), "Đã xóa", Toast.LENGTH_SHORT).show();
                        intels.loadData();
                        Toast.makeText(context, " xóa", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("NO",null);
                builder.show();

            }
        });

        return view;

    }
    public class ViewHolder {
        private TextView itemTenkh;
        private TextView itemHdkhunggio;
        private TextView itemHdtensan;
        private TextView itemHdtrangthai;
        private TextView itemHdtongtien;
        private ImageView itemsua;
        private ImageView itemxoa;
        private TextView itemngaythue;




    }


    private ArrayList<HashMap<String,Object>> getdssan(){
        listSan = (ArrayList<San>) ApplicationDatabase.getInstance(context.getApplicationContext()).dao_san().getAllSan();

        ArrayList<HashMap<String,Object>> listhm = new ArrayList<>();
        for(San san : listSan){
            HashMap<String , Object> hs = new HashMap<>();
            hs.put("masan",san.getId_san());
            hs.put("tensan",san.getTensan());
            hs.put("giasan",san.getGiasan());

            listhm.add(hs);

        }
        return listhm;
    }

    private ArrayList<HashMap<String,Object>> getdskhunggio(){
        listkhunggio = (ArrayList<KhungGio>) ApplicationDatabase.getInstance(context.getApplicationContext()).dao_khunggio().getAllkhunggio();

        ArrayList<HashMap<String,Object>> listhmkg = new ArrayList<>();
        for(KhungGio san1 : listkhunggio){
            HashMap<String , Object> hs = new HashMap<>();
            hs.put("makhunggio",san1.getId_khunggio());
            hs.put("tenkhunggio",san1.getKhunggio());

            listhmkg.add(hs);

        }
        return listhmkg;
    }

}
