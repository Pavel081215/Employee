name := "Employee"

version := "1.0"

lazy val `employee` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"




libraryDependencies ++= Seq( jdbc , anorm , cache , ws )

libraryDependencies += "postgresql" % "postgresql" % "9.1-901.jdbc4"


unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  