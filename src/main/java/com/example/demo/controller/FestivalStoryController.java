package com.example.demo.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.vo.FestivalDetailVo;
import com.example.demo.vo.FestivalListVo;
import com.google.gson.Gson;

@RestController
public class FestivalStoryController {

	String[] state = {"서울", "부산", "대구", "인천", "광주", "대전", "울산", "세종", "경기", "강원", "충북", "충남", "전북", "전남", "경북", "경남", "제주"};
	long[] addr_code = {110000000l, 2600000000l, 2700000000l, 2800000000l, 2900000000l, 3000000000l, 3100000000l, 3600000000l, 4100000000l, 4200000000l, 4300000000l, 4400000000l, 4500000000l, 4600000000l, 4700000000l, 4800000000l, 5000000000l};
	
	@RequestMapping(value="/listFestival", produces="application/json;charset=UTF-8")
	public String listFestival(boolean ing_festival, String current_state) {
		System.out.println("FestivalController 작동시작 !!! =======================");
		String str = "ok";
		String status = "null";
		int festCnt = 0;
		int pageNum = 1;
		String addrCd = "ALL";
		//리스트에 담기전 리스트를 비워준다.
		ArrayList<FestivalListVo> list = new ArrayList<FestivalListVo>();
		
		System.out.println("==========================================");
		System.out.println("ing_festival:"+ing_festival);
		if(ing_festival) {
			status = "ING";
		}else {
			status = "FOR";
		}
		
		System.out.println("current_state:"+current_state);
		if(current_state != "") {
			int k = 0;
			for(int i=0; i<state.length; i++) {
				if(state[i].equals(current_state)) {
					k = i;
				}
			}
			addrCd = addr_code[k]+"";
		}else {
			addrCd = "ALL";
		}
		
		while(true) {
		
			String wholeUrl = "https://www.gov.kr/portal/vfnews?srchSttusCls="+status+"&srchAddrCd="+addrCd+"&srchStdt=&srchEddt=&srchTxt=&pageIndex="+pageNum;
			System.out.println(wholeUrl);
			try {
				
				Document doc = Jsoup.connect(wholeUrl).get();
				
				festCnt = Integer.parseInt(doc.select("div.title-box2 > p > span").text()); 
				System.out.println("축제 수:" +festCnt);
				
				Element element = doc.select("div.gallery_wrap.k-festival > ul").get(0);
				
				Elements li = element.select("li");
				for (Element e : li) {
					
					String url1 = e.select("div > dl > dt > a").attr("href");
					String url = "https://www.gov.kr"+url1;
					String title = e.select("div > dl > dt > a").text();
					String period = e.select("div > dl > dd:nth-child(3)").text();
					String addr = e.select("div > dl > dd:nth-child(4)").text();
					String detailAll = e.select("div > dl > dd:nth-child(5)").html();
					String detailHead;
					if(detailAll.length()>50) {
						detailHead = detailAll.substring(0,45)+" ...더보기";
					}else {
						detailHead = detailAll;
					}
					String fname = e.select("img").attr("src");
					String ingStatus = e.select("div.deco").text();
				   
					period = period.substring(period.lastIndexOf(":")+2);
					addr = addr.substring(addr.lastIndexOf(":")+2);
					
					System.out.println("--------------------------------------");
					System.out.println("title:"+title);
					System.out.println("url:"+url);
					System.out.println("period:"+period);
					System.out.println("addr:"+addr);
					System.out.println("detailHead:"+detailHead);
					System.out.println("ingStatus:"+ingStatus);
					System.out.println("fname:"+fname);
					
					list.add(new FestivalListVo(title, period, addr, detailHead, detailAll, ingStatus, url, fname));
					System.out.println("리스트의 개수:"+list.size());
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			System.out.println("리스트의 개수:"+list.size());
			if(list.size() >= festCnt) {
				System.out.println("총 "+list.size()+" 건의 축제가 있습니다.");
				break;
			}else {
				System.out.println("페이지를 추가합니다. 현재페이지:"+pageNum);
				pageNum += 1;
			}
		
		
		
		}
		str = new Gson().toJson(list);
		return str;
	}
	
	@RequestMapping(value="/detailFestival.do", produces="application/json;charset=UTF-8")
	public String detailFestival(String url, String fTitle) {
		String str = "ok";
		System.out.println(url);
		FestivalDetailVo f = new FestivalDetailVo();
		try {
			
			Document doc = Jsoup.connect(url).get();
			String fname = doc.select("div.thumb-detail > p > img").attr("src");
			String title = fTitle;
			String period = doc.select("div.thumb-detail > dl > dd:nth-child(2)").text();
			String location = doc.select("div.thumb-detail > dl > dd:nth-child(4)").text();
			String tel = doc.select("div.thumb-detail > dl > dd:nth-child(6)").text();
			String host = doc.select("div.thumb-detail > dl > dd:nth-child(8)").text();
			String addr = doc.select("div.thumb-detail > dl > dd:nth-child(10)").text();
			String homepage = doc.select("div.thumb-detail > dl > dd:nth-child(12)").text();
			String detail = doc.select("div.view-contents > div > div.box-cont > p").html();
			
			if(homepage.length() > 1) {
				addr = homepage;
			}
			
			System.out.println("title:"+title);
			System.out.println("period:"+period);
			System.out.println("location:"+location);
			System.out.println("tel:"+tel);
			System.out.println("host:"+host);
			System.out.println("addr:"+addr);
			System.out.println("detail:"+detail);
			System.out.println("fname:"+fname);
			System.out.println("homepage:"+homepage);
			
			f = new FestivalDetailVo(title, period, location, tel, host, addr, detail, fname);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
		str = new Gson().toJson(f);
		
		return str;
	}
	
	@RequestMapping(value="/festivalThumb.do", produces="application/json;charset=UTF-8")
	public String festivalThumb() {
		String str = "";
		ArrayList<FestivalListVo> list = new ArrayList<FestivalListVo>();
		
		String wholeUrl = "https://www.gov.kr/portal/vfnews?srchSttusCls=ING&srchAddrCd=ALL&srchStdt=&srchEddt=&srchTxt=";
		try {
			
			Document doc = Jsoup.connect(wholeUrl).get();
			Element element = doc.select("div.gallery_wrap.k-festival > ul").get(0);
			
			Elements li = element.select("li");
			for (Element e : li) {
				
				String url1 = e.select("div > dl > dt > a").attr("href");
				String url = "https://www.gov.kr"+url1;
				String title = e.select("div > dl > dt > a").text();
				String period = e.select("div > dl > dd:nth-child(3)").text();
				String addr = e.select("div > dl > dd:nth-child(4)").text();
				String detailAll = e.select("div > dl > dd:nth-child(5)").html();
				String detailHead;
				if(detailAll.length()>50) {
					detailHead = detailAll.substring(0,45)+" ...더보기";
				}else {
					detailHead = detailAll;
				}
				String fname = e.select("img").attr("src");
			   
				period = period.substring(period.lastIndexOf(":")+2);
				addr = addr.substring(addr.lastIndexOf(":")+2);
				/*
				System.out.println("--------------------------------------");
				System.out.println("title:"+title);
				System.out.println("url:"+url);
				System.out.println("period:"+period);
				System.out.println("addr:"+addr);
				System.out.println("detailHead:"+detailHead);
				System.out.println("fname:"+fname);
				*/
				list.add(new FestivalListVo(title, period, addr, detailHead, detailAll, null, url, fname));
				//System.out.println("리스트의 개수:"+list.size());
				
				// 화면에 표시될 축제 갯
				if(list.size() >= 3) {
					break;
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		//System.out.println("총 "+list.size()+" 건의 축제가 있습니다.");
		
		str = new Gson().toJson(list);
		
		return str;
	}
}






















