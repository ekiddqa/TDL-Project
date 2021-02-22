Coverage: 83.6%
# TDL-Project

A barebones CI to do list organisation web app.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

You'll need to download Maven, Spring Tool, and Java perform unit tests, which can be gotten [here](https://www.oracle.com/java/technologies/javase-downloads.html), [here](https://spring.io/tools) and [here.](https://maven.apache.org/download.cgi)

Check out [this guide](https://www.javatpoint.com/how-to-install-maven) to get Maven running. https://www.javatpoint.com/how-to-install-maven

### Installing
This README won't cover installation for the above as the war file should include everything you need to run the program as long as you don't wish to perform tests.

This program must be executed from the command line. I would run the command line as an admin. This can be done in one of two ways. Skip to **"Running the Program"** to skip running command line instructions.

## 1. One off instances
This won't change default settings. Go to your search field in your OS.

![Windows10 "Type here to search" program](https://cdn.discordapp.com/attachments/761214563660070922/804547527080018010/unknown.png)

Type in "Command" into the search field.

![](https://cdn.discordapp.com/attachments/761214563660070922/804547805896376340/unknown.png)

Right click on the command prompt (or whatever your version of Windows calls the command line) and select "Run as administrator".

![](https://cdn.discordapp.com/attachments/761214563660070922/804548276724301845/unknown.png)

End with an example of getting some data out of the system or using it for a little demo

## 2. Run command line as admin for all instances.
Everytime you run command line it'll be ran as an admin with the benefits that entails. Follow the previous method up to right the right click. Instead of clicking "Run as administrator",
click on "open file location"

![](https://cdn.discordapp.com/attachments/761214563660070922/804549922841296916/unknown.png)

This will take you to the shortcut location. Right click on "Command Prompt" (or your Window's version) shortcut in the Windows Explorer and select "Properties"

![Right click on Command Prompt. Properties is usually at the bottom. Arrow points to this](https://cdn.discordapp.com/attachments/761214563660070922/804550363570896916/unknown.png)

Go to the "Shortcut" tab and then to "Advanced button".

![](https://cdn.discordapp.com/attachments/761214563660070922/804550692215062558/unknown.png)

You should see the option to "Run as administrator". Check this box if it's not already. Click "OK" then "Apply" to make the changes go into effect.

![]https://cdn.discordapp.com/attachments/761214563660070922/804551313370906664/unknown.png)

A quick way to open command line is "Ctrl + r" and then type in "cmd".

### Running the Program
Open your command line. Change the directory to where ever you have the program saved. To change directory use "cd " followed by the directory of the program.

![](https://cdn.discordapp.com/attachments/761214563660070922/813339085459947550/unknown.png)

From here you will need to locate the was file. By default it is at the base level

You'll need to execute the following command:  
```
java -jar TDL-0.0.1-SNAPSHOT.war
```

![](https://cdn.discordapp.com/attachments/761214563660070922/813340369026547722/unknown.png)

If everything has gone as intended the application should start running on local port 9092.


### Unit Tests 

Unit tests confirm whether the a method in a class works as intended. These tests use JUnit and thus require mvn to use despite the program providing a war. The integration tests confirm whether the module connects to the other parts correctly.

```
   @Test
	void testDelete() {
		when(this.repo.existsById(2L)).thenReturn(false);
		
		assertThat(this.service.delete(2L))
				.isTrue();
		
		verify(this.repo, atLeastOnce()).deleteById(2L);
	}
```
The test above mocks the "existsById" method found in the service class' delete() method. By mocking this we can focus on testing service on it's own without having to worry about whether the repository also works.
The assert section then performs the functionality being tested for (in this case deleting the entry with an id of 2) and returns positive if and only if the service returns true (boolean). This is because the tested method
is returns a boolean depending on whether the entry still exists by id at the end (true if it doesn't exist, false otherwise). Lastly the test checks whether the function has at least been done once.

## Performing the prewritten tests
To run the tests yourself you'll need to have Maven installed. From there you'll need to open command line and change directory to where the folder is stored (see Installing). From there run
```
mvn clean package
```

## Deployment

By default the TDL will use H2 database which can be found at http://localhost:9092/h2-console/login.do?jsessionid=bbc1f2253cb6fac6e798c864992de21c.
This database will be wiped upon closure of the program (database specifically). To change to another data base of choice you'll need to edit application.properties.
![Image of application.properties in the windows explorer](https://cdn.discordapp.com/attachments/761214563660070922/813342361299976222/unknown.png)

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management and executable builder
* JUnit - Unit testing the application and red green code refactoring
* Mockito - Unit testing the application - for dependencies
* Visual Studio - Coding in JavaScript, CSS, and HTML
* Sonarqube - Static code analysis

## Written With
* [Eclipse](https://www.eclipse.org/ide/) - IDE that Spring Tool is based on

## Project Board
* [Jira](https://ekiddqa.atlassian.net/secure/BrowseProjects.jspa) - Used for Agile Scrum sprint management
* Dashboard for this project [here](https://ekiddqa.atlassian.net/secure/RapidBoard.jspa?rapidView=3)

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Vinesh Ghela** - *Initial work* - [vineshghela](https://github.com/vineshghela)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Vinesh Ghela
* Alan Davis
* Reese Elder
* Angelica Charry
* Kazumi Kidd
* Gie-Anne Fortuna
* Arsalan Asad
