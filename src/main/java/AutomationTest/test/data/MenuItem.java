package AutomationTest.test.data;

public class MenuItem {

  public MenuItem(String argName, String argNo, int argLevel, String argParentNo) {
    this.name = argName;
    this.no = argNo;
    this.level = argLevel;
    this.parentNo = argParentNo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNo() {
    return no;
  }

  public void setNo(String no) {
    this.no = no;
  }

  public String getParentNo() {
    return parentNo;
  }

  public void setParentNo(String parentNo) {
    this.parentNo = parentNo;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

//  public List<MenuItem> getSubMenu() {
//    return subMenu;
//  }
//
//  public void setSubMenu(List<MenuItem> subMenu) {
//    this.subMenu = subMenu;
//  }

  private String name;

  private String no;

  private String parentNo;

  private int level;
//
//  private List<MenuItem> subMenu;

}
