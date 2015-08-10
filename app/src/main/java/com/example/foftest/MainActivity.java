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
                            // �}�j�t�F�X�g�t�H���_�ɂ��ǉ�������܂�
                                            // LocationListener��ǉ�
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

    @Override   // �ʒu�����擾������Ă΂�郁�\�b�h(�K�{)
    public void onLocationChanged(Location location) {
        //�ܓx���擾����
        double latitude=location.getLatitude();

        //�o�x���擾����
        double longitude=location.getLongitude();

        // �ܓx�̕\��(�m�F�p)
        TextView tv_lat = (TextView) findViewById(R.id.Latitude);
        tv_lat.setText("Latitude:"+latitude);

        // �o�x�̕\��(�m�F�p)
        TextView tv_lng = (TextView) findViewById(R.id.Longitude);
        tv_lng.setText("longitude:"+longitude);

    }
    // �v���o�C�_��OFF�ɂȂ����Ƃ��ɌĂ΂�郁�\�b�h
    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }
    // �v���o�C�_��ON�ɂȂ����Ƃ��ɌĂ΂�郁�\�b�h
    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }
    // �ʒu���̃X�e�[�^�X���X�V�����ƌĂ΂�郁�\�b�h
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }
    public void onClick(View v) {

        // LocationManager���擾
        LocationManager mLocationManager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        switch (v.getId()) {
            case R.id.onLL:

                // Criteria�I�u�W�F�N�g�𐶐�
                Criteria criteria = new Criteria();


                // GPS�̏�Ԃ��擾
                String gpsStatus = android.provider.Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

                // GPS�������������ꍇ
                if (gpsStatus.indexOf("gps", 0) < 0) {

                    // network�ňʒu�����擾����
                    criteria.setAccuracy(Criteria.ACCURACY_COARSE);

                }

                // GPS���L���ȏꍇ
                else {

                    // GPS�ňʒu�����擾����
                    criteria.setAccuracy(Criteria.ACCURACY_FINE);

                }
                // ���P�[�V�����v���o�C�_�̎擾
                String provider = mLocationManager.getBestProvider(criteria, true);
                // �擾�������P�[�V�����v���o�C�_��\���i�m�F�p�j
                TextView tv_provider = (TextView) findViewById(R.id.Provider);
                tv_provider.setText("Provider: " + provider);

                // LocationListener��o�^�i�ʒu�����ŏ�0�b�̎����Ŏ擾���邱�Ƃ��n�߂�j
                mLocationManager.requestLocationUpdates(provider, 0, 0, this);
                break;
            case R.id.offLL:
                if (mLocationManager != null) {
                    // �ʒu���擾�I��
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
