A = LOAD '$inputFile' using PigStorage(',') AS (commentID: chararray,commentDescription:chararray,userID:chararray);
B = FILTER A BY (commentDescription);
STORE B INTO '$outputFile' using PigStorage(',');