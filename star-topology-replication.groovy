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
    def includedRepos = "$repo.value-remote,$repo.value-virtual".split(",")*.trim()
    repository(includedRepos) {
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