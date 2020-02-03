FROM openjdk:8-jre-stretch@sha256:5d22a19e8b1194ff7a1cbb9d4d2241e3cd0e2ddd42cefc41927c945f0414db1d

ARG build_version

EXPOSE 8080

# Include "shush" to decode KMS_ENCRYPTED_STUFF
RUN curl -sL -o /usr/local/bin/shush \
    https://github.com/realestate-com-au/shush/releases/download/v1.3.4/shush_linux_amd64 \
 && chmod +x /usr/local/bin/shush
ENTRYPOINT ["/usr/local/bin/shush", "exec", "--"]

ENV NEW_RELIC_ENV production
ENV JAVA_OPTS "-javaagent:newrelic/newrelic.jar"
ENV NEWRELIC_VERSION 5.2.0
ENV VERSION=$build_version

RUN curl -sL -o newrelic-java-${NEWRELIC_VERSION}.zip \
    https://repo1.maven.org/maven2/com/newrelic/agent/java/newrelic-java/${NEWRELIC_VERSION}/newrelic-java-${NEWRELIC_VERSION}.zip \
 && unzip newrelic-java-${NEWRELIC_VERSION}.zip newrelic/newrelic.jar -d /app \
 && rm -f newrelic-java-${NEWRELIC_VERSION}.zip

WORKDIR /app

ADD newrelic/newrelic.yml /app/newrelic/
ADD target/app.jar /app/

# Copy Run Script under Root WorkDir
ADD support/prod/run /app/

RUN groupadd -r appuser && useradd -r -m -g appuser appuser

USER appuser

CMD exec ./run
