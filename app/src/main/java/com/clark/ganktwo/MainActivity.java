package com.clark.ganktwo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.clark.ganktwo.fragment.AllFragment;
import com.clark.ganktwo.fragment.AndroidFragment;
import com.clark.ganktwo.fragment.BaseFragment;
import com.clark.ganktwo.fragment.VideoFragment;
import com.clark.ganktwo.fragment.WelfareFragment;

import java.util.ArrayList;


public class MainActivity extends Activity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private TabLayout mTabLayout;
    private ArrayList<BaseFragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView)findViewById(R.id.navigation);
        mTabLayout = (TabLayout)findViewById(R.id.tab_layout);
        mNavigationView.setNavigationItemSelectedListener(this);

        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.all));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.android));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.xiuxishiping));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.fuli));
        mTabLayout.setOnTabSelectedListener(new TabSelectedListener());

        mFragments.add(new AllFragment());
        mFragments.add(new AndroidFragment());
        mFragments.add(new VideoFragment());
        mFragments.add(new WelfareFragment());
    }

    /**
     * navigation item 点击回调
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Log.e("MainActivity",item.getTitle().toString());
        return true;
    }

    /**
     * tablayout 点击回调
     * 切换fragment
     */

    private class TabSelectedListener implements TabLayout.OnTabSelectedListener {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            int index = mTabLayout.getSelectedTabPosition();

            FragmentManager mFragmentManager = getFragmentManager();
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.main_content,mFragments.get(index));
            mFragmentTransaction.commit();
            Toast.makeText(MainActivity.this, "切换" + index, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }
}
