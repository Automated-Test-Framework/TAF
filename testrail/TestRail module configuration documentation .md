
[Spring multi-module projects](https://spring.io/guides/gs/multi-module)
[Getting Started | Creating a Multi Module Project](https://spring.io/guides/gs/multi-module)
## Lien utiles du projet

[Github du projet](https://github.com/Yojda/TAF)

## Commande utiles

### Spring

Pour compiler le projet 
```bash
mvn clean install
```

Pour run le projet une fois compiler
```Bash
mvn spring-boot:run -pl backend
```

Pour compiler puis run le projet
```Bash
mvn clean install && mvn spring-boot:run -pl backend
```


## Fichiers importants

### Structure

#### backend

La structure du backend est une structure typique d'un application spring

```txt
backend
├── src
│   ├── main
│   │   ├── java
│   │   │   └── ca
│   │   │       └── etsmtl
│   │   │           └── taf
│   │   │               ├── apiCommunication
│   │   │               ├── config
│   │   │               ├── controller
│   │   │               ├── dto
│   │   │               ├── entity
│   │   │               ├── jmeter
│   │   │               │   ├── controllers
│   │   │               │   ├── model
│   │   │               │   ├── provider
│   │   │               │   └── utils
│   │   │               ├── payload
│   │   │               │   ├── request
│   │   │               │   └── response
│   │   │               ├── provider
│   │   │               ├── repository
│   │   │               ├── security
│   │   │               │   ├── jwt
│   │   │               │   └── services
│   │   │               └── service
│   │   └── resources
│   │       ├── jmeter
│   │       │   └── results
│   │       ├── static
│   │       │   ├── css
│   │       │   ├── images
│   │       │   └── js
│   │       └── templates
│   │           └── edit
│   └── test
│       └── java
│           └── ca
│               └── etsmtl
│                   └── taf
└── target
    ├── classes
    │   ├── META-INF
    │   ├── ca
    │   │   └── etsmtl
    │   │       └── taf
    │   │           ├── apiCommunication
    │   │           ├── config
    │   │           ├── controller
    │   │           ├── dto
    │   │           ├── entity
    │   │           ├── jmeter
    │   │           │   ├── controllers
    │   │           │   ├── model
    │   │           │   ├── provider
    │   │           │   └── utils
    │   │           ├── payload
    │   │           │   ├── request
    │   │           │   └── response
    │   │           ├── provider
    │   │           ├── repository
    │   │           ├── security
    │   │           │   ├── jwt
    │   │           │   └── services
    │   │           └── service
    │   ├── jmeter
    │   │   └── results
    │   ├── static
    │   │   ├── css
    │   │   ├── images
    │   │   └── js
    │   └── templates
    │       └── edit
    ├── generated-sources
    │   └── annotations
    ├── generated-test-sources
    │   └── test-annotations
    ├── maven-archiver
    ├── maven-status
    │   └── maven-compiler-plugin
    │       ├── compile
    │       │   └── default-compile
    │       └── testCompile
    │           └── default-testCompile
    ├── surefire-reports
    └── test-classes
        └── ca
            └── etsmtl
                └── taf

```

#### testrail module

Le module *testrail* reprend la structure classique d'un projet spring. Ci-dessous est donné sa structure partiel puisqu'elle ne précise pas encore la structure interne aux fichiers sources du module.

```txt
	testrail
	├── src
	│   ├── main
	│   │   ├── java
	│   │   │   └── ca
	│   │   │       └── etsmtl
	│   │   │           └── testrail
	│   │   └── resources
	│   └── test
	│       └── java
	│           └── ca
	│               └── etsmtl
	│                   └── testrail
	└── target
	    ├── classes
	    │   └── ca
	    │       └── etsmtl
	    │           └── testrail
	    ├── generated-sources
	    │   └── annotations
	    ├── generated-test-sources
	    │   └── test-annotations
	    ├── maven-archiver
	    ├── maven-status
	    │   └── maven-compiler-plugin
	    │       ├── compile
	    │       │   └── default-compile
	    │       └── testCompile
	    │           └── default-testCompile
	    ├── surefire-reports
	    └── test-classes
	        └── ca
	            └── etsmtl
	                └── testrail


```


#### packages

##### controller

gestion des requêtes HTTP

##### service

logique métier, notamment les interactions avec TestRail et les autres outils

##### repository
Pour les interactions avec la base de données

##### entity

Les entités de la base de données JPA

##### dto

Les objets de transfert de données

#### Structure

![](TestRail%20module%20Class%20Diagram.svg)

### Configuration
#### Java
##### /pom.xml

> [!FILE] /pom.xml
> Contient les informations de configuration de tous les modules java du projet (backend, testrail). Il s'agit du fichier principal de configuration Java.

Il faut décrire les modules du projet dans ce fichier pour qu'ils soient bien tous compiler

```yml
	<modules>  
	    <module>backend</module>  
	    <module>testrail</module>  
	</modules>
```

C'est aussi dans ce fichier que l'on retrouve la version de à utiliser 

```yml
	<properties>  
		<java.version>17</java.version>  
		<lombok.version>1.18.24</lombok.version>  
	    <spring-boot.version>2.6.6</spring-boot.version>  
	</properties>
```
##### /backend/pom.xml

> [!FILE] /backend/pom.xml
> Contient les informations de configuration propres au module java principal "backend" (ca.etsmtl.taf).
> 

##### /testrail/pom.xml

> [!FIEL] /testrail/pom.xml
> Contient les informations de configuration propres au module java "testrail" (ca.etsmtl.testrail)

#### Docker

##### /docker-compose.yml

> [!FILE] /docker-compose.yml
> Fichier utiliser pour spécifier quels docker crée pour faire tourner l'application. On retrouve aussi des informations comme le mapping des ports.

##### /backend/Dockerfile

> [!FILE] /backend/Dockerfile
> Fichier qui décris comment crée l'image docker du backend du projet. Cette image compile le projet avec tous les modules spécifier dans le fichier /pom.xml.


## Docker compose with dedicate database


**/docker-compose.yml**

```yml
services:  
  backend:  
    image: back  
    container_name: back  
    build:  
      context: ./  
      dockerfile: ./backend/Dockerfile  
    ports:  
      - "8083:8083"  
    env_file:  
      # Specifying the env file with necessaries values  
      - .docker_config.env  
  
  frontend:  
    image: front  
    container_name: front  
    build:  
      context: ./frontend  
    ports:  
      - "4200:80"  
    depends_on:  
      - backend  
    
  selenium:  
    image: selenium  
    container_name: selenium  
    build:  
      context: ./  
      dockerfile: ./selenium/Dockerfile  
    ports:  
      - "8090:8090"  
  
  
  mariadb:  
    image: mariadb  
    container_name: mariadb-2  
    environment:  
      MYSQL_ROOT_PASSWORD: root  
      MYSQL_DATABASE: taf-db  
      MYSQL_USER: taf  
      MYSQL_PASSWORD: taf  
    ports:  
      - "3306:3306"  
    networks:  
      - taf-network  
  
networks:  
  taf-network:  
    driver: bridge
```

**/.docker_config.env**

```env
TEST_API_SERVICE_URL="http://127.0.0.1"  
TEST_API_SERVICE_PORT="8082"  
SPRING_DATASOURCE_URL="jdbc:mysql://127.0.0.1:3306/taf-db"  
SPRING_DATASOURCE_USERNAME="taf"  
SPRING_DATASOURCE_PASSWORD="taf"
```

**/backend/pom.xml**

```xml
	<dependency>  
	    <groupId>org.mariadb.jdbc</groupId>  
	    <artifactId>mariadb-java-client</artifactId>  
	    <version>3.4.1</version>  
	</dependency>
```

**/backend/src/main/ressources/application.yml**

```yml
	spring:  
	  datasource:  
	    driver-class-name: org.mariadb.jdbc.Driver  
	    url: jdbc:mariadb://127.0.0.1:3306/taf-db  
	    username: taf  
	    password: taf
```

