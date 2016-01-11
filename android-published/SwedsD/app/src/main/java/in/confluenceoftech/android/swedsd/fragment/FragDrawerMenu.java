package in.confluenceoftech.android.swedsd.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import in.confluenceoftech.android.swedsd.R;

import java.util.ArrayList;

/**
 * Created by Jewel on 12/18/2015.
 */
public class FragDrawerMenu extends Fragment {
    private View view;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView list;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_drawer_menu, null);
        list = (ListView) view.findViewById(R.id.list);
        return view;
    }

    public void setUp(DrawerLayout drawerLayout, Toolbar toolbar) {
        this.drawerLayout = drawerLayout;
        drawerToggle = new ActionBarDrawerToggle(getActivity(), this.drawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                // TODO Auto-generated method stub
                super.onDrawerOpened(drawerView);

                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                // TODO Auto-generated method stub
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };

        prepareMenu();
        this.drawerLayout.setDrawerListener(drawerToggle);
        this.drawerLayout.post(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                drawerToggle.syncState();
            }
        });
    }

    private void prepareMenu() {
        ArrayList<String> menus = new ArrayList<String>();
        menus.add("Donate");
        menus.add("Notifications");
        menus.add("Contact Us");

        list.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, menus));
    }
}
