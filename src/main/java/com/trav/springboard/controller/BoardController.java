package com.trav.springboard.controller;

import com.trav.springboard.dto.BoardDTO;
import com.trav.springboard.dto.PageRequestDTO;
import com.trav.springboard.entity.BoardCategory;
import com.trav.springboard.service.BoardService;
import lombok.extern.log4j.Log4j2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@Log4j2
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @ModelAttribute("boardCategorys")
    public BoardCategory[] boardCategories() {
        return BoardCategory.values();
    }

    @ModelAttribute("boardCategoryCnt")
    public Map<BoardCategory,Long> boardCategoryCnt(){
        return boardService.getBoardCategory();
    }



    @GetMapping("/list")
    public void getList(@ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {

        model.addAttribute("result", boardService.getList(requestDTO));
    }

    @GetMapping("/register")
    public void getRegister(Model model) {

        model.addAttribute("boardDTO", new BoardDTO());
    }

    @PostMapping("/register")
    public String postRegister(BoardDTO boardDTO, RedirectAttributes redirectAttributes, Model model) {


        //검증 오류 결과 보관
        Map<String, String> errors = new HashMap<>();

        //검증 로직
        if(!StringUtils.hasText(boardDTO.getTitle())){
            errors.put("title","제목은 필수입니다");
        }
        if(boardDTO.getBoardCategory() == null){
            errors.put("boardCategory", "카테고리를 선택은 필수입니다.");
        }

        if (!errors.isEmpty()) {
            log.info("errors={}",errors);
            model.addAttribute("errors", errors);

            return "/board/register";
        }


        boardService.register(boardDTO);
        redirectAttributes.addFlashAttribute("msg", "게시글이 정상적으로 등록됐습니다.");

        return "redirect:/board/list";
    }

    @GetMapping({"/read", "/modify"})
    public void getRead(Long bno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {


        model.addAttribute("boardDTO", boardService.read(bno));
    }


    @PostMapping("/modify")
    public String modify(BoardDTO boardDTO, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {

        boardService.modify(boardDTO);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("bno", boardDTO.getBno());

        return "redirect:/board/read";
    }

    @GetMapping("/remove")
    public String remove(Long bno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {

        boardService.remove(bno);

        redirectAttributes.addAttribute("page", requestDTO.getPage());

        return "redirect:/board/list";
    }
}
