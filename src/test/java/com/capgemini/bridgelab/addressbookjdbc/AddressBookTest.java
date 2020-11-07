package com.capgemini.bridgelab.addressbookjdbc;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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
	
	@Test
	public void givenDateRange_WhenRetrieved_ShouldMatchEmployeeCount() {
		AddressBookService addressBookService = new AddressBookService();
		addressBookService.readContactData();
		LocalDate startDate = LocalDate.of(2018, 01, 01);
		LocalDate endDate = LocalDate.now();
		List<PersonInfo> contactList = addressBookService.readContactDataForDateRange(startDate, endDate);
		Assert.assertEquals(2, contactList.size());
	}
	
	@Test
	public void givenContacts_RetrieveNumberOfContacts_ByCityOrState() {
		AddressBookService addressBookService = new AddressBookService();
		addressBookService.readContactData();
		Map<String, Integer> contactByCityMap = addressBookService.readContactByCityOrState();
		Integer count = 1;
		Assert.assertEquals(count, contactByCityMap.get("hajipur"));
	}
}