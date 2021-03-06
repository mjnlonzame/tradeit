package tradeit.controller;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import tradeit.model.FieldErrorMessage;
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

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e) {
//        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
//        List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
//        return fieldErrorMessages;
//    }

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Item> createItem(@RequestBody @Valid Item item) {
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
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteItem(@PathVariable("id") Long id) {
        try {
            itemService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
