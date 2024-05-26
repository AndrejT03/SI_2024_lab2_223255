import java.util.List;

class Item {
    String name;
    String barcode; //numerical
    int price;
    float discount;

    public Item(String name, String code, int price, float discount) {
        this.name = name;
        this.barcode = code;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getPrice() {
        return price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBarcode(String code) {
        this.barcode = code;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}


public class SILab2 {
    public static boolean checkCart(List<Item> allItems, int payment) {
        if (allItems == null) { //1
            throw new RuntimeException("allItems list can't be null!"); //2
        }

        float sum = 0; //3

        for (int i = 0; i < allItems.size(); i++) { //3 -> 3.1 3.2 3.3
            Item item = allItems.get(i); //4
            if (item.getName() == null || item.getName().length() == 0) { //4
                item.setName("unknown"); //5
            }
            if (item.getBarcode() != null) { //6
                String allowed = "0123456789"; //7
                char chars[] = item.getBarcode().toCharArray(); //7
                for (int j = 0; j < item.getBarcode().length(); j++) { //7 -> 7.1 7.2 7.3
                    char c = item.getBarcode().charAt(j); //8
                    if (allowed.indexOf(c) == -1) { //8
                        throw new RuntimeException("Invalid character in item barcode!"); //9
                    }
                }
                if (item.getDiscount() > 0) { //10
                    sum += item.getPrice() * item.getDiscount(); //11
                } else {
                    sum += item.getPrice(); //12
                }
            } else {
                throw new RuntimeException("No barcode!"); //13
            }
            if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0') { //14
                sum -= 30; //15
            }
        }
        if (sum <= payment) { //16
            return true; //17
        } else {
            return false; //18
        }
    } //19
}