Feature: QA Load time test

  Scenario Outline: Load time test
    Given User is already on Login page
    When Title of login page is Personal Info
    Then Record load time
    Then Refresh Page and record load time
    Then User logs in with "<Username>" "<Password>" "<BookingNumber>" "<Month>" "<Day>" and "<Year>" and record load time
    Then Logout and logs in again with "<Username>" "<Password>" "<BookingNumber>" "<Month>" "<Day>" and "<Year>" and record load time
    Then Go to Personal Info and record load time 
    Then Go to Payment method and record load time
    Then Go to Passage contract and record load time
    Then Go to Travel Documents and record load time
    Then Go to Security Photo and record load time
    Then Go to Emergency return airport and record load time
    Then Go to Travel information and record load time
    Then Go to Add a celebration and record load time
    Then Go to Profile photo and record load time
    Then Go to Medallion ordering and record load time 
    Then Go to Health status and record load time
    Then Starting second iteration
    Then Go to Personal Info again and record load time
    Then Go to Payment method again and record load time
    Then Go to Passage contract again and record load time
    Then Go to Travel Documents again and record load time
    Then Go to Security Photo again and record load time
    Then Go to Emergency return airport again and record load time
    Then Go to Travel information again and record load time
    Then Go to Add a celebration again and record load time
    Then Go to Profile photo again and record load time
    Then Go to Medallion ordering again and record load time
    Then Go to Health status again and record load time
    
  	Examples:
		| Username | Password | BookingNumber | Month | Day | Year |
		| Gary     | Grosser  | 2T4M6N        | SEP   | 21  | 1949 |