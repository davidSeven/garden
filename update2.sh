#!/bin/sh

git filter-branch --env-filter '
CORRECT_NAME="小小小程序员"
CORRECT_EMAIL="yy_z3em@163.com"
export GIT_COMMITTER_NAME="$CORRECT_NAME"
export GIT_COMMITTER_EMAIL="$CORRECT_EMAIL"
export GIT_AUTHOR_NAME="$CORRECT_NAME"
export GIT_AUTHOR_EMAIL="$CORRECT_EMAIL"
' --tag-name-filter cat -- --branches --tags