package com.gfike.BillTracker.data;

import com.gfike.BillTracker.models.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BillDao extends CrudRepository<Bill, Long> {
    List<Bill> findAll();
}
