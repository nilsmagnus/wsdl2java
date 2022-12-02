package com.yupzip.wsdl2java

enum LineEnding {
    /**
     * {@code \n} on unix systems, {@code \r\n} on windows systems.
     */
    PLATFORM_NATIVE(System.lineSeparator()),
    /**
     * {@code \r\n}
     */
    WINDOWS("\r\n"),
    /**
     * {@code \n}
     */
    UNIX("\n"),
    /**
     * {@code \r}
     */
    MAC_CLASSIC("\r");


    private LineEnding(String value) {
        this.value = value;
    }

    private final String value;

    String getValue() {
        return value;
    }
}
