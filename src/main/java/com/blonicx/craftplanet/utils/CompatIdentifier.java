package com.blonicx.craftplanet.utils;

//? if >=1.21.11 {
/*import net.minecraft.resources.Identifier;
 *///?} else {
import net.minecraft.resources.ResourceLocation;
//?}

//? if >=1.21.11 {
/*
public class CompatIdentifier {

    public static Identifier create(String namespace, String path) {
        return Identifier.fromNamespaceAndPath(namespace, path);
    }

    public static boolean isValidNamespace(String namespace) {
        return Identifier.isValidNamespace(namespace);
    }

    public static Identifier tryBuild(String namespace, String path) {
        return Identifier.tryBuild(namespace, path);
    }
}
*///?} else {
public class CompatIdentifier {

    public static ResourceLocation create(String namespace, String path) {
        return ResourceLocation.fromNamespaceAndPath(namespace, path);
    }

    public static boolean isValidNamespace(String namespace) {
        return ResourceLocation.isValidNamespace(namespace);
    }

    public static ResourceLocation tryBuild(String namespace, String path) {
        return ResourceLocation.tryBuild(namespace, path);
    }
}//?}
