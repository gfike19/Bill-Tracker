package com.gfike.BillTracker.data;

import com.gfike.BillTracker.models.Expenditure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface ExpenditureDao extends CrudRepository<Expenditure, Long> {
}
