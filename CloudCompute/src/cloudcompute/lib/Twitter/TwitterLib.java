/*
 * Copyright (C) ChemicalDevelopment 2016
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cloudcompute.lib.Twitter;

/**
 *
 * @author brown
 */
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterLib {
    public static Twitter t;

    public static void init() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("yZpAVykzcxwyohWOVBwuFaaaB")
                .setOAuthConsumerSecret("tXZQzYPeh1YmxGUPYotaKFoeLKeI6m8dAPgPh60l4mSltmMpxw")
                .setOAuthAccessToken("4741197613-i0WRUBMg1oGM2JR6GWnAztKs60u8lhMBhlr8TXD")
                .setOAuthAccessTokenSecret("H4EMPCNPV0NkTom3aUZB8J2uOaWnYHC28vxyTZ1bCvpd6");
        TwitterFactory tf = new TwitterFactory(cb.build());
        t = tf.getInstance();
    }
    
    public static void tweet(String s) throws TwitterException {
        Status status = t.updateStatus(s);
    }
}
