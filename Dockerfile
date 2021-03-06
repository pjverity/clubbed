FROM openjdk:11-jre-slim-sid

RUN mkdir -p /usr/local/assets/images \
    && addgroup --system clubbed \
    && adduser --system clubbedapp --ingroup clubbed

EXPOSE 8080

ARG ARTIFACT_VERSION

ENV ARTIFACT reigate-mens-joggers-${ARTIFACT_VERSION}.war
ENV DATABASE_HOST=postgres DATABASE_NAME=rmj EXTERNAL_STATIC_ASSETS_PATH=/usr/local/assets

WORKDIR /usr/local

COPY /target/${ARTIFACT} .
COPY assets/images/* /usr/local/assets/images/

RUN chown clubbedapp:clubbed ${ARTIFACT}

USER clubbedapp

ENTRYPOINT ./${ARTIFACT}
