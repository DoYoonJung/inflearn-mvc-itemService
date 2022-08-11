package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemRepositoryTest {

    ItemRepositroy itemRepositroy = new ItemRepositroy();

    @AfterEach
    void afterEach() {
        itemRepositroy.clearStore();
    }

    @Test
    void save() {
        Item item = new Item("itemA", 10000, 10);
        System.out.println(item.getId());
        Item savedItem = itemRepositroy.save(item);
        System.out.println(item.getId());
        Item findItem = itemRepositroy.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemRepositroy.save(item1);
        itemRepositroy.save(item2);

        List<Item> result = itemRepositroy.findAll();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);

    }

    @Test
    void updateItem() {
        Item item = new Item("item1", 10000, 10);

        Item savedItem = itemRepositroy.save(item);
        Long itemId = savedItem.getId();

        Item updateParam = new Item("item2", 20000, 30);
        itemRepositroy.update(itemId, updateParam);
        System.out.println(item.getItemName());
        Item findItem = itemRepositroy.findById(itemId);


        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());

    }
}
