package com.androiddeveloper.gpstracker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;

public class MainActivity extends AppCompatActivity {
    private MapView mMapView = null;
    private BaiduMap mBaiduMap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取地图控件引用
        mMapView = findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        //定义Maker坐标点
        LatLng point = new LatLng(41, 121.400244);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_gcoding);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .icon(bitmap)
                .position(point);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
        //重设缩放级别
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder()
                .target(point) //将当前聚合点的位置作为中心点,
//                .zoom((int) mBaiduMap.getMapStatus().zoom + 1) //以当前缩放级别的基础上放大,
                .build()));


        // sourceLatLng待转换坐标
        CoordinateConverter converter = new CoordinateConverter()
                .from(CoordinateConverter.CoordType.GPS)
                .coord(null);
        //desLatLng 转换后的坐标
        LatLng desLatLng = converter.convert();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
}