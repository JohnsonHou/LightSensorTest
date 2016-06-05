package com.johnson.lightsensortest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //创建sensorManager，是系统所有传感器的管理器
    private android.hardware.SensorManager sensorManager;
    private TextView lightLevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化控件和管理器
        lightLevel= (TextView) findViewById(R.id.light_level);
        sensorManager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(sensorManager!=null){
            sensorManager.unregisterListener(sensorEventListener);
        }
    }
    //sensorEventListener监听传感器发出的信号
    private SensorEventListener sensorEventListener=new SensorEventListener() {
        //传感器的数值发生变化时调用此方法
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            //values数组中第一个下标的值就是当前的光照强度
            float value=sensorEvent.values[0];
            lightLevel.setText("Current light level is "+value+" lx");
        }

        //传感器的精度发生变化时调用此方法
        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
}
