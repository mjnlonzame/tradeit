package tradeit.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tradeit.model.ItemValue;
import tradeit.model.Item;
import tradeit.service.ItemService;

@RestController
@RequestMapping(path = "/item", produces = "application/json")
@CrossOrigin(origins = "*")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getById(@PathVariable("id") Long id) {
        Optional<Item> optItem = itemService.getById(id);
        if (optItem.isPresent()) {
            return new ResponseEntity<>(optItem.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<List<Item>> getAll() {
        List<Item> items = itemService.getAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Item> createItem(@RequestBody @Valid Item item, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<Item> optFoundItem = itemService.getByName(item.getName());
        if (optFoundItem.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(itemService.save(item), HttpStatus.CREATED);
    }

    @GetMapping("item-by-value")
    public ResponseEntity<Map<ItemValue, List<Item>>> getGroupedItem() {
        Optional<Map<ItemValue, List<Item>>> optItemByCarVal = itemService.getItemByValue();
        if (optItemByCarVal.isPresent()) {
            return new ResponseEntity<>(optItemByCarVal.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(Collections.emptyMap(), HttpStatus.NOT_FOUND);
    }
//	
//	
//	@PatchMapping("/{id}")
//	public ResponseEntity<Item> patchItem(@PathVariable("id") Long id, @RequestBody Item patchItem) {
// 		Optional<Item> optItem = itemRepository.findById(id);
//		if(optItem.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//		
//		Item item = optItem.get();
//		
//		if(patchItem.getName() != null) {
//			item.setName(patchItem.getName());
//		}
//		
//		if(patchItem.getPrice() != 0) {
//			item.setPrice(patchItem.getPrice());
//		}
//		
//		return new ResponseEntity<>(itemRepository.save(item), HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/{id}")
//	@ResponseStatus(code = HttpStatus.NO_CONTENT)
//	public void deleteItem(@PathVariable("id") Long id) {
//		try {
//			itemRepository.deleteById(id);
//		} catch (EmptyResultDataAccessException e) {
//		}
//	}


}
