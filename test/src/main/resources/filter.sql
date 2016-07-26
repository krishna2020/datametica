CREATE EXTERNAL TABLE filter(commentID STRING,commentDescription STRING,userID STRING) 
 ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
 STORED AS TEXTFILE
 LOCATION '/user/cloudera/input';
 
CREATE TABLE output AS SELECT commentid, commentdescription from filter where commentdescription like '%Hadoop%';