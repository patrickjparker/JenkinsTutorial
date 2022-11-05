pipeline {
    environment {
        registry = "patrickjpark/204calculator"
        registryCredential = 'dockerhub'
        dockerImage=''
    }
    agent any
    tools {
      jdk 'JDK 8'
      maven 'apache maven 3.6.3'
    }
    stages {
        stage ('Clean') {
            steps {
                sh 'mvn clean'
            }
        }
        stage ('Build') {
            steps {
                sh 'mvn compile'
            }
        }
        
        stage ('Short Test') {
            steps {
                sh 'mvn -Dtest=CalculatorTest test'
            }
        }
        
        stage ('Long Tests') {
            steps {
                sh 'mvn -Dtest=CalculatorTestThorough test'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }
        }
        stage ('Package') {
            steps {
                sh 'mvn package'
                archiveArtifacts artifacts: 'src/**/*.java'
                archiveArtifacts artifacts: 'target/*.jar'
            }
        }
        stage ('Building image') {
            steps {
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
            }
        }
        stage ('Deploy Image') {
            steps {
                script {
                    docker.withRegistry('', registryCredential) {
                        dockerImage.push()
                    }
                }
            }
        }
        stage('Remove unused docker image') {
            steps {
                sh "docker rmi $registry:$BUILD_NUMBER"
            }
        }
    }
    post {
        failure {
            mail to: 'patrickjpark@gmail.com',
                subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
                body: "Something is wrong with ${env.BUILD_URL}"
        }
    }
}
