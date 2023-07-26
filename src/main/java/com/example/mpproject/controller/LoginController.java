package com.example.mpproject.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.mpproject.dto.LoginDTO;
import com.example.mpproject.service.AccountService;
import com.example.mpproject.service.ResourceService;
import com.example.mpproject.vo.ResourcesVO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author
 * @Date 2023/7/26 10:19
 * @Description
 * @Since version-1.0
 */

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final AccountService accountService;
    private final ResourceService resourceService;

    /**
     * 登录验证
     * @param username
     * @param password
     * @param session
     * @param attributes
     * @return
     */
    @PostMapping("/login")
    public String login(String username, String password, HttpSession session, RedirectAttributes attributes
    , Model model) {
        LoginDTO loginDTO = accountService.login(username, password);
        String error = loginDTO.getError();
        if (StringUtils.isEmpty(error)) {
            session.setAttribute("account", loginDTO.getAccount());
            List<ResourcesVO> resourcesVOS = resourceService.listResourceByRoleId(loginDTO.getAccount().getRoleId());
            model.addAttribute("resources",resourcesVOS);
        } else {
            attributes.addFlashAttribute("error", error);
        }
        return loginDTO.getPath();
    }

    /**
     * 登出
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }


}
