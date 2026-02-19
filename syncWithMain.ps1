$BRANCH=$(git branch --show-current)

git checkout main
git pull
git checkout $BRANCH
git merge main
git push
