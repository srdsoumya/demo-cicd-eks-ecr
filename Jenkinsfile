node {
	environment {
        AWS_ACCOUNT_ID="796098993163"
        AWS_DEFAULT_REGION="us-east-1"
        IMAGE_REPO_NAME="info-cicd-eks-ecr"
        IMAGE_TAG="latest"
        REPOSITORY_URI = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPO_NAME}"
	}
	stage ('Checkout') {
		checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/srdsoumya/demo-cicd-eks-ecr']]])"
    }
	stage ('Maven Build') {
		script {
			sh "mvn -Dmaven.test.skip=true -f pom.xml clean install"
        }
    }
}