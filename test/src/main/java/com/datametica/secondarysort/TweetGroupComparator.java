package com.datametica.secondarysort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class TweetGroupComparator extends WritableComparator {
	
	public TweetGroupComparator() {
		super();
	}
	
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		TweetKeyPair tkp = (TweetKeyPair) a;
		TweetKeyPair tkp2 = (TweetKeyPair) b;
		return tkp.getEmp_id().compareTo(tkp2.getEmp_id());
	}
}
