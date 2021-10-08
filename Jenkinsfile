node {
     stage('Clone repository') {
         checkout scm
     }

     stage('Config') {
        sh'''
        cp /var/jenkins_home/ConfigDir/firebase-service-account.json /var/jenkins_home/workspace/EZY-Spring-Boot/src/main/resources
        rm -rf /var/jenkins_home/workspace/EZY-Spring-Boot/src/main/resources/application.yml
        cp /var/jenkins_home/ConfigDir/application.yml /var/jenkins_home/workspace/EZY-Spring-Boot/src/main/resources
        '''
     }


     stage('Build BackEnd') {
        sh'''
        ./gradlew build --exclude-task test
        '''
     }

     stage('Build image') {
        app = docker.build("${REPOSITORY_NAME}/${CONTAINER_NAME}:latest")
     }

     stage('Push image') {
        docker.withRegistry('https://registry.hub.docker.com', 'docker-hub') {
            app.push()
        }
     }

     stage('Code Deploy') {
         sh '''docker stop ${CONTAINER_NAME} || true && docker rm ${CONTAINER_NAME} || true''' // 컨테이너 rm
         sh '''docker rmi -f `docker images | awk '$1 ~ /ezy-server/ {print $3}'`''' // image 삭제
         sh '''docker run -d -p ${PORT}:${PORT} --name ${CONTAINER_NAME} ${REPOSITORY_NAME}/${CONTAINER_NAME}:latest''' // 컨테이너 1 // local : container
     }
}