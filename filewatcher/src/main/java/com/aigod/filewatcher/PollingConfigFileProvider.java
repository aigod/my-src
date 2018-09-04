package com.aigod.filewatcher;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class PollingConfigFileProvider {
	private final Logger logger = LoggerFactory.getLogger(PollingConfigFileProvider.class);
	
	private final EventBus eventBus;
	private final Map<String, File> files;
	private final int interval;
	private final ScheduledExecutorService executorService;
	
	public PollingConfigFileProvider(EventBus eventBus, Map<String, File> files, int interval) {
		this.eventBus = eventBus;
		this.files = files;
		this.interval = interval;
		ThreadFactory tf = new ThreadFactoryBuilder().setNameFormat("update-file-poller-%d").build();
		this.executorService = Executors.newSingleThreadScheduledExecutor(tf);
	}
	
	public void start() {
		eventBus.register(this);
		FileWatcherRunnable fileWatcher = new FileWatcherRunnable(files,eventBus);
		executorService.scheduleWithFixedDelay(fileWatcher, 0, interval, TimeUnit.SECONDS);
		logger.debug("FileWatcher 启动成功!");
	}
	
	public void stop() {
		logger.debug("Config File Provider is going to shutdown...");
		executorService.shutdown();
		try {
			while(!executorService.awaitTermination(500, TimeUnit.MICROSECONDS)) {
				logger.debug("Waiting file watcher to terminate...");
			}
		} catch (InterruptedException e) {
			logger.error("Interrupted while waiting file watcher to stop!");
			Thread.currentThread().interrupt();
		}
		logger.debug("Config File Provider has been stopped.");
	}
	
	@Subscribe
	public void update(Object eventObj) {
		HashMap<String,File> updateFiles = (HashMap<String,File>) eventObj;
		for(String key : updateFiles.keySet()) {
			updateConfig(key, updateFiles.get(key));
		}
	}
	
	public void updateConfig(String key, File file) {
		switch(key) {
		case "template":
			System.out.println("template update");
			break;
		case "whiteknight":
			System.out.println("whiteknight update");
			break;
		case "suanhua":
			System.out.println("suanhua update");
			break;
		case "gdmap":
			System.out.println("gdmap update");
			break;
		default:
			logger.warn("error key: {}", key);
			break;
		}
	}

}
