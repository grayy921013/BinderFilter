package usecases;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import models.Case;
import utils.Const;
import utils.FileUtil;

/**
 * Created by liuhaodong1 on 12/2/17.
 */

public class GetNewLog {

    FileUtil fileUtil;

    public GetNewLog(){
        this.fileUtil = new FileUtil();
    }

    public ArrayList<String> getNewLogList(){
        List<String> blackList = fileUtil.getFilesNameInFolder(Const.BLACK_LIST_FOLDER);
        List<String> whiteList = fileUtil.getFilesNameInFolder(Const.WHITE_LIST_FOLDER);
        List<String> logList = fileUtil.getFilesNameInFolder(Const.LOG_FOLDER);
        ArrayList<String> overlapping = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        for(String str : blackList) set.add(str);
        for (String str : whiteList) set.add(str);
        for(String log_str : logList){
            if(!set.contains(log_str)) overlapping.add(log_str);
        }
        return overlapping;
    }
}
