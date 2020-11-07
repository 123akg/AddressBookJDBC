package com.capgemini.bridgelab.addressbookjdbc;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import java.util.logging.Logger;

public class AddressBookTest {
	
	private static Logger log = Logger.getLogger(AddressBookTest.class.getName());

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
	
	@Test
	public void givenNewContact_WhenAdded_ShouldSyncWithDB() {
		AddressBookService addressBookService = new AddressBookService();
		addressBookService.readContactData();
		LocalDate date = LocalDate.now();
		addressBookService.addContactToAddressBook("sri", "gannu", "shsh", "hajipur", "hajipur", "123145", "9897572552",
				"sri@gmail", "officeContacts", "colleague", date);
		boolean result = addressBookService.checkConatctDetailsInSyncWithDB("sri");
		Assert.assertTrue(result);
	}
	
	@Test
	public void givenNewContacts_WhenAdded_ShouldMatchEntries() {
		PersonInfo[] arrayOfContacts= {
				new PersonInfo("rishabh", "singh", "PK", "jharia", "agra", "123566", "9132532252",
				"rishabh@gmail", "contacts", "colleague", LocalDate.now()),
				new PersonInfo("mohit", "dhand", "ramni", "delhi", "delhi", "112535", "9243592252",
						"mohit@gmail", "frndcontacts", "friend", LocalDate.now()),
				new PersonInfo("yash", "sharma", "ghat", "delhi", "delhi", "112585", "9095672252",
						"yash@gmail", "offcontacts", "colleague", LocalDate.now())
		};
		AddressBookService addressBookService = new AddressBookService();
		addressBookService.readContactData();
		Instant start = Instant.now();
		addressBookService.addEmployeeToPayrollWithThreads(Arrays.asList(arrayOfContacts));
		Instant end = Instant.now();
		log.info("Duration with thread : " + Duration.between(start, end));
		List<PersonInfo> contactList = addressBookService.readContactData();
		Assert.assertEquals(5, contactList.size());
	}
}