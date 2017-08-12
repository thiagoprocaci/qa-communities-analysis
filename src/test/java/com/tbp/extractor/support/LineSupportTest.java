package com.tbp.extractor.support;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LineSupportTest {

    LineSupport lineSupport;

    @Before
    public void before() {
        lineSupport = new LineSupport();
    }

    @Test
    public void prepare() {
        assertNull(lineSupport.prepareLine(null));
        assertNull(lineSupport.prepareLine(""));
        assertNull(lineSupport.prepareLine(" "));
        assertNull(lineSupport.prepareLine("abc"));
        assertNull(lineSupport.prepareLine("<something"));
        String line = " <row Id=\"-1\" Reputation=\"1\" CreationDate=\"2016-01-11T22:16:50.167\" DisplayName=\"Community\" LastAccessDate=\"2016-01-11T22:16:50.167\" Location=\"on the server farm\" AboutMe=\"&lt;p&gt;Hi, I'm not really a person.&lt;/p&gt;&#xD;&#xA;&lt;p&gt;I'm a background process that helps keep this site clean!&lt;/p&gt;&#xD;&#xA;&lt;p&gt;I do things like&lt;/p&gt;&#xD;&#xA;&lt;ul&gt;&#xD;&#xA;&lt;li&gt;Randomly poke old unanswered questions every hour so they get some attention&lt;/li&gt;&#xD;&#xA;&lt;li&gt;Own community questions and answers so nobody gets unnecessary reputation from them&lt;/li&gt;&#xD;&#xA;&lt;li&gt;Own downvotes on spam/evil posts that get permanently deleted&lt;/li&gt;&#xD;&#xA;&lt;li&gt;Own suggested edits from anonymous users&lt;/li&gt;&#xD;&#xA;&lt;li&gt;&lt;a href=&quot;http://meta.stackoverflow.com/a/92006&quot;&gt;Remove abandoned questions&lt;/a&gt;&lt;/li&gt;&#xD;&#xA;&lt;/ul&gt;\" Views=\"45\" UpVotes=\"1\" DownVotes=\"0\" Age=\"1\" AccountId=\"-1\" /> ";
        assertEquals(line.trim(), lineSupport.prepareLine(line));
    }



}
