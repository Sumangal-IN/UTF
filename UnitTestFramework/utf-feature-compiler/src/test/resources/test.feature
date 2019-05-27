Setup: Create Queue A
Setup: Execute Script /src/test/resources/commit.sql

Feature: User Certification
  	
    Scenario: User is Passed
    Given that the user Vinod is given a task to clear Java certification exam
    When Vinod got 60 marks in exam
    Then Vinod is known as Java certified    
    
    # Next one
    Scenario: User is failed
    Given that the user Vinod is given a task to clear Java certification exam
    When Vinod got 60 marks in exam
    Then Vinod is known as Java certified
    
Feature: User Certification
  	
    Scenario: User is Passed
    Given that the user Vinod is given a task to clear Java certification exam
    When Vinod got 60 marks in exam
    Then Vinod is known as Java certified    
    
    # Next one
    Scenario: User is failed
    Given that the user Vinod is given a task to clear Java certification exam
    When Vinod got 60 marks in exam
    Then Vinod is known as Java certified
    
Teardown: Remove Queue A
Teardown: Execute Script /src/test/resources/rollback.sql

End