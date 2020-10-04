package com.lv.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        try {
            csv2jsonvpid("/Users/shuxinghu/Desktop/RechargeInfo.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void csv2jsonvpid(String csvPath) throws IOException {
        int startRow=1;//起始行索引（0开始）
        int gameGoodsIdCol=0;//游戏实际商品id列索引
        int chanGoodsIdCol=2;//渠道商品id列索引
        String chanPrex="c";//渠道商品ID前缀
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
