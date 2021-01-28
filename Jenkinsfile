pipeline {

  agent any
  
  stages {
  
    stage("build"){
 
      steps{
        echo 'Building application'
      }
     }
     
    stage("test"){
 
      steps{
        echo 'Testing application.......'
        script{
          sh './gradlew clean test --no-daemon'
        }
      }
     }
     
    stage("deploy"){
 
      steps{
        echo 'Deploying application'
      }
     }     
     
     
   }
} 
