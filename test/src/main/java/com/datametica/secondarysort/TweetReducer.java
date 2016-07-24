package com.datametica.secondarysort;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TweetReducer extends Reducer<TweetKeyPair, Text, Text, Text> {

	@Override
	protected void reduce(TweetKeyPair arg0, Iterable<Text> tweets,
			Reducer<TweetKeyPair, Text, Text, Text>.Context context) throws IOException, InterruptedException {

		for (Text tweet : tweets) {
			context.write(null, tweet);
		}
	}

}
