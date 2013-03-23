libraryDependencies <++= (scalaVersion) { (scalaVersion) =>
  Seq(
    "org.scala-lang"           % "scala-compiler"  % scalaVersion,
    if (scalaVersion startsWith "2.9")
      "org.specs2"  % "specs2_2.9.2"     % "1.12.3"  % "test" else
      "org.specs2"  % "specs2_2.10"     % "1.13"  % "test",
    "junit"                    % "junit"           % "4.8.2"  % "test",
    "com.novocode"             % "junit-interface" % "0.7"    % "test"
  )
}

scalacOptions in console in Compile <+= (packageBin in Compile) map { pluginJar =>
  "-Xplugin:"+pluginJar
}

scalaVersion := "2.10.1"

crossScalaVersions <<= scalaVersion { scalaVersion => Seq(scalaVersion, "2.9.2") }

name := "linter"

organization := "com.foursquare.lint"

//Well, if we're gonna do static analysis, why not see what the compiler already does ;)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Xlint")

scalacOptions ++= Seq("-Ywarn-adapted-args", "-Ywarn-all", "-Ywarn-dead-code", "-Ywarn-inaccessible", "-Ywarn-nullary-override", "-Ywarn-nullary-unit", "-Ywarn-value-discard")
