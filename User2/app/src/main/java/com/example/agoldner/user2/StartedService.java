package com.example.agoldner.user2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class StartedService extends Service {
    Firebase mref;

    public StartedService() {
    }

    final class MyThread implements Runnable{
        int startId;
        public MyThread(int startId) { this.startId = startId; }

        @Override
        public void run() {
            synchronized (this){
                mref = new Firebase("https://agoldner-110.firebaseio.com/first");
                String greeting [] = { "hey", "there", "from", "user", "2"};
                int i = 0;
                while( i < 5){
                    try{
                        wait(4000);
                        mref.setValue(greeting[i]);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        }
    }

    @Override
    public int onStartCommand( Intent intent, int flags, int startId){
        Toast.makeText(StartedService.this, "Service Started", Toast.LENGTH_SHORT).show();
        Thread thread = new Thread( new MyThread(startId));
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
