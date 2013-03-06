package com.kiok.powerpoint.service;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import com.kiok.powerpoint.model.Announcement;

public class AnnouncementServiceTest extends TestCase {

	private String KEY_TEST_SPREADSHEET = "0Ajx2qAf_mG-zdGFTbjViTmlDcVhsUHZKYS1qTXZ6dEE";

	private AnnouncementService service;

	public void setUp() throws Exception {
		service = new AnnouncementService();
		service.setCsvSpreadsheetKeys(KEY_TEST_SPREADSHEET);
		service.afterPropertiesSet();
	}
	
	@Test
	public void testGetAnnouncements() throws Exception {
		List<Announcement> announcements = service.getAnnouncements(KEY_TEST_SPREADSHEET);
		
		Assert.assertNotNull(announcements);
		Assert.assertEquals(3, announcements.size());
	}
}
