# TestFramework
# Testframework using SeleniumCucumberTestNGFramework

This is Selenium based Test Automation Framework which is developed combining both Cucumber (BDD) and Page Object Model strategy. This framework can be used to automation web-based applications. I have taken a sample application to showcase the functionality of it.

#Pre-requisite:-
1. Please make sure your IDE is compatible for the Maven , Cucumber , Gherkins and TestNG by downloading the available plug-ins.
2. Run the command "mvn clean install" to download all the maven dependency which is defined in the pom.xml file. 
   if you get any error around "extentreport-adapter" then run the below command in the Terminal.

command:- mvn install:install-file -Dfile=complete file path of jar file available in lib folder -DgroupId=com.aventstack -DartifactId=extentreports-cucumber4-adapter -Dversion=1.0.8 -Dpackaging=jar


#How to Run Test cases:-

There is test runner available in framework one for single thread execution (TestRunner.java) 
As cucumber gives nice options to add Tagging of the test within feature file and then run those tests from test runner by just adding the same tag into "tags" attribute of CucumberOptions

#After run:-

Refresh the project directory you coould see test output directory with various reports and screenshot directory with screenshots.

#How to add new Test cases:-

Create/Edit feature file and write down the test scenarios using simple English language using Gherkins Keywords and generate Steps class in StepDefinition package and implement those methods.
Create/Edit Page class in PageObjects package and creates locators of WebElements and action functions that to be execute in test case and available on that page.

