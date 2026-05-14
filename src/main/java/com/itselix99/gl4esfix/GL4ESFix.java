package com.itselix99.gl4esfix;

import java.lang.instrument.Instrumentation;
import java.util.HashMap;
import java.util.Map;

public class GL4ESFix {
    public static final Map<String, String> tessellatorNames = new HashMap<>();
    public static String mcVersion;

    public static void premain(String args, Instrumentation inst) {
        System.out.println("[GL4ES Fix] Initializing GL4ES Fix");
        System.out.println("[GL4ES Fix] Checking Minecraft version");
        mcVersion = System.getProperty("gl4esfix.mcVersion");

        if (mcVersion == null) {
            throw new RuntimeException("Please set -Dgl4esfix.mcVersion in your JVM arguments");
        } else if (!tessellatorNames.containsKey(mcVersion)) {
            throw new RuntimeException("Failed to apply GL4ES Fix: Unsupported Minecraft version");
        }

        inst.addTransformer(new TessellatorTransformer(tessellatorNames.get(mcVersion)));
    }

    static {
        tessellatorNames.put("b1.7.x", "nw");
        tessellatorNames.put("b1.6.x", "ns");
        tessellatorNames.put("b1.5.x", "na");
        tessellatorNames.put("b1.4.x", "lj");
        tessellatorNames.put("b1.3.x", "kv");
        tessellatorNames.put("b1.2.x", "jy");
        tessellatorNames.put("b1.1.x", "jg");
        tessellatorNames.put("b1.0.x", "jf");
        tessellatorNames.put("a1.2.6", "is");
        tessellatorNames.put("a1.2.5-a1.2.3", "ir");
        tessellatorNames.put("a1.2.2", "ip");
        tessellatorNames.put("a1.2.1_01-a1.2.0", "ij");
        tessellatorNames.put("a1.1.x", "ho");
        tessellatorNames.put("a1.0.17_04-a1.0.17_02", "hn");
        tessellatorNames.put("a1.0.16", "he");
        tessellatorNames.put("a1.0.15", "hd");
        tessellatorNames.put("a1.0.14", "ha");
        tessellatorNames.put("a1.0.13", "gy");
        tessellatorNames.put("a1.0.12-a1.0.11", "gx");
        tessellatorNames.put("a1.0.10", "gr");
        tessellatorNames.put("a1.0.9", "go");
        tessellatorNames.put("a1.0.8_01", "gn");
        tessellatorNames.put("a1.0.7", "gl");
        tessellatorNames.put("a1.0.6", "gj");
        tessellatorNames.put("a1.0.5_01", "fz");
        tessellatorNames.put("a1.0.5", "fy");
        tessellatorNames.put("a1.0.4", "ai");
        tessellatorNames.put("a1.0.3-inf_20100630", "ag");
        tessellatorNames.put("inf_20100629-inf_20100627", "ae");
        tessellatorNames.put("inf_20100625-inf_20100611", "net.minecraft.client.a.d");
    }
}