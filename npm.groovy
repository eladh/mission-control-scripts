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
        description "Local npm repository"
        packageType "npm"
    }

    remoteRepository("$repo.value-remote") {
        description "Remote npm repository"
        url "https://registry.npmjs.org"
        packageType "npm"
    }

    virtualRepository("$repo.value-virtual") {
        def includedRepos = "$repo.value-remote,$repo.value-local".split(",")*.trim()
        repositories includedRepos
        description "Virtual gradle repository"
        defaultDeploymentRepo "$repo.value-local"
        packageType "npm"
    }
}