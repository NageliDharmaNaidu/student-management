pipeline {
    agent any

    environment {
        APP_NAME = 'student-management'
        JAR_NAME = 'student-management-1.0.0.jar'
        EC2_USER = 'ubuntu'
        EC2_IP   = 'YOUR_EC2_PUBLIC_IP'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building with Maven...'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvn test'
            }
        }

        stage('Deploy to EC2') {
            steps {
                echo 'Deploying to AWS EC2...'
                sshagent(['ec2-ssh-key']) {
                    sh '''
                        scp -o StrictHostKeyChecking=no target/${JAR_NAME} ${EC2_USER}@${EC2_IP}:/home/ubuntu/
                        ssh -o StrictHostKeyChecking=no ${EC2_USER}@${EC2_IP} "
                            pkill -f ${JAR_NAME} || true
                            nohup java -jar /home/ubuntu/${JAR_NAME} > /home/ubuntu/app.log 2>&1 &
                            echo 'App deployed successfully'
                        "
                    '''
                }
            }
        }
    }

    post {
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Deployment failed!'
        }
    }
}
