package com.yak;

import com.yak.been.YakReport;
import com.yak.service.ReportService;

public class YakShopMain {
	  
    public static void main(String[] args) {
		ReportService service = new ReportService();
		YakReport report = service.createReport("herd.xml", 14);
		System.out.println(report);
	}
}
