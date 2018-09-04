package com.aigod.filewatcher;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.google.common.eventbus.EventBus;

public class Main {

	public static void main(String[] args) {
		
//		EventBus eventBus = new EventBus();
//		Map<String, File> files = new HashMap<>();
//		
//		File dir = new File("C:\\Users\\aigod\\Desktop\\elk日志对接配置\\cns-ifs-parser\\conf");
//		files.put("temxxxx", new File(dir, "cns-ifs-xxxx.txt"));
//		files.put("suanxxx", new File(dir, "fields_xxxa.txt"));
//		files.put("whitexxxxxx", new File(dir, "fields_xxxt.txt"));
//		files.put("gdxxxp", new File(dir, "fields_xxxx.txt"));
//		
//		
//		int interval = 5;
//		PollingConfigFileProvider poller = 
//				new PollingConfigFileProvider(eventBus, files, interval);
//		
//		poller.start();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		poller.stop();
		
		File dir = new File("C:\\Users\\xxxx\\Desktop\\xxx\\cns-ifs-xxx\\conf");
		File f = new File(dir, "xxx-template.txt");
		while(true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(f.lastModified());
		}
		
	}

}
