package com.example.roadcondition;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "collect";
    private static final String ARG_PARAM2 = "infor";
    ImageView map;
    boolean num = false;

    // TODO: Rename and change types of parameters
    private String collect;
    private String infor;


    public static ShowDetailFragment newInstance(String collect,String infor) {
        ShowDetailFragment fragment = new ShowDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, collect);
        args.putString(ARG_PARAM2, infor);
        fragment.setArguments(args);
        return fragment;
    }

    public ShowDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            collect = getArguments().getString(ARG_PARAM1);
            infor = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_show_detail, container, false);
        TextView routeName = (TextView) myView.findViewById(R.id.routeName);
        routeName.setText(collect);

        String [] temp = null;
        temp = infor.split("=");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd   hh:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);

        TextView from = (TextView) myView.findViewById(R.id.from);
        TextView to = (TextView) myView.findViewById(R.id.to);
        TextView route = (TextView) myView.findViewById(R.id.route);
        TextView infor = (TextView) myView.findViewById(R.id.infor);
        TextView refresh = (TextView) myView.findViewById(R.id.refresh);
        map = (ImageView) myView.findViewById(R.id.map);
        ViewGroup.LayoutParams ps = map.getLayoutParams();
        ps.width = 500;
        ps.height = 220;
        num = true;
        map.setLayoutParams(ps);

        from.append(temp[0]);
        to.append(temp[1]);
        route.append("\n\t\t\t\t"+temp[2]);
        infor.append("\n\t\t\t\t"+temp[3]);
        refresh.append(str);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams ps = map.getLayoutParams();
                if(num == false) {
                    ps.width = 1080;
                    ps.height = 400;
                    num = true;
                }
                else{
                    ps.width = 500;
                    ps.height = 220;
                    num = false;
                }
                map.setLayoutParams(ps);
            }
        });

        return myView;
    }


}
