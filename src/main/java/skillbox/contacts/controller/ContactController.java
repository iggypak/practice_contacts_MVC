package skillbox.contacts.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import skillbox.contacts.dto.ContactDto;
import skillbox.contacts.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public String getAllContacts(Model model) {
        model.addAttribute("contacts", contactService.getAll());
        return "contacts/list.html";
    }

    @GetMapping("/create")
    public String createContact(Model model) {
        var contact = new ContactDto(null, null, null, null, null);
        model.addAttribute("contact", contact);
        return "contacts/edit";
    }

    @GetMapping("/edit/{id}")
    public String editContactList(@PathVariable Long id, Model model) {
        var contact = contactService.getById(id);
        model.addAttribute("contact", contact);
        return "contacts/edit";
    }

    @PostMapping("/save")
    public String saveContact(@ModelAttribute ContactDto contactDto) {
        if(contactDto.id() == null) {
            contactService.createContact(contactDto);
        } else {
            contactService.updateContact(contactDto.id(), contactDto);
        }
        return "redirect:/contacts";
    }

    @PostMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteById(id);
        return "redirect:/contacts";
    }
}
