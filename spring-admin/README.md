# Spring Boot Admin
Spring Boot Admin is a web application, used for managing and monitoring Spring Boot applications. Each application is considered as a client and registers to the admin server. Behind the scenes, the magic is given by the Spring Boot Actuator endpoints.

## application.properties (Spring Admin Config)
```properties
spring.application.name= spring-admin
spring.security.user.name =admin
spring.security.user.password=admin
spring.boot.admin.notify.mail.enabled=true
spring.boot.admin.notify.mail.to= admin@email.com
spring.boot.admin.notify.mail.cc= email@email.com, email2@email.com
spring.mail.host= smtp.gmail.com
spring.mail.port= 587
spring.mail.username= example@gmail.com
spring.mail.password= app_password_in_case_of_gmail
spring.mail.properties.mail.smtp.auth= true
spring.mail.properties.mail.smtp.starttls.enable= true
```
### Explanation

1. spring.security.user.name =admin: This sets the username for the default Spring Security user as "admin".

2. spring.security.user.password=admin: This sets the password for the default Spring Security user as "admin".

3. spring.boot.admin.notify.mail.enabled=true: This enables email notifications for Spring Boot Admin.

4. spring.boot.admin.notify.mail.to= admin@email.com: This sets the recipient email address for Spring Boot Admin notifications to "admin@email.com".

5. spring.boot.admin.notify.mail.cc= email@email.com, email2@email.com: This sets the CC email address for Spring Boot Admin notifications to "email@email.com" and "email2@email.com".

6. spring.mail.host= smtp.gmail.com: This sets the SMTP server hostname to use for sending emails to "smtp.gmail.com".

7. spring.mail.port= 587: This sets the SMTP server port to use for sending emails to "587".

8. spring.mail.username= example@gmail.com: This sets the username of the email account to use for sending emails to "example@gmail.com".

9. spring.mail.password= app_password_in_case_of_gmail: This sets the password of the email account to use for sending emails to "app_password_in_case_of_gmail". This is likely an app-specific password generated by Google for use with the Gmail account.

10. spring.mail.properties.mail.smtp.auth= true: This enables authentication when sending emails using SMTP.

11. spring.mail.properties.mail.smtp.starttls.enable= true: This enables the use of the STARTTLS command when sending emails using SMTP. This is a security feature that ensures that the email transmission is encrypted.


## Connecting Spring Boot Application to Spring Boot Admin

We need to make changes in application.properties in spring boot client.
We don't need to make any changes in Spring Boot Admin.
### Dependencies : 
pom.xml
```xml
 <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
 </dependency>
 
 <dependency>
        <groupId>de.codecentric</groupId>
        <artifactId>spring-boot-admin-starter-client</artifactId>
 </dependency>
```

### Spring boot Client - application.properties
The following changes need to be made in the application: properties file
```properties
spring.boot.admin.client.url= 
spring.boot.admin.client.username= admin
spring.boot.admin.client.password= admin
spring.boot.admin.client.instance.metadata.user.name= client
spring.boot.admin.client.instance.metadata.user.password= client
spring.boot.admin.client.instance.name= LearnSecurity
management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always
```
### Explanation

1. "spring.boot.admin.client.url" specifies the URL of the Spring Boot Admin server.

2. "spring.boot.admin.client.username" and "spring.boot.admin.client.password" 
specify the username andpassword that the Spring Boot application will use 
to authenticate itself with the Spring Boot Admin server.

3. "spring.boot.admin.client.instance.metadata.user.name" 
and "spring.boot.admin.client.instance.metadata.user.password" 
specify the username and password that the Spring Boot Admin 
server will use to authenticate itself with the Spring Boot application.

4. The line "spring.boot.admin.client.instance.name" specifies 
a name for the Spring Boot application instance that will be 
displayed on the Spring Boot Admin server.

5. The last two lines "management.endpoints.web.exposure.include=*" and "management.endpoint.health.show-details=always" configure 
the Spring Boot application's management endpoints.
The first line exposes all the management endpoints, 
while the second line enables detailed health information
to be displayed in the management endpoints.


