package com.yashablendeer.CarHireServlet.dao;

import com.yashablendeer.CarHireServlet.model.Repair;

import java.util.List;

public interface RepairDao {
    Repair findById(long id);
    List<Repair> findAll();
    Repair findRepairByOrderId(long id);
    void deleteById(long id);
    void deleteByOrderId(long id);

}
