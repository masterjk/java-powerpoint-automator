package com.kiok.powerpoint.model;

public class Announcement {

	private String title;
	private String description;

	/**
	 * Constructor.
	 * @param title
	 * @param description
	 */
	public Announcement(String title, String description) {
		this.title = title;
		this.description = description;
	}

	/**
	 * Returns the title.
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns the description.
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Announcement Item - Title: [" + title + "]; description: [" + description + "]";
	}
}