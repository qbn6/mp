package com.example.mpproject.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.mpproject.dto.LoginDTO;
import com.example.mpproject.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @Author
 * @Date 2023/7/26 10:19
 * @Description
 * @Since version-1.0
 */

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class LoginController {

    private final AccountService accountService;

    @PostMapping("/login")
    public String login(String username, String password, HttpSession session, RedirectAttributes attributes) {
        LoginDTO loginDTO = accountService.login(username, password);

        String error = loginDTO.getError();
        if (StringUtils.isEmpty(error)) {
            session.setAttribute("account", loginDTO.getAccount());
        } else {
            attributes.addFlashAttribute("error", error);
        }
        return loginDTO.getPath();
    }
}
