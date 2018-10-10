package com.stefan.hospitalmanager.config;

import org.springframework.stereotype.Component;

@Component
public class HospitalConfig {
    private static String hospitalName = "FIM Hospital";
    private static String hospitalAdress = "str Polizu nr 1-7, corp F, etaj 3;";
    private static String hospitalCity = "Bucureşti";


    public static String getHospitalName() {
        return hospitalName;
    }

    public static String getHospitalAdress() {
        return hospitalAdress;
    }

    public static String getHospitalCity() {
        return hospitalCity;
    }
}
