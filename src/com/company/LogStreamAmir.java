package com.company;

import java.io.PrintStream;

public class LogStreamAmir extends PrintStream {
    private final PrintStream out2;

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
    }
}
