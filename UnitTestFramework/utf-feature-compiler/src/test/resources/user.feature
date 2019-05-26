Feature: User Certification
  
  Setup:
  * Create Queue A
  * Execute Script /src/test/resouces/a.sql
  
  Scenario: User is Passed
    Given that the user Vinod is given a task to clear Java certification exam
    When Vinod got 60 marks in exam
    Then Vinod is known as Java certified
    
    
    Scenario: User is failed
    Given that the user Vinod is given a task to clear Java certification exam
    When Vinod got 60 marks in exam
    Then Vinod is known as Java certified