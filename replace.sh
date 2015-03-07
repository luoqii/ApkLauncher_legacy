#!/bin/bash
#
# replace android.util.Log with blablabla.
# bysong@tudou.com
# 

# with this text, it means sb use android.util.Log method, fix it!!!
#Robot_R="\/\*Robot\*\/"
Robot_R=""

export PREFIX=com.example.android.apis.stub.Base_
for file in `find . -name "*.java"`; do

echo "file: $file"

#ExpandableListActivity
#TabActivity
cp $file ${file}.bak
sed  "/^.*extends\s*[^_]*Activity.*/ s/\(.*extends\s*\)\(.*Activity.*\)\(.*\)/\1${PREFIX}\2\3/" ${file}.bak > $file
rm ${file}.bak


done

