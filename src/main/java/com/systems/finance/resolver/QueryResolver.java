package com.systems.finance.resolver;

import com.systems.finance.model.Expense;
import com.systems.finance.model.Income;
import com.systems.finance.model.User;
import com.systems.finance.repository.ExpenseRepository;
import com.systems.finance.repository.IncomeRepository;
import com.systems.finance.repository.UserRepository;
import graphql.kickstart.annotations.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.List;


@Component
public class QueryResolver implements GraphQLQueryResolver {

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

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}

