package com.brouck.horizon.tools;

/**
 * @author lts
 * Create time 2022/3/23
 */
public class CloseUtils {

    public static void close(AutoCloseable closeable) {
        try {
            if (closeable != null)
                closeable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
