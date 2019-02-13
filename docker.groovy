art = userInput (
        type : "ARTIFACTORY",
        name : "Instance",
        description : "Please select1 the artifactory instance to run against"
)

repo = userInput (
        type : "STRING",
        description : "Repository Key",
)

artifactory(art.name) {
    localRepository("$repo.value-local") {
        description "Local docker repository"
        packageType "docker"
    }

    remoteRepository("$repo.value-remote") {
        description "Remote docker repository"
        url "https://registry-1.docker.io/"
        packageType "docker"
    }

    virtualRepository("$repo.value-virtual") {
        def includedRepos = "$repo.value-remote,$repo.value-local".split(",")*.trim()
        repositories includedRepos
        description "Virtual gradle repository"
        defaultDeploymentRepo "$repo.value-local"
        packageType "docker"
    }
}