package com.lv.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
//        try {
//            csv2jsonvpid("/Users/shuxinghu/Desktop/googs.csv");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        String temp = "200";
        String ec = encode(temp);
        System.out.println(ec);
        System.out.println(decode(ec));
//        String ja = Testss.jiami(temp);
//        System.out.println(ja);
//        String jie = Testss.jiemi(ja);
//        System.out.println(jie);

    }

    static String encode(String source){
        StringBuilder buffer = new StringBuilder();
        for(int i=0;i<source.length();i++){
            int intNum = source.charAt(i);
            buffer.append(intNum);
        }
        return buffer.toString();
    }

    static String decode(String source){
        StringBuilder buffer = new StringBuilder();
        for(int i=0;i<source.length()/2;i++){
            char t = (char) Integer.parseInt(source.substring(i*2,(i+1)*2));
            buffer.append(t);
        }
        return buffer.toString();
    }





    private static void csv2jsonvpid(String csvPath) throws IOException {
        int startRow=1;//起始行索引（0开始）
        int gameGoodsIdCol=5;//游戏实际商品id列索引
        int chanGoodsIdCol=0;//渠道商品id列索引
        String chanPrex="";//渠道商品ID前缀
        List<String> lines = FileUtils.readLines(new File(csvPath));
        int size = lines.size();
        String rowData;
        String[] vals;
        String pid;
        String chnid;
        HashMap<String, String> pids = new HashMap<String, String>();
        boolean isok=true;
        for (int i = 0; i < size; i++) {
            if(i<startRow){
                continue;
            }
            rowData = StringUtils.trimToNull(lines.get(i));
            vals = rowData.split(",");
            pid = vals[gameGoodsIdCol];
            chnid = chanPrex+vals[chanGoodsIdCol];
            if(pids.containsKey(pid)){
                System.out.println(i+":>>>重复的商品id："+pid+",当前渠道id："+chnid+",已有渠道id:"+pids.get(pid));
                isok=false;
                continue;
            }
            pids.put(pid, chnid);
        }
        JSONObject rs = new JSONObject();
        rs.put("gidmap", pids);
        System.out.println(isok?"<<<<<<<<<<成功>>>>>>>>>>":"<<<<<<<<<<异常>>>>>>>>>>");
        System.out.println("total:"+pids.size());
        String jsonString = rs.toJSONString();
        System.out.println(jsonString);
        System.out.println("字符长度:"+jsonString.length());

    }
}
