package com.example.wojewodztwaapp;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    public class ViewHolder{
        TextView tv1;
        TextView tv2;
        ImageView im1;
    }
    ViewHolder vh = new ViewHolder();
    LayoutInflater mInflater;
    private ArrayList<Student> students;
    public ListAdapter(Context context, ArrayList<Student> students){
        mInflater = LayoutInflater.from(context);
        this.students=students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if(view == null){
            view = mInflater.inflate(R.layout.wyglad_listy, null);
            vh.tv1 = view.findViewById(R.id.nameTextView);
            vh.tv2 = (TextView) view.findViewById(R.id.avgTextView);
            vh.im1 = (ImageView) view.findViewById(R.id.imageView2);
            view.setTag(vh);
            Student st = students.get(i);
            vh.tv1.setText(st.toString());
            vh.tv2.setText("Srednia = "+Double.toString(st.Srednia));
            vh.im1.setImageResource(st.zdjêcie);
            return view;

        }else{
            vh = (ViewHolder) view.getTag();


        }
        return null;
    }




}
