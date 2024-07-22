FROM openjdk:12.0.2
EXPOSE 8020
ADD ../Sprint3/Sprint3_Progetto/build/distributions/monitoringdevice-1.0 /
WORKDIR /monitoringdevice-1.0/bin
COPY ./*.pl ./
COPY ./*.json ./
COPY ./*.bin ./
COPY ./*.txt ./

CMD ["bash", "monitoring_device"]
