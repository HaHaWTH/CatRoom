package gg.pufferfish.pufferfish.simd;

public class SIMDDetection {

    private static boolean isInitialized = false;
    static int intVectorBitSize;
    static int floatVectorBitSize;
    static int intElementSize;
    static int floatElementSize;
    static boolean supportingJavaVersion;
    static boolean testRunStarted;
    static boolean testRunCompleted;
    static boolean unsupportingLaneSize;
    static boolean isEnabled;

    public static void initialize() {
        try {
            SIMDChecker.initialize();
        } catch (Throwable ignored) {}
    }

    static void setInitialized() {
        isInitialized = true;
    }

    public static boolean isInitialized() {
        return isInitialized;
    }

    public static int intVectorBitSize() {
        return intVectorBitSize;
    }

    public static int floatVectorBitSize() {
        return floatVectorBitSize;
    }

    public static int intElementSize() {
        return intElementSize;
    }

    public static int floatElementSize() {
        return floatElementSize;
    }

    public static boolean supportingJavaVersion() {
        return supportingJavaVersion;
    }

    public static boolean testRunCompleted() {
        return testRunCompleted;
    }

    public static boolean unsupportingLaneSize() {
        return unsupportingLaneSize;
    }

    public static boolean isEnabled() {
        return isEnabled;
    }

    public static int getJavaVersion() {
        // https://stackoverflow.com/a/2591122
        String version = System.getProperty("java.version");
        if(version.startsWith("1.")) {
            version = version.substring(2, 3);
        } else {
            int dot = version.indexOf(".");
            if(dot != -1) { version = version.substring(0, dot); }
        }
        version = version.split("-")[0]; // Azul is stupid
        return Integer.parseInt(version);
    }

}
