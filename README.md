# Java PowerPoint Automator

## Introduction

This tool is geared towards organizations which meets frequently and flashes announcements in PowerPoint before events.  This makes it possible for multiple contributors to update their announcement in their own Google Spreadsheet, and the event coordinator simply clicks "download" on the day of the event.

## Technical Summary

Web application that allows user to dynamically create a PowerPoint document based on certain Google Docs spreadsheets.  It internally uses Apache POI.

## Process Flow

<img src="https://f.cloud.github.com/assets/3783092/226085/3ebc8ea0-8616-11e2-9ac8-9c737caa848b.png" width="640" />

# Installation
  * Deploy ROOT.war into your J2EE application server (See downloads section, or compile from scratch)
  * Modify the exploded webapp/ROOT/WEB-INF/classes/powerpoint.properties:
  *   google.spreadsheet.keys.csv={key1},{key2}...{keyN}
  * Launch the browser and load http://localhost:8080/
  * Click "Generate Powerpoint File"

# Example Usage in Organization

In this example, different people have their own spreadsheet that they manage.  All that needs to happen is that the "keys" (or identifier" of the spreadsheet be configured in the application.

<img src="https://f.cloud.github.com/assets/3783092/226086/3ecfa35a-8616-11e2-928b-d5d044c2acb1.png" width="640" />


# Google Docs Tip

### How do I enable my Google Docs to be accessible to the application?

<img src="https://f.cloud.github.com/assets/3783092/226084/3ea8f764-8616-11e2-8839-71f23be0b01c.png" width="640" />
