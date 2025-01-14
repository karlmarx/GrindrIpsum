package com.karlmarxindustries.grindripsum.controller;

import com.karlmarxindustries.grindripsum.dao.DaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GripsumController {
    @Autowired
     DaoImpl dao;

    @GetMapping("/getIpsum")
    public String displayEmployees(HttpServletRequest req, Model model){
        int paragraphNum = Integer.parseInt(req.getParameter("paragraphNum"));
        String result = dao.buildParagraphs(paragraphNum);
        model.addAttribute("result", result);
        return "result";
    }
}
