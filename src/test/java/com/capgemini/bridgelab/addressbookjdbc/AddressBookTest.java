package com.capgemini.bridgelab.addressbookjdbc;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class AddressBookTest {

	@Test
	public void contactsWhenRetrievedFromDB_ShouldMatchCount() {
		AddressBookService addressBookService = new AddressBookService();
		List<PersonInfo> contactList = addressBookService.readContactData();
		Assert.assertEquals(2, contactList.size());
	}

	@Test
	public void givenNewSalaryForEmployee_WhenUpdatedUsingPreparedStatement_ShouldSyncWithDB() {
		AddressBookService addressBookService = new AddressBookService();
		List<PersonInfo> contactList = addressBookService.readContactData();
		addressBookService.updateContactDetails("abhijeet", "rampor");
		boolean result = addressBookService.checkConatctDetailsInSyncWithDB("abhijeet");
		Assert.assertTrue(result);
	}
}