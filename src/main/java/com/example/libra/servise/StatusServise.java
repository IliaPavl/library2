package com.example.libra.servise;

import com.example.libra.domain.Status;
import com.example.libra.reposit.StatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServise {

    private final StatusRepo statusRepo;

    public StatusServise(StatusRepo statusRepo) {
        this.statusRepo = statusRepo;
    }

    public Status findByIdStatus(String idStatus) {
        Status status=new Status();
        if(!idStatus.equals(""))
        if(statusRepo.findById(Long.parseLong(idStatus)).isPresent())
        status=statusRepo.findById(Long.parseLong(idStatus)).get();
        else {
            status.setId((long) 0);
            status.setNameStatus("Любой статус");
        }
        else {
            status.setId((long) 0);
            status.setNameStatus("Любой статус");
        }
        return status;
    }
}
