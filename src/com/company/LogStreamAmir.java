package com.company;

import java.io.PrintStream;
import java.util.Arrays;

public class LogStreamAmir extends PrintStream {
    public String getPrint() {
        return Print;
    }

    private final PrintStream out2;
    private String Print = "";

    public LogStreamAmir(PrintStream out1, PrintStream out2) {
        super(out1);
        this.out2 = out2;
    }

    @Override
    public void flush() {
        super.flush();
        out2.flush();
    }

    @Override
    public void write(byte[] buf, int off, int len) {
        super.write(buf, off, len);
        out2.write(buf, off, len);
        Print += new String(Arrays.copyOfRange(buf, 0, len));
    }
}
