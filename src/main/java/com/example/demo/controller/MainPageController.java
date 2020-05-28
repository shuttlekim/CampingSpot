package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.db.DBManager;
import com.example.demo.vo.CampingSpotVo;
import com.google.gson.Gson;

@RestController
public class MainPageController {

	@RequestMapping("/main_search_camping")
	public String searchCamping(HttpServletRequest request, String keyword) {
		
		
		String str = "";
		
		System.out.println(keyword);
		
		//str = new Gson().toJson(DBManager.search_list(keyword));
		System.out.println(str);
		return str;
	}
}
