package usecases;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import models.Case;
import utils.FileUtil;

/**
 * Created by liuhaodong1 on 11/20/17.
 */

public class CaseFromFiles {

    FileUtil fileUtil;

    @Inject
    public CaseFromFiles(FileUtil fileUtil){
        this.fileUtil = fileUtil;
    }

    public Single<List<Case>> getCasesTask(){
        Single<List<Case>> task = Single.fromCallable(()->fileUtil.getCasesFromFolder());
        return task;
    }
//
//    public Observable<Case> getCasesOneByOne(){
//
//    }
}
