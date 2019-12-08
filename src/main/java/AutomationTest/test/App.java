package AutomationTest.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import AutomationTest.test.data.MenuItem;

/**
 * 深度優先探索(multi root)
 * ■input:
 *   test1− test4 −test5−test6−test7−test8
 *                ⊢test9
 *                ⊢test10
 *         ⊢test11
 *         ⊢test12
 *   test2− test13
 *         ⊢test14
 *   test3− test15
 *         ⊢test16
 * ■output:
 *   test8[test1,test4,test5,test6,test7,test8,]
 *   test9[test1,test4,test9,]
 *   test10[test1,test4,test10,]
 *   test11[test1,test11,]
 *   test12[test1,test12,]
 *   test13[test2,test13,]
 *   test14[test2,test14,]
 *   test15[test3,test15,]
 *   test16[test3,test16,]
 *
 */
public class App {
  public static void main(String[] args) {
    List<MenuItem> items = new ArrayList<>();

    // テストデータの作成（input）
    // コンストラクタ MenuItem(String argName, String argNo, int argLevel, String argParentNo)
    items.add(new MenuItem("test1", "0", 1, ""));
    items.add(new MenuItem("test2", "1", 1, ""));
    items.add(new MenuItem("test3", "2", 1, ""));
    items.add(new MenuItem("test4", "3", 2, "0"));
    items.add(new MenuItem("test5", "4", 3, "3"));
    items.add(new MenuItem("test6", "5", 4, "4"));
    items.add(new MenuItem("test7", "6", 5, "5"));
    items.add(new MenuItem("test8", "7", 6, "6"));
    items.add(new MenuItem("test9", "8", 3, "3"));
    items.add(new MenuItem("test10", "9", 3, "3"));
    items.add(new MenuItem("test11", "10", 2, "0"));
    items.add(new MenuItem("test12", "11", 2, "0"));
    items.add(new MenuItem("test13", "12", 2, "1"));
    items.add(new MenuItem("test14", "13", 2, "1"));
    items.add(new MenuItem("test15", "14", 2, "2"));
    items.add(new MenuItem("test16", "15", 2, "2"));

    //　メニュー辞書(output)
    Map<String, List<MenuItem>> dicts = new HashMap<>();

    // ルートから深度優先で探索する(multi rootが存在する)
    items.stream().filter(s -> s.getLevel() == 1).forEach(s -> {
      List<MenuItem> routers = new ArrayList<>(); // 開始点を記憶する
      routers.add(s);
      dicts.put(s.getNo(), routers); // rootのtrace情報
      SetSubMenu(items, s, dicts);
    });

    System.out.printf("OK");
  }

  /**
   * 親項目の全て直下の子項目を洗い出す.rootからの遷移辞書を作成する
   * @param items
   * @param parent
   * @param dicts
   */
  private static void SetSubMenu(List<MenuItem> items, MenuItem parent, Map<String, List<MenuItem>> dicts) {
    int parentLevel = parent.getLevel();
    String parentNo = parent.getNo();

    //parentの直下項目の洗い出す。（上位関連項目No=親項目No）
    List<MenuItem> subMenus = items.stream()
        .filter(s -> parentNo.equals(s.getParentNo()) && s.getLevel() == parentLevel + 1)
        .collect(Collectors.toList());

    if (subMenus == null || subMenus.size() <= 0) {
      // 調査項目存在しない場合（leafとなる場合）、rootからの遷移情報を印刷する
      List<MenuItem> routers = dicts.get(parent.getNo());

      if (routers != null) {
        System.out.printf(parent.getName());
        System.out.printf("[");
        for (MenuItem item : routers) {
          System.out.printf(item.getName()).print(",");;
        }
        System.out.printf("] ");
      }
      return;
    }

    //leafではない場合、rootからの遷移情報を記録しておいて、
    //下の項目を探し続けていく
    subMenus.forEach(s -> {
      List<MenuItem> routers = new ArrayList<>();
      routers.addAll(dicts.get(s.getParentNo()));
      routers.add(s);
      dicts.put(s.getNo(), routers); // rootのtrace情報
      SetSubMenu(items, s, dicts);
    });

  }
}
