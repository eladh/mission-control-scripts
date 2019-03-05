//Artifactory
sourceArti = userInput (
        type : "ARTIFACTORY",
        description : "Please provide the source Artifactory you want to host the replication",
)

targetArti = userInput (
        type : "ARTIFACTORY",
        description : "Please provide the target Artifactory you want to host the replication",

)

repo = userInput (
        type : "STRING",
        description : "Repository Key",
)


artifactory(sourceArti.name) {
    repository("$repo.value-local") {
        starPull(targetArti.name){
            socketTimeoutMillis 60000
            enableEventReplication true
            cronExp '0 0/9 14 * * ?'
            syncDeletes false
            syncProperties false
            syncStatistics false
        }
    }
}