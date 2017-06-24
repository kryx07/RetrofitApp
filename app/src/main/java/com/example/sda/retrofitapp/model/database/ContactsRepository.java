package com.example.sda.retrofitapp.model.database;

import com.example.sda.retrofitapp.model.Contact;

import java.util.List;

public class ContactsRepository {

    public void add(Contact contact) {
       /* Realm realm = Realm.getInstance(MyApplication.getInstance());
        realm.beginTransaction();

        Contact realmContact = realm.createObject(Contact.class);
        realmContact.setId(contact.getId());
        realmContact.setFirstName(contact.getFirstName());
        realmContact.setLastName(contact.getLastName());
        realmContact.setPhoneNo1(contact.getPhoneNo1());

        realm.commitTransaction();*/

    }

    public List<Contact> getAll() {
       // Realm realm = Realm.getInstance(MyApplication.getInstance());
        //RealmResults<List<Contact>>
        return null;
    }

}