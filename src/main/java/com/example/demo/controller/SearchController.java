package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.db.DBManager;
import com.example.demo.vo.CampingSearchResultVo;
import com.example.demo.vo.CampingSpotVo;
import com.google.gson.Gson;

@RestController
public class SearchController {

	@RequestMapping(value="/campingSearchList.do", produces="application/json;charset=UTF-8")
	public String campingSearchList(String checkin, String checkout, String keyword, String campingType, String theme, String facility, String lowPrice, String highPrice) {
		String str = "";
		System.out.println("===== campingSearchList 컨트롤러 작동 !! =================");
		System.out.println("체크인:"+checkin);
		System.out.println("체크아웃:"+checkout);
		System.out.println("캠핑타입:"+campingType);
		System.out.println("키워드:"+keyword);
		System.out.println("테마필터:"+theme);
		System.out.println("시설필터:"+facility);
		System.out.println("최저가:"+lowPrice);
		System.out.println("최고가:"+highPrice);
		
		Date checkin_date = new Date();
		Date checkout_date = new Date();
		//체크인, 체크아웃 차이일수 : 숙박일수
		long calDateDays = 0;
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
		
			checkin_date = transFormat.parse(checkin);
			checkout_date = transFormat.parse(checkout);
			
			long calDate = checkout_date.getTime() - checkin_date.getTime();
			calDateDays = calDate / (24*60*60*1000);

			System.out.println("차이 일수 :" +calDateDays);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//유효날짜를 구한다.
		System.out.println("-------유효 일수-------");
		Calendar cal = Calendar.getInstance();
		cal.setTime(checkin_date);
		
		String validDate1 = null;
		String validDate2 = null;
		String validDate3 = null;
		String validDate4 = null;
		
		//체크인정보가 null 이 아니고, ""(공백) 이 아니라면 유효일1에 정보를 넣는다.
		if((checkin!=null)&&(!checkin.equals(""))) {
			validDate1 = checkin;
		}
		if(calDateDays >= 2) {
			cal.add(Calendar.DATE,	1);
			validDate2 = transFormat.format(cal.getTime());
		}
		if(calDateDays >= 3) {
			cal.add(Calendar.DATE,	1);
			validDate3 = transFormat.format(cal.getTime());
		}
		if(calDateDays == 4) {
			cal.add(Calendar.DATE,	1);
			validDate4 = transFormat.format(cal.getTime());
		}
		
		System.out.println("유효일수 1:"+validDate1);
		System.out.println("유효일수 2:"+validDate2);
		System.out.println("유효일수 3:"+validDate3);
		System.out.println("유효일수 4:"+validDate4);
		
		//DBManager에 보낼 map을 설정한다.
		HashMap map = new HashMap();
		map.put("keyword", keyword);
		map.put("campingType", campingType);
		map.put("validDate1", validDate1);
		map.put("validDate2", validDate2);
		map.put("validDate3", validDate3);
		map.put("validDate4", validDate4);
		
		//필터를 위한 정보를 담은 map_filter
		HashMap map_filter = new HashMap();
		
		//테마가 null이 아니고 ""(공백)이 아니라면
		System.out.println("-------테마 필터-------");
		if((theme != null)&&(!theme.equals(""))) {
			String[] themeSplit = theme.split(", ");
			String[] themeArray = new String[6]; //테마의 개수: 6
			
			for(int i=0; i<themeArray.length; i++) { 
				if(themeSplit.length >= (i+1) ) {
					themeArray[i] = themeSplit[i];
				}else {
					themeArray[i]=null;

				}
				System.out.println("themeArray "+(i+1)+"번째 값:"+themeArray[i]);
				map_filter.put("theme"+(i+1), themeArray[i]);
			}
		}else {
			for(int i=0; i<6; i++) {
				map_filter.put("theme"+(i+1), null);
			}
		}
		
		//시설이 null이 아니고 ""(공백)이 아니라면
		System.out.println("-------시설 필터-------");
		if((facility != null)&&(!facility.equals(""))) {
			String[] facilitySplit = facility.split(", ");
			String[] facilityArray = new String[7]; //시설의 개수: 7
					
			for(int i=0; i<facilityArray.length; i++) { 
				if(facilitySplit.length >= (i+1) ) {
					facilityArray[i] = facilitySplit[i];
				}else {
					facilityArray[i]=null;
					//map.put("theme"+i, null);
				}
				System.out.println("facilityArray "+(i+1)+"번째 값:"+facilityArray[i]);
				map_filter.put("facility"+(i+1), facilityArray[i]);
			}
		}else {
			for(int i=0; i<7; i++) {
				map_filter.put("facility"+(i+1), null);
			}
		}
		
		int low = 0; //최저가는 0으로 초기화 되어있다.
		int high = 1000000; //최고가는 100백만으로 초기화 되어있다.
		System.out.println("-------가격 필터-------");
		//최저가가 null이 아니고 ""(공백)이 아니라면
		if((lowPrice != null)&&(!lowPrice.equals(""))) {
			low = Integer.parseInt(lowPrice);
		}
		map_filter.put("lowPrice", low);
		//최고가가 null이 아니고 ""(공백)이 아니라면
		if((highPrice != null)&&(!highPrice.equals(""))) {
			high = Integer.parseInt(highPrice);
		}
		map_filter.put("highPrice", high);
		System.out.println("최저가: "+low+", 최고가: "+high);
		
		
		
		
		//검색결과를 불러오지만 cs_no 값만 채워지고, 나머지는 전부 null
		List<CampingSearchResultVo> list = DBManager.search_list(map);
		
		//비어있는 Vo 를 cs_no를 통해 다시 불러온다.
		for(int i=0; i<list.size(); i++) {
			System.out.println("검색결과"+i+": "+list.get(i));
			int no = list.get(i).getNo();
			map_filter.put("no", no);
			System.out.println(map_filter);
			list.set(i, DBManager.getCampingSpot(map_filter));
			System.out.println("검색결과(수정)"+i+": "+list.get(i));
			
		}
		for(int i=0; i<list.size(); i++) {
			if(list.get(i) == null) {
				list.remove(i);
				i--;
			}
		}
		str = new Gson().toJson(list);
		
		return str;
	}
	
}
