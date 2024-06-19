package com.systems.finance.resolver;
import com.systems.finance.model.*;
import com.systems.finance.repository.*;
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

    @Autowired
    private SavingsGoalRepository savingsGoalRepository;

    @Autowired
    private InvestmentRepository investmentRepository;

    public Investment createInvestment(Long userId, String type, String name, Float amount, String purchaseDate, Float currentValue) {
        Investment investment = new Investment();
        investment.setType(type);
        investment.setName(name);
        investment.setAmount(amount);
        investment.setPurchaseDate(LocalDate.parse(purchaseDate));
        investment.setCurrentValue(currentValue);
        investment.setUser(new User());
        return investmentRepository.save(investment);
    }

    public Investment updateInvestment(Long id, String type, String name, Float amount, String purchaseDate, Float currentValue) {
        Investment investment = investmentRepository.findById(id).orElse(null);
        if (investment != null) {
            investment.setType(type);
            investment.setName(name);
            investment.setAmount(amount);
            investment.setPurchaseDate(LocalDate.parse(purchaseDate));
            investment.setCurrentValue(currentValue);
            investmentRepository.save(investment);
        }
        return investment;
    }

    public boolean deleteInvestment(Long id) {
        if (investmentRepository.existsById(id)) {
            investmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public SavingsGoal createSavingsGoal(Long userId, String description, Float targetAmount, Float currentAmount, String targetDate) {
        SavingsGoal goal = new SavingsGoal();
        goal.setDescription(description);
        goal.setTargetAmount(targetAmount);
        goal.setCurrentAmount(currentAmount);
        goal.setTargetDate(LocalDate.parse(targetDate));
        goal.setUser(new User());
        return savingsGoalRepository.save(goal);
    }

    public SavingsGoal updateSavingsGoal(Long id, String description, Float targetAmount, Float currentAmount, String targetDate) {
        SavingsGoal goal = savingsGoalRepository.findById(id).orElse(null);
        if (goal != null) {
            goal.setDescription(description);
            goal.setTargetAmount(targetAmount);
            goal.setCurrentAmount(currentAmount);
            goal.setTargetDate(LocalDate.parse(targetDate));
            savingsGoalRepository.save(goal);
        }
        return goal;
    }

    public boolean deleteSavingsGoal(Long id) {
        if (savingsGoalRepository.existsById(id)) {
            savingsGoalRepository.deleteById(id);
            return true;
        }
        return false;
    }

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

