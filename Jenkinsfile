pipeline {

  agent any
  
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
