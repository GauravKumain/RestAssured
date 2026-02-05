package com.api.utils;

import com.dataproviders.api.bean.CreateJobBean;

import java.util.Iterator;

public class demo {
    public static void main(String[] args) {

        Iterator it = CSVReaderUtil.loadCSV("testData/CreateJobData.csv", CreateJobBean.class);

        while(it.hasNext())
        {
            System.out.println(it.next());
        }
    }
}
