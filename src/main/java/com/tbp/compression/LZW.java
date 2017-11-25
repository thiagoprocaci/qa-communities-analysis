package com.tbp.compression;


/******************************************************************************
 *  Compilation:  javac LZW.java
 *  Execution:    java LZW - < input.txt   (compress)
 *  Execution:    java LZW + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   https://algs4.cs.princeton.edu/55compression/abraLZW.txt
 *                https://algs4.cs.princeton.edu/55compression/ababLZW.txt
 *
 *  Compress or expand binary input from standard input using LZW.
 *
 *  WARNING: STARTING WITH ORACLE JAVA 6, UPDATE 7 the SUBSTRING
 *  METHOD TAKES TIME AND SPACE LINEAR IN THE SIZE OF THE EXTRACTED
 *  SUBSTRING (INSTEAD OF CONSTANT SPACE AND TIME AS IN EARLIER
 *  IMPLEMENTATIONS).
 *
 *  See <a href = "http://java-performance.info/changes-to-string-java-1-7-0_06/">this article</a>
 *  for more details.
 *
 ******************************************************************************/

/**
 *  The {@code LZW} class provides static methods for compressing
 *  and expanding a binary input using LZW compression over the 8-bit extended
 *  ASCII alphabet with 12-bit codewords.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/55compress">Section 5.5</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class LZW {
    private static final int R = 256;        // number of input chars
    private static final int L = 4096;       // number of codewords = 2^W
    private static final int W = 12;         // codeword width

    // Do not instantiate.
    private LZW() { }

    /**
     * Reads a sequence of 8-bit bytes from standard input; compresses
     * them using LZW compression with 12-bit codewords; and writes the results
     * to standard output.
     */
    public static void compress(String input) {
        System.out.println(input.getBytes().length);
       // String input = BinaryStdIn.readString();
        TST<Integer> st = new TST<Integer>();
        for (int i = 0; i < R; i++)
            st.put("" + (char) i, i);
        int code = R+1;  // R is codeword for EOF

        while (input.length() > 0) {
            String s = st.longestPrefixOf(input);  // Find max prefix match s.
            BinaryStdOut.write(st.get(s), W);      // Print s's encoding.
            int t = s.length();
            if (t < input.length() && code < L)    // Add s to symbol table.
                st.put(input.substring(0, t + 1), code++);
            input = input.substring(t);            // Scan past s in input.

        }
        BinaryStdOut.write(R, W);
        
        BinaryStdOut.close();

    }

    /**
     * Reads a sequence of bit encoded using LZW compression with
     * 12-bit codewords from standard input; expands them; and writes
     * the results to standard output.
     */
    public static void expand() {
        String[] st = new String[L];
        int i; // next available codeword value

        // initialize symbol table with all 1-character strings
        for (i = 0; i < R; i++)
            st[i] = "" + (char) i;
        st[i++] = "";                        // (unused) lookahead for EOF

        int codeword = BinaryStdIn.readInt(W);
        if (codeword == R) return;           // expanded message is empty string
        String val = st[codeword];

        while (true) {
            BinaryStdOut.write(val);
            codeword = BinaryStdIn.readInt(W);
            if (codeword == R) break;
            String s = st[codeword];
            if (i == codeword) s = val + val.charAt(0);   // special case hack
            if (i < L) st[i++] = val + s.charAt(0);
            val = s;
        }
        BinaryStdOut.close();
    }

    /**
     * Sample client that calls {@code compress()} if the command-line
     * argument is "-" an {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        //compress("I like to play soccer. I like it.");
        compress(s);
/*        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");*/
    }

    static String s2 = "\u00040o\u0006�e\u0002 o\u0006�,\u0002 c\u0010!\u0004\u0010a\b\u0007@u\u0007 n\u0002 t\u0006�\u0004\u0007 a\u0006@i\u0006�\u0005\u0006�I\u0007@'\u00070 \u0004`r\u0006�d\u0006\u0010y\u0002 n\u0006�g\u0006�t\u0002 a\u0006�d\u0002 i\u0012�w\u0010`'\u0012�b\u0010@l\u0010`g\u0004po\u0007@t\u0006\u0010 \u0006A\u001B\u0006�(\u0006�a\u0006�r\u0010�p\u0007Q.\u0014p \u0006�a\u0006�e\u0002�u\u0007\u0001\u001C\u0011� \u0012!$\u0012a(\u0012�,\u0012�0\u0013!4\u0002\u00016\u0006�8\u0002\u0001:\u0002\u0001<\u0006�g\u0002\u00018\u0006�l\u0002 I\u0004�f\u0011A\u0004\u0012`n\u00060e\u0006a<\u0006�r\u0017q.\u0017�D\u0013\u0001}\u0017�o\u0018\u0010I\u0002 g\u0014\u0001/\u0006�t\u0018�n\u0006Pe\u0006@N\u0011��\u0014�i\u0012�\u0012�c\u0006\u0010s\u0006��\u0006��\u0018я\u001A\u0001�\u0004!O\u0017Q�\u0012�y\u0006�u\u0016�a\u0006 y\u0004!�\u0012��\u0014Q�\u0019A�\u0014@o\u0019\u0010a\u0007!k\u00170l\u0012\u0010t\u0011�I\u0007a\u0004\u0006`u\u0011!�\u0016\u0011-\u0002��\u0013��\u0010�\u0015\u0006\u0011Y\u0011A$\u0019\u0010s\u0002\u0010)\u001Bq�\u001Aѻ\u0016��\u0013!E\u001C\u0011�\u0006!�\u001Cq�\u0006\u0011�\u001C��\u0010a+\u001D!�\u0006��\u00061�\u001D�h\u001D��\u001D�\u0013q.\u001Ea)\u0014a\u0006\u0006Py\u0004\u0011!\u0016�p\u001A\u0011�\u001A\u0001\u0012\u0006a�\u0017A�\u0013�a\u0007B \u001B��\u0013 n Q� �" +
            "\u0013�/\u0012\u0011�\u0015Pe\u0015�{\u00061�\u0006q\u0001\u00101\u001C\u0010�\u000B\u0011�\u000E\u0011\u0001�\u0011P \u0011q\u0019\u0011�\u0006\u0015�!\u00052\u0016\u0011\u0001_\u0012�1\u001315 \u0011k\u0013�=\u0013�A\u00140p\u0019�P\u0016\u0001J\u001CaM\u001A�Q\u0006��\u0002\u0001\u0015\u0006Q�\u0015�\u001F#�>\u0007\"@\u0016BC\u0016rE\u0016�\u000B\u0017\u0010t\u00171u\u00181\u0013#R(\u0017�\u0018!x&ч&�\u0007!�\u0019�\u0019!)!��\u001Aћ\u0016�y\u001A��\u0012�(\u0011�\u001A\u0010h\u001A�.'��\u001B\u0001�\u0006!�\u001E\u0011�\u001B�\u0019\u001B��\u0006��\u001CA�\u00111�\u001F\u0001�)��\u0007A�\u0016��\u0010A�\u0006Q�&��\u001D��\u001B��$R\u0003)��\u001E��\u001C�W\u001E��*\u0001�\u001FB�\u001Fa�\u001F��\u001E��)Q�!�\u0004\u0015\"\u0006!�\u0016�\" �0\u0002\u0002\u0011%�l\u0010B\u0015!r� \"�!��!�\t,�\f\"0 \"R'\u0018r**A�*r�\u001D��\u0002��*a�*��*��\u001EB�\u001B��\u0006��-�f ь ��!\"�$b\u0016!��/r\u001D r�\"\u0012�.!�.A|\"�O\u0006�\b\u0006�*�\u0005/a�\u001C\u0002�\u001E��)Ҹ)��*\"�,\u0002�,!�\u001D�+\u0002�1��)�Z+q�+�\"+�$.�&.�(\u0002�\u0018'�\u0007-�\t\"\u0002�0��-\"\u0013#R�3�\u0003/��4\u0012�\u001A��\u0018c\u0010\u0016�L\u0006\u0011\b)�V5Qm5�W5�X3b�*�9\u001F�)5CX6c]6�\\)�_*�a\u001F��6Si5�s6sk+�76��6Ct7#u\u0006\u0013l.�o3� ";
    
    static String s = "Come on, come on, turn the radio on" +
            "It's Friday night and it won't be long" +
            "Gotta do my hair, put my make-up on" +
            "It's Friday night and it won't be long 'til I" +
            "Hit the dancefloor" +
            "Hit the dancefloor" +
            "I got all I need" +
            "No I ain't got cash" +
            "I ain't got cash" +
            "But I got you baby" +
            "Baby I don't need dollar bills to have fun tonight" +
            "(I love cheap thrills!)" +
            "Baby I don't need dollar bills to have fun tonight" +
            "(I love cheap thrills!)" +
            "I don't need no money" +
            "As long as I can feel the beat" +
            "I don't need no money" +
            "As long as I keep dancing" +
            "Come on come on, turn the radio on" +
            "It's Saturday and it won't be long" +
            "Gotta paint my nails, put my high heels on" +
            "It's Saturday and it won't be long 'til I" +
            "Hit the dancefloor" +
            "Hit the dancefloor" +
            "I got all I need" +
            "No I ain't got cash" +
            "I ain't got cash" +
            "But I got you baby" +
            "Baby I don't need dollar bills to have fun tonight" +
            "(I love cheap thrills!)" +
            "Baby I don't need dollar bills to have fun tonight" +
            "(I love cheap thrills!)" +
            "I don't need no money" +
            "As long as I can feel the beat" +
            "I don't need no money" +
            "As long as I keep dancing" +
            "(I love cheap thrills!)" +
            "(I love cheap thrills!)" +
            "I don't need no money" +
            "As long as I can feel the beat" +
            "I don't need no money" +
            "As long as I keep dancing" +
            "Oh, oh" +
            "Baby I don't need dollar bills to have fun tonight" +
            "(I love cheap thrills!)" +
            "Baby I don't need dollar bills to have fun tonight" +
            "(I love cheap thrills!)" +
            "I don't need no money" +
            "As long as I can feel the beat" +
            "I don't need no money" +
            "As long as I keep dancing" +
            "La, la, la, la, la, la, la" +
            "(I love cheap thrills!)" +
            "La, la, la, la, la, la, la" +
            "(I love cheap thrills!)" +
            "La, la, la, la, la, la, la" +
            "(I love cheap thrills!)" +
            "La, la, la, la, la, la" +
            "(I love cheap thrills!)";

}
