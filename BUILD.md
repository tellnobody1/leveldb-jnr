```bash
git clone https://github.com/google/snappy.git
cd snappy
mkdir build
cd build
cmake .. -DBUILD_SHARED_LIBS=true
make
make install
cmake .. -DBUILD_SHARED_LIBS=false
make
make install

git clone https://github.com/google/leveldb.git
cd leveldb
mkdir build
cd build
export LDFLAGS="-L/usr/local/lib"
export CPPFLAGS="-I/usr/local/include"
export CFLAGS="-I/usr/local/include"
export CXXFLAGS="-I/usr/local/include"
export LD_LIBRARY_PATH="-L/usr/local/lib"
export C_INCLUDE_PATH="-L/usr/local/lib"
export CPLUS_INCLUDE_PATH="-L/usr/local/lib"
cmake .. -DBUILD_SHARED_LIBS=false -DCMAKE_INSTALL_PREFIX=../install
make
g++ -fpic -shared -Wl,-all_load libleveldb.a /usr/local/lib/libsnappy.a -Wl -o libleveldb.dylib

brew install crc32c gperftools coreutils
libtool -static -o combined.a \
  $(greadlink -f /usr/local/lib/libcrc32c.a) \
  $(greadlink -f /usr/local/lib/libtcmalloc.a) \
  ./snappy/build/libsnappy.a \
  ./leveldb/build/libleveldb.a
g++ -fpic -shared -Wl,-all_load combined.a -Wl,-noall_load -o libleveldb.dylib
```
