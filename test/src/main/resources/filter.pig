REGISTER '$pigudfjar';

A = LOAD '$input' using PigStorage(',') AS (commentID: chararray,commentDescription:chararray,userID:chararray);
B = FOREACH (FILTER A BY com.datametica.util.filterUDF(commentDescription)) GENERATE $0, $1;
STORE B INTO '$output' using PigStorage(',');