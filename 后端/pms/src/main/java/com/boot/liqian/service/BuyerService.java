package com.boot.liqian.service;

import com.boot.liqian.model.*;

import java.util.List;

public interface BuyerService {
    public List<Medince> getMedinceINfo(Integer pn);

    public Medince getMedinceById(String id);

    public void addSail(String mi,String buyerId,Integer buyNu);

    public List<Medince> getMedinceByName(Integer pn,String name);

    public List<Sail> getSailByID(String ID);

    public void returnOrder(String buyID,String userID,String medinceID);

    public Buyer getJifenByID(String id);
}
