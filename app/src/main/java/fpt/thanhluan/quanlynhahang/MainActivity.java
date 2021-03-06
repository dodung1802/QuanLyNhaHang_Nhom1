package fpt.thanhluan.quanlynhahang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import fpt.thanhluan.quanlynhahang.Fragment.AddUserFragment;
import fpt.thanhluan.quanlynhahang.Fragment.BanFragment;
import fpt.thanhluan.quanlynhahang.Fragment.ChangePassFragment;
import fpt.thanhluan.quanlynhahang.Fragment.DoAnFragment;
import fpt.thanhluan.quanlynhahang.Fragment.DoUongFragment;
import fpt.thanhluan.quanlynhahang.Fragment.DoanhThuFragment;
import fpt.thanhluan.quanlynhahang.Fragment.HoaDonFragment;
import fpt.thanhluan.quanlynhahang.Fragment.KhachHangFragment;
import fpt.thanhluan.quanlynhahang.Fragment.LoaiDoAnFragment;
import fpt.thanhluan.quanlynhahang.Fragment.LoaiDoUongFragment;
import fpt.thanhluan.quanlynhahang.Fragment.PhongFragment;
import fpt.thanhluan.quanlynhahang.Fragment.TopFragment;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer;
    Toolbar toolbar;
    View mHeaderView;
    TextView edUser;

    FragmentManager manager;
    AddUserFragment addUserFragment;
    ChangePassFragment changePassFragment;
    HoaDonFragment hoaDonFragment;
    PhongFragment phongFragment;
    BanFragment banFragment;
    LoaiDoUongFragment loaiDoUongFragment;
    DoUongFragment doUongFragment;
    LoaiDoAnFragment loaiDoAnFragment;
    DoAnFragment doAnFragment;
    KhachHangFragment khachHangFragment;
    TopFragment topFragment;
    DoanhThuFragment doanhThuFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addUserFragment = new AddUserFragment();
        changePassFragment = new ChangePassFragment();
        hoaDonFragment = new HoaDonFragment();
        phongFragment = new PhongFragment();
        banFragment = new BanFragment();
        loaiDoUongFragment = new LoaiDoUongFragment();
        doUongFragment = new DoUongFragment();
        loaiDoAnFragment = new LoaiDoAnFragment();
        doAnFragment = new DoAnFragment();
        khachHangFragment = new KhachHangFragment();
        topFragment = new TopFragment();
        doanhThuFragment = new DoanhThuFragment();



        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);

        //set toolbar thay th??? actionbar
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.menu);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //d??ng fragment phi???u m?????n l??m home

        manager = getSupportFragmentManager();

        manager.beginTransaction().add(R.id.frg_container_view,hoaDonFragment).commit();

        NavigationView nvView = findViewById(R.id.nvView);

        //show user in header
        mHeaderView = nvView.getHeaderView(0);
        edUser = mHeaderView.findViewById(R.id.tvUser);

        Intent i = getIntent();
        String user = i.getStringExtra("user");

        //admin c?? quy???n add user
        if(user.equalsIgnoreCase("admin")){
            nvView.getMenu().findItem(R.id.sub_AddNhanVien).setVisible(true);
        }
        //??i???u h?????ng navigation

        nvView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_HoaDon:
                        toolbar.setTitle("Qu???n l?? h??a ????n");
                        manager.beginTransaction().replace(R.id.frg_container_view,hoaDonFragment).commit();
                        break;
                    case R.id.nav_Phong:
                        toolbar.setTitle("Qu???n l?? ph??ng");
                        manager.beginTransaction().replace(R.id.frg_container_view,phongFragment).commit();
                        break;
                    case R.id.nav_Ban:
                        toolbar.setTitle("Qu???n l?? b??n");
                        manager.beginTransaction().replace(R.id.frg_container_view,banFragment).commit();
                        break;
                    case R.id.nav_LoaiDoUong:
                        toolbar.setTitle("Qu???n l?? lo???i ????? u???ng");
                        manager.beginTransaction().replace(R.id.frg_container_view,loaiDoUongFragment).commit();
                        break;
                    case R.id.nav_DoUong:
                        toolbar.setTitle("Qu???n l?? ????? u???ng");
                        manager.beginTransaction().replace(R.id.frg_container_view,doUongFragment).commit();
                        break;
                    case R.id.nav_LoaiDoAn:
                        toolbar.setTitle("Qu???n l?? lo???i ????? ??n");
                        manager.beginTransaction().replace(R.id.frg_container_view,loaiDoAnFragment).commit();
                        break;
                    case R.id.nav_DoAn:
                        toolbar.setTitle("Qu???n l?? ????? ??n");
                        manager.beginTransaction().replace(R.id.frg_container_view,doAnFragment).commit();
                        break;
                    case R.id.nav_KhachHang:
                        toolbar.setTitle("Qu???n l?? kh??ch h??ng");
                        manager.beginTransaction().replace(R.id.frg_container_view,khachHangFragment).commit();
                        break;

                    case R.id.sub_Top:
                        toolbar.setTitle("Qu???n l?? s???n ph???m b??n ch???y");
                        manager.beginTransaction().replace(R.id.frg_container_view,topFragment).commit();
                        break;

                    case R.id.sub_DoanhThu:
                        toolbar.setTitle("Qu???n l?? doanh thu");
                        manager.beginTransaction().replace(R.id.frg_container_view,doanhThuFragment).commit();
                        break;

                    case R.id.sub_AddNhanVien:
                        toolbar.setTitle("Qu???n l?? th??m nh??n vi??n");
                        manager.beginTransaction().replace(R.id.frg_container_view,addUserFragment).commit();
                        break;

                    case R.id.sub_DoiMatKhau:
                        toolbar.setTitle("Qu???n l?? ?????i m???t kh???u");
                        manager.beginTransaction().replace(R.id.frg_container_view,changePassFragment).commit();
                        break;

                    case R.id.sub_DangXuat:
                        Toast.makeText(getApplicationContext(), "???? ????ng xu???t", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        finish();
                        break;
                }

                drawer.closeDrawer(nvView);
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            drawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}