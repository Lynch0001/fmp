pipeline {

  agent any
  tools{
    jdk 'JDK1.8_221'
  }
  stages {
  
    stage("build"){
 
      steps{
        sh './gradlew clean build'
      }
     }
     
    stage("test"){
 
      steps{
        echo 'Testing application.......'
      }
     }
     
    stage("deploy"){
 
      steps{
        echo 'Deploying application'
      }
     }     
     
     
   }
} 
