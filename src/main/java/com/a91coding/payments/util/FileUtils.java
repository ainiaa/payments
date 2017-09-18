package com.a91coding.payments.util;

public final class FileUtils {

    public static void main(String[] args) {
        String s = getRandomFileName("ddddd");
        System.out.println("s:" + s);
    }
    public static String getRandomFileName(String oldFileName) {
        String randomStr = RandomStringUtils.getRandomString();
        String ext = getExtensionName(oldFileName);
        return randomStr + ext;
    }

    public static String getExtensionName(String fileName) {
        return getExtensionName(fileName, true);
    }
    /*
 * Java文件操作 获取文件扩展名
 */
    public static String getExtensionName(String fileName, boolean withDot) {
        if ((fileName != null) && (fileName.length() > 0)) {
            int dot = fileName.lastIndexOf('.');
            if ((dot > -1) && (dot < (fileName.length() - 1))) {
                int benginIdx = dot;
                if (!withDot) {
                    benginIdx += 1;
                }
                return fileName.substring(benginIdx);
            }
        }
        return "";
    }

    /*
     * Java文件操作 获取不带扩展名的文件名
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }
}