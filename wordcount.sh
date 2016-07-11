#!/bin/bash

docker exec -it spark-master bin/spark-submit --class "BookCount" --num-executors 1 --executor-cores 1 target/bookanalytics_2.11-1.0.jar "hdfs://namenode:8020/user/bluetab/books/pg1.txt"
