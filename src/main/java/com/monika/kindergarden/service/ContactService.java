package com.monika.kindergarden.service;

import com.monika.kindergarden.constans.MoniSchoolConstans;
import com.monika.kindergarden.model.Contact;
import com.monika.kindergarden.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(MoniSchoolConstans.OPEN);
        contact.setCreatedBy(MoniSchoolConstans.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        int result = contactRepository.saveContactMsg(contact);
        if (result > 0) {
            isSaved = true;
        }

        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus() {
        List<Contact> contactMsgs = contactRepository.findMsgsWithStatus(MoniSchoolConstans.OPEN);
        return contactMsgs;
    }

    public boolean updateMsgStatus(int contactId, String updatedBy){
        boolean isUpdated = false;
        int result = contactRepository.updateMsgStatus(contactId,MoniSchoolConstans.CLOSE, updatedBy);
        if(result>0) {
            isUpdated = true;
        }
        return isUpdated;
    }



}
