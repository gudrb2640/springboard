package com.trav.springboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

    @GetMapping({"/", "/list"})
    public void getList(String boardname,Model model) {

        if(boardname == null)
            boardname = "게시판";
        model.addAttribute("boardname",boardname);
    }

    @GetMapping("/login")
    public void getLogin() {

    }

    @PostMapping("/login")
    public void postLogin() {

    }

}
