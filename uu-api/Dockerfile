FROM java:8
EXPOSE 8081

VOLUME /tmp
ADD market-api.jar /market-api.jar
RUN bash -c 'touch /market-api.jar'
ENTRYPOINT ["java","-jar","/market-api.jar"]
