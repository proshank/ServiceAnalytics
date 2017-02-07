# ServiceAnalytics

Project to call GET service n no of time and print its response statistcs.

###Build Instructions

> Application depends on the following software

 1. Java SE Development Kit (JDK) version 1.8
 2. Maven version 3.1.1 or newer
Due to these dependencies, you must perform  one-time setup tasks before building and running the application.


> One-Time Setup Tasks

 1. Download and install JDK version 1.8 if necessary, you can use JDK1.7 too. 
 	To use JDK1.7 update pom.xml >> "maven-compiler-plugin" >> configuration >> source & Target to 1.7.
 2. Download and install Maven version 3.1.1 or newer if necessary, I used Maven version 3.3.9 while working on this project.

> Maven Dependencies

 1. Apache httpclient for making service call
 2. SLF4J & logback for logging
 3. Apache Commons-math3 for getting statistics on response time
 4. JUnit4 for unit testing 

> Building and Running Application

1. Compile application using Maven cmd: mvn clean compile
2. You can run this application by running main method of MainController.java 
3. Alternatively you can execute tests in MainControllerTest.java, 
   these test cases are invoking MainController for 100 requests with thread pool of 10, 50 & 100.
