# Releasing

To release a new version in Clojars, first make sure your `main` branch is up to date:

```bash
git checkout main && git pull
```

Now run this command:

```bash
./release.sh
```

The `release.sh` script creates a git tag with the project's current version and pushes it to GitHub. 

This will trigger a GithubAction that tests and uploads JAR files to Clojars.
