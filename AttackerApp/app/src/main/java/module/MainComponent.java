package module;

import android.app.IntentService;

import com.example.liuhaodong.myapplication.MainActivity;

import dagger.Component;

/**
 * Created by liuhaodong1 on 11/18/17.
 */
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);

    void inject(IntentService intentService);
}