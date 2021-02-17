package cn.fenqing.arithmetic.recursion;

import cn.fenqing.test.RunTime;
import com.alibaba.fastjson.JSON;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 */
public class EightEueenServer {

    private static int size = 8;

    public static void main(String[] args) throws IOException {
//        //创建一个HttpServer实例，并绑定到指定的IP地址和端口号
//        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 0);
//        httpServer.createContext("/", System.out::println);
//        //创建一个HttpContext，将路径为/myserver请求映射到MyHttpHandler处理器
//        httpServer.createContext("/myserver", new MyHttpHandler(1));
//        httpServer.createContext("/myserver2", new MyHttpHandler(2));
//
//        //设置服务器的线程池对象
//        httpServer.setExecutor(Executors.newFixedThreadPool(10));
//
        //启动服务器
//        httpServer.start();
        Resp resp = new Resp();
        resp.setUnderway(new int[size]);
        resp.setFind(new ArrayList<>());
        MyHttpHandler myHttpHandler = new MyHttpHandler(1);
        RunTime.millisecondTest(() -> myHttpHandler.start(resp), "耗时：");
        Resp resp1 = new Resp();
        resp1.setUnderway(new int[size]);
        resp1.setFind(new ArrayList<>());
        MyHttpHandler myHttpHandler1 = new MyHttpHandler(2);
        RunTime.millisecondTest(() -> myHttpHandler1.start2(resp1), "耗时：");
        System.out.println();
    }


}
class MyHttpHandler implements HttpHandler {

    int size = 8;
    int type;
    int num = 0;

    Map<String, Resp> map = new ConcurrentHashMap<>();

    ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public MyHttpHandler(int type) {
        this.type = type;
    }

    @SneakyThrows
    @Override
    public void handle(HttpExchange httpExchange) {
        String requestParam = getRequestParam(httpExchange);
        String[] split = requestParam.split("=");
        String uuid = null;
        if(split.length == 2){
            uuid = split[1];
        }
        Resp resp = map.get(uuid);
        if(resp == null){
            resp = new Resp();
            resp.setUnderway(new int[size]);
            resp.setFind(new ArrayList<>());
            Resp respTemp = resp;
            map.put(uuid, resp);
            new Thread(() -> {
                if(type == 1){
                    start(respTemp);
                }else {
                    start2(respTemp);
                }
            }).start();
        }
        handleResponse(httpExchange, JSON.toJSONString(resp));
    }

    public void start(Resp resp){
        start(resp, 0);
        resp.setOk(true);
    }

    public void start2(Resp resp){
        start2(resp, 0);
        resp.setOk(true);
        System.out.println(num);
    }

    @SneakyThrows
    public void start(Resp resp, int level){
        int[] underway = resp.getUnderway();
        for (int i = 0; i < size; i++) {
            underway[level] = i;
            if(level == size - 1){
                if(isOk(underway)){
                    resp.getFind().add(underway.clone());
                }
            }else {
                start(resp, level + 1);
            }
        }
    }

    @SneakyThrows
    public void start2(Resp resp, int level){
        int[] underway = resp.getUnderway();
        for (int i = 0; i < size; i++) {
            underway[level] = i;
            if(judge(level, underway)){
                if(level == size - 1){
                    resp.getFind().add(underway.clone());
                }else {
                    start2(resp, level + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new MyHttpHandler(1).isOk(new int[]{0, 4, 7, 5, 2, 6, 1, 3}));
    }

    public boolean judge(int n, int[] nums){
        num++;
        for (int i = 0; i < n; i++) {
            if(nums[i] == nums[n] || Math.abs(n - i) == Math.abs(nums[n] - nums[i])){
                return false;
            }
        }
        return true;
    }

    public boolean isOk(int[] nums){
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            for (int j = 0; j < nums.length; j++) {
                int num1 = nums[j];
                //是否在同一列
                //是否在同一斜线
                if(i != j) {
                    if(num == num1){
                        return false;
                    }
                    int index1 = num + Math.abs(i - j);
                    int index2 = num - Math.abs(i - j);
                    if(index1 < size && num1 == index1){
                        return false;
                    }
                    if(index2 > -1 && num1 == index2){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 获取请求参数
     * @param httpExchange a
     * @return a
     * @throws Exception
     */
    private String getRequestParam(HttpExchange httpExchange) throws Exception {
        String paramStr;

        if ("GET".equals(httpExchange.getRequestMethod())) {
            //GET请求读queryString
            paramStr = httpExchange.getRequestURI().getQuery();
        } else {
            //非GET请求读请求体
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8));
            StringBuilder requestBodyContent = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                requestBodyContent.append(line);
            }
            paramStr = requestBodyContent.toString();
        }

        return paramStr;
    }

    /**
     * 处理响应
     * @param httpExchange a
     * @param responsetext a
     * @throws Exception a
     */
    private void handleResponse(HttpExchange httpExchange, String responsetext) throws Exception {
        //生成html
        byte[] responseContentByte = responsetext.getBytes(StandardCharsets.UTF_8);
        //设置响应头，必须在sendResponseHeaders方法之前设置！
        httpExchange.getResponseHeaders().add("Content-Type:", "application/json;charset=utf-8");
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Credentials", "true");
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "*");
        httpExchange.getResponseHeaders().add("Access-Control-Max-Age", "3600");
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Authorization,Origin,X-Requested-With,Content-Type,Accept,"
                + "content-Type,origin,x-requested-with,content-type,accept,authorization,token,id,X-Custom-Header,X-Cookie,Connection,User-Agent,Cookie,*");
        httpExchange.getResponseHeaders().add("Access-Control-Request-Headers", "Authorization,Origin, X-Requested-With,content-Type,Accept");
        httpExchange.getResponseHeaders().add("Access-Control-Expose-Headers", "*");
        //设置响应码和响应体长度，必须在getResponseBody方法之前调用！
        httpExchange.sendResponseHeaders(200, responseContentByte.length);
        OutputStream out = httpExchange.getResponseBody();
        out.write(responseContentByte);
        out.flush();
        out.close();
    }
}

@Data
class Resp {
    private List<int[]> find;
    private int[] underway;
    private boolean ok;
}