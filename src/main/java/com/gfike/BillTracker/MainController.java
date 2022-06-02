package com.gfike.BillTracker;

import com.gfike.BillTracker.data.BillDao;
import com.gfike.BillTracker.models.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    BillDao billDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexGet(Model model, HttpSession session) {
        String msg = "";
        if (session.getAttribute("msg") != "") {
            msg = (String) session.getAttribute("msg");
            session.removeAttribute("msg");
        }

        List<Bill> bills = billDao.findAll();
        model.addAttribute("bills", bills);
        model.addAttribute("msg", msg);
        return "index";
    }

    @RequestMapping(value = "/newBill", method = RequestMethod.GET)
    public String addBillGet(HttpSession session, Model model) {
        String msg = "";
        if (session.getAttribute("msg") != "") {
            msg = (String) session.getAttribute("msg");
            session.removeAttribute("msg");
        }
        model.addAttribute("msg", msg);
        return "newBill";
    }

    @RequestMapping(value = "/newBill", method = RequestMethod.POST)
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
}
