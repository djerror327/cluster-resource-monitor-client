package com.dinusha.soft.utills;

import com.sun.management.OperatingSystemMXBean;
import org.apache.log4j.Logger;

import java.lang.management.ManagementFactory;
import java.util.function.IntSupplier;

public interface CPU {
    Logger LOGGER = Logger.getLogger(CPU.class);
//    Supplier<Integer> USAGE = () -> {
//        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
//        ObjectName name = null;
//        try {
//            name = ObjectName.getInstance("java.lang:type=OperatingSystem");
//        } catch (MalformedObjectNameException e) {
//            LOGGER.error(e.getStackTrace());
//        }
//        AttributeList list = null;
//        try {
//            list = mbs.getAttributes(name, new String[]{"ProcessCpuLoad"});
//        } catch (InstanceNotFoundException | ReflectionException e) {
//            LOGGER.error(e.getStackTrace());
//        }
//        if (list.isEmpty()) {
//            LOGGER.debug("List is empty : " + (int) Math.ceil(Double.NaN));
//            return (int) Math.ceil(Double.NaN);
//        }
//        Attribute att = (Attribute) list.get(0);
//        Double value = (Double) att.getValue();
//        // usually takes a couple of seconds before we get real values
//        if (value == -1.0) {
//            LOGGER.debug("Value is equal to -1.0 : " + (int) Math.ceil(Double.NaN));
//            return (int) Math.ceil(Double.NaN);
//        }
//        // returns a percentage value with 1 decimal point precision
//        LOGGER.debug("Returning CPU usage : " + (int) Math.ceil((value * 1000) / 10.0));
//        return (int) (Math.ceil((value * 1000) / 10.0));
//    };

    IntSupplier USAGE = () -> {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
//        double processCpu = (osBean.getProcessCpuLoad() * osBean.getAvailableProcessors());
        double sysCpu = (osBean.getSystemCpuLoad());
//        double avgCPU = ((processCpu + sysCpu) / 2) * 100;
        LOGGER.debug("Returning average CPU usage : " + (int) Math.ceil(sysCpu * 100));
        return (int) Math.ceil(sysCpu * 100);
    };
}
