package com.example.a20201026001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import android.widget.SimpleAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {
    String MainTitle[]={
            "碧螺春  40/L BILUOCHUN TEA",
            "海神    40/L NEPTUNE TEA  ",
            "熟滄觀音 40/L GUANYIN OOLONG TEA",
            "東方美人 50/L Oriental Beauty OOLONG TEA",
            "四季春   40/L FOUR SEASONS OOLONG TEA"
    };

    String SecTitle[]={
            "清幽花果氣息，入喉甘甜沘涼，\n如此清新高雅荼飲，百喝不膩.",
            "以清爽日式煎茶調製，淡淡蜜香\n的黃金茶湯如海波動,獨創茶的\n神話",
            "屬重發酵的茶葉，擁有熟果香及熟\n火香的特性，製程需特別反覆進行\n焙揉，以形成特別的熟韻．",
            "熟果香與蜜糖香在橙紅明亮的茶\n湯，恣意張揚,濃厚香醇口感，\n叫人上癮.",
            "具百花香氣，入口回甘清香，豐富\n不單調，每一口都是小驚喜．"
    };
    List<String> UserSelected=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv=findViewById(R.id.lv1);
        show_data(lv);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //TextView UserTxv=(TextView) view;
        //String  UserChoiceText=UserTxv.getText().toString();

        String  UserChoiceText=MainTitle[i].toString();
        if (UserSelected.contains(UserChoiceText)){
            UserSelected.remove(UserChoiceText);
        }
        else{
            UserSelected.add(UserChoiceText);
        }

        String ShowMsg;
        if (UserSelected.size()>0){
            ShowMsg="你點了:";
            for (String rice:UserSelected){
                ShowMsg+=rice;
                ShowMsg+=" ";
            }
        }
        else {
            ShowMsg="請點餐:";
        }
        TextView txv1=findViewById(R.id.txv1);
        txv1.setText(ShowMsg);
    }


    public void show_data(ListView lv){
        /**因ListView要同時顥示二列文字,所以要用 List方式處理,建一個DataList，用來存放HashMap物件:
         使用List存入HashMap，用來顯示ListView上面的文字。*/

        List<HashMap<String , String>> DataList = new ArrayList<>();

        //陣列MainTitle[]  SecTitle[]

        for(int i = 0 ; i < MainTitle.length ; i++){
            /** 把title , text存入HashMap之中 */
            HashMap<String , String> hashMap = new HashMap<>();
            hashMap.put("TEA_Name" , MainTitle[i]);
            hashMap.put("TEA_Detail" , SecTitle[i]);
            /** 把HashMap存入Datalist之中 */
            DataList.add(hashMap);
        }

        ListAdapter listAdapter = new SimpleAdapter(
                this,
                DataList,
                android.R.layout.simple_list_item_2 ,
                new String[]{"TEA_Name" , "TEA_Detail"} ,
                new int[]{android.R.id.text1 , android.R.id.text2}) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView txv1 = (TextView) view.findViewById(android.R.id.text1);
                TextView txv2 = (TextView) view.findViewById(android.R.id.text2);
                txv1.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.Txv1_Font_Size));
                txv2.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.Txv2_Font_Size));
                txv1.setTextColor(getResources().getColor(R.color.ListViewTxv1,null));
                txv2.setTextColor(getResources().getColor(R.color.ListViewTxv2,null));
                return view;
            }
        };
        // 5個參數 : context , List , layout , key1 & key2 , text1 & text2
        lv.setBackgroundColor(getResources().getColor(R.color.ListViewBackground));
        lv.setAdapter(listAdapter);

    }


}