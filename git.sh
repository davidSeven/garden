#!/bin/sh

branch=""

# git log
# git reset --hard commitId
# git push --force
push() {

    echo "branch:${branch}"

    old_un=`git config user.name`
    old_ue=`git config user.email`

    echo "current user name : ${old_un}"
    echo "current user email: ${old_ue}"

    echo "switch..."

    new_un="xxxcxy"
    new_ue="yy_z3em@163.com"

    git config user.name $new_un
    git config user.email $new_ue

    echo "current user name : $(git config user.name)"
    echo "current user email: $(git config user.email)"

    echo "do git commit -a -m 'add'"
    git commit -a -m 'add'

    echo "do git push origin ${branch}"
    git push origin $branch

    echo "switch..."

    git config user.name $old_un
    git config user.email $old_ue

    #echo "current user name : $(git config user.name)"
    #echo "current user email: $(git config user.email)"
    echo "current user name : `git config user.name`"
    echo "current user email: `git config user.email`"

    pause
}

function pause(){
        read -n 1 -p "$*" INP
        if [ $INP != '' ] ; then
                echo -ne '\b \n'
        fi
}

# 使用说明，用来提示输入参数
usage() {
    echo "Usage: sh shell.sh [push]"
    pause
}

# 合并分支
# git merge <source_branch>
# git push

# set upstream to
# git branch --set-upstream-to=origin/<remote_branch> your_branch

echo "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||"
echo "current: $0"
echo "params1: $1"
echo "params*: $*"
echo "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||"

if [ !$2 ] ; then
  # branch="master"
  branch=`git rev-parse --abbrev-ref HEAD`
else
  branch=$2
fi

echo "branch:${branch}"

case "$1" in
  "push")
    push
    ;;
  *)
    usage
    ;;
esac