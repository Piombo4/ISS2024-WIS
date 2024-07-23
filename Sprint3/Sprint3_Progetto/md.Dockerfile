FROM openjdk:12.0.2
EXPOSE 8020
ADD ./build/distributions/monitoringdevice-1.0-deployment.tar /
WORKDIR /monitoringdevice-1.0/bin
COPY ./*.pl ./

