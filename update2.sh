#!/bin/sh

# git push --force
git filter-branch --env-filter '
CORRECT_NAME="xxxcxy"
CORRECT_EMAIL="yy_z3em@163.com"
export GIT_COMMITTER_NAME="$CORRECT_NAME"
export GIT_COMMITTER_EMAIL="$CORRECT_EMAIL"
export GIT_AUTHOR_NAME="$CORRECT_NAME"
export GIT_AUTHOR_EMAIL="$CORRECT_EMAIL"
' --tag-name-filter cat -- --branches --tags

# git filter-branch -f --env-filter "export GIT_COMMITTER_NAME=xxxcxy export GIT_COMMITTER_EMAIL=yy_z3em@163.com" -- --all
# git push origin --force --all