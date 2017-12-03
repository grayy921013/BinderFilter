package utils;


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
        file = new File(Const.BLACK_LIST_FOLDER);
        file.mkdirs();
        file = new File(Const.WHITE_LIST_FOLDER);
        file.mkdirs();
        file = new File(Const.LOG_FOLDER);
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

    public List<String> getFilesNameInFolder(String folderName){
        File folder = new File(folderName);
        File[] listOfFiles = folder.listFiles();
        List<String> ret = new ArrayList<>();
        if(listOfFiles != null){
            for(File files : listOfFiles){
                ret.add(files.getName());
            }
        }
        return ret;
    }

    public void createFileNameInFolder(String fileName, String folderName){
        File file = new File(folderName + fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
