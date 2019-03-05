artifactory('artifactory') {
    repository("npm-local", "npm-virtual") {
        starPull('shanghai-artifactory'){
            socketTimeoutMillis 60000
            enableEventReplication true
            cronExp '0 0/9 14 * * ?'
            syncDeletes false
            syncProperties false
            syncStatistics false
        }
    }
}