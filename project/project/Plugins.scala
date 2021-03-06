import sbt._
import Keys._

object Plugins extends Build {
  lazy val plugins = Project(
    "plugins",
    file("."),
    settings = Defaults.defaultSettings ++ Seq(
      libraryDependencies += ("com.github.siasia" %% "xsbt-web-plugin" % "0.12.0-0.2.11.1"),
      addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.1.0"),
      addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.0"),
      addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.0.1"),
      addSbtPlugin("com.github.philcali" % "sbt-jslint" % "0.1.3")))
    .dependsOn(
    // execute jasmin tests during sbt test phase
    uri("git://github.com/guardian/sbt-jasmine-plugin.git#0.7")
  )
}