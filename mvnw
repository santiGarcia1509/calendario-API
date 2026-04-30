#!/bin/sh
# ----------------------------------------------------------------------------
# Maven Wrapper startup script, version 3.2.0
# ----------------------------------------------------------------------------

BASEDIR="$(cd "$(dirname "$0")" && pwd)"
WRAPPER_DIR="$BASEDIR/.mvn/wrapper"
WRAPPER_JAR="$WRAPPER_DIR/maven-wrapper.jar"

if [ ! -f "$WRAPPER_JAR" ]; then
  echo "Error: No se encontró $WRAPPER_JAR"
  exit 1
fi

JAVA_EXE="java"
if [ -n "$JAVA_HOME" ] && [ -x "$JAVA_HOME/bin/java" ]; then
  JAVA_EXE="$JAVA_HOME/bin/java"
fi

exec "$JAVA_EXE" -classpath "$WRAPPER_JAR" -Dmaven.multiModuleProjectDirectory="$BASEDIR" org.apache.maven.wrapper.MavenWrapperMain "$@"

