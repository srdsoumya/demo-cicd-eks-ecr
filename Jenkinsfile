node {
	withEnv(['AWS_ACCOUNT_ID=796098993163','AWS_DEFAULT_REGION=us-east-1','IMAGE_REPO_NAME=info-cicd-eks-ecr','IMAGE_TAG=latest']){

		stage ('Checkout') {
			checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/srdsoumya/demo-cicd-eks-ecr']]])
		}
		stage ('Maven Build') {
			script {
				sh "mvn -Dmaven.test.skip=true -f pom.xml clean install"
			}
		}
		stage ('Docker Build') {
			dockerImage = docker.build "${IMAGE_REPO_NAME}:${IMAGE_TAG}"
		}
		stage ('AWS ECR') {
			script {
				sh "aws ecr get-login-password --region ${AWS_DEFAULT_REGION} | docker login --username AWS --password-stdin ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com"
				sh "docker tag ${IMAGE_REPO_NAME}:${IMAGE_TAG} ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPO_NAME}:$IMAGE_TAG"
				sh "docker push ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPO_NAME}:${IMAGE_TAG}"
			}
		}
		stage ('EKS Deploy') {
			withKubeConfig([credentialsId: 'K8S') {
				sh "kubectl apply -f springboot-eks-lb.yaml"
			}
		}
	}
	post {
        // Clean after build ok
        always {
            cleanWs(cleanWhenNotBuilt: false,
                    deleteDirs: true,
                    disableDeferredWipeout: true,
                    notFailBuild: true,
                    patterns: [[pattern: '.gitignore', type: 'INCLUDE'],
                               [pattern: '.propsfile', type: 'EXCLUDE']])
        }
    }
}