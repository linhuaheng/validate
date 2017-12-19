
import org.junit.Test;

import java.util.*;

/**
 * Created by huaheng.lin on 2017/12/13.
 */
//list中有map,需要根据mao中的一个字段进行排序
public class SortMapTest {

    @Test
    public void sortMap(){
        List<HashMap> list = new ArrayList<HashMap>();
        HashMap map1 = new HashMap<String, Object>();
        map1.put("name", "p");
        map1.put("cj", "5");
        HashMap map2 = new HashMap<String, Object>();
        map2.put("name", "h");
        map2.put("cj", "12");
        HashMap map3 = new HashMap<String, Object>();
        map3.put("name", "f");
        map3.put("cj", "4");
        HashMap map4 = new HashMap<String, Object>();
        map4.put("name", "f");
        map4.put("cj", "31");
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        //排序前
        for (Map<String, Object> map : list) {
            System.out.println(map.get("cj"));
        }
        sortMap(list);
        //排序后
        System.out.println("-------------------");
        for (Map<String, Object> map : list) {
            System.out.println(map.get("cj"));
        }
    }

    private void sortMap(List<HashMap> list) {
        Collections.sort(list, new Comparator<HashMap>() {
            public int compare(HashMap o1, HashMap o2) {
                Integer name1 = Integer.valueOf(o1.get("cj").toString()) ;//name1是从你list里面拿出来的一个
                Integer name2 = Integer.valueOf(o2.get("cj").toString()) ; //name1是从你list里面拿出来的第二个name
                return name1.compareTo(name2);
            }
        });
    }
}
