CREATE EXTERNAL TABLE filter(commentID STRING,commentDescription STRING,userID STRING) 
 ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
 STORED AS TEXTFILE
 LOCATION '/user/cloudera/input';
 
 select commentid, commentdescription from filter where commentdescription like '%Hadoop%';