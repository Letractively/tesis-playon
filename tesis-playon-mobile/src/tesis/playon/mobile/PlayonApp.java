package tesis.playon.mobile;

import android.app.Application;

public class PlayonApp extends Application {

    private static PlayonApp instance;

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
    
    public static PlayonApp getInstance(){
	return instance;
    }
    
}