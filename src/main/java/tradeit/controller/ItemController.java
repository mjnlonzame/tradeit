package tradeit.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tradeit.model.Item;
import tradeit.repository.ItemRepository;

@RestController
@RequestMapping(path = "/item", produces = "application/json")
@CrossOrigin(origins = "*")
public class ItemController {
	private final ItemRepository itemRepository;
	@Autowired
	public ItemController(ItemRepository itemRepository) { 
		this.itemRepository = itemRepository;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Item> getById(@PathVariable("id") Long id) {
		Optional<Item> optItem = itemRepository.findById(id);
		if (optItem.isPresent()) {
			return new ResponseEntity<>(optItem.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Item>> getAll(){
		List<Item> items = StreamSupport.stream(itemRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
		if (items.size() > 0) {
			return new ResponseEntity<>(items, HttpStatus.OK);
		}
				
		return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/", consumes = "application/json")
	public ResponseEntity<Item> createItem(@RequestBody @Valid Item item, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Optional<Item> optFoundItem = itemRepository.findByName(item.getName());
		if(optFoundItem.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return  new ResponseEntity<>(itemRepository.save(item), HttpStatus.CREATED);
	}
	
	
	@PatchMapping("/{id}")
	public ResponseEntity<Item> patchItem(@PathVariable("id") Long id, @RequestBody Item patchItem) {
 		Optional<Item> optItem = itemRepository.findById(id);
		if(optItem.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		Item item = optItem.get();
		
		if(patchItem.getName() != null) {
			item.setName(patchItem.getName());
		}
		
		if(patchItem.getPrice() != 0) {
			item.setPrice(patchItem.getPrice());
		}
		
		return new ResponseEntity<>(itemRepository.save(item), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteItem(@PathVariable("id") Long id) {
		try {
			itemRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
		}
	}
	
	
	
}
