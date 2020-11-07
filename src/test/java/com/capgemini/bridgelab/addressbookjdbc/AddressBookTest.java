package com.capgemini.bridgelab.addressbookjdbc;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;



public class AddressBookTest {

	@Test
	public void contactsWhenRetrievedFromDB_ShouldMatchCount() {
		AddressBookService addressBookService = new AddressBookService();
		List<PersonInfo> contactList = addressBookService.readContactData();
		Assert.assertEquals(4, contactList.size());
	}
}