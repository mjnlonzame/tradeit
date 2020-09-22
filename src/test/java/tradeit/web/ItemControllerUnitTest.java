package tradeit.web;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import tradeit.controller.ItemController;
import tradeit.service.ItemService;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ItemService itemService;

	@Test
	public void getAllitems() {
		try {
			mockMvc.perform(get("/item/")).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(content().json("[]"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		verify(itemService, times(1)).getAll();
	}

}
