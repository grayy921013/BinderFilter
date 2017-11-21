package utils;

import android.content.Context;
import android.util.Log;

import models.Case;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by liuhaodong1 on 11/19/17.
 */

public class FileUtil {

    @Inject
    public FileUtil(){
        File file = new File(Const.CASE_FOLDER);
        file.mkdirs();
    }

    public File[] getCaseFiles(){
        File folder = new File(Const.CASE_FOLDER);
        File[] listOfFiles = folder.listFiles();
        return listOfFiles;
    }

    public Case getCasesFromAFile(File file, int index){
        Case aCase = null;
        if(file.isFile()){
            aCase = new Case() {
                @Override
                public String name() {
                    return "case_"+String.valueOf(index);
                }

                @Override
                public String description() {
                    return file.getName();
                }

                //todo replace loading from file when invoking the data function
                @Override
                public String data() {
                    return readContentFromFile(file);
                }
            };
        }
        return aCase;
    }

    public List<Case> getCasesFromFolder() {

        File folder = new File(Const.CASE_FOLDER);
        File[] listOfFiles = folder.listFiles();
        List<Case> cases = new ArrayList<>();

        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            final int case_index = i + 1;
            if (file.isFile()) {
                cases.add(new Case() {
                    @Override
                    public String name() {
                        return "case_"+String.valueOf(case_index);
                    }

                    @Override
                    public String description() {
                        return file.getName();
                    }

                    @Override
                    public String data() {
                        return readContentFromFile(file);
                    }
                });
            }
        }
        return cases;
    }

    public String readContentFromFile(File file){

        StringBuilder sb = new StringBuilder();
        try {
            InputStream is = new FileInputStream(file);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();
            while(line != null){
                sb.append(line).append("\n");
                line = buf.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
