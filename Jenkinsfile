pipeline {
    agent any
    
    stages {
        stage ('Clean') {
            steps {
                sh 'export MAVEN_HOME=/opt/maven'
                sh 'export PATH=$PATH:$MAVEN_HOME/bin'
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
    }
}
