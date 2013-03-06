package com.kiok.powerpoint.controllers;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextBox;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kiok.powerpoint.model.Announcement;
import com.kiok.powerpoint.service.AnnouncementService;
import com.kiok.powerpoint.utils.BackgroundColor;

@Controller
public class DownloadController implements InitializingBean {

	protected final static Log logger = LogFactory.getLog(DownloadController.class);

	@Autowired
	private AnnouncementService announcementService;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(announcementService);
	}

	@RequestMapping(value="/download")
	public ModelAndView docHandler(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// Create new PPT Slide Show
        SlideShow slideShow = new SlideShow();

        // Retrieve all Announcements
        List<Announcement> items = announcementService.getAllAnnouncements();
        
        // Randomize Background Colors
        List<Color> colors = BackgroundColor.getRandomColors(items.size());

        int index = 0;
        for (Announcement item : items) {
        	createSlide(slideShow, item.getTitle(), item.getDescription(), colors.get(index));
        	index++;
        }

        // Set the HTTP Mime Type
		response.setContentType("application/vnd.ms-powerpoint");
		
		// Set the Download File Name
		response.setHeader("Content-Disposition", "attachment;Filename=" + System.currentTimeMillis() + ".ppt");
        slideShow.write(response.getOutputStream());

		return null;
	}

	/**
	 * Adds a slide to the existing slideshow.
	 * @param slideShow
	 * @param title
	 * @param description
	 * @param color
	 */
	private void createSlide(SlideShow slideShow, String title, String description, Color color) {

		Slide slide = slideShow.createSlide();

		// Set the background
        slide.setFollowMasterBackground(false);
        slide.getBackground().getFill().setForegroundColor(color);

        // Set the title
        TextBox boxTitle = new TextBox();
        boxTitle.setAnchor(new Rectangle(54, 78, 612, 115));
        boxTitle.setText(title);
        RichTextRun rtTitle = boxTitle.getTextRun().getRichTextRuns()[0];
        rtTitle.setFontColor(Color.WHITE);
        rtTitle.setFontName("Arial");
        rtTitle.setFontSize(40);
        rtTitle.setBold(true);
        rtTitle.setAlignment(TextBox.AlignLeft);
        slide.addShape(boxTitle);

        // Set the description
        TextBox boxDesc = new TextBox();
        boxDesc.setAnchor(new Rectangle(54, 204, 612, 400));
        boxDesc.setText(description);
        RichTextRun rtDesc = boxDesc.getTextRun().getRichTextRuns()[0];
        rtDesc.setFontColor(Color.WHITE);
        rtDesc.setFontName("Arial");
        rtDesc.setFontSize(36);
        rtDesc.setBold(false);
        rtDesc.setAlignment(TextBox.AlignLeft);
        slide.addShape(boxDesc);
	}
}
