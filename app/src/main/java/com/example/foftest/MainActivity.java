package com.example.foftest;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
                            // マニフェストフォルダにも追加があります
                                            // LocationListenerを追加
public class MainActivity extends Activity implements LocationListener, View.OnClickListener {

                                private Button b1;
                                private Button b2;

                                @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.onLL);
        b2=(Button)findViewById(R.id.offLL);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override   // 位置情報を取得したら呼ばれるメソッド(必須)
    public void onLocationChanged(Location location) {
        //緯度を取得する
        double latitude=location.getLatitude();

        //経度を取得する
        double longitude=location.getLongitude();

        // 緯度の表示(確認用)
        TextView tv_lat = (TextView) findViewById(R.id.Latitude);
        tv_lat.setText("Latitude:"+latitude);

        // 経度の表示(確認用)
        TextView tv_lng = (TextView) findViewById(R.id.Longitude);
        tv_lng.setText("longitude:"+longitude);

    }
    // プロバイダがOFFになったときに呼ばれるメソッド
    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }
    // プロバイダがONになったときに呼ばれるメソッド
    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }
    // 位置情報のステータスが更新されると呼ばれるメソッド
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }
    public void onClick(View v) {

        // LocationManagerを取得
        LocationManager mLocationManager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        switch (v.getId()) {
            case R.id.onLL:

                // Criteriaオブジェクトを生成
                Criteria criteria = new Criteria();


                // GPSの状態を取得
                String gpsStatus = android.provider.Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

                // GPSが無効だった場合
                if (gpsStatus.indexOf("gps", 0) < 0) {

                    // networkで位置情報を取得する
                    criteria.setAccuracy(Criteria.ACCURACY_COARSE);

                }

                // GPSが有効な場合
                else {

                    // GPSで位置情報を取得する
                    criteria.setAccuracy(Criteria.ACCURACY_FINE);

                }
                // ロケーションプロバイダの取得
                String provider = mLocationManager.getBestProvider(criteria, true);
                // 取得したロケーションプロバイダを表示（確認用）
                TextView tv_provider = (TextView) findViewById(R.id.Provider);
                tv_provider.setText("Provider: " + provider);

                // LocationListenerを登録（位置情報を最少0秒の周期で取得することを始める）
                mLocationManager.requestLocationUpdates(provider, 0, 0, this);
                break;
            case R.id.offLL:
                if (mLocationManager != null) {
                    // 位置情報取得終了
                    mLocationManager.removeUpdates(this);
                }
                break;

        }
    }
                                @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}//a
