#!/bin/bash
#
# replace android.util.Log with blablabla.
# bysong@tudou.com
# 

# with this text, it means sb use android.util.Log method, fix it!!!
#Robot_R="\/\*Robot\*\/"
Robot_R=""

for file in `find . -name "*.java"`; do

echo "file: $file"

#ExpandableListActivity
#TabActivity
cp $file ${file}.bak
sed  "/^.*extends\s*[a-zA-Z0-9-_]*Activity.*/ s/\(.*extends\s*\)\(.*Activity.*\)\(.*\)/\1${PREFIX}_\2\3/" ${file}.bak > $file
rm ${file}.bak


done

