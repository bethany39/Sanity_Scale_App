# Sanity Scale ADRs

## Platform

**Summary:**
In order to create a platform, we decided to develop an Android app.

**Problem:**

We need to decide what type of mobile app we’re making. We figured out that we are making a mobile app, as opposed to a web app, but then we were presented with the problem of what kind of mobile app: iOS, Android, or an app that could work for both iPhones and Androids.

**Constraints:**
The main constraint for us is that we do not all have Macs, so it would be hard to create an iOS app. However, none of us have Android phones.

**Options:**

  * iOS
    * (con) one of our members would have to borrow a Mac from the App Lab and learn how to use it.
    * (con) Additionally, he would have to pay for a dev account to test on a device instead of an emulator.
    * (pro) all three of us have iPhones
    * (pro) apparently Swift isn’t as much code as Java
  * Android
    * (pro) we all have the hardware to do it
    * (pro) Kotlin is compatible with Java, so it should be fairly simple for us to learn and use.
    * (pro) is that Androids reach more globally and it’s cheaper to release to an app store.
    * (con) we would have to use emulators to test instead of real phones (unless we can borrow one)
    * (con) device fragmentation; there are lots of different versions and sizes of Androids.

**Rationale:**
We chose to make an Android app mainly so all of us can use our own laptops to develop it.

## Language

**Summary:**
In order to write code for the frontend of our app, we decided to use Java as our programming language.

**Problem:**
We need a language to write our code in. When we decided we were making an Android app, we immediately said we were going to use Java, but after researching the other parts, we decided to change our minds.

**Constraints:**
Not really any constraints. Most languages can be used for Android development, although Java and Kotlin are the best.

**Options:**
  * Java
    * (pro) it’s the official language for Android development
    * (con) it sometimes takes a lot of code to do simple things
  * Kotlin
    * (pro) similar to Java, so it will be fairly easy for us to learn, and there’s a Java to Koitlin converter
    * (pro) it’s the new second official language for Android development
    * (pro) it requires less boilerplate code
    * (con) none of us have used it before

**Rationale:**
We decided to use Kotlin as our programming language because while it is very similar to Java, it is simpler to use and has some features that Java does not have. Very shortly after our decision, however, we switched to Java because we found that tutorials for linking APIs were mostly in Java and we realized we didn’t have extra time to learn Kotlin.


## Cloud Database
**Summary:**
In order to store user data persistently and potentially over multiple devices, we decided to use Amazon’s Relational Database Service as the cloud provider for our database.

**Problem:**
We need a service for storing our database.  This will be necessary for storing usernames, passwords, names, and weight data obtained from the scale.  

**Constraints:**
Our database platform needs to be able to be accessed at any time.

Our database platform should be secure and preferably would integrate well with techniques concurrent with securing data.

Our database must be compatible with the language we decide on to query the database.

Our database should be a reasonable price for our client.  It would be unacceptable to set up a database that will exceed the revenue of the company, even in the beginning.

**Options:**
  * Amazon Relational Database Service
    * (pro)  Relatively cheap ($13-25 per month)
  * (pro)  Scalability with no downtime
    * (pro)  Built-in security
    * (pro)  First month free
    * (con)  Extremely expensive (at least $400 per month)
  * Google Cloud SQL
    * (pro)  Fast to apply updates, manage backups
    * (pro)  Relatively cheap ($7-25 per month)
    * (con)  Charged each time data leaves the cloud
  * Oracle Database
    * (pro)  High level security
    * (pro)  Fast to set up
    * (con)  Extremely expensive (at least $2000 per year)
  * Heroku
    * (pro)  Has free version
    * (pro)  Our professor uses and recommends this service
    * (con)  Missing a lot of features at cheap tier (limit on rows, no disk encryption, up to 4 hours of downtime)
    * (con)  Standard tier isn’t cheap ($50 per month)

