libraryDependencies += "org.slf4j" % "slf4j-nop" % "latest.integration"
libraryDependencies += "io.github.zero-deps" %% "ext-git" % "2.0.0"

scalacOptions ++= Seq("-feature","-deprecation")

resolvers += Resolver.bintrayRepo("zero-deps", "maven")