package com.systems.finance.resolver;

import com.systems.finance.model.*;
import com.systems.finance.repository.*;
import graphql.kickstart.annotations.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.List;


@Component
public class QueryResolver implements GraphQLQueryResolver {

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

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<Income> getIncomes(Long userId) {
        return incomeRepository.findByUserId(userId);
    }

    public List<Expense> getExpenses(Long userId) {
        return expenseRepository.findByUserId(userId);
    }

    public List<SavingsGoal> getSavingsGoals(Long userId) {
        return savingsGoalRepository.findAll();
    }

    public List<Investment> getInvestments(Long userId) {
        return investmentRepository.findAll();
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}

