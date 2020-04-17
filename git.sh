#!/bin/sh

push() {
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

    echo "do git push"
    git push

    echo "switch..."

    git config user.name $old_un
    git config user.email $old_ue

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

$1