@important
Feature: The user shall be able to change the language in Tesco

  Rule: Allow to change the language

    Background:
      Given open main page
      And accept cookies

    @TC_ChangeLang
    Scenario Outline: Change language
      Given language is set to "<language>"
      When change the language to "<new_language>"
      Then it shows elements in "<new_language>"

      Examples:
        | language  | new_language  |
        | magyar    | english       |
        | english   | magyar       |