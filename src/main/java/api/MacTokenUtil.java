package api;

import data.MainCoreDataManager;
import lombok.SneakyThrows;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class MacTokenUtil {
    private static final String mokejimaiMacId = MainCoreDataManager.getMacAuthConfiguration().mokejimaiMacId();
    private static final String mokejimaiMacSecret = MainCoreDataManager.getMacAuthConfiguration().mokejimaiMacSecret();

    public static String getMacToken(String requestUrl) {
        return getMacToken(requestUrl, "GET", "", mokejimaiMacId, mokejimaiMacSecret);
    }

    public static String getMacToken(String requestUrl, String macId, String macSecret) {
        return getMacToken(requestUrl, "GET", "", macId, macSecret);
    }

    public static String getMacToken(String requestUrl, String body) {
        return getMacToken(requestUrl, "POST", body, mokejimaiMacId, mokejimaiMacSecret);
    }

    public static String getMacToken(String requestUrl, String body, String macId, String macSecret) {
        return getMacToken(requestUrl, "POST", body, macId, macSecret);
    }

    @SneakyThrows
    private static String getMacToken(String requestUrl, String method, String body, String macId, String macSecret) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("function process(e,n,r,a){" +
                "               var Hashing = Java.type('com.google.common.hash.Hashing');" +
                "               var StandardCharsets = Java.type('java.nio.charset.StandardCharsets');" +
                "               var Base64 = Java.type('java.util.Base64');" +
                "               function o(t,e,n){" +
                "                   this.nonceChars=\"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\",this.macId=t,this.macSecret=e,this.ext=n" +
                "               }o.prototype.signRequest=function(t,e,n,r,a){" +
                "                   var o=Math.round(new Date/1e3)," +
                "                   s=this.getNonce()," +
                "                   h=this.ext?this.ext:\"\";" +
                "                   \"string\"==typeof r&&(h&&(h+=\"&\"),h+=\"body_hash=\"+this.createBodyHash(r));" +
                "                   var c=this.createMessageHash(o,s,a,n,t,e,h,this.macSecret);" +
                "                   return this.createAuthorizationHeader(this.macId,o,s,c,h)" +
                "               },o.prototype.getNonce=function(){" +
                "                   for(var t=\"\",e=32;e>0;--e)t+=this.nonceChars[Math.round(Math.random()*(this.nonceChars.length-1))];" +
                "                   return t;" +
                "               },o.prototype.createBodyHash=function(t){" +
                "                   return encodeURIComponent(Base64.getEncoder().encodeToString(Hashing.sha256().hashString(t, StandardCharsets.UTF_8).asBytes()));" +
                "               },o.prototype.createMessageHash=function(t,e,n,r,a,o,s,h){" +
                "                   var c=\"\"+t+\"\\n\"+e+\"\\n\"+n+\"\\n\"+r+\"\\n\"+a+\"\\n\"+o+\"\\n\"+s+\"\\n\";" +
                "                   return Base64.getEncoder().encodeToString(Hashing.hmacSha256(h.getBytes(StandardCharsets.UTF_8)).hashString(c, StandardCharsets.UTF_8).asBytes());" +
                "               },o.prototype.createAuthorizationHeader=function(t,e,n,r,a){" +
                "                   return 'MAC id=\"'+t+'\", ts=\"'+e+'\", nonce=\"'+n+'\", mac=\"'+r+'\", ext=\"'+a+'\"'" +
                "               };" +
                "               var s=new o(n,r,a)," +
                "               h=\"" + requestUrl + "\" ;" +
                "               matches=h.match(\"(https?)://(.*?)/(.*)\"),\"https\"===matches[1]?port=443:port=80,host=matches[2],path=\"/\"+matches[3];" +
                "               var c=s.signRequest(host,port,path, JSON.stringify(" + body + "),\"" + method + "\");" +
                "               return c;" +
                "}");

        Invocable invocable = (Invocable) engine;

        Object funcResult = invocable.invokeFunction("process", "wallet_api", macId, macSecret, "");

        return (String) funcResult;
    }
}