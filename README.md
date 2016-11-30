# Social Network for university Jean-Monnet Saint-Etienne

[![N|Solid](https://github.com/jbrat/SocialNetworkUJM-SpringReact/blob/master/src/main/resources/static/images/logo_ujm.png?raw=true)](https://www.univ-st-etienne.fr/fr/index.html)

[![N|Solid](http://rubenjgarcia.es/wp-content/uploads/2016/09/springboot.png)](https://projects.spring.io/spring-boot/)

This application was developted for the university Jean-Monnet's students to communicate between us. The objective is to realise a social network like facebook for the university. 

The principal functionnalities for this application : 
  - Manage events, you can add an event like a party, conference, you can update it, join it, delete it when you are the owner
  - Consult actualities, you can add an actuality, update and delete it, all the peoples will watch it
  - You can create a group, add student in it for communciate, organise something 
  - You have a chat to communicate between the students, send messages, delete



### Technical description

This application is based on a spring boot app, there is a part with templating using thymeleaf, actualites, groups, events use the templating thymeleaf. 
And there is a second part which use ReactJS and a Rest API, it's the chat. In the templating part you can find some ajax request.

The application use the MVC architecture, and MVVM pattern, the application will communicate with a MySQL database and you can use SSL to access on the website (https on port 8043).
You can access at the rest API with this link : http://yourdomain.com/api/{repository} for example http://localhost:8080/api/messages will return the messages

`You need to be login on the application to use the functionalities`
The password are encrypted with Bcrypt algorithm.

### Installation

`This application require the java 8 version to be compiled.` 
Download and extract the [java 8 version from oracle](https://www.java.com/fr/download/faq/java8.xml).

This application use maven to get the differents libraries. So you can install maven in command or use an IDE like netbeans or eclipse to run it.

Install maven in command :
```sh
$ apt-get install maven
```

When you have your IDE or maven installed you can install the project, clone the repository and download dependencies
```sh
$ cd yourFolder/
$ git clone https://github.com/jbrat/SocialNetworkUJM-SpringReact.git
$ cd SocialNetworkUJM-SpringReact/
```
If you don't use an IDE : 
```sh
$ mvn clean install 
$ mvn spring-boot:run
```
`` Before launch the application you need to configure your database access in the application.yml in src/main/resources/, put your login, password and database name MySQL``` 

You can access on the social network with : http://localhost:8080 

### Organisation and contributors of the projets 
* [Contributors & organisation](retrospective.md)
* [Time spend on the projects, an evaluation](auto-evaluation.md)

### Development

Want to contribute? Great!

### Todos

 - Write Tests
 - Secure and test the differents form informations
 - Add a new a design
 - Update messages for the chat
 - For a group, show the members of it 
 - Manage actualites for a group
 - Send an alert email for the member of a group when there are an actualities in the group

License
----
MIT

**Free Software, you can use it as you want !**
