## What is JWatch

JWatch is an observer pattern based java library to perform various functions:

* Error handling
* Security measures
* Logging
* Data Validation
* Etc...

  JWatch comes as a Maven dependency and must be used in any java code.

  ## Installation

Add this dependency in your pom.xml
```
  <dependency>
      <groupId>io.github.robertomessabrasil</groupId>
      <artifactId>jwatch-lib</artifactId>
      <version>1.0</version>
  </dependency>
```
  ## How to use

  JWatch uses the oberver pattern to handle various types of events in java code.

  The following items are necessary to use JWatch in you java code:
  1. Create events with appropriate data
  2. Create listeners to consume those events
  3. Subscribe the listeners in your observer
  4. Publish the events in your code

The decisions you put in your listeners on what to do with the events will handle the situation.

Ex.

You have a data validation in a method. Some validations can be tolerated, some don't.

The tolerable validations can be handled with data been assigned in the listener and the intolerable ones can raise and exception to interrupt the code excecution.

Follow the [jwatch tutorial](https://github.com/RobertoMessaBrasil/jwatch-tutorial) to learn how to use JWatch in you project.
