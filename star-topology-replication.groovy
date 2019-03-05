//Artifactory
art1 = userInput (
        type : "ARTIFACTORY",
        description : "Please provide the target Artifactory you want to host the replication",
)


artifactory(art1.name) {
    repository("maven-local", "maven-virtual") {
        starPull('Bangkok', 'Cape Town', 'Denver'){
            socketTimeoutMillis 60000
            enableEventReplication true
            cronExp '0 0/9 14 * * ?'
            syncDeletes false
            syncProperties false
            syncStatistics false
        }
    }
}