import sbt._
import Keys._
import com.gu.SbtJasminePlugin._
import net.virtualvoid.sbt.graph.Plugin._
import com.typesafe.sbt.SbtScalariform._
import sbtjslint.Plugin._
import sbtjslint.Plugin.LintKeys._
object Resolvers {

  val kataResolvers = Seq(
    "Sonatype releases" at "http://oss.sonatype.org/content/repositories/releases/",
    "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
  )
}

object BuildSettings {

  import Resolvers._

  val buildSettings = Defaults.defaultSettings ++ defaultScalariformSettings ++ Seq(

    organization := "kpc",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.9.2",

    resolvers := kataResolvers,
    scalacOptions += "-unchecked",
    classpathTypes ~= (_ + "orbit"),
    libraryDependencies ++= Dependencies.testingDependencies,
    libraryDependencies ++= Dependencies.logging)
}

object Dependencies {

  val scalatraVersion = "2.2.0-RC1"
  val logBackVersion = "1.0.9"

  val scalatra = "org.scalatra" % "scalatra" % scalatraVersion
  val scalatraSpec2 = "org.scalatra" % "scalatra-specs2" % scalatraVersion % "test"
  val scalatraJson = "org.scalatra" % "scalatra-json" % scalatraVersion
  val scalatraBinding = "org.scalatra" % "scalatra-data-binding" % scalatraVersion
  val json4s = "org.json4s" %% "json4s-jackson" % "3.1.0"

  val jetty = "org.eclipse.jetty" % "jetty-webapp" % "8.1.7.v20120910" % "container"

  val logBackClassic = "ch.qos.logback" % "logback-classic" % logBackVersion % "runtime"

  val logging = Seq(logBackClassic)
  val scalatraStack = Seq(scalatra, scalatraSpec2, scalatraJson, json4s, scalatraBinding)

  val testingDependencies = Seq()

  // If the scope is provided;test, as in scalatra examples then gen-idea generates the incorrect scope (test).
  // As provided implies test, so is enough here.
  val servletApiProvided = "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "provided" artifacts (Artifact("javax.servlet", "jar", "jar"))
}

object KataBuild extends Build {
  import Dependencies._
  import BuildSettings._
  import com.github.siasia.WebPlugin.webSettings

  lazy val game: Project = Project(
    "minesweeper",
    file("."),
    settings = buildSettings ++ jasmineSettings ++ webSettings ++ Seq(libraryDependencies ++= scalatraStack ++ Seq(jetty, servletApiProvided),
    libraryDependencies ++= Seq(jetty, servletApiProvided),
    appJsDir <+= sourceDirectory { src => src / "main" / "webapp" / "js" },
    appJsLibDir <+= sourceDirectory { src => src / "main" / "webapp" / "assets" / "js" },
    jasmineTestDir <+= sourceDirectory { src => src / "test" / "unit" },
    jasmineConfFile <+= sourceDirectory { src => src / "test" / "unit" / "test.dependencies.js" },
    jasmineRequireJsFile <+= sourceDirectory { src => src / "test" / "lib" / "require" / "require.js" },
    jasmineRequireConfFile <+= sourceDirectory { src => src / "test" / "unit" / "require.conf.js" },
    (test in Test) <<= (test in Test) dependsOn (jasmine))
  )


}