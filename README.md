# Cloud Storage with Android using the gcloud-java library #

This sample Android app shows the [gcloud-java library for Cloud Storage](https://github.com/GoogleCloudPlatform/gcloud-java/tree/master/gcloud-java-storage) working on an Android device.
 
 
## Configuration
 
1) Android Studio 2.1 Beta 2
 
2) Android plugin for gradle
 
 
## Authentication
 
I use a service account of my cloud project. Replace with yours by downloading the corresponding json file
into assets/ file. You can use alternative authentication mechanisms if desired.
 
Remember to enable BigQuery API on your project.
 
 
## Dependencies
 It is required to exclude some files on the **packagingOptions** block on **build.gradle**.
 
 
```groovy  
     packagingOptions {
         exclude 'META-INF/INDEX.LIST'
     }
 ```
 
The following dependency needs to be added for proper compilation and deployment. It must be noted that the google-api-client-appengine artifact (a dependency of gcloud-java-core) does not work on Android (and is not needed). 
So, it's mandatory to exclude that dependency when adding **gcloud-java-storage** as follows:
 
 ```groovy  
    compile ('com.google.cloud:gcloud-java-storage:0.2.1') {
        exclude group: 'com.google.api-client', module: 'google-api-client-appengine'
        exclude group: 'com.google.guava', module: 'guava-jdk5'
    }
    compile ('com.google.apis:google-api-services-storage:v1beta1-rev131-1.22.0') {
        exclude group: 'com.google.guava', module: 'guava-jdk5'
    }
 ```
 
## Screenshots
 
Main activity: (pending)