libraryDependencies += "org.slf4j" % "slf4j-nop" % "latest.integration"
libraryDependencies += "org.eclipse.jgit" % "org.eclipse.jgit" % "latest.integration"

scalacOptions ++= Seq("-feature","-deprecation")

addSbtPlugin("org.foundweekends" % "sbt-bintray" % "latest.integration")
