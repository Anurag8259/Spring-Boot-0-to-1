package com.example.week1hw.Week1.HW;

import org.springframework.stereotype.Component;

@Component
public class CakeBaker {

    private Frosting frosting;
    private Syrup syrup;

    public CakeBaker(Frosting frosting,Syrup syrup){
        this.frosting=frosting;
        this.syrup=syrup;
    }
    void bakeCake(){
        System.out.println("Baking a cake with "+frosting.getFrostingType()+" and "+syrup.getSyrupType());
    }
}
