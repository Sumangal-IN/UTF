
##### How to build this plugin

Execute the following gradle task
```
gradlew publishToMavenLocal
```

##### How to use the pugin in other projects

```
buildscript {
    repositories {
    	mavenCentral()
    	mavenLocal()
    }
    dependencies {
        classpath group: 'com.kingfisher.utf', name: 'utf-gradle-plugin', version: '0.0.1'
    }
}

apply plugin: 'com.kingfisher.utf'
```

Execute the following gradle task in other project
```
gradlew UnitTest
```
