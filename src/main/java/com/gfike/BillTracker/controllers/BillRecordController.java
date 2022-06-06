package com.gfike.BillTracker.controllers;

import com.gfike.BillTracker.data.BillDao;
import com.gfike.BillTracker.data.BillRecordDao;
import com.gfike.BillTracker.models.Bill;
import com.gfike.BillTracker.models.BillRecord;
import com.gfike.BillTracker.models.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/newRecord")
public class BillRecordController {

    @Autowired
    BillDao billDao;

    @Autowired
    BillRecordDao billRecordDao;

    @GetMapping
    public String billRecordGet (Model model) {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();
        return "newRecord";
    }

    @PutMapping
    public String billRecordPut (Model model, HttpServletRequest request) {
        int month = Integer.parseInt(request.getParameter("month"));
        int year = Integer.parseInt(request.getParameter("year"));

        List<Bill> bills = billDao.findAll();
        Set<Bill> billSet = Tools.listToSet(bills);
        BillRecord br = new BillRecord(month, year, billSet);
        billRecordDao.save(br);
        return String.format("redirect:/viewRecord/%s", br.getId());
    }
}
