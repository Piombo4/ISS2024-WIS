FROM openjdk:12.0.2
EXPOSE 8014
ADD ./build/distributions/wis-1.0-deployment.tar.gz /
cmd ["bash","/bin/wis", "||","/bin/monitoringdevice"]
