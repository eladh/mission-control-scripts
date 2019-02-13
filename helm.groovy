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
        description "Local helm repository"
        packageType "helm"
    }

    remoteRepository("$repo.value-remote") {
        description "Remote helm repository"
        url "https://storage.googleapis.com/kubernetes-charts"
        packageType "helm"
    }

    virtualRepository("$repo.value-virtual") {
        def includedRepos = "$repo.value-remote,$repo.value-local".split(",")*.trim()
        repositories includedRepos
        description "Virtual gradle repository"
        defaultDeploymentRepo "$repo.value-local"
        packageType "helm"
    }
}