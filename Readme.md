## How to build application

`mvn clean package`

## How to run application

`java -jar target/swaggerdemo-0.0.1.jar`

## How to test the scripts
### There are 2 ways to test the script
1. Manual testing can be done by importing **demo-app.postman_collection.json** into postman

### Or

2. Using python scripts under folder tst_scripts

## How to run python test scripts

`cd test_scripts`

### create persons
`python crt_person.py`

### get list of all persons
`python lst_persons.py`

### get person by id
`python get_person.py`

### update person
`python upd_person.py`

### delete person
`python del-person.py`


## How to set the profiles
### adding the profile prefix in main application.properties file e.g

`spring.profiles.active=dev`

### from command line
`java -jar -Dspring.profiles.active=dev target/swaggerdemo-0.0.1.jar`

## on windows
`java -jar '-Dspring.profiles.active=dev' target/swaggerdemo-0.0.1.jar`

### from mvn command line
`mvn spring-boot:run -Dspring-boot.run.profiles=dev`

## on windows
`mvn spring-boot:run '-Dspring-boot.run.profiles=dev'`

## how to create and run the docker image

`docker build -t swagger-demo:1.0 .`

`docker run -d -p 8080:8080 -t swagger-demo:1.0`