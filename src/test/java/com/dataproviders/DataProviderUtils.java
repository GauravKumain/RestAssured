package com.dataproviders;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.FakerDataGenerator;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.api.utils.CreateJobBeanMapper.mapper;

public class DataProviderUtils {

    @DataProvider(name = "LoginAPIDataProvider",parallel = true)
    public static Iterator<UserBean> loginAPIDataProvider()
    {
        return CSVReaderUtil.loadCSV("testData/LoginTest.csv",UserBean.class);
    }

    @DataProvider(name = "CreateJobAPIDataProvider",parallel = true)
    public static Iterator<CreateJobPayload> CreateJobAPIDataProvider(){
        Iterator<CreateJobBean> createJobBeanIterator = CSVReaderUtil.loadCSV("testData/CreateJobData.csv", CreateJobBean.class);

        List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();

        while(createJobBeanIterator.hasNext())
        {
            CreateJobBean tempBean =createJobBeanIterator.next();
            CreateJobPayload tempPayload = CreateJobBeanMapper.mapper(tempBean);
            payloadList.add(tempPayload);


        }
        return payloadList.iterator();

    }

    @DataProvider(name = "CreateJobAPIFakerDataProvider",parallel = true)
    public static Iterator<CreateJobPayload> CreateJobFakeDataProvider(){

        Iterator<CreateJobPayload> createJobPayloadIterator = FakerDataGenerator.generateFakeCreateJobData(10);
        return createJobPayloadIterator;

    }

}
