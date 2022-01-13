pipeline {
   environment {
          imagename = 'terjebh/trophonius'
          registryCredential = 'dockerhub'
          dockerImage = ''
        }

   agent {
     label "master"
   }
  
  stages {
   
    stage("Maven Package") {
     
      steps {
        sh 'mvn clean package'
      }
      
    }

     stage('Build docker image') {
              steps{
                script {
                  dockerImage = docker.build imagename
                }
              }
           }

     stage('Deploy Image') {
              steps{
                script {
                  docker.withRegistry( '', registryCredential ) {
                    dockerImage.push("$BUILD_NUMBER")
                     dockerImage.push('latest')

                  }
                }
              }
            }
            stage('Remove Unused docker image') {
              steps{
                sh "docker rmi $imagename:$BUILD_NUMBER"
                 sh "docker rmi $imagename:latest"

              }
            }
          }

}
