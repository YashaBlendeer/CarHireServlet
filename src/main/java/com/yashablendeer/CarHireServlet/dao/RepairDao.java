package com.yashablendeer.CarHireServlet.dao;

import com.yashablendeer.CarHireServlet.model.Repair;

import java.util.List;
import java.util.Optional;

public interface RepairDao {
    Optional<Repair> findById(long id);
    List<Repair> findAll();
    Optional<Repair> findRepairByOrderId(long id);
    void deleteById(long id);
    void deleteByOrderId(long id);

}
