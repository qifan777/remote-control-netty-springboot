package com.cheng.api.protocol;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class CommonRequest implements Serializable {
    private String command;
    String friendId;
    String userId;
    String requestId;
//    private final static Map<Byte, Class<? extends CommonRequest>> map = new HashMap<>();
//    static {
//        String packageName = Command.class.getPackageName();
//        File file = new File(Command.class.getResource(".").getPath());
//        try {
//            scanFiles(file, new StringBuilder(packageName));
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

//    public static Class<? extends CommonRequest> getType(Byte b) {
//        return map.get(b);
//    }

//    public static void scanFiles(File file, StringBuilder packagePath) throws ClassNotFoundException {
//        File[] files = file.listFiles();
//        assert files != null;
//        for (File file1 : files) {
//            String appendString = "." + file1.getName().replace(".class", "");
//            packagePath.append(appendString);
//            if (file1.isDirectory()) {
//                //递归继续扫描子目录
//                scanFiles(file1, packagePath);
//            } else {
//                //加载有 requestType注解的类,并放到map里面
//                Class<?> aClass = Class.forName(packagePath.toString());
//                RequestType annotation = aClass.getAnnotation(RequestType.class);
//                if (annotation != null) {
//                    Class<? extends CommonRequest> aClass1 = aClass.asSubclass(CommonRequest.class);
//                    byte value = annotation.value();
//                    map.put(value, aClass1);
//                }
//            }
//            packagePath.delete(packagePath.length() - appendString.length(), packagePath.length());
//        }
//
//    }

}
