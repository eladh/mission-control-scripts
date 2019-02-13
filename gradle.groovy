art = userInput (
        type : "ARTIFACTORY",
        name : "Instance",
        description : "Please select the artifactory instance to run against"
)

repo = userInput (
        type : "STRING",
        description : "Repository Key",
)

artifactory(art.name) {
    localRepository("$repo.value-local") {
        description "Local gradle repository"
        packageType "gradle"
    }

    remoteRepository("$repo.value-remote") {
        description "Remote gradle repository"
        url "https://jcenter.bintray.com"
        packageType "gradle"
    }

    virtualRepository("$repo.value-virtual") {
        def includedRepos = "$repo.value-remote,$repo.value-local".split(",")*.trim()
        repositories includedRepos
        description "Virtual gradle repository"
        defaultDeploymentRepo "$repo.value-local"
        packageType "gradle"
    }
}