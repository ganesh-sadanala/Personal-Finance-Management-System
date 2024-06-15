package com.systems.finance.resolver;
import com.systems.finance.model.Expense;
import com.systems.finance.model.Income;
import com.systems.finance.model.User;
import com.systems.finance.repository.ExpenseRepository;
import com.systems.finance.repository.IncomeRepository;
import com.systems.finance.repository.UserRepository;
import graphql.kickstart.annotations.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.time.LocalDate;

@Component
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    public User createUser(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        return userRepository.save(user);
    }

    public Income createIncome(Long userId, String source, Double amount, String date) {
        Income income = new Income();
        income.setSource(source);
        income.setAmount(amount);
        income.setDate(LocalDate.parse(date));
        income.setUser(userRepository.findById(userId).orElse(null));
        return incomeRepository.save(income);
    }

    public Expense createExpense(Long userId, String category, Double amount, String date) {
        Expense expense = new Expense();
        expense.setCategory(category);
        expense.setAmount(amount);
        expense.setDate(LocalDate.parse(date));
        expense.setUser(userRepository.findById(userId).orElse(null));
        return expenseRepository.save(expense);
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}

