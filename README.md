CONTENTS OF THIS FILE
---------------------

 * Introduction
 * Installation
 * Folder Stucture
 * Execution
 


INTRODUCTION
------------

Current Maintainer: Swapnil Paunikar <swapnilpaunikar21@gmail.com>

This is a sample basic Mobile automation framework to test Amazon application.


INSTALLATION
------------

This is a maven based project. Once clone this project at any machine it will 
start downloading required dependancies automatically.

FOLDER STRUCTURE
----------------

MobileBaseUtility under BaseUtility Package is java class where driver initilization get done.
ReadFromExcel Utility used to read file from external source.

Pages Package contain all element of screen wise.

Tests package keep all the test cases.
TestNG framework have been used to create prerequiste, Test and postrequisite.

Resource folder contain .apk application to be installed on device and Excel where we can keep the data to use as test script prerequiste.

EXCECUTION
----------------
In order to execute test case 

mvn test -Dtest=className

we can use above command.


Thanks..!!
