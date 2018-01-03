package com.example.pradeep.word_meaningsaver.dummy;

import java.util.HashMap;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */


    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;



    private static DummyItem createDummyItem(String position[]) {
        return new DummyItem(position);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String s[] = new String[] {};

        public DummyItem(String s[]) {
           this.s[0]=s[0];
            this.s[1]=s[1];
        }

       // @Override
        //public String toString() {
          //  for (String e:s) {
            //    return e;

            //}
        //}


    }
}
