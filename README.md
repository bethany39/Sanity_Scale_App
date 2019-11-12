# SANITY SCALE

Nobody likes the bathroom scale. We want to change that! Discover the Sanity Scale—the new way to track your weight without losing your mind! Learn how to monitor your weight and other health factors in a way that promotes the positive and sustainable lifestyle you deserve.

### Getting Started

You will need to download Android Studio (works on Mac or Windows) and follow the instructions found at this link: https://developer.android.com/studio?gclid=Cj0KCQiA2ITuBRDkARIsAMK9Q7ODtXWMZygxa2JlyncmmpiTl8S2UZJyJSnl_HwBamje-bPRlWPYGwIaAo9gEALw_wcB.

Next you'll need to open up a new project on Android Studio and pull our code from GitHub. You can either do this entirely through Android Studio, or by using Git through command line. If you wish to use Git through Android Studio, navigate in Android Studio to VCS→Checkout from Version Control→ Git. In the URL input box, paste the url gotten from cloning the project in Github, and then click clone. To build the project, just click the green Play button on the top bar of Android Studio, or navigate to Run→ Run App.

### Testing
Our project currently has two test suites: one using the Espresso test framework for the frontend Java code, and one using Pytest for the API endpoints.

#### Android Studio (Espresso):
Before running tests in Android Studio, ensure that the app’s build variant (found in the side tab called Build Variants) is set to debug, not release.

To run the frontend test suite in Android Studio, under the “Project” tab in the sidebar, navigate to app→ java→ com.example.sanityscaleapp (androidTest). Right click on this folder and then click “Run tests in com.example.sanityscaleapp”. Alternatively (because that approach seems to be finicky), use Terminal in Android Studio to run ./gradlew test to run junit tests, and ./gradlew connectedAndroidTest to run instrumented unit tests, which is currently all that we have right now.

For more advanced commands and testing desires, please visit the official documentation at https://developer.android.com/studio/test/command-line.

#### Pytest:
The tests for Sanity Scale’s API were written using PyTest.  In order to run them, navigate to the file test_app.py.  PyTest automatically detects the test methods, and therefore all you have to do to run the test suite is type the command “pytest” into the terminal.  If test_app.py and app.py are in the same directory as they are on GitHub, this will run the tests from test_app.py on app.py.


### Deployment
We do not currently have the app deployed on anything like Google Play. Our code is all on GitHub, so a user can clone our repo to get access to the code and use it in Android Studio.


### Technologies Used
The following list contains the technologies that have been used thus far in this project:

1. Heroku

   The backend services for the Sanity Scale application are deployed as an app on Heroku.  This is where the Flask API lives in addition to the database with which this API interacts.  The database is a PostgreSQL add on to the Heroku app.
2. Flask

   The Flask micro web framework is used for Sanity Scale’s API.  It was used because of familiarity with Python and general intuitiveness.  The API written in Flask is deployed on Heroku.
3. SQLAlchemy

   The package SQLAlchemy is used in the Flask API to query the PostgreSQL database.
4. Android Studio

   Android Studio is the tool used to develop the front end of the Sanity Scale app.
5. Java

   The frontend of the Sanity Scale app (i.e. what’s running on the Android device) is written in Java.
6. PostgreSQL

   The database in which user authentication data, weight data, and session tokens lives was created and is queried with PostgreSQL.  Specifically, it is a PostgreSQL add on to a Heroku web app.

The ADRs for this project can be found on Github in the main directory Sanity_Scale_App in the file SS_ADRs.md.

### Contributing

A new developer would need to get access to GitHub and Trello before he or she is able to contribute. Contact Tosha Smith at tosha.smith@gmail.com for access to the Trello project management board. Contact Bethany Ebbitt at bethanyjebbitt@gmail.com to get access to the front end GitHub repo and contact David Mayes at damayes5@gmail.com, to get access to the back end GitHub repo.

Thus far, we have not adhered to any strict style guidelines or conventions. If it works, ship it!

Our website can be found at: http://sanityscale.web.unc.edu/

### Authors
This project was created by seniors Katie Kania, David Mayes, and Bethany Ebbitt in the fall semester of 2019.

### License
The MIT license is applied to both the frontend of Sanity Scale and the backend API.  This is so that our client, Tosha Smith, encounters absolutely no restrictions whatsoever in the use of the software we created in the case that she wants to build off of it.  Furthermore, it would allow for any development team, student or professional, to extend and use the code we wrote.

### Acknowledgements
Thanks to our client Tosha Smith for being supportive of us and proud of our accomplishments. Thanks to our Jeff Terrell and Jeff Byzek for always answering our questions and patiently guiding us through the app development process. Thanks to the App Lab staff, especially Isha for helping us struggle through our code.
