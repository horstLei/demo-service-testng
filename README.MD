Arquillian / Junit5 Test on Wildfly with EJB Service and/or Rest in JakarteEE 8.0

small testproject with two services A / B deployed on an Wildfly and tested with arquillian and junit5

a made a few testsuites to probe different scenarios where it fails, at the momemnt is just start with the simplest one
test is not failing... also if fail is directly called...


to be able to access the local repo you have to add the file

~/.m2/settings-security.xml

<settingsSecurity>
    <master>OUTPUT OF THE COMMAND: mvn --encrypt-master-password</master>
</settingsSecurity>


# IMPORTANT
i have in the demo-test-ejb/test/resources/arquillian.xml

the jboss_home fully qualified with MY local path !!

# build

top level directory

````
mvn clean install -DskipTests
````

# test EJB

````
cd demo-test-ejb
````

## test no tests found
execute single suite
````
mvn clean install -Parq-jboss-managed -Dsurefire.suiteXmlFiles=src/test/resources/demoservicesuite_assertonfirsttest.xm
````
expected behaviour / actual behaviour
correct exeption thrown no tests to execute found

## execute single suite DemoSimpleServiceSuiteTest

````
mvn clean install -Parq-jboss-managed -Dsurefire.suiteXmlFiles=src/test/resources/demoservicesuite_simpletest.xm
````
expected behaviour:
two tests are Running
````
at.it4health.test.servicea.DemoSimpleServiceATest.functionATest 
output: DemoSimpleServiceATest.functionATest executed: ModuleAService.functionA() called
result: SUCCESS Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
````
````
at.it4health.test.serviceb.DemoSimpleServiceBTest.functionATest
output: DemoSimpleServiceBTest.functionATest executed...
result: ERROR Tests run: 1, Failures: 1, Errors: 0, Skipped: 0
````
actual behaviour:
````
at.it4health.test.servicea.DemoSimpleServiceATest.functionATest
output: DemoSimpleServiceATest.functionATest executed: ModuleAService.functionA() called
result: SUCCESS Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
````
````
at.it4health.test.serviceb.DemoSimpleServiceBTest.functionATest
output: DemoSimpleServiceBTest.functionATest executed...
result: SUCCESS Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
````
all is green also when directly fail is called

## other descriptions are following...



# test REST

cd demo-test-rest

