package com.example.jewel.drawelayout.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jewel.drawelayout.R;

import java.util.ArrayList;

/**
 * Created by Jewel on 12/27/2015.
 */
public class FragDrawer extends Fragment {
    ListView listView;
    ActionBarDrawerToggle drawerToggle;
    ArrayList<String> name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_drawer, container, false);
        listView = (ListView) view.findViewById(R.id.lst);
        return view;
    }

    public void setUp(DrawerLayout layout, Toolbar toolbar) {
        drawerToggle = new ActionBarDrawerToggle(getActivity(), layout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
        layout.setDrawerListener(drawerToggle);
        layout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });
        dataSet();
    }

    private void dataSet() {
        name = new ArrayList<String>();
        name.add("Miran");
        name.add("Sumon");
        name.add("miraj");
        listView.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter {
        LayoutInflater inflater;
        ViewHolder viewHolder;

        public MyAdapter() {
            inflater = LayoutInflater.from(getActivity());
        }

        @Override
        public int getCount() {
            return name.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.row_list, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
                viewHolder.names = (TextView) convertView.findViewById(R.id.names);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.names.setText(name.get(position));

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), name.get(position), Toast.LENGTH_SHORT).show();
                }
            });
            return convertView;
        }

        class ViewHolder {
            ImageView image;
            TextView names;
        }
    }
}
