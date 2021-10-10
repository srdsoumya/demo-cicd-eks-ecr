pipeline {
    agent any
    stages {
        stage ('Maven Build') {
            steps { 
				sh 'mvn -Dmaven.test.skip=true -f pom.xml clean install' 
            }
        }
        stage ('Docker Build') {
            steps {
				sh 'docker build -t demo-cicd-eks-ecr .' 
				//sh 'docker run -d -p 8089:8089 -t springio/gs-spring-boot-docker'
            }
        }
		stage ('AWS Push') {
            steps {
				sh 'aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 796098993163.dkr.ecr.us-east-1.amazonaws.com'
				sh 'docker tag demo-cicd-eks-ecr:latest 796098993163.dkr.ecr.us-east-1.amazonaws.com/demo-cicd-eks-ecr:latest'
				sh 'docker push 796098993163.dkr.ecr.us-east-1.amazonaws.com/demo-cicd-eks-ecr:latest'
            }
        }
    }
	post {
        always {
				cleanWs()
			}
		}
}