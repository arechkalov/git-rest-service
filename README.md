# README #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

* A simple REST service which returns details of all publicly available user repositories.
* Version 1.0
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### How do I get set up? ###

* Run com.exercise.app.Application class.
* API of the service is reachable under following endpoint: localhost:8080/repositories/{owner}?forks=(true | false (default false))
* instead of owner write an existing username.
* you can add forks parameter to the URL if you need forks repositories (default is set to false)
