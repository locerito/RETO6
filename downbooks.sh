#!/bin/bash
TOTAL=10
cd ./data/namenode
for i in `seq 1 $TOTAL`; do
  wget http://www.gutenberg.org/cache/epub/$i/pg$i.txt 
done
cd -

docker exec -it namenode hadoop fs -mkdir /user/bluetab/books
docker exec -it namenode hadoop fs -put /hadoop/dfs/name/pg* /user/bluetab/books

sudo rm -rf ./data/namenode/pg*

