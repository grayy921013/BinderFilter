package module;

import com.example.liuhaodong.myapplication.MainTarget;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liuhaodong1 on 11/17/17.
 */

@Module
public class MainModule  {

    private final MainTarget target;

    public MainModule(MainTarget target) {
        this.target = target;
    }

    @Provides
    MainTarget provideMainTarget() {
        return target;
    }
}
