FROM tomcat:9-jdk8
COPY target/classes/com/wizzie/EmploymentM/EmployeeApplication.class /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/
EXPOSE 8080
CMD ["catalina.sh", "run"]
