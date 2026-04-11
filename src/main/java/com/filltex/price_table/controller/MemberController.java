package com.filltex.price_table.controller;

import com.filltex.price_table.domain.Member;
import com.filltex.price_table.dto.LoginDto;
import com.filltex.price_table.dto.MemberDto;
import com.filltex.price_table.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입 폼 이동
     */
    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "members/member-form";
    }

    /**
     * 회원가입 처리
     */
    @PostMapping("/members/new")
    public String create(MemberDto memberDto) {
        memberService.join(memberDto);
        return "redirect:/login";
    }

    /**
     * 로그인 폼 이동
     */
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "members/login-form";
    }

    /**
     * 로그인 처리
     */
    @PostMapping("/login")
    public String login(LoginDto loginDto, HttpSession session, Model model) {
        Member loginMember = memberService.login(loginDto.getLoginId(), loginDto.getPassword());

        if (loginMember == null) {
            model.addAttribute("errorMessage", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "members/login-form";
        }

        session.setAttribute("loginMember", loginMember);
        return "redirect:/products";
    }

    /**
     *  로그아웃 처리
     */
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
