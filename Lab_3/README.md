### Given task 
[Link](https://prnt.sc/zE4-insqP7mL)


### In order to read the API KEY from `local.properties` you can follow these two links:
- [Link 1](https://jordan-mungujakisa.medium.com/how-to-safeguard-your-api-keys-in-android-projects-with-github-secrets-5679e0e89a77)
- [Link 2](https://stackoverflow.com/questions/60474010/read-value-from-local-properties-via-kotlin-dsl)

There are primarily two ways to set up the API KEY without publishing it on GitHub.
You can see the two ways [here](https://prnt.sc/01E7OG6KJS7w), in the `build.gradle.kts` file.

The **<ins>First method</ins>** is that you need to add the API KEY in the `local.properties` file
as follows:
- `API_KEY=YOUR_API_KEY` (without quotes, just paster it there)

Then, in the `build.gradle.kts` file in the `defaultConfig{}` method, add
the following lines:
```kotlin
val key: String = gradleLocalProperties(rootDir).getProperty("API_KEY")
buildConfigField("String", "API_KEY", "\"${key ?: ""}\"")
```
import the this package:
```java
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties;
```
and `Sync` at the end in the top right corner of the file.

The <ins>**Second method**</ins> is pretty much the same, but it's done with
`Properties()` class from `java.util` package. Now, in the `local.properties`
file, add the API KEY as follows:
- `API_KEY="YOUR_API_KEY"` (with quotes)

Then, in the `build.gradle.kts` file in the `defaultConfig{}` method, add
the following lines:
```kotlin
val props = Properties()
props.load(project.rootProject.file("local.properties").inputStream())
buildConfigField("String", "API_KEY", props.getProperty("API_KEY"))
```
and you will be prompted to import `Properties()` class from:
```java
import java.util.Properties;
```
and `Sync` at the end in the top right corner of the file.

At the end, clean and rebuild the project.
`(Build > Clean/Rebuild Project)`

If you did everything correct, it will be created a hidden folder with `BuildConfig` 
class inside. There, you can spot your **API_KEY**.

All the best `:)`.
