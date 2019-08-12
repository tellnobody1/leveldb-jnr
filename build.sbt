ThisBuild / scalaVersion := "2.12.8"
ThisBuild / organization := "io.github.zero-deps"
ThisBuild / licenses := "MIT" -> url("https://raw.githubusercontent.com/zero-deps/leveldb-jnr/master/LICENSE") :: Nil
ThisBuild / version := {
  val repo = org.eclipse.jgit.api.Git.open(file("."))
  val desc = repo.describe.call
  val dirty = if (repo.status.call.isClean) "" else "-dirty"
  s"${desc}${dirty}"
}
ThisBuild / javacOptions ++= Seq("-source", "12", "-target", "12")
ThisBuild / scalacOptions in Compile ++= Seq(
  "-target:jvm-1.8",
  "-feature",
  "-unchecked",
  "-deprecation",
  "-Xfatal-warnings",
  "-Ywarn-unused:imports",
)
ThisBuild / resolvers += Resolver.bintrayRepo("zero-deps", "maven")
ThisBuild / isSnapshot := true
ThisBuild / publishArtifact := true
ThisBuild / publishArtifact in Test := true

lazy val root = project.in(file(".")).settings(
  name := "leveldb-jnr",
  libraryDependencies ++= Seq(
    "com.github.jnr" % "jnr-ffi" % "2.1.7",
    "org.scalatest" %% "scalatest" % "3.0.1" % Test,
  )
)
