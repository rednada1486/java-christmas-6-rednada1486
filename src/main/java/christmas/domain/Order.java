package christmas.domain;

public class Order {
    private Menu menu;
    private int count;

    public Order(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public Order(String menuHyphenCount) {
        String menuName = menuHyphenCount.split("-")[0];
        String count = menuHyphenCount.split("-")[1];

        this.menu = Menu.findByName(menuName);
        this.count = Integer.parseInt(count);
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int calculateSubTotal() {
        return menu.getPrice() * count;
    }

    @Override
    public String toString() {
        return menu.getName() + " " + count + '개';
    }
}
