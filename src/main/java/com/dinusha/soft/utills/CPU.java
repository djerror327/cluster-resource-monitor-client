package com.dinusha.soft.utills;

import org.apache.log4j.Logger;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.function.Supplier;

public interface CPU {
    Logger LOGGER = Logger.getLogger(CPU.class);
    Supplier<Integer> USEAGE = () -> {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = null;
        try {
            name = ObjectName.getInstance("java.lang:type=OperatingSystem");
        } catch (MalformedObjectNameException e) {
            LOGGER.error(e.getStackTrace());
        }
        AttributeList list = null;
        try {
            list = mbs.getAttributes(name, new String[]{"ProcessCpuLoad"});
        } catch (InstanceNotFoundException | ReflectionException e) {
            LOGGER.error(e.getStackTrace());
        }
        if (list.isEmpty()) {
            LOGGER.debug("List is empty : " + (int) Math.ceil(Double.NaN));
            return (int) Math.ceil(Double.NaN);
        }
        Attribute att = (Attribute) list.get(0);
        Double value = (Double) att.getValue();
        // usually takes a couple of seconds before we get real values
        if (value == -1.0) {
            LOGGER.debug("Value is equal to -1.0 : " + (int) Math.ceil(Double.NaN));
            return (int) Math.ceil(Double.NaN);
        }
        // returns a percentage value with 1 decimal point precision
        LOGGER.debug("Returning CPU usage : " + (int) Math.ceil((value * 1000) / 10.0));
        return (int) (Math.ceil((value * 1000) / 10.0));
    };
}
