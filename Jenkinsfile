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

     stage('Deploy Docker Image to Dockerhub') {
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

          stage('Upload jar to Nexus') {
               steps {
                 nexusArtifactUploader artifacts: [[artifactId: 'TrophoniusRestServer', classifier: '', file: 'target/TrophoniusRestServer-0.0.1-SNAPSHOT.jar', type: 'jar']],
                 credentialsId: 'c9b3d9ca-d42d-4688-9f04-5d63007b1332',
                 groupId: 'no.itfakultetet',
                 nexusUrl: 'noderia.com:8081',
                 nexusVersion: 'nexus3',
                 protocol: 'http',
                 repository: 'Trophonius',
                 version: '0.0.1-SNAPSHOT'

               }
              }

              stage('Message Mattermost') {
                 steps {

                mattermostSend channel: 'CustoSales', message: 'New version in Github - Jenkins says:  Job Name: ${env.JOB_NAME}   Build Number:  ${env.BUILD_NUMBER}', text: 'You\'re welcome !   From CustoSales Dev Team'

               }
              }
          }

}
