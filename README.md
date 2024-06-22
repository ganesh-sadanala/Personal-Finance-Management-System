**Project Overview:**
- **Technical Stack:**
  - **Backend:** Java with Spring Boot and GraphQL.
  - **Database:** PostgreSQL
  
Develop a personal finance management system that allows users to track their income, expenses, investments, and savings. This system will feature a GraphQL API backend built with Java and will include the following features:

- **Financial Tracking:**
  - Users can add, update, and delete income and expense records.
  - Users can categorize expenses (e.g., food, rent, entertainment).
  - Users can set monthly budgets and track their spending against these budgets.
- **Investment Portfolio Management:**
  - Users can add, update, and delete investment records (stocks, bonds, mutual funds, etc.).
  - Users can track the performance of their investments.
- **Savings Goals:**
  - Users can set savings goals (e.g., saving for a vacation, emergency fund).
  - Users can track their progress towards these goals.
- **Financial Insights and Reports:**
  - Generate monthly and yearly financial reports.
  - Provide visual insights such as spending trends, budget vs. actuals, investment growth, etc.

- **Advanced features:** 
  - **Pagination & Filtering:** Improves the performance of API and also provides more options for users.
  - **DataLoader:** To handle `N+1` query problem by providing batching and caching capabilities. 
  - **GraphQL Error Handling:** 
  - **Subscriptions**
  - **API Performance Monitoring**
  - **Documentation**
  - **User Authentication and Authorization:** Secure user registration and login using JWT tokens.
  - **Testing:** JUnit and Mockito for unit testing, and integration tests.


**Project Set Up & Run**
- Install Java 17, PostgreSQL server(create necessary Databases) and Maven
- Clone Repo
- [OPTIONAL] Comment `JWT security code part` for learning purpose 
- Execute `mvn clean install`
- Open GraphiQL interface on browser: `http://localhost:8080/graphiql?path=/graphql`
- Interact and learn from GraphQL APIs