

Elasticsearch Java API CRUD Example
===============================================================
Elasticsearch Java API CRUD Example in Spring MVC.

## Getting Started
###  Requirements
- STS(or maven)
- tomcat
- Elasticsearch 5.2.2


### Elasticsearch
- Downlaod From official website, [link](https://www.elastic.co/downloads/past-releases/elasticsearch-5-2-2)

    or 

- Run with docker

```
$ cd es/

$ docker run -p 9200:9200 -p 9300:9300 \ 
    -v ./elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml elasticsearch:5.2.2 
```
- Run with docker-compose
```
$ cd es/

$ docker-compose up
```


### Spring 
- Run with STS
  - import as Maven project
- Using maven to build WAR file


### Configuration
Elasticsearch IP Setting in Spring
- src/main/webapp/WEB-INF/spring-servlet.xml