FROM openjdk:13-jdk-alpine3.10

RUN apk add --no-cache bash

COPY target/kafka-test-1.0-SNAPSHOT.jar /opt/kafka-test/server/server.jar
COPY start.sh /opt/kafka-test/server/start.sh
COPY doc/kafka_2.12-2.4.0.tgz /opt/kafka-test/kafka_2.12-2.4.0.tgz

RUN chmod -R o+x /opt/kafka-test/ \
    && cd /opt/kafka-test/ \
    && tar -xzf kafka_2.12-2.4.0.tgz \
    && mv kafka_2.12-2.4.0 kafka \
    && rm -rf kafka_2.12-2.4.0.tgz \
    && cd /opt/kafka-test/server

WORKDIR /opt/kafka-test/server/

EXPOSE 8484

CMD /bin/bash /opt/kafka-test/server/start.sh ${springProfile}
