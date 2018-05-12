#]!/bin/sh
set -x
Def_txt=`git diff | grep -m1 -o "[^ /]*.txt" | head -1`

if [ ! -z $Def_txt ]; then
    Tail=${#Def_txt}-4
    Name=${Def_txt:0:$Tail}
    if [ -e ~/VCTT/Class/$Name.java ]; then
        echo "java exist"
        git add $Name.txt
        git commit -m "modified"
        cp ~/VCTT/Definition/$Name.txt ~/VCTT/Working/
        cp ~/VCTT/Class/$Name.java ~/VCTT/Working/
        cp ~/VCTT/Table/table.txt ~/VCTT/Working/
        cd ~/VCTT/Working/
        bash ~/VCTT/Working/run.sh $Name
    else
        echo "java non exist"
    fi
fi


#echo ${#File_txt} //str length
#Hello="abcde"
#echo ${Hello:0:5-2}
