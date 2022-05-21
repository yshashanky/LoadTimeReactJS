$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("login.feature");
formatter.feature({
  "line": 1,
  "name": "Persnoal Info Login",
  "description": "",
  "id": "persnoal-info-login",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Personal Info login test",
  "description": "",
  "id": "persnoal-info-login;personal-info-login-test",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "User is already on Login page",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "Title of login page is Personal Info",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "Record load time",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "Refresh Page",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "Title of login page is loaded",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "Record load time for sec time",
  "keyword": "Then "
});
formatter.step({
  "line": 10,
  "name": "User enters username and password",
  "keyword": "Then "
});
formatter.step({
  "line": 11,
  "name": "User clicks on login button",
  "keyword": "Then "
});
formatter.step({
  "line": 12,
  "name": "Wait till page loads",
  "keyword": "Then "
});
formatter.step({
  "line": 13,
  "name": "User is on Dashboard",
  "keyword": "And "
});
formatter.match({
  "location": "pageLoadStepDefination.user_is_already_on_Login_page()"
});
formatter.result({
  "duration": 13911232200,
  "status": "passed"
});
formatter.match({
  "location": "pageLoadStepDefination.title_of_login_page_is_Personal_Info()"
});
formatter.result({
  "duration": 20744000,
  "status": "passed"
});
formatter.match({
  "location": "pageLoadStepDefination.record_load_time()"
});
formatter.result({
  "duration": 52410000,
  "status": "passed"
});
formatter.match({
  "location": "pageLoadStepDefination.refresh_page()"
});
formatter.result({
  "duration": 39632500,
  "status": "passed"
});
formatter.match({
  "location": "pageLoadStepDefination.title_of_login_page_is_loaded()"
});
formatter.result({
  "duration": 790481700,
  "status": "passed"
});
formatter.match({
  "location": "pageLoadStepDefination.record_load_time_for_sec_time()"
});
formatter.result({
  "duration": 46885800,
  "status": "passed"
});
formatter.match({
  "location": "pageLoadStepDefination.user_enters_username_and_password()"
});
formatter.result({
  "duration": 731470700,
  "status": "passed"
});
formatter.match({
  "location": "pageLoadStepDefination.user_clicks_on_login_button()"
});
formatter.result({
  "duration": 155592200,
  "status": "passed"
});
formatter.match({
  "location": "pageLoadStepDefination.wait_till_page_loads()"
});
formatter.result({
  "duration": 46335100,
  "status": "passed"
});
formatter.match({
  "location": "pageLoadStepDefination.user_is_on_Dashboard()"
});
formatter.result({
  "duration": 20200,
  "status": "passed"
});
});