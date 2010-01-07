package com.jezhumble.javasysmon;

public class SolarisMonitor implements Monitor {
    private static Monitor monitor = null;

    static {
        if (System.getProperty("os.name").toLowerCase().startsWith("sunos")) {
	    if (System.getProperty("os.arch").toLowerCase().startsWith("x86")) {
	        new NativeLibraryLoader().loadLibrary("libjavasysmonsolx86.so");
                monitor = new SolarisMonitor();
            }
        }
    }

    public SolarisMonitor() {
        JavaSysMon.addSupportedConfig("Solaris (x86)");
        if (monitor != null) {
            JavaSysMon.setMonitor(monitor);
        }
    }

    public String osName() {
        return System.getProperty("os.name");
    }

    public native int numCpus();
    public native long cpuFrequencyInHz();
    public native long uptimeInSeconds();
    public native int currentPid();
    public native CpuTimes cpuTimes();
    public native MemoryStats physical();
    public native MemoryStats swap();

    public ProcessInfo[] processTable() {
        return new ProcessInfo[0];
    }

    public native void killProcess(int pid);
}
