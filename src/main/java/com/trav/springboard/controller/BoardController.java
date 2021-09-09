package com.trav.springboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {


    @GetMapping("/list")
    public void getList(String boardname,Model model) {

        if(boardname == null)
            boardname = "게시판";
        model.addAttribute("boardname",boardname);
    }

    @GetMapping("/register")
    public void getRegister(Model model) {


    }
}
