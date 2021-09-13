package com.trav.springboard.controller;

import com.trav.springboard.dto.MemberDTO;
import com.trav.springboard.entity.Member;
import com.trav.springboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@Log4j2
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public void getLogin() {

    }

    @PostMapping("/login")
    public void postLogin(Member member, RedirectAttributes redirectAttributes) {

    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("member",new MemberDTO());

        return "/member/register";

    }
    @PostMapping("/register")
    public String postRegister(MemberDTO memberDTO, RedirectAttributes redirectAttributes) {


        Long mno = memberService.register(memberDTO);
        redirectAttributes.addFlashAttribute("msg", "회원가입이 정상적으로 처리됐습니다.");

        return "redirect:/board/list";
    }
}
