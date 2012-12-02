organization := "kpc"

name := "minesweeper-kata"

version := "0.1.0-SNAPSHOT"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

scalaVersion := "2.9.2"

libraryDependencies ++= Seq(
"org.specs2" %% "specs2" % "1.12.3" % "test"
)
