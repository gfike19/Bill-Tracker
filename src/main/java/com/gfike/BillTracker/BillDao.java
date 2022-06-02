package com.gfike.BillTracker;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface BillDao extends CrudRepository<Bill, UUID> {
    List<Bill> findAll();
}
