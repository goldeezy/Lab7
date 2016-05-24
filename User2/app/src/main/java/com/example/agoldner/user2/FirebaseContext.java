package com.example.agoldner.user2;

import com.firebase.client.Firebase;

/**
 * Created by agoldner on 5/23/2016.
 */
public class FirebaseContext extends android.app.Application{
    public void onCreate()
    {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }


}
