import sbt._
import sbt.Keys._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._


object BuildSettings {
  val buildSettings = Defaults.coreDefaultSettings ++ Seq(
    organization := "co.ifwe",
    version := "0.1.0",
    scalaVersion := "2.10.4",
    scalacOptions ++= Seq("-deprecation", "-feature", "-language:implicitConversions")
  )
}

object PredictBuild extends Build {
  import BuildSettings._

  lazy val root = Project("root", file("."),
    settings = buildSettings
  ) aggregate (antelope, demo, demoweb)

  lazy val antelope = Project("antelope", file("antelope"),
    settings = buildSettings ++ Seq (
      name := "antelope",
      libraryDependencies := libraryDependencies.value ++ Seq(
        "org.slf4j" % "slf4j-simple" % "1.7.7",
        "org.scalatest" %% "scalatest" % "2.2.2"
      )
    )
  )

  lazy val demo = Project("demo", file("demo"),
    settings = buildSettings ++ Seq (
      name := "demo"
    )
  ) dependsOn(antelope)

  val ScalatraVersion = "2.3.0"
  lazy val demoweb = Project ("antelope-best-buy-demo", file("antelope-best-buy-demo"),
    settings = buildSettings ++ ScalatraPlugin.scalatraWithJRebel ++ scalateSettings ++ Seq(
      name := "Antelope Best Buy Demo",
      resolvers += Classpaths.typesafeReleases,
      libraryDependencies ++= Seq(
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
        "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
        "ch.qos.logback" % "logback-classic" % "1.1.2" % "runtime",
        "org.eclipse.jetty" % "jetty-webapp" % "9.1.5.v20140505" % "container",
        "org.eclipse.jetty" % "jetty-plus" % "9.1.5.v20140505" % "container",
        "javax.servlet" % "javax.servlet-api" % "3.1.0",
        "org.scalatra" %% "scalatra-json" % ScalatraVersion,
        "org.json4s"   %% "json4s-jackson" % "3.2.11"
      ),
      scalateTemplateConfig in Compile <<= (sourceDirectory in Compile){ base =>
        Seq(
          TemplateConfig(
            base / "webapp" / "WEB-INF" / "templates",
            Seq.empty,  /* default imports should be added here */
            Seq(
              Binding("context", "_root_.org.scalatra.scalate.ScalatraRenderContext", importMembers = true, isImplicit = true)
            ),  /* add extra bindings here */
            Some("templates")
          )
        )
      }
    )
  ) dependsOn(demo)

}