**Rationale:**
We chose to use Amazon RDS.  Oracle and Azure were both absolutely not feasible after research because of how expensive they are.  Heroku has the benefit of having a free version that could be used for testing, but ultimately a version that would be usable for the actual product would be pricey.  Google Cloud SQL and Amazon RDS are very similar on paper.  They both are relatively inexpensive.  Ultimately, the decision to use RDS was made because one of the team members was already familiar with Amazon’s platform and Amazon doesn’t start to charge for data transfer until after 1 GB has been transferred (whereas Google starts to charge immediately).


## Framework
**Summary:**
In order to create an app in which we can easily implement a RESTful API, handle dependency injection, and MVC framework, we decided to use the Spring for Android framework.

**Problem:**
Our main problem is finding what framework, if any, will help us to implement our front end, API, and configuration in a timely manner and with the least amount of headaches.

**Constraints:**
Our primary constraint is that our framework needs to work for a native android app. We have no external constraints in terms of desires or price range of the client, or requirements of our professor. Our team’s technology constraints should not apply to the framework that we choose, as each framework we considered is available in the environments we will be developing in.

**Options:**
  * No framework:
    * (pro) Not having to learn another new technological feature could give us back time we would have lost.
    * (pro) An Android application in Java can achieve high functionality without a framework.  
    * (pro) Certain frameworks, such as Java Spring, can be added on later
    * (con) Frameworks are often built to help with common problems and speed up development, so in the long run we may be missing out on something that could save us time and headaches.
  * React Native:
    * (pro) One team member has used it before in a web application. This would likely mean that transitioning to React Native would be fairly easy, so our team would at least have some guidance as they learn, making the learning process quicker and less painful.
    * (pro) Mobile apps developed in React Native are supposedly fairly easy to convert into a web application down the road, which our client has expressed interest in.
    * (pro) There are a lot of React Tutorials on the internet to help us out.
    * (con) While React is not super complex, it does change the style and feel of the code quite a bit, so the time to learn enough to produce something in React would be much more than in a language like Java.
    * (con) If the one teammate with React experience were to effectively serve as a resource for the team, we would have to use TypeScript, meaning that two out of three teammates would be learning both React and a new programming language.
  * Spring for Android
Pros:
    * (pro) Spring seems to be a less involved framework than React, meaning that it would probably take less time to become comfortable with.
    * (pro) According to internet sources, Spring provides simplification for database interactions, dependency management, and abstraction for RESTful APIs.
    * (pro) There is a good amount of tutorials and support for Spring.
    * (con) None of our teammates have used this framework before.

**Rationale:**
Given our team’s lack of experience in mobile app design and our tight deadlines, we decided it would not be the best use of our time learning and implementing our app through a specific framework. We are therefore using just Java and have decided to revisit the framework question only if we come against problems that are greatly offset by using a framework.



## Query Language
**Summary:**
In order to access data for user authentication and weight tracking, we decided to use PostgreSQL as our query language.

**Problem:**
We need a query language to access organized data from the app’s database and provided to a user’s application.

**Constraints:**
It’s preferable that the syntax of the query language is similar to the syntax of SQLite, because SQLite is a language taught at UNC and that we know.

The query language must be supported by the database platform we choose.

**Options:**
  * MySQL
    * (pro)  Free and open-source
    * (pro)  SSL support
    * (pro)  Supported by all major cloud providers
    * (con)  Not ACID compliant (atomicity, consistency, isolation, durability) unless used under specific cases
  * PostgreSQL
    * (pro)  Free and open-source
    * (pro)  ACID compliant out of the box
    * (pro) Integrated well with Heroku
    * (pro)  Native SSL support
    * (pro)  Supported by all major cloud providers
    * (pro)  Supports data encryption
  * GraphQL
    * (pro)  Good for getting data from multiple sources

**Rationale:**
We chose to use PostgreSQL.  Upon more research, despite being recommended by friends, GraphQL just wasn’t what we were looking for in terms of a simple query language.  MySQL and PostgreSQL are very similar in aspects such as syntax and are both widely supported; however, PostgreSQL has some additional benefits such as being ACID compliant out of the box and having high performance for complex queries.
