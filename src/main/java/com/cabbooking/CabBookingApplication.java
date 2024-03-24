package com.cabbooking;

import com.cabbooking.utils.CreateDriverTestCases;
import com.cabbooking.utils.CreateUserTestCases;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CabBookingApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(CabBookingApplication.class, args);
        CreateUserTestCases createUserTestCases = applicationContext.getBean(CreateUserTestCases.class);
        CreateDriverTestCases createDriverTestCases = applicationContext.getBean(CreateDriverTestCases.class);
        createDriverTestCases.addDriverTestCase();
        createUserTestCases.addUserTestCases();
    }

}
