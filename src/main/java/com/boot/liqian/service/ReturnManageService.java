package com.boot.liqian.service;

import com.boot.liqian.model.Purchase;
import com.boot.liqian.model.Return;

import java.util.List;

public interface ReturnManageService {
    public List<Return> getAllReturnInfo(Integer pn);

    public void deleteReturnById(String id);

    public void deleteReturnsById(String ids[]);

    public List<Return> getReturnByID(Integer pn,String id);
}
