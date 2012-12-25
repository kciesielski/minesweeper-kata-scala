organization := "kpc"

name := "minesweeper-kata"

version := "0.1.0-SNAPSHOT"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype Release" at "https://oss.sonatype.org/content/repositories/releases"

scalaVersion := "2.10.0"

scalaBinaryVersion <<= scalaVersion.identity

libraryDependencies ++= Seq(
"org.specs2" % "specs2_2.10" % "1.13" % "test",
"org.mockito" % "mockito-all" % "1.9.0" % "test"
)
