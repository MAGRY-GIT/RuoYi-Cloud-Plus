FROM ubuntu:20.04   as   build

ARG zlmGitUrl="https://gitee.com/xia-chu/ZLMediaKit"

RUN export DEBIAN_FRONTEND=noninteractive &&\
        apt-get update && \
        apt-get install -y --no-install-recommends openjdk-11-jre git   npm build-essential \
        cmake ca-certificates openssl ffmpeg &&\
        mkdir -p /opt/wvp/config /opt/wvp/heapdump /opt/wvp/config /opt/assist/config /opt/assist/heapdump /opt/media/www/record



#RUN cd /home && \
#		git clone "${gitUrl}/wvp-pro-assist.git"
#RUN cd /home/wvp-pro-assist && \
#	git reset --hard 58f1a79136a55a7cd1593c95b56ddadcc2225b61 && \
#        mvn clean package -Dmaven.test.skip=true && \
#        cp /home/wvp-pro-assist/target/*.jar /opt/assist/ && \
#        cp /home/wvp-pro-assist/src/main/resources/application-dev.yml /opt/assist/config/application.yml

RUN cd /home && \
        git clone --depth=1 "${zlmGitUrl}"
RUN cd /home/ZLMediaKit && \
        git submodule update --init --recursive && \
        mkdir -p build release/linux/Release/ &&\
        cd build && \
        cmake -DCMAKE_BUILD_TYPE=Release .. && \
        make -j4 && \
        rm -rf ../release/linux/Release/config.ini && \
        cp -r ../release/linux/Release/* /opt/media
ADD ./jar/wvp.jar ./app.jar

RUN cd /opt/wvp && \
        echo '#!/bin/bash' > run.sh && \
        echo 'echo ${WVP_IP}' >> run.sh && \
        echo 'echo ${WVP_CONFIG}' >> run.sh && \
#        echo 'cd /opt/assist' >> run.sh && \
#        echo 'nohup java ${ASSIST_JVM_CONFIG} -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/opt/assist/heapdump/ -jar *.jar --spring.config.location=/opt/assist/config/application.yml --userSettings.record=/opt/media/www/record/  --media.record-assist-port=18081 ${ASSIST_CONFIG} &' >> run.sh && \
        echo 'nohup /opt/media/MediaServer -d -m 3 &' >> run.sh && \
        echo 'cd /opt/wvp' >> run.sh && \
        echo 'java ${WVP_JVM_CONFIG} -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/opt/wvp/heapdump/ -jar *.jar  --media.record-assist-port=18081 ${WVP_CONFIG}' >> run.sh && \
        chmod +x run.sh

FROM ubuntu:20.04

EXPOSE 18080/tcp
EXPOSE 8117/tcp
EXPOSE 8117/udp
EXPOSE 9520/tcp
EXPOSE 1935/tcp
EXPOSE 8554/tcp
EXPOSE 8554/udp
EXPOSE 30000-30500/tcp
EXPOSE 30000-30500/udp

ENV LC_ALL zh_CN.UTF-8

RUN export DEBIAN_FRONTEND=noninteractive &&\
        apt-get update && \
        apt-get install -y --no-install-recommends openjdk-11-jre ca-certificates ffmpeg language-pack-zh-hans && \
        apt-get autoremove -y && \
        apt-get clean -y && \
        rm -rf /var/lib/apt/lists/*dic

COPY --from=build /opt /opt
WORKDIR /opt/wvp
CMD ["sh", "run.sh"]
