package com.gfike.BillTracker.controllers;

import com.gfike.BillTracker.data.BillDao;
import com.gfike.BillTracker.data.BillRecordDao;
import com.gfike.BillTracker.models.BillRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/viewRecord")
public class ViewRecordController {
    @Autowired
    BillRecordDao billRecordDao;

    @Autowired
    BillDao billDao;

    @GetMapping("/{id}")
    public String viewRecordById (String id, Model model) {
        Optional<BillRecord> br = billRecordDao.findById(UUID.fromString(id));
        model.addAttribute("br", br);
        return "viewRecord";
    }
}
