package com.trav.springboard.controller;

import com.trav.springboard.dto.BoardDTO;
import com.trav.springboard.dto.PageRequestDTO;
import com.trav.springboard.entity.Board;
import com.trav.springboard.entity.BoardCategory;
import com.trav.springboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/list")
    public void getList(PageRequestDTO pageRequestDTO, Model model) {

        model.addAttribute("result", boardService.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void getRegister(Model model) {

        model.addAttribute("boardDTO", new BoardDTO());
    }

    @PostMapping("/register")
    public String postRegister(BoardDTO boardDTO, RedirectAttributes redirectAttributes) {

        boardService.register(boardDTO);
        redirectAttributes.addFlashAttribute("msg", "게시글이 정상적으로 등록됐습니다.");

        return "redirect:/board/list";
    }

    @GetMapping("/read")
    public void getRead(Long bno, Model model) {

        model.addAttribute("boardDTO", boardService.getBoardDTO(bno));
    }

    @GetMapping("/modify")
    public void getModify(Long bno, Model model) {

        model.addAttribute("boardDTO", boardService.getBoardDTO(bno));
    }
}
