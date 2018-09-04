package com.aigod.filewatcher;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.EventBus;

public class FileWatcherRunnable implements Runnable {
	private final Logger logger = LoggerFactory.getLogger(FileWatcherRunnable.class);
	
	private final EventBus eventBus;
	private final Map<String, File> files;
	private Map<String, Long> lastModifieds;
	
	public FileWatcherRunnable(Map<String, File> files, EventBus eventBus) {
		this.files = files;
		this.eventBus = eventBus;
		lastModifieds = new HashMap<String,Long>();
		for(String key : files.keySet()) {
			lastModifieds.put(key, 0L);
		}
	}

	@Override
	public void run() {
		Map<String, File> needToUpdate = new HashMap<>();
		for(String key : files.keySet()) {
			long oldTime = lastModifieds.get(key);
			long newTime = files.get(key).lastModified();
			if(newTime > oldTime) {
				needToUpdate.put(key, files.get(key));
				lastModifieds.put(key, newTime);
			}
		}
		if(needToUpdate.size() == 0) {
			logger.debug("Don't need to update.");
		} else {
			logger.warn("File updated, start to syncronize!");
			eventBus.post(needToUpdate);
		}
	}

}
