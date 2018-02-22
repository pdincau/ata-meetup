node {

    def mvnHome = tool "Maven"
    def app

    stage("Checkout") {
        git url: 'https://github.com/basdijkstra/vienna-meetup'
    }

    stage("Test") {
        sh "${mvnHome}/bin/mvn clean test"
    }

}
