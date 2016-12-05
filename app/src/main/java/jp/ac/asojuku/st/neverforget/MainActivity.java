package jp.ac.asojuku.st.neverforget;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    final String[] fragments ={
            "jp.ac.asojuku.st.neverforget.MysizeFragment",
            "jp.ac.asojuku.st.neverforget.PropertyFragment",
            "jp.ac.asojuku.st.neverforget.MemorialFragment",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activity_mainからビューを生成
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //drawer_layoutを見つける
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //三本線のアイコンをタップしたらドロワーが開く設定
        //サポートv7ライブラリのActionDrawerToggleのインスタンスを生成
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //ActionBarDrawerインスタンスをDrawerLayoutのsetDrawerListenerメソッドの引数にしてい
        drawer.setDrawerListener(toggle);
        //toggle.syncStateメソッドでDrawerLayoutとシンクロ
        toggle.syncState();

        //NavigationViewを見つけて、setNavigationItemSelectedListener(this)でNavigationView上のメニュー項目が選択された時に
        //このアクティビティがイベントを受け取ることができるようにする
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer.openDrawer(GravityCompat.START);

    }

    //戻るボタンが押された時に呼び出されるが、ドロワーが開いている時は閉じる
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //NavigationViewのメニュー項目が選択された時には、onNavigationItemSelectedメソッド
    //@SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (id){
            case R.id.nav_mysize:
                //Toast.makeText(this, "MysizeFragment", Toast.LENGTH_SHORT).show();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, Fragment.instantiate(MainActivity.this, fragments[0]))
                        .commit();
                break;
            case R.id.nav_property:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, Fragment.instantiate(MainActivity.this, fragments[1]))
                        .commit();
                break;
            case R.id.nav_memorial:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, Fragment.instantiate(MainActivity.this, fragments[2]))
                        .commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
