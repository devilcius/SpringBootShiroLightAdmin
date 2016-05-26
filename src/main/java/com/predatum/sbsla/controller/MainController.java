package com.predatum.sbsla.controller;

import com.predatum.sbsla.entity.Application;
import com.predatum.sbsla.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController
{
    @Autowired
    private ApplicationRepository applicationRepository;

    @RequestMapping("/")
    public String home(Model model)
    {
        Application application = applicationRepository.findOne(1L);
        model.addAttribute("application", application);

        return "welcome";
    }
}
