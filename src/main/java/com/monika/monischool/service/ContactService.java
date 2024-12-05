package com.monika.monischool.service;

import com.monika.monischool.constans.MoniSchoolConstans;
import com.monika.monischool.model.Contact;
import com.monika.monischool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(MoniSchoolConstans.OPEN);
        Contact savedContact = contactRepository.save(contact);
        if (null != savedContact && savedContact.getContactId() > 0) {
            isSaved = true;
        }

        return isSaved;
    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum,String sortField, String sortDir){
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findByStatusWithQuery(
                MoniSchoolConstans.OPEN,pageable);
        return msgPage;
    }

    public boolean updateMsgStatus(int contactId) {
        boolean isUpdated = false;
        int rows = contactRepository.updateStatusById(MoniSchoolConstans.CLOSE, contactId);
        if (rows > 0) {
            isUpdated = true;
        }
        return isUpdated;
    }


}
