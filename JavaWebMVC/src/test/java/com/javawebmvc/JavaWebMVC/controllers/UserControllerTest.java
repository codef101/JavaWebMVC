import com.fasterxml.jackson.databind.ObjectMapper;
import com.javawebmvc.JavaWebMVC.controllers.UserController;
import com.javawebmvc.JavaWebMVC.models.UserEntity;
import com.javawebmvc.JavaWebMVC.policies.ValidationResult;
import com.javawebmvc.JavaWebMVC.services.FormService;
import com.javawebmvc.JavaWebMVC.services.PageHitsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class UserControllerTest {

    @Mock
    private FormService formService;

    @Mock
    private PageHitsService pageHitsService;

    @Mock
    private Model model;

    @InjectMocks
    private UserController userController;

    @Test
    public void testUserListWithoutFilter() {
        // Mock the behavior of formService.getAllUsers() to return an empty list
        Mockito.when(formService.getAllUsers()).thenReturn(new ArrayList<>());

        List<UserEntity> userList = new ArrayList<>();
        String result = userController.userList(null, model);

        assertEquals("user-list", result);
        verify(model, times(1)).addAttribute("userList", userList);
        verify(model, times(1)).addAttribute("pageHits", pageHitsService.getPageHits());
    }
}
