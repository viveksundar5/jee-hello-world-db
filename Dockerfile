FROM kerdlix/wildfly-alpine-mysql:latest
ADD target/helloworldDB-2.0.war /opt/java/server/as/wildfly/standalone/deployments
