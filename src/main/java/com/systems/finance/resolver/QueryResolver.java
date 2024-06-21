package com.systems.finance.resolver;

import com.systems.finance.model.*;
import com.systems.finance.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

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
}