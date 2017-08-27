package com.example.demo;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by avakil on 8/22/17.
 */
@org.springframework.stereotype.Service
public class Service {

public int getAmount(int amount){
    if(amount == 22){
        return 22;
    }
    return 0;
}

    @PostConstruct
    private void iniDataForTesting() {

        List<String> items = new ArrayList<>();

        String item1 = "Wood";
        String item2 = "Plastic";

        items.add(item1);
        items.add(item2);

    }
}
