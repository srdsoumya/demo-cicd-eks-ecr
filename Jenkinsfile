pipeline {
    agent any
	environment {
        AWS_ACCOUNT_ID="796098993163"
        AWS_DEFAULT_REGION="us-east-1"
        IMAGE_REPO_NAME="demo-cicd-eks-ecr"
        IMAGE_TAG="latest"
        REPOSITORY_URI = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPO_NAME}"
    }
    stages {
        stage ('Maven Build') {
            steps {
				script {
                  sh "mvn -Dmaven.test.skip=true -f pom.xml clean install"
                }
            }
        }
        stage ('Docker Build') {
            steps {
                script {
                  dockerImage = docker.build "${IMAGE_REPO_NAME}:${IMAGE_TAG}"
                }
            }
        }
        stage ('AWS ECR') {
            steps {
                  script {
                        sh "aws ecr get-login-password --region ${AWS_DEFAULT_REGION} | docker login --username AWS --password-stdin ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com"
                        sh "docker tag ${IMAGE_REPO_NAME}:${IMAGE_TAG} ${REPOSITORY_URI}:$IMAGE_TAG"
                        sh "docker push ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPO_NAME}:${IMAGE_TAG}"
                  } 
            }
        }
        stage ('Deploy') {
          steps {
                  script {
                      sh "kubectl apply -f springboot-eks-lb.yaml"
                  } 
                }
            }
        }
    post {
          always {
          cleanWs()
        }
      }
}