package com.example.board.filter;

import java.io.IOException;

import com.example.board.entity.User;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user_info");

        String url = req.getRequestURI();

        if (user == null && url.equals("/board/write")) {
            res.sendRedirect("/signin");
            return;
        }

        if (user != null && url.equals("/signin")) {
            res.sendRedirect("/board/list");
            return;
        }

        chain.doFilter(request, response);
    }

}
