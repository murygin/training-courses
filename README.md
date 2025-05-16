# Training Courses API

This proof of concept implements a REST API for a training provider to manage its training courses. The API is documented in a Postman Collection: 

[postman.com/dmurygin/training-courses](https://www.postman.com/dmurygin/training-courses/collection/tn7o8gn/training-courses)

## Built With

* [Java](https://en.wikipedia.org/wiki/Java_(programming_language))
* [Spring Boot](https://spring.io/projects/spring-boot)
* [H2 Database](https://www.h2database.com/html/main.html)
* [Apache Maven](https://maven.apache.org/)

## Prerequisites

To build and start the project, the following tools must be installed:

- Git
- Java 21
- [Maven](https://maven.apache.org/install.html)

## Build & Run

1. Clone the repo
   ```sh
   git clone https://github.com/murygin/training-courses.git
   cd training-courses
   ```
2. Compile
   ```sh
   ./mvnw clean compile
   ```
3. Run
   ```sh
   ./mvnw spring-boot:run
   ```
   The application will start on port 8080 by default. You can change the port in the `application.properties` file. The base url is `http://localhost:8080`.


## Usage

The API is documented in a Postman Collection: [postman.com/dmurygin/training-courses](https://www.postman.com/dmurygin/training-courses/collection/tn7o8gn/training-courses)


## Notes

This section contains notes that are important for the further development and operation of this application.

### Security

The application is not secure by default. It is important to implement security measures before deploying the application in a production environment.

**OWASP**

- [OWASP Top Ten](https://owasp.org/www-project-top-ten/)
- [OWASP Web Security Testing Guide](https://github.com/OWASP/wstg/)

**Authentication and authorization**

- Authentication: OAuth2 with JWT Tokens, access delegation
- [Spring Security](https://docs.spring.io/spring-security/reference/index.html)
- OAuth2 Authorization Server: [Keycloak](https://www.keycloak.org/), Open Source, simple integration of other identity providers 

**Supply Chain Management**

- Dependency update tool: [Renovate](https://github.com/renovatebot/renovate) or [Dependabot](https://github.com/dependabot) on GitHub
- Signing artifacts: Docker images, Java archives, etc.
- Automatic checking of security vulnerabilities in the libraries used, in the build tool and also in the runtime environments, e.g. with [Trivy](https://github.com/aquasecurity/trivy)

**Git**

- Git has no built-in secure authentication, so Git commits must be signed

### CI / CD

- [Gitflow vs. trunk-based development](https://www.atlassian.com/continuous-delivery/continuous-integration/trunk-based-development)
- Automatic build of Docker images and deployment in Kubernetes environments after completion of the test suite with end-to-end tests
- Build with: [GitLab pipelines](https://docs.gitlab.com/ci/pipelines/), [GitHub Actions](https://github.com/features/actions), [Jenkins](https://www.jenkins.io/doc/book/pipeline/jenkinsfile/), etc.
- The configuration of the build should be managed together with the source code in the Git repository
- Kubernetes deployment with [Helm](https://helm.sh/)
- Scalability: is configured in Kubernetes ([The Twelve Factors for SaaS apps](https://12factor.net/) must be taken into account)

### Code Quality

- Unit test for classes that are not simple records
- Multi-level integration tests, for the web or API layer, access to the database or a message queue
- Integration tests and unit tests should be run in different profiles: Fast unit tests for the IDE, quick integration tests for each build on the build server, all integration tests only before a merge
- Code review (by developers) integrated in the build process
- Automatic code analysis: [SonarQube](https://www.sonarsource.com/products/sonarqube/)
- Code coverage: [JaCoCo](https://www.jacoco.org/jacoco/)
- Code formatter and linter integrated into the build tool
- During development, [The Twelve Factors](https://12factor.net/) must be taken into account from the very beginning

### Monitoring & Logging

- L ogging agent used in Kubernetes: [Fluentd](https://www.fluentd.org/)
- [ELK Stack](https://www.elastic.co/elastic-stack): Elasticsearch, Logstash und Kibana
- [OpenSearch (Dashboard)](https://opensearch.org/) (Open Source)
- [Grafana](https://grafana.com/) (Open Source)
- [Prometheus](https://prometheus.io/) (Open Source)
- [Sentry - Application monitoring software](https://sentry.io/)
- [Datadog - Cloud Monitoring as a Service](https://www.datadoghq.com/)
- [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)  provides production-ready features such as monitoring and metrics about the running application

## Articles

With the articles in this section you can learn more about frameworks and systems that are used in this application.


**Spring Boot**

- [Building a “Hello, World” RESTful Web Service](https://spring.io/guides/gs/rest-service)
- [Building REST services with Spring](https://spring.io/guides/tutorials/rest)
- [Spring Boot Folder Structure (Best Practices)](https://malshani-wijekoon.medium.com/spring-boot-folder-structure-best-practices-18ef78a81819)
- [Testing the Web Layer - spring.io](https://spring.io/guides/gs/testing-web)
- [Spring Boot MockMVC Best Practices](https://www.kapresoft.com/java/2023/11/16/spring-boot-mockmvc-best-practices.html)


**Postman**

- [Creating an OpenAPI definition from a collection with the Postman API](https://blog.postman.com/creating-an-openapi-definition-from-a-collection-with-the-postman-api/)
- [Postman to OpenAPI](https://www.postman.com/api-evangelist/artificial-intelligence/collection/txy0rdd/postman-to-openapi)


**Misc.**

- [Which data type would you choose for storing currency values like Trading Price? What’s your opinion about Float, Double and BigDecimal?](https://medium.com/@cancerian0684/which-data-type-would-you-choose-for-storing-currency-values-like-trading-price-dd7489e7a439)
- [How to store date, time, and timestamps in UTC time zone with JDBC and Hibernate](https://vladmihalcea.com/how-to-store-date-time-and-timestamps-in-utc-time-zone-with-jdbc-and-hibernate/)

## Contact

Daniel Murygin - [linkedin.com/in/murygin](https://www.linkedin.com/in/murygin/) - daniel.murygin@gmail.com

Project Link: [github.com/murygin/training-cources](https://github.com/murygin/training-cources)