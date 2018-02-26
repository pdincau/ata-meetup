node {

    def mvnHome = tool "Maven"
    def app

    stage("Checkout") {
        git url: 'https://github.com/basdijkstra/vienna-meetup'
    }

    stage("Test") {
        sh "${mvnHome}/bin/mvn clean test"
        sh "${mvnHome}/bin/mvn clean -Dhost=http://192.168.99.100 -Dport=32690 test"
   }

}
