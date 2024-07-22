FROM openjdk:12.0.2
EXPOSE 8014
ADD ../Sprint3/Sprint3_Progetto/build/distributions/wis-1.0 /
WORKDIR /wis-1.0/bin
COPY ./*.pl ./
COPY ./*.json ./
COPY ./*.bin ./
COPY ./*.txt ./

CMD ["bash", "wis"]