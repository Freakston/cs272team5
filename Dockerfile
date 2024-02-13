FROM aflplusplus/aflplusplus:latest

RUN apt-get update && apt-get install -y make autoconf automake libtool pkg-config libfontconfig1-dev libfreetype-dev libfribidi-dev libharfbuzz-dev python3-pip && pip3 install meson==0.55.0 ninja

COPY libass /libass

RUN cd /libass && ./autogen.sh && CC=/AFLplusplus/afl-cc CXX=/AFLplusplus/afl-c++ ./configure FUZZ_CPPFLAGS="-DASS_FUZZMODE=1" --disable-asm --disable-shared --enable-fuzz && make

RUN mkdir -p /libass/out

COPY fuzz.sh /fuzz.sh

RUN chmod +x /fuzz.sh

ENTRYPOINT [ "/fuzz.sh" ]