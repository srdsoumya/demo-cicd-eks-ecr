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
				sh 'docker build -t springio/gs-spring-boot-docker .' 
            }
        }
		stage ('Docker Run') {
            steps {
				sh 'docker run -d -p 8080:8080 -t springio/gs-spring-boot-docker'
            }
        }
    }
}