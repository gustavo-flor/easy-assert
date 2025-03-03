#bin/bash

project_version="$(lein project-version)"

git tag "$project_version"
git push origin "$project_version"
