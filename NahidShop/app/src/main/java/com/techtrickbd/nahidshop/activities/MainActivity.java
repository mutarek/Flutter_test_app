package com.techtrickbd.nahidshop.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.techtrickbd.nahidshop.R;
import com.techtrickbd.nahidshop.fragments.DevsFragment;
import com.techtrickbd.nahidshop.fragments.EarnFragment;
import com.techtrickbd.nahidshop.fragments.HomeFragment;
import com.techtrickbd.nahidshop.fragments.ProfileFragment;
import com.techtrickbd.nahidshop.fragments.SupportFragment;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    private CircleImageView imageView;
    private TextView username, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView nav_view = findViewById(R.id.nav_view);
        View headerView = nav_view.getHeaderView(0);
        imageView = headerView.findViewById(R.id.profiel_image_id);
        username = headerView.findViewById(R.id.profile_users_name);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        nav_view.setCheckedItem(R.id.home_id);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.home_id:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                        break;
                    case R.id.earn_ID:
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, new EarnFragment(), "DetailFragment").commit();
                        fragmentTransaction.addToBackStack(null);
                        break;
                    case R.id.profile_id:
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.fragment_container, new ProfileFragment(), "DetailFragment").commit();
                        ft.addToBackStack(null);
                        break;
                    case R.id.exit_id:
                        finish();
                        break;
                    case R.id.contact_id: {
                        FragmentManager fm2 = getSupportFragmentManager();
                        FragmentTransaction ft3 = fm2.beginTransaction();
                        ft3.replace(R.id.fragment_container, new SupportFragment(), "DetailFragment").commit();
                        ft3.addToBackStack(null);
                        break;
                    }
                    case R.id.devs_id:
                        FragmentManager fm2 = getSupportFragmentManager();
                        FragmentTransaction ft3 = fm2.beginTransaction();
                        ft3.replace(R.id.fragment_container, new DevsFragment(), "DetailFragment").commit();
                        ft3.addToBackStack(null);
                        break;

                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
}
