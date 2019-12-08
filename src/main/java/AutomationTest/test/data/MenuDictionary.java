package AutomationTest.test.data;

import java.util.List;

public class MenuDictionary {

  private String name;

  private List<MenuItem> subMenu;

  public List<MenuItem> getSubMenu() {
    return subMenu;
  }

  public void setSubMenu(List<MenuItem> subMenu) {
    this.subMenu = subMenu;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
