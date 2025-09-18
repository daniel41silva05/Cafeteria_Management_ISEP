# Authentication and Authorization

## 1. Analysis

### 1.1. Use Case Diagram

Shows that the system handles the login, logout, password change, and ensuring an authenticated user.

![Use Case Diagram](svg/use-case-diagram.svg "Use Case Diagram")

### 1.2. Domain Model

Defines the main entities related to authentication and authorization.

![Domain Model](svg/domain-model.svg "Domain Model")

### 1.3. Sequence System Diagrams (SSD)

The SSDs represent the high-level interactions between the **User** (actor) and the **System**, focusing on the
messages exchanged without detailing internal components.

**Login:** Interaction showing how a user requests to login.

![Login Sequence Diagram](svg/SSD-Login.svg "A Sequence Diagram for Login")

**Logout:** Interaction showing how a user initiates logout.

![Logout Sequence Diagram](svg/SSD-Logout.svg "A Sequence Diagram for Logout")

**Change Password:** Interaction showing how a user changes their password.

![Change Password Sequence Diagram](svg/SSD-ChangePassword.svg "A Sequence Diagram for Change Password")

## 2. Design

### 2.1. Class Diagram (CD)

The following class diagrams define the structure and responsibilities of the classes involved in the use cases.
They represent the internal static design of the authentication and authorization functionalities.

**Login:** Class structure responsible for handling user authentication.

  ![Login Class Diagram](svg/CD-Login.svg "A Class Diagram for Login")

**Logout:** Class structure responsible for ending user sessions.

  ![Logout Class Diagram](svg/CD-Logout.svg "A Class Diagram for Logout")

**Change Password:** Class structure responsible for validating and updating user passwords.

  ![Change Password Class Diagram](svg/CD-ChangePassword.svg "A Class Diagram for Change Password")

**Ensure Authenticated User:** Class structure responsible for checking and enforcing user authentication roles.

  ![Ensure Authenticated User Class Diagram](svg/CD-EnsureAuthenticatedUser.svg "A Class Diagram for Ensure Authenticated User")

### 2.2. Sequence Diagram (SD)

The SDs present the detailed dynamic behavior of the system during the execution of each use case. Unlike SSDs,
they include the internal interactions between system components or objects.

**Login:** How the system authenticates a user.

  ![Login Sequence Diagram](svg/SD-Login.svg "A Sequence Diagram for Login")

**Logout:** How the system logs out a user.

  ![Logout Sequence Diagram](svg/SD-Logout.svg "A Sequence Diagram for Logout")

**Change Password:** How the system handles changing a user's password.

  ![Change Password Sequence Diagram](svg/SD-ChangePassword.svg "A Sequence Diagram for Change Password")

**Ensure Authenticated User:** How the system ensures that a user is authenticated before allowing access to protected resources.

  ![Ensure Authenticated User Sequence Diagram](svg/SD-EnsureAuthenticatedUser.svg "A Sequence Diagram for Ensure Authenticated User")

### 2.3. Applied Patterns

- MVC (Model-View-Controller): Separates user interface, application logic, and domain model.
- Repository Pattern: Used to abstract persistence logic in CafeteriaUserRepository.
- Domain-Driven Design (DDD): Aggregates like CafeteriaUser ensure consistency and encapsulate business rules.
- Authorization Check: Applied before critical operations through AuthorizationService.