package com.example.liuhaodong.myapplication;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by liuhaodong1 on 11/18/17.
 */

public class CaseProvider {


    List<Case> caseList;

    @Inject
    public CaseProvider(){
        caseList = new ArrayList<>();
        caseList.add(case1());
        caseList.add(case2());
    }


    public Case case1(){
        Case aCase = new Case() {

            @Override
            public String name() {
                return "case1";
            }

            @Override
            public String description() {
                return "a phone number";
            }

            @Override
            public String data() {
                return "4123201893";
            }
        };
        return aCase;
    }

    public Case case2(){
        Case aCase = new Case() {

            @Override
            public String name() {
                return "case2";
            }

            @Override
            public String description() {
                return "a phone number with format";
            }

            @Override
            public String data() {
                return "+1-412-320-1893";
            }
        };
        return aCase;
    }
}
