package com.gfike.BillTracker.controllers;

import com.gfike.BillTracker.data.BillDao;
import com.gfike.BillTracker.data.ExpenditureDao;
import com.gfike.BillTracker.models.Bill;
import com.gfike.BillTracker.models.Expenditure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    BillDao billDao;

    @Autowired
    ExpenditureDao expenditureDao;

    @GetMapping(value = "/")
    public String indexGet(Model model, HttpSession session) {
        String msg = "";
        if (session.getAttribute("msg") != "") {
            msg = (String) session.getAttribute("msg");
            session.removeAttribute("msg");
        }

        List<Expenditure> e = (List<Expenditure>) expenditureDao.findAll();
        model.addAttribute("bills", e);
        model.addAttribute("msg", msg);
        return "index";
    }

    @GetMapping(value = "/newBill")
    public String addBillGet(HttpSession session, Model model) {
        String msg = "";
        if (session.getAttribute("msg") != "") {
            msg = (String) session.getAttribute("msg");
            session.removeAttribute("msg");
        }
        model.addAttribute("msg", msg);
        return "newBill";
    }

    @PostMapping(value = "/newBill")
    public String addBillsPost(HttpServletRequest request, Model model, HttpSession session) {
        try {
            String billName = request.getParameter("billName");
            int billAmount = Integer.parseInt(request.getParameter("billAmount"));
            int dueDate = Integer.parseInt(request.getParameter("dueDate"));
            boolean autopay = Boolean.parseBoolean((request.getParameter("autopay")));
            String autopayMethod = request.getParameter("autopayMethod");
            Bill b = new Bill(billName, billAmount, dueDate, autopay, autopayMethod);
            billDao.save(b);
        } catch (Exception e) {
            String msg = e.toString();
            model.addAttribute("msg", msg);
            session.setAttribute("msg", msg);
            return "redirect:/newBill";
        }
        String msg = "Bill has successfully added to the database!";
        model.addAttribute("msg", msg);
        session.setAttribute("msg", msg);
        return "redirect:/";
    }

    @GetMapping(value = "/newExpenditure")
    public String newExpenditureGet () {
        return "newExpenditure";
    }

    @PostMapping(value="/newExpenditure")
    public String newExpenditurePost (HttpServletRequest request, Model model, HttpSession session) {
        try {
            int yr = Integer.parseInt(request.getParameter("yr"));
            int month = Integer.parseInt(request.getParameter("month"));
            ArrayList <Bill> allBills = (ArrayList<Bill>) billDao.findAll();
            String bills = "";

            for(Bill b : allBills) {
                bills += "(" + b.getId() + "," + "0)";
            }

            Expenditure e = new Expenditure(yr, month, bills);
            expenditureDao.save(e);
        } catch (Exception e) {
            String msg = e.toString();
            model.addAttribute("msg", msg);
            session.setAttribute("msg", msg);
            return "redirect:/newExpenditure";
        }
        String msg = "Expenditure report has successfully added to the database!";
        model.addAttribute("msg", msg);
        session.setAttribute("msg", msg);
        return "redirect:/";
    }

    @GetMapping(value="/editBill")
    public String editBillGet (Model model) {
        ArrayList <Bill> allBills = (ArrayList<Bill>) billDao.findAll();
        model.addAttribute("allBills", allBills);
        return "editBill";
    }
}
