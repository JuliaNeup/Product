package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Book one = new Book(1,"Унесенные ветром",350,"Маргарет Митчелл");
    private Book two = new Book(2,"Анна Каренина",150,"Лев Толстой");
    private Book three = new Book(3,"Преступление и наказание",200,"Федор Достоевский");
    private Smartphone four = new Smartphone(4,"Honor",4500,"Китай");
    private  Smartphone five = new Smartphone(5,"Samsung",15000,"Вьетнам");
    private Smartphone six = new Smartphone(6,"Apple",25000,"США");
    private Smartphone seven = new Smartphone(7,"Samsung",28000,"Вьетнам");

    @BeforeEach
    public void setUp(){
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);
        manager.add(seven);
    }

    @Test
    void shouldNotSearchBy() {
    Product[] expected= new Product[0];
    Product[]actual= manager.searchBy("Xiaomi");
    assertArrayEquals(expected,actual);

    }
    @Test
    void  shouldSearchBy () {
        Product[] expected = new Product[] {six};
        Product[] actual = manager.searchBy("Apple");
        assertArrayEquals(expected,actual);
    }

    @Test
    void addSmartphone (){
        Product[] expected = new Product[] {five,seven};
        Product[] actual = manager.searchBy("Samsung");
        assertArrayEquals(expected,actual);
    }

    @Test
    void searchSmartphoneByManufacture(){
        Product[] expected = new Product[] {six};
        Product[] actual = manager.searchBy("США");
        assertArrayEquals(expected,actual);
    }
    @Test
    void searchBookByName (){
        Product[] expected = new Product[] {two};
        Product[] actual = manager.searchBy("Анна Каренина");
        assertArrayEquals(expected,actual);
    }
    @Test
    void searchBookByAutor(){
        Product[] expected = new Product[] {one};
        Product[] actual = manager.searchBy("Маргарет Митчелл");
        assertArrayEquals(expected,actual);
    }
    @Test
    void searchGetAll(){
        Product[] expected = new Product[] {seven,six,five,four,three,two,one};
        Product[] actual = manager.getAll();
        assertArrayEquals(expected,actual);
    }
    @Test
    void searchFindAll(){
        Product[] expected = new Product[] {one,two,three,four,five,six,seven};
        Product[] actual = manager.findAll();
        assertArrayEquals(expected,actual);
    }
    @Test
    void removeByIdTest(){
        Product[] expected = new Product[] {one,three,four,five,six,seven};
        Product[] actual = manager.findAll();
        int id = actual[1].getId();
        manager.removeById(id);
        actual = manager.findAll();
        assertArrayEquals(expected,actual);
    }
    @Test
    void findByIdTest(){
        Product expected = three;
        Product actual = manager.findById(three.getId());
        assertEquals(expected,actual);
    }
}