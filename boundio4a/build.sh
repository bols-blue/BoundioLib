#!/bin/bash
rm -rf src/jp
cp -r ../boundio4j/src/main/java/* ./src/
perl -pi -e 's/(public class)/import android.util.Log;\n\n$1/g' src/*/*/*/*/*/*.java
perl -pi -e 's/(public class)/import android.util.Log;\n\n$1/g' src/*/*/*/*/*.java
perl -pi -e 's/System.out.println\(/Log.d\("boundio4j",/g' src/*/*/*/*/*/*.java
perl -pi -e 's/System.out.println\(/Log.d\("boundio4j",/g' src/*/*/*/*/*.java
ant release
cp bin/classes.jar boundio4a.jar
