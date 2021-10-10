pipeline {
    agent any
    stages {
        stage ('Build') {
            steps {
				sh 'mvn clean' 
				sh 'mvn install'
            }
        }
        stage ('Docker Build') {
            steps {
				sh 'docker version' 
            }
        }
		stage ('CleanUp') {
            steps {
				cleanWs() 
            }
        }
    }
}