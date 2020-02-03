FROM openjdk:8-jdk-stretch@sha256:384c565f6c333cc9fb075774091edf2c46f06e972d5b141994f5e228b2eff001

RUN useradd -m build

RUN mkdir /target
RUN chown build:build /target
VOLUME /target

WORKDIR /home/build/app
COPY sbt build.sbt ./
COPY project/build.properties project/plugins.sbt ./project/
RUN chown -R build:build .

USER build
RUN ./sbt update
