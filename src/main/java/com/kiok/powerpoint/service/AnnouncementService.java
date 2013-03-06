package com.kiok.powerpoint.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.CustomElementCollection;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.kiok.powerpoint.model.Announcement;

/**
 * AnnouncementService retrieves all the announcements from
 * Google Docs (SpreadSheet) -- with the keys that you have
 * provided.
 */
public class AnnouncementService implements InitializingBean {

	protected final static Log LOG = LogFactory.getLog(AnnouncementService.class);

	// GData SpreadSheet Service
	private SpreadsheetService spreadsheetService;

	// Service Name
	private final static String APP_NAME = "JAVA_POWERPOINT_SVC";

	// Google Docs Column Titles
	private final static String ROW_ITEM_TITLE = "title";
	private final static String ROW_ITEM_DESCRIPTION = "description";

	// List of all SpreadSheet Keys
	private List<String> listSpreadsheetKeys;
	
	@Value("${google.spreadsheet.keys.csv}")
	private String csvSpreadsheetKeys;

	@Override
	public void afterPropertiesSet() throws Exception {
		
		// Check Asserts
		Assert.notNull("Expected 'csvSpreadsheetKeys' to be dependency injected", csvSpreadsheetKeys);
		
		// Initialize the GData Service
		spreadsheetService = new SpreadsheetService(APP_NAME);
		
		// Initialize the SpreadSheet Key Array
		listSpreadsheetKeys = new ArrayList<String>();
		
		// Parse the CSV SpreadSheet Keys
		StringTokenizer tokenizer = new StringTokenizer(csvSpreadsheetKeys, ",");
		while (tokenizer.hasMoreElements()) {
			String key = tokenizer.nextToken();
			listSpreadsheetKeys.add(key);
			LOG.debug("Found spreadsheet key: " + key);
		}

		LOG.info("Service initialized with " + listSpreadsheetKeys.size() + " keys");		
	}

	/**
	 * Returns a list of announcement from all of the spreadsheet keys configured.
	 * @return announcements
	 */
	public List<Announcement> getAllAnnouncements() throws Exception {

		List<Announcement> announcements = new ArrayList<Announcement>();

		for(String key : listSpreadsheetKeys) {
			announcements.addAll(getAnnouncements(key));
		}

		LOG.debug("Total [" + announcements.size() + "] announcements retrieved from all spreadsheets");

		return announcements;
	}

	/**
	 * Return the list of announcements found within a spreadsheet identified by 'key'.
	 * @param key
	 * @return announcements
	 * @throws Exception
	 */
	protected List<Announcement> getAnnouncements(String key) throws Exception {

		List<Announcement> announcements = new ArrayList<Announcement>();

		URL metafeedUrl = new URL("https://spreadsheets.google.com/feeds/list/" + key + "/default/public/values");
		ListFeed feed = spreadsheetService.getFeed(metafeedUrl, ListFeed.class);
		
		for (ListEntry entry : feed.getEntries()) {
			CustomElementCollection elements = entry.getCustomElements();
			String title = elements.getValue(ROW_ITEM_TITLE);
			String description = elements.getValue(ROW_ITEM_DESCRIPTION);

			if (StringUtils.isNotEmpty(title) && StringUtils.isNotEmpty(description)) {
				announcements.add(new Announcement(title, description));
			} else {
				LOG.error("Unable to add: title: " + title + "; description: " + description);
			}
		}

		LOG.debug("Found [" + announcements.size() + "] announcements from spreadsheet key [" +  key + "]");
		return announcements;
	}

	public void setCsvSpreadsheetKeys(String csvSpreadsheetKeys) {
		this.csvSpreadsheetKeys = csvSpreadsheetKeys;
	}
}