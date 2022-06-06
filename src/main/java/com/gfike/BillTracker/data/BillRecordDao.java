package com.gfike.BillTracker.data;

import com.gfike.BillTracker.models.BillRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface BillRecordDao extends CrudRepository<BillRecord, UUID> {
}
