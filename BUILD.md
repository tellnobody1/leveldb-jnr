# Build LevelDB from Source

## macOS

```bash
git clone https://github.com/google/leveldb && cd leveldb
git checkout 1.21
mkdir build && cd build && cmake .. && make
cd ../..
```

```bash
git clone https://github.com/google/snappy && cd snappy
git checkout 1.1.7
mkdir build && cd build && cmake .. && make
cd ../..
```

```bash
# crc32 for libcrc32c, gperftools for libtcmalloc, coreutils for greadlink
brew install crc32c gperftools coreutils
libtool -static -o combined.a \
  $(greadlink -f /usr/local/lib/libcrc32c.a) \
  $(greadlink -f /usr/local/lib/libtcmalloc.a) \
  ./snappy/build/libsnappy.a \
  ./leveldb/build/libleveldb.a
g++ -fpic -shared -Wl,-all_load combined.a -Wl,-noall_load -o libleveldb.dylib
```

## Distribute with JAR

```bash
mkdir -p src/main/resources/lib/
cp libleveldb.dylib src/main/resources/lib/
cp libleveldb.so src/main/resources/lib/
cp libleveldb.dll src/main/resources/lib/
```

```scala
import java.io.File
import java.nio.file.{Files, StandardCopyOption}
import scala.util.Try

def copyLib(name: String): Throwable Either Long = {
  val is = classOf[LevelDb].getResourceAsStream(s"/lib/${name}")
  val dest = new File(s"./tmp/${name}")
  Try {
    dest.mkdirs()
    Files.copy(is, dest.toPath(), StandardCopyOption.REPLACE_EXISTING)
  }.toEither
}

copyLib("libleveldb.dylib")
copyLib("libleveldb.so") 
copyLib("leveldb.dll")

sys.props += "java.library.path" -> "./tmp/"
```
