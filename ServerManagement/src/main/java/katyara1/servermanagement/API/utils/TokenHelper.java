package katyara1.servermanagement.API.utils;

//import java.util.Arrays;
//import java.util.Base64;
//import com.google.gson.Gson;
//import katyara1.servermanagement.API.DTOs.UserDto;
//import katyara1.servermanagement.ServerManagement;

//public class TokenHelper {
//    private static Gson _gson;
//
//    public TokenHelper() {
//        _gson = new Gson();
//    }

//    public static String createToken() {
//        UserDto user = new UserDto(
//                ServerManagement.getConfigPlugin().getString("apiserver.username"),
//                ServerManagement.getConfigPlugin().getString("apiserver.password"));
//
//        String token = Arrays.toString(Base64.getEncoder().encode(_gson.toJson(user).getBytes()));
//        ServerManagement.getInstancePlugin().getConfig().set("token", token);
//
//        return token;
//    }

//    public static String getToken() {
//        return  ServerManagement.getInstancePlugin().getConfig().getString("token");
//    }
//}
