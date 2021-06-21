package com.example.libra.servise;

import com.example.libra.domain.AgeRate;
import com.example.libra.reposit.AgeRateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgeRateServise {
    @Autowired
    private AgeRateRepo ageRateRepo;

    public AgeRate findByIdAgeRate(String idAgeRAte) {
        AgeRate ageRate=new AgeRate();
        if(!idAgeRAte.equals(""))
        if(ageRateRepo.findById(Long.parseLong(idAgeRAte)).isPresent())
            ageRate=ageRateRepo.findById(Long.parseLong(idAgeRAte)).get();
        else {
            ageRate.setId((long) 0);
            ageRate.setAgeRate("Любой возрастной рейтинг");
        }
        else {
            ageRate.setId((long) 0);
            ageRate.setAgeRate("Любой возрастной рейтинг");
        }
        return ageRate;
    }
}
