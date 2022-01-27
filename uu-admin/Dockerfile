FROM java:8
EXPOSE 8080

VOLUME /tmp
ADD market-admin.jar /market-admin.jar
RUN bash -c 'touch /market-admin.jar'
ENTRYPOINT ["java","-jar","/market-admin.jar"]
