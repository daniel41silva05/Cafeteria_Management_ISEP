# US014 - Consult User Balance

### 1. Context

**As a cafeteria user, I want to check my balance, so that I know how much credit I have available.**

### 2. Analysis

**Domain Model:**

![Domain Model](svg/domain-model.svg "Domain Model")

### 3. Design

**Sequence Diagram (SD):**

  ![Sequence Diagram](svg/sequence-diagram.svg "A Sequence Diagram")

### 4. Integration/Demonstration

- To test the bootstrap process, simply run the script: *./run-bootstrap*
- To manually consult user balance, you must run the script *./run-backoffice*, log in with a user who is an Cafeteria User,
and click on the Consult User Balance option.
