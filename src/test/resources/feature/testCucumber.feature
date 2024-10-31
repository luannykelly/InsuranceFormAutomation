  Feature: Vehicle Insurance Form
    Scenario: Successfully complete and submit the form
      Given i am on the page web
      When i fill out the "Enter Vehicle Data" section and press Next
      And i fill out the "Enter Insurant Data" section and press Next
      And i fill out the "Enter Product Data" section and press Next
      And i select a price option and press Next
      When i enter my email on the "Send Quote" section and press Send
      Then i should see the message "Sending e-mail success!" on screen
