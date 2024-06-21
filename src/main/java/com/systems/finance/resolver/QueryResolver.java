package com.systems.finance.resolver;

import com.systems.finance.model.*;
import com.systems.finance.repository.*;
import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
public class QueryResolver {

    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private SavingsGoalRepository savingsGoalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @QueryMapping
    public User getUser(@Argument Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Income> getIncomes(@Argument Long userId) {
        return incomeRepository.findByUserId(userId);
    }

    @QueryMapping
    public List<Expense> getExpenses(@Argument Long userId) {
        return expenseRepository.findByUserId(userId);
    }

    @QueryMapping
    public List<SavingsGoal> getSavingsGoals(@Argument Long userId) {
        return savingsGoalRepository.findAll();
    }

    @QueryMapping
    public List<Investment> getInvestments(@Argument Long userId) {
        return investmentRepository.findAll();
    }

    @QueryMapping
    public Page<Expense> getExpensesPaginated(@Argument Long userId, @Argument int page, @Argument int size,
                            @Argument String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return expenseRepository.findByUserId(userId, pageable);
    }

    @SchemaMapping(typeName = "Income", field = "user")
    public CompletableFuture<User> incomeUser(Income income, DataLoader<Long, User> userLoader){
        // batch the ids and fetch in one go
        return userLoader.load(income.getUser().getId());
    }

    @SchemaMapping(typeName = "Expense", field = "user")
    public CompletableFuture<User> expenseUser(Expense expense, DataLoader<Long, User> userLoader) {
        return userLoader.load(expense.getUser().getId());
    }

    @SchemaMapping(typeName = "Investment", field = "user")
    public CompletableFuture<User> investmentUser(Investment investment, DataLoader<Long, User> userLoader) {
        return userLoader.load(investment.getUser().getId());
    }

    @SchemaMapping(typeName = "SavingsGoal", field = "user")
    public CompletableFuture<User> savingsGoalUser(SavingsGoal savingsGoal, DataLoader<Long, User> userLoader) {
        return userLoader.load(savingsGoal.getUser().getId());
    }
}