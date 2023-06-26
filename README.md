# ALDI Coin

## Table of Contents
* [Task Description](#tasks-description)
* [Setup](#setup)

## Task Description
User Story

As a user, I would like to have an app that shares information on crypto currencies in the market, so that I
can make informed investment decisions based on up-to-date information.

Acceptance Criteria

On start, the app should load a list of the top ten ranked crypto currencies.

While loading the list, a progress view (Android default) should be displayed.

When the list is loaded, the following information for each list item should be displayed:

  • Icon (please use the icons in figma, if the icon is not present in figma pick an icon as a default one)
  
  • Name

  • Symbol (e.g. BTC)
  
  • Price
  
  • Change percentage during the last 24 hours
  
The list should be scrollable.

The list should be refreshed every 1 minute.

When tapping on a list item, a details screen for this item should be shown, containing the following

elements:
  • Back button
  
  • Price
  
  • Change percentage during the last 24 hours
  
  • Market capitalization
  
  • Exchange volume during the last 24 hours
  
  • Supply
  
When presenting the details screen, the underlying data should be refreshed.

While refreshing the data for the details screen, a progress view should be displayed (Android default
progress-view).

Tapping on the back button should navigate back to the list screen.

The color of the change percentage text should reflect its value: green if positive, red if negative.

Every decimal value should be formatted using abbreviations (K for thousands, M for millions and B
for billions).

The font can be the default font from android.


## Setup
build and install app from command line:

	path/to/AldiCoin> gradlew installrelease

