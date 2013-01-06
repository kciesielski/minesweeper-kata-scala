#!/bin/sh
java -Dorg.eclipse.jetty.util.log.class=org.eclipse.jetty.util.log.StdErrLog  -Dorg.eclipse.jetty.servlet.LEVEL=ALL -DwithInMemory=true -jar sbt-launch.jar container:start "~ compile"
