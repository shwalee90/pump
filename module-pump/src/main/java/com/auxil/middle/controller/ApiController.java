package com.auxil.middle.controller;

import com.auxil.middle.domain.TbTagBase;
import com.auxil.middle.service.TbService;
import com.auxil.middle.service.TestMod;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RestController
public class ApiController {


    @Lazy
    private final TestMod testMod;
    @Lazy
    private final TbService tbService;


    @GetMapping("/api/allTag")
    public ResponseEntity allTag(){

        List<TbTagBase> allTag = tbService.findTagForBatch();

        return new ResponseEntity<List<TbTagBase>>(allTag , HttpStatus.OK) ;
    }


    @PostMapping("/pump")
    public String pump(HttpServletRequest request, HttpServletResponse response ,Model model){

        String token = request.getParameter("token");

        System.out.println("token : " + token);

        System.out.println("token2 : " +request.getHeader("token"));

        return "redirect:/auth/home";


    }

}
