BRANCH=$(git branch --show-current)

git stash

git checkout main
git pull

git checkout $BRANCH
git merge main
git push

git stash pop
