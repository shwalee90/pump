package com.auxil.pump.controller;

import com.auxil.pump.aop.RequestWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ApiController {

    @GetMapping("/api")
    public String oauthKakao(
            @RequestParam(value = "code", required = false) String code
            , Model model) {

        BufferedReader br;
        PrintWriter pw;


        System.out.println("#########" + code);


        try {
            // 소켓 생성
            Socket socket = new Socket();
            // 주소 생성
            SocketAddress address = new InetSocketAddress("211.240.38.70", 7007);
            // 연결

            socket.connect(address);

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream());

            pw.println(code);
            pw.flush();


            pw.close();
            br.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return "home";
    }



    @PostMapping ("/auth/home")
    public String authHomeP(){



        return "auth/home";
    }






    @PostMapping("/pump")
    public String pump(HttpServletRequest request, HttpServletResponse response ,Model model){

        String token = request.getParameter("token");

        System.out.println("token : " + token);

        System.out.println("token2 : " +request.getHeader("token"));

      //  request.setAttribute("token",token);

//        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("token" , token);
//
//        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params,headers);
//
//
//        RestTemplate rt = new RestTemplate();
//        ResponseEntity <String> res = rt.exchange("http://localhost:7000/auth/home" , HttpMethod.POST
//        , entity , String.class);
//
//        System.out.println(res);

        return "redirect:/auth/home";


    }





    @PostMapping("/auth/token")
    @ResponseBody
    public Map<String, String> apiTokenPost() {
        final Map<String, String> map = new HashMap<>();


        map.put("message", "Welcome");
        return map;
    }




}
