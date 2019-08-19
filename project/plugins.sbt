libraryDependencies += "org.slf4j" % "slf4j-nop" % "latest.integration"
libraryDependencies += "io.github.zero-deps" %% "gs-git" % "latest.integration"

scalacOptions ++= Seq("-feature","-deprecation")

resolvers += Resolver.jcenterRepo