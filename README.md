# Втора лабораториска вежба по Софтверско инженерство

## Андреј Трајковски, бр.на индекс 223255

### Група на код:
Ја добив групата на код 5

### Control Flow Graph
![SI_Lab_2 drawio](https://raw.githubusercontent.com/AndrejT03/SI_2024_lab2_223255/e897ea14c47d5f4d622cb6915e779c78dd59f5b1/SI_Lab_2.drawio.png?token=BHKMB7MP5IPFN5Q7HHC7KKLGKM7YY)
Легенда:
- Жолти јазол: If услов
- Син јазол: For циклус
- Црвена линија: Исфрлање на исклучок на екран
  
### Цикломатска комплексност

Цикломатската комплексност на графот е: E-N+2, каде Е претставува број на ребра, додека N е број за јазли. Во овој граф бројот на ребрата е 31, а бројот на јазли е 23. Па цикломатската комплексност е (31-23)+2=8+2=10

### Тест случаи според критериумот Every statement

@Test
    public void EveryBranchTest() {

        List<Item> allItems1 = new ArrayList<>();
        assertTrue(SILab2.checkCart(allItems1, 0));

        List<Item> allItems2 = new ArrayList<>();
        allItems2.add(new Item("", "12345", 100, 0));
        assertTrue(SILab2.checkCart(allItems2, 100));

        List<Item> allItems3 = new ArrayList<>();
        allItems3.add(new Item("Item1", null, 100, 0));
        Exception exception3 = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(allItems3, 100);
        });
        assertEquals("No barcode!", exception3.getMessage());

        List<Item> allItems4 = new ArrayList<>();
        allItems4.add(new Item("Item1", "012x4", 100, 0));
        Exception exception4 = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(allItems4, 100);
        });
        assertEquals("Invalid character in item barcode!", exception4.getMessage());

        List<Item> allItems5 = new ArrayList<>();
        allItems5.add(new Item("Item1", "12345", 100, 0));
        allItems5.add(new Item("Item2", "02345", 400, 1));
        assertTrue(SILab2.checkCart(allItems5, 1000));

        List<Item> allItems6 = new ArrayList<>();
        allItems6.add(new Item("Item1", "12345", 100, 0));
        allItems6.add(new Item("Item2", "02345", 400, 1));
        assertFalse(SILab2.checkCart(allItems6, 100));
    }

## Тест случаи според критериумот Multiple Condition за if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0')

@Test
    public void MultipleConditionTest() {

        List<Item> items1 = new ArrayList<>();
        items1.add(new Item("Item1", "012345", 100, 1));
        assertTrue(SILab2.checkCart(items1, 1000));

        List<Item> items2 = new ArrayList<>();
        items2.add(new Item("Item1", "02345", 301, 0));
        assertTrue(SILab2.checkCart(items2, 1000));

        List<Item> items3 = new ArrayList<>();
        items3.add(new Item("Item1", "12345", 500, 5));
        assertFalse(SILab2.checkCart(items3, 1000));

        List<Item> items4 = new ArrayList<>();
        items4.add(new Item("Item1", "02345", 430, 1));
        assertTrue(SILab2.checkCart(items4, 1000));
    }

### Објаснување на напишаните unit tests

### Every statement тестирање:

#### Случај 1: Листата allItem=null 
Во овој случај се поминуваат ребрата: 1-2, 2-19. Каде во вториот јазол на екран се испишува Exception("allItems list can't be null!").

#### Случај 2: Листата allItem содржи еден елемент со празно име (allItems.add(new Item("", "12345", 100, 0)))
Во овој случај се поминуваат ребрата: 1-3.1, 3.1-3.2, 3.2-4, 4-5, 5-19. Каде во петтиот јазол се добива item.setName("unknown").

#### Случај 3: Листата allItem содржи еден елемент со име Item1 и празен баркод (allItems.add(new Item("Item1", null, 100, 0)))
Во овој случај се поминуваат ребрата: 1-3.1, 3.1-3.2, 3.2-4, 4-6, 6-13, 13-19. Каде во 13тиот јазол се добива Exception("No barcode!").

#### Случај 4: Листата allItem содржи еден елемент со име Item1 и погрешен баркод (allItems.add(new Item("Item1", 12x345, 100, 0)))
Во овој случај се поминуваат ребрата: 1-3.1, 3.1-3.2, 3.2-4, 4-6, 6-13, 6-7.1, 7.1-7.2, 7.2-8, 8-9, 9-19. Каде во 9тиот јазол се добива Exception("Invalid character in item barcode!").

#### Случај 5: Листата allItem содржи два елемента со имиња Item1 и Item2:  allItems.add(new Item("Item1", "12345", 100, 0)); allItems.add(new Item("Item2", "02345", 400, 1)) и вредност за payment=1000
Во овој случај се поминуваат ребрата: 1-3.1, 3.1-3.2, 3.2-4, 4-6, 6-13, 6-7.1, 7.1-7.2, 7.2-8, 8-7.3, 7.3-7.2, 7.2-10, 10-11, 10-12, 11-14, 12-14, 14-15, 14-3.3, 15-3.3, 3.3-3.2, 3.2-16, 16-17, 17-19.

#### Случај 6: Листата allItem содржи два елемента со имиња Item1 и Item2:  allItems.add(new Item("Item1", "12345", 100, 0)); allItems.add(new Item("Item2", "02345", 400, 1)) и вредност за payment=100
Во овој случај се поминуваат ребрата: 1-3.1, 3.1-3.2, 3.2-4, 4-6, 6-13, 6-7.1, 7.1-7.2, 7.2-8, 8-7.3, 7.3-7.2, 7.2-10, 10-11, 10-12, 11-14, 12-14, 14-15, 14-3.3, 15-3.3, 3.3-3.2, 3.2-16, 16-18, 18-19.

Со овие 6 случаи се изминуваат сите ребра во Control Flow Graph.

### Multiple Condition тестирање:

За да се испитаат сите сценарија ќе треба да испитаме 4 случаеви за if-от:
| item.GetPrice()| item.getDiscout() | item.getBarcode()    |
|----------|-----|-------------|
| F  | X  | X  |
| T | F | X |
| T  | T  | F |
| T  | T  | T |

#### Случај 1: item.GetPrice() e false
Во овој случај не ни е се важни другите два изрази бидејќи ако item.GetPrice() е False, без разлика на другите два изрази, исказот ќе биде false.

#### Случај 2: item.GetPrice() e true, item.getDiscout() e false
Во овој случај не ни е се важен изразот бидејќи item.getBarcode(), односно ако item.getDiscout() e false, без разлика на последниот израз, исказот ќе биде false.

#### Случај 3: item.GetPrice() e true, item.getDiscout() e true, item.getBarcode() e false
Во овој случај бидејќи item.getBarcode() e false, исказот ќе биде false.

#### Случај 4: item.GetPrice() e true, item.getDiscout() e true, item.getBarcode() e true
Во овој случај бидејќи сите изрази се true, исказот ќе биде true.
