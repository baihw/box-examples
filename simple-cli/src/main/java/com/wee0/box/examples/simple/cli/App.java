package com.wee0.box.examples.simple.cli;

import com.wee0.box.BoxConfig;
import com.wee0.box.IBoxConfig;
import com.wee0.box.log.ILogger;
import com.wee0.box.log.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2021/2/27 13:17
 * @Description 功能描述
 * <pre>
 * 补充说明
 * </pre>
 **/
public class App {
    // 日志对象
    private static ILogger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        IBoxConfig _boxConfig = BoxConfig.impl();
        log.debug("_boxConfig: {}", _boxConfig);

        test1();
    }


    static void test1() {

        List<Map<String, String>> _rows = new ArrayList<>(16);
        String[] _keys = new String[]{"交易对手类型名称", "产品名称", "是否符合标准小微企业", "境内外标识", "信用等级", "求偿顺序", "原始期限", "权重"};
        listAddMap(_rows, _keys, new String[]{"中央政府", "债券", null, "境内", null, null, null, "0"});
        listAddMap(_rows, _keys, new String[]{"中央银行", "债券", null, "境内", null, null, null, "0"});
        listAddMap(_rows, _keys, new String[]{"中央政府和中央银行", null, null, null, null, null, null, "0"});
        listAddMap(_rows, _keys, new String[]{"我国地方政府", null, null, "境外", "(1,3]", "高级", null, "-"});
        listAddMap(_rows, _keys, new String[]{null, "1010", null, null, null, null, null, "0.5"});
        listAddMap(_rows, _keys, new String[]{"商业银行", null, null, "境内", null, "高级", "<=3M", "0.2"});
        System.out.println("_rows:" + _rows);

        Map<String, String> _item = new HashMap<>();
        _item.put("交易对手类型名称", "中央银行");
        _item.put("产品名称", "1010");
        _item.put("信用等级", "2");

        Map<String, String> _itemResult = matchMap(_rows, _item);
        System.out.println("_itemResult: " + _itemResult);
    }










    static Map<String, String> matchMap(List<Map<String, String>> rules, Map<String, String> item) {

        return null;
    }

    // 增加一行键值对数据
    static void listAddMap(List<Map<String, String>> data, String[] keys, String... values) {
        if (null == values) return;
        final int _keysLength = keys.length;
        final int _valuesLength = values.length;
        Map<String, String> _row = new HashMap<>(_valuesLength);
        for (int _i = 0; _i < _valuesLength; _i++) {
            if (_i >= _keysLength)
                break;
            _row.put(keys[_i], values[_i]);
        }
        data.add(_row);
    }

}
