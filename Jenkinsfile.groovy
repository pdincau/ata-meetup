node {

    def mvnHome = tool "Maven"
    def app

    stage("Checkout and Test") {
        checkout scm
        sh "${mvnHome}/bin/mvn clean test"
    }

    stage("Build") {
        sh "${mvnHome}/bin/mvn package"
        app = docker.build("pdincau/ata-meetup")
    }

    stage("Test vs Container") {
        docker.image('pdincau/ata-meetup').withRun('-p 9999:8080') { c ->
            sh 'sleep 5'
            sh 'curl -v --fail 127.0.0.1:9999/ping'
            sh 'curl -s http://127.0.0.1:9999/hello?name=Ivo | grep -a \"Hello Ivo!\"'
        }
    }

    stage("Push Image") {
        //app.push("${env.BUILD_NUMBER}")
    }

    stage("Deploy") {
        //sh "kubectl set image deployment/ata-meetup-deployment ata-meetup=pdincau/ata-meetup:${env.BUILD_NUMBER}"
    }

    stage("UAT") {
        build job: 'ata-meetup/UAT'
    }

}
