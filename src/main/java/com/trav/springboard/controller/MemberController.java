package com.trav.springboard.controller;

import com.trav.springboard.dto.MemberDTO;
import com.trav.springboard.entity.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("member",new MemberDTO());

        return "/member/register";

    }
    @PostMapping("/register")
    public void postRegister(MemberDTO memberDTO) {

    }
}
