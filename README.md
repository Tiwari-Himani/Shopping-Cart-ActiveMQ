# Shopping-Cart-ActiveMQ

# Problem

# Shopping Cart: 

Imagine you are working on a shopping Cart component, when user choose a product, it will be published to a message queue.

You are developing a micro service component which receives message from message queue, save it into database. You can use embedded database/jms for illustration purpose.

The code should:

1. Should have good test coverage.
2. The app should not lose message if the database connections goes down.
3. The app should be able to auto create table if the table doesn’t exist in database.
4. Self-healing from database issue without human intervention.
5. Good fault tolerate.

# Solution


## Setup and run

Import shopping-cart-assignment project to IDE

Run as a conventional SpringBoot application though IDE or command line.

`java -jar target/shopping-cart-assignment-0.0.1-SNAPSHOT.jar` <br>
`java -jar -Dspring.profiles.active=standalone target/shopping-cart-assignment-0.0.1-SNAPSHOT.jar`

```
# Solution

## Good Test coverage

Unit Test Coverage Using,

	1.JUnit
	2.Mockito
	3.AssertJ

**Solution is able to auto create table if the table doesn't exist in database

Application is using JPA and default spring-hibernate configuration hence no need of existing schema.
At time of schema generation on startup if it doesn't exist, application will also load test data from the "data.sql" file.

** This solution does't lose message if the database connections goes down

Even when database is not consistent, use of JMS Broker "ActiveMQ" will ensure delivery of messages.

--maximumRedeliveries can be configured as has been done in "application-standalone.properties"

## Good fault tolerate

Since application uses docker,in case of a failure of one or more nodes, system will still be able to run.
Several replicas can be created for this application.

Since application is using "Message Queue",based on the node availability messages will be processed consistently.

## Self-healing from database issue without human intervention

No human intervention is required for database.


